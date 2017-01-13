package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.LandingPageApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.LandingPage;
import org.apache.commons.lang3.StringUtils;

public class LandingPageClient extends EloquaClient<LandingPageApi>
{
    public LandingPageClient(final Configuration configuration)
    {
        super(configuration, LandingPageApi.class);
    }

    public Elements<LandingPage> listLandingPages(final int page, final int count, String sortBy, String order, String searchTerm)
    {
        final String orderBy = StringUtils.isEmpty(sortBy) ? "" : sortBy + ' ' + order;
        final String search = StringUtils.isEmpty(searchTerm) ? "" : "[name =] \"" + searchTerm + '"';
        return executeCall(landingPageApi -> landingPageApi.listLandingPages(EloquaApi.Depth.MINIMAL, page, count, orderBy, search));
    }

    public LandingPage getLandingPage(final long id)
    {
        return executeCall(landingPageApi -> landingPageApi.getLandingPage(EloquaApi.Depth.COMPLETE, id));
    }

    public LandingPage createLandingPage(LandingPage landingPageToCreate)
    {
        return executeCall(landingPageApi -> landingPageApi.createLandingPage(landingPageToCreate));
    }

    public void updateLandingPage(Long id, LandingPage email)
    {
        executeCall(landingPageApi -> landingPageApi.updateLandingPage(id, email));
    }

    public Void deleteLandingPage(final long id)
    {
        return executeCall(landingPageApi -> landingPageApi.deleteLandingPage(id));
    }

    public Elements<LandingPage> searchForLandingPage(final String name)
    {
        return executeCall(landingPageApi -> landingPageApi.searchForLandingPage(EloquaApi.Depth.COMPLETE, "[name =] \"" + name + '"'));
    }

    public String getHtmlPreview(long landingPageId, long contactId)
    {
        final String previewLink = executeCall(landingPageApi -> landingPageApi.getPreview(landingPageId, contactId));
        return getPreviewHtml(previewLink);
    }
}
