package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.DynamicContentApi;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.DynamicContent;
import org.apache.commons.lang3.StringUtils;

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
         return executeCall(dynamicContentApi -> dynamicContentApi.getDynamicContent(EloquaApi.Depth.COMPLETE, id));
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
         return executeCall(dynamicContentApi -> dynamicContentApi.searchForDynamicContent(EloquaApi.Depth.COMPLETE, "[name =] \"" + name + '"'));
     }

}
