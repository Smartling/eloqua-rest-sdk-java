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
import org.apache.commons.lang3.mutable.MutableLong;

import java.util.List;

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
        MutableLong counter = new MutableLong(-500000);
        invertIds(formToCreate.getElements(), counter);
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

    private void invertIds(List<FormElement> formElements, MutableLong counter)
    {
        if (CollectionUtils.isEmpty(formElements))
        {
            return;
        }

        for (FormElement formElement : formElements)
        {
            formElement.setId(invertId(formElement.getId(), counter));
            if (CollectionUtils.isNotEmpty(formElement.getValidations()))
            {
                for (Validation validation : formElement.getValidations())
                {
                    validation.setId(invertId(validation.getId(), counter));
                }
            }
            if (CollectionUtils.isNotEmpty(formElement.getFields()))
            {
                invertIds(formElement.getFields(), counter);
            }
            if (CollectionUtils.isNotEmpty(formElement.getStages()))
            {
                invertIds(formElement.getStages(), counter);
            }
        }
    }

    private static long invertId(Long id, MutableLong counter)
    {
        Long invertedId = (id == null || id == 0) ? counter.getValue() : ((id > 0) ? -id : id);
        counter.decrement();
        return invertedId;
    }
}
