package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.FormApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Form;
import org.apache.commons.lang3.StringUtils;

public class FormClient extends EloquaClient<FormApi>
{
    public FormClient(final Configuration configuration)
    {
        super(configuration, FormApi.class);
    }

    public Elements<Form> listForms(final int page, final int count, String sortBy, String order, String searchTerm)
    {
        final String orderBy = StringUtils.isEmpty(sortBy) ? "" : sortBy + " " + order;
        final String search = StringUtils.isEmpty(searchTerm) ? "" : "name=" + searchTerm;
        return executeCall(formApi -> formApi.listForms(EloquaApi.Depth.MINIMAL, page, count, orderBy, search));
    }

    public Form getForm(final long id)
    {
        return executeCall(formApi -> formApi.getForm(EloquaApi.Depth.COMPLETE, id));
    }

    public Form createForm(Form formToCreate)
    {
        return executeCall(formApi -> formApi.createForm(formToCreate));
    }

    public void updateForm(Long id, Form form)
    {
        executeCall(formApi -> formApi.updateForm(id, form));
    }

    public Void deleteForm(final long id)
    {
        return executeCall(formApi -> formApi.deleteForm(id));
    }

    public Elements<Form> searchForForm(final String name)
    {
        return executeCall(formApi -> formApi.searchForForm(EloquaApi.Depth.COMPLETE, "name=" + name));
    }
}
