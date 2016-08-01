package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.EmailApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.HtmlContent;
import org.apache.commons.lang3.StringUtils;

public class EmailEloquaClient extends EloquaClient<EmailApi>
{

    public EmailEloquaClient(final Configuration configuration)
    {
        super(configuration, EmailApi.class);
    }

    public Elements<Email> listEmails(final int page, final int count, String sortBy, String order)
    {
        final String orderBy = StringUtils.isEmpty(sortBy) ? "" : sortBy + ' ' + order;
        return executeCall(emailApi -> emailApi.listEmails(EloquaApi.Depth.MINIMAL, page, count, orderBy));
    }

    public Email getEmail(final long id)
    {
        return executeCall(emailApi -> emailApi.getEmail(EloquaApi.Depth.COMPLETE, id));
    }

    public void createOrUpdateEmail(final String title, final long id, String html)
    {
        final Email emailToClone = getEmail(id);
        Elements<Email> targetEmails = searchForEmail(title);
        HtmlContent htmlContent = new HtmlContent();
        htmlContent.setType(HtmlContent.RAW_HTML_CONTENT);
        htmlContent.setHtml(html);
        if (targetEmails.total > 0)
        {
            final Email emailToUpdate = targetEmails.elements.get(0);
            emailToUpdate.setHtmlContent(htmlContent);
            executeCall(emailApi -> emailApi.updateEmail(emailToUpdate.getId(), emailToUpdate));
        }
        else
        {
            emailToClone.setName(title);
            emailToClone.setHtmlContent(htmlContent);
            executeCall(emailApi -> emailApi.createEmail(emailToClone));
        }
    }

    public Void deleteEmail(final long id)
    {
        return executeCall(emailApi -> emailApi.deleteEmail(id));
    }

    public Elements<Email> searchForEmail(final String name)
    {
        return executeCall(emailApi -> emailApi.searchForEmail(EloquaApi.Depth.COMPLETE, "[name =] \"" + name + '"'));
    }
}
