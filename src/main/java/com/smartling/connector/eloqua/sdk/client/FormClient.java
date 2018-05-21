package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.FormApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Form;
import com.smartling.connector.eloqua.sdk.rest.model.FormElement;
import com.smartling.connector.eloqua.sdk.rest.model.Validation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class FormClient extends EloquaClient<FormApi>
{
    private static int counter;

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
        counter = -500000;
        prepareForCreation(formToCreate.getElements());
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

    private void prepareForCreation(List<FormElement> formElements)
    {
        if (CollectionUtils.isEmpty(formElements))
        {
            return;
        }

        for (FormElement formElement : formElements)
        {
            formElement.setId(invertId(formElement.getId()));
            if (CollectionUtils.isNotEmpty(formElement.getValidations()))
            {
                for (Validation validation : formElement.getValidations())
                {
                    validation.setId(invertId(validation.getId()));
                }
            }
            if (CollectionUtils.isNotEmpty(formElement.getFields()))
            {
                prepareForCreation(formElement.getFields());
            }
            if (CollectionUtils.isNotEmpty(formElement.getStages()))
            {
                prepareForCreation(formElement.getStages());
            }
        }
    }

    private static long invertId(Long id)
    {
        return (id == null || id == 0) ? counter-- : ((id > 0) ? -id : id);
    }
}
