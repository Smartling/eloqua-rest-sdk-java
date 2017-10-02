package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.DynamicContentApi;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.Condition;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.ContentSection;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.Criterium;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.DynamicContent;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.Rule;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

public class DynamicContentClient extends EloquaClient<DynamicContentApi>
{
     public DynamicContentClient(final Configuration configuration)
     {
         super(configuration, DynamicContentApi.class);
     }

     public Elements<DynamicContent> listDynamicContents(final int page, final int count, String sortBy, String order, String searchTerm)
     {
         final String orderBy = StringUtils.isEmpty(sortBy) ? "" : sortBy + ' ' + order;
         final String search = StringUtils.isEmpty(searchTerm) ? "" : "[name =] \"" + searchTerm + '"';
         return executeCall(dynamicContentApi -> dynamicContentApi.listDynamicContent(EloquaApi.Depth.MINIMAL, page, count, orderBy, search));
     }

     public DynamicContent getDynamicContent(final long id)
     {
         return executeCall(dynamicContentApi -> dynamicContentApi.getDynamicContent(EloquaApi.Depth.PARTIAL, id));
     }

     public DynamicContent createDynamicContent(DynamicContent dynamicContentToCreate)
     {
         return executeCall(dynamicContentApi -> dynamicContentApi.createDynamicContent(dynamicContentToCreate));
     }

     public void updateDynamicContent(Long id, DynamicContent dynamicContent)
     {
         executeCall(dynamicContentApi -> dynamicContentApi.updateDynamicContent(id, dynamicContent));
     }

     public Void deleteDynamicContent(final long id)
     {
         return executeCall(dynamicContentApi -> dynamicContentApi.deleteDynamicContent(id));
     }

     public Elements<DynamicContent> searchForDynamicContent(final String name)
     {
         return executeCall(dynamicContentApi -> dynamicContentApi.searchForDynamicContent(EloquaApi.Depth.PARTIAL, "[name =] \"" + name + '"'));
     }

    public void saveTranslatedRule(final long id, final long fieldId, final String locale, final String translatedContent)
    {
        DynamicContent dynamicContent = getDynamicContent(id);

        final Rule rule = createNewRuleForDynamicContent(fieldId, locale, translatedContent, dynamicContent);

        dynamicContent.getRules().add(rule);

        updateDynamicContent(dynamicContent.getId(), dynamicContent);
    }

    public static Rule createNewRuleForDynamicContent(final long fieldId, final String locale, final String translatedContent, final DynamicContent dynamicContent)
    {
        final ContentSection contentSection = new ContentSection();
        contentSection.setType(dynamicContent.getDefaultContentSection().getType());
        contentSection.setDepth(dynamicContent.getDefaultContentSection().getDepth());
        contentSection.setScope(dynamicContent.getDefaultContentSection().getScope());
        contentSection.setSize(dynamicContent.getDefaultContentSection().getSize());
        contentSection.setFolderId(dynamicContent.getDefaultContentSection().getFolderId());
        contentSection.setContentHtml(translatedContent);
        contentSection.setName(locale);

        final Condition condition = new Condition();
        condition.setValue(locale);
        condition.setOperator("equal");
        condition.setType("TextValueCondition");

        final Criterium criterium = new Criterium();
        criterium.setType("ContactFieldCriterion");
        criterium.setFieldId(fieldId);
        criterium.setCondition(condition);

        final Rule rule = new Rule();
        rule.setType("DynamicContentRule");
        rule.setDepth("complete");
        rule.setCriteria(Collections.singletonList(criterium));
        rule.setContentSection(contentSection);
        rule.setStatement("1111");//could be anything but required by API :D
        return rule;
    }
}
