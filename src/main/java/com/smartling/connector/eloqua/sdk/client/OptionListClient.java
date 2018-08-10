package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.LandingPageApi;
import com.smartling.connector.eloqua.sdk.rest.api.OptionListApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.LandingPage;
import com.smartling.connector.eloqua.sdk.rest.model.OptionList;
import org.apache.commons.lang3.StringUtils;

public class OptionListClient extends EloquaClient<OptionListApi>
{
    public OptionListClient(final Configuration configuration)
    {
        super(configuration, OptionListApi.class);
    }

    public Elements<OptionList> listOptionLists(final int page, final int count, String sortBy, String order, String searchTerm)
    {
        final String orderBy = StringUtils.isEmpty(sortBy) ? "" : sortBy + " " + order;
        final String search = StringUtils.isEmpty(searchTerm) ? "" : "[name =] \"" + searchTerm + '"';
        return executeCall(optionListApi -> optionListApi.listOptionLists(EloquaApi.Depth.MINIMAL, page, count, orderBy, search));
    }

    public OptionList getOptionList(final long id)
    {
        return executeCall(optionListApi -> optionListApi.getOptionList(EloquaApi.Depth.COMPLETE, id));
    }

    public OptionList createOptionList(OptionList optionListToCreate)
    {
        return executeCall(optionListApi -> optionListApi.createOptionList(optionListToCreate));
    }

    public void updateOptionList(Long id, OptionList optionList)
    {
        executeCall(optionListApi -> optionListApi.updateOptionList(id, optionList));
    }

    public Void deleteOptionList(final long id)
    {
        return executeCall(optionListApi -> optionListApi.deleteOptionList(id));
    }

    public Elements<OptionList> searchForOptionList(final String name)
    {
        return executeCall(optionListApi -> optionListApi.searchForOptionList(EloquaApi.Depth.COMPLETE, "[name =] \"" + name + '"'));
    }
}
