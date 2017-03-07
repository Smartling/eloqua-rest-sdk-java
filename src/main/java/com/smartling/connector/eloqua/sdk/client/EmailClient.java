package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.EmailApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.login.TokenInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;

public class EmailClient extends EloquaClient<EmailApi>
{
    public static final int MAX_PAGE_SIZE = 1000;

    public EmailClient(final Configuration configuration)
    {
        super(configuration, EmailApi.class);
    }

    public EmailClient(final Configuration configuration, final TokenInfo tokenInfo)
    {
        super(configuration, EmailApi.class);
    }

    public EmailClient(final Configuration configuration, final Class<EmailApi> apiType, final LoginApi loginApi, HttpClient httpClient)
    {
        super(configuration, apiType, loginApi, httpClient);
    }

    public Elements<Email> listEmails(final int page, final int count, String sortBy, String order, String searchTerm)
    {
        final String orderBy = StringUtils.isEmpty(sortBy) ? "" : sortBy + ' ' + order;
        final String search = StringUtils.isEmpty(searchTerm) ? "" : "[name =] \"" + searchTerm + '"';
        if (count > MAX_PAGE_SIZE)
        {
            final Elements<Email> elements = executeCall(emailApi -> emailApi.listEmails(EloquaApi.Depth.MINIMAL, page, MAX_PAGE_SIZE, orderBy, search));
            int counter = page;
            while (elements.getElements().size() < count && elements.getElements().size() < elements.getTotal())
            {
                counter ++;
                final Elements<Email> newPack = getEmailElements(orderBy, search, counter);
                elements.getElements().addAll(newPack.getElements());
            }
            return elements;
        }
        return executeCall(emailApi -> emailApi.listEmails(EloquaApi.Depth.MINIMAL, page, count, orderBy, search));
    }

    private Elements<Email> getEmailElements(final String orderBy, final String search, final int counter)
    {
        return executeCall(emailApi -> emailApi.listEmails(EloquaApi.Depth.MINIMAL, counter, MAX_PAGE_SIZE, orderBy, search));
    }

    public Email getEmail(final long id)
    {
        return executeCall(emailApi -> emailApi.getEmail(EloquaApi.Depth.COMPLETE, id));
    }

    public Email createEmail(Email emailToCreate)
    {
        return executeCall(emailApi -> emailApi.createEmail(emailToCreate));
    }

    public void updateEmail(Long id, Email email)
    {
        executeCall(emailApi -> emailApi.updateEmail(id, email));
    }

    public Void deleteEmail(final long id)
    {
        return executeCall(emailApi -> emailApi.deleteEmail(id));
    }

    public Elements<Email> searchForEmail(final String name)
    {
        return executeCall(emailApi -> emailApi.searchForEmail(EloquaApi.Depth.COMPLETE, "[name =] \"" + name + '"'));
    }

    public String getHtmlPreviewLink(long emailId, long contactId)
    {
        return executeCall(emailApi -> emailApi.getPreview(emailId, contactId));
    }

}
