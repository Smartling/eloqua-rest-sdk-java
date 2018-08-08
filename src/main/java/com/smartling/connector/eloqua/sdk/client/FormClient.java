package com.smartling.connector.eloqua.sdk.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.FormApi;
import com.smartling.connector.eloqua.sdk.rest.model.CopyRequest;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.FieldCriteria;
import com.smartling.connector.eloqua.sdk.rest.model.FieldCriteriaCondition;
import com.smartling.connector.eloqua.sdk.rest.model.Form;
import com.smartling.connector.eloqua.sdk.rest.model.FormElement;
import com.smartling.connector.eloqua.sdk.rest.model.Mapping;
import com.smartling.connector.eloqua.sdk.rest.model.ProcessingStep;
import com.smartling.connector.eloqua.sdk.rest.model.ProcessingStepCondition;
import com.smartling.connector.eloqua.sdk.rest.model.ProcessingStepValue;
import com.smartling.connector.eloqua.sdk.rest.model.Rule;
import com.smartling.connector.eloqua.sdk.rest.model.RuleSet;
import com.smartling.connector.eloqua.sdk.rest.model.Validation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableLong;

import java.io.IOException;
import java.io.InputStreamReader;
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

    public Form createForm(Form formToCreate, boolean sanitizeIds)
    {
        if (sanitizeIds) {
            MutableLong counter = new MutableLong(-500000);
            invertFormElementsIds(formToCreate.getElements(), counter);
            invertProcessingStepsIds(formToCreate.getProcessingSteps(), counter);
        }
        return executeCall(formApi -> formApi.createForm(formToCreate));
    }

    public void updateForm(Long id, Form form, boolean sanitizeIds)
    {
        if (sanitizeIds) {
            MutableLong counter = new MutableLong(-500000);
            invertFormElementsIds(form.getElements(), counter);
            invertProcessingStepsIds(form.getProcessingSteps(), counter);
        }
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

    public Form copyForm(long id, String name, Long folderId)
    {
        CopyRequest copyRequest = new CopyRequest();
        copyRequest.setName(name);
        copyRequest.setFolderId(folderId);

        return executeCall(formApi -> formApi.copyForm(id, copyRequest));
    }

    private void invertFormElementsIds(List<FormElement> formElements, MutableLong counter)
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
                invertFormElementsIds(formElement.getFields(), counter);
            }
            if (CollectionUtils.isNotEmpty(formElement.getStages()))
            {
                invertFormElementsIds(formElement.getStages(), counter);
            }
        }
    }

    private void invertProcessingStepsIds(List<ProcessingStep> processingSteps, MutableLong counter)
    {
        if (CollectionUtils.isEmpty(processingSteps))
        {
            return;
        }

        for (ProcessingStep processingStep : processingSteps)
        {
            processingStep.setId(invertId(processingStep.getId(), counter));

            invertProcessingStepConditionIds(processingStep.getCondition(), counter);

            invertRuleSetIds(processingStep.getContactUpdateRuleSet(), counter);
            invertRuleSetIds(processingStep.getAccountUpdateRuleSet(), counter);

            invertMappingIds(processingStep.getKeyFieldMapping(), counter);

            processingStep.setKeyFieldMappingSourceId(invertId(processingStep.getKeyFieldMappingSourceId(), counter));
            if (CollectionUtils.isNotEmpty(processingStep.getMappings()))
            {
                for (Mapping mapping : processingStep.getMappings())
                {
                    invertMappingIds(mapping, counter);
                }
            }

            processingStep.setEmailAddressFormFieldId(invertId(processingStep.getEmailAddressFormFieldId(), counter));
            invertProcessingStepValueIds(processingStep.getNewEmailAddressId(), counter);
            invertProcessingStepValueIds(processingStep.getEmailId(), counter);
            invertProcessingStepValueIds(processingStep.getSubject(), counter);
            invertProcessingStepValueIds(processingStep.getRecipientEmailAddress(), counter);
            invertProcessingStepValueIds(processingStep.getEncodingId(), counter);
            invertProcessingStepValueIds(processingStep.getProgramElementId(), counter);
            invertProcessingStepValueIds(processingStep.getLandingPageId(), counter);
            invertProcessingStepValueIds(processingStep.getPageUrl(), counter);
            invertProcessingStepValueIds(processingStep.getContactListId(), counter);
            invertProcessingStepValueIds(processingStep.getExternalUrl(), counter);
            invertProcessingStepValueIds(processingStep.getIntegrationRuleSetId(), counter);
            invertProcessingStepValueIds(processingStep.getEmailGroupId(), counter);
            invertProcessingStepValueIds(processingStep.getIsSubscribing(), counter);
            invertEventIdProcessingStepValue(processingStep, counter);
        }
    }

    private void invertProcessingStepConditionIds(ProcessingStepCondition processingStepCondition, MutableLong counter)
    {
        if (processingStepCondition != null)
        {
            processingStepCondition.setId(invertId(processingStepCondition.getId(), counter));
            if (CollectionUtils.isNotEmpty(processingStepCondition.getConditionalFieldCriteria()))
            {
                for (FieldCriteria fieldCriteria : processingStepCondition.getConditionalFieldCriteria())
                {
                    fieldCriteria.setId(invertId(fieldCriteria.getId(), counter));
                    if (StringUtils.isNotEmpty(fieldCriteria.getType()) && fieldCriteria.getType().equals("FormFieldComparisonCriteria")) {
                        fieldCriteria.setFieldId(invertId(fieldCriteria.getFieldId(), counter));
                    }
                    FieldCriteriaCondition fieldCriteriaCondition = fieldCriteria.getCondition();
                    if (fieldCriteriaCondition != null)
                    {
                        fieldCriteriaCondition.setId(invertId(fieldCriteriaCondition.getId(), counter));
                        fieldCriteriaCondition.setFormFieldId(invertId(fieldCriteriaCondition.getFormFieldId(), counter));
                    }
                }
            }
        }
    }

    private void invertRuleSetIds(RuleSet ruleSet, MutableLong counter)
    {
        if (ruleSet != null)
        {
            ruleSet.setId(invertId(ruleSet.getId(), counter));
            if (CollectionUtils.isNotEmpty(ruleSet.getUpdateRules()))
            {
                for (Rule rule : ruleSet.getUpdateRules())
                {
                    rule.setId(invertId(rule.getId(), counter));
                    rule.setFormFieldId(invertId(rule.getFormFieldId(), counter));
                }
            }
        }
    }

    private void invertMappingIds(Mapping mapping, MutableLong counter)
    {
        if (mapping != null)
        {
            mapping.setId(invertId(mapping.getId(), counter));
            mapping.setSourceFormFieldId(invertId(mapping.getSourceFormFieldId(), counter));
        }
    }

    private void invertProcessingStepValueIds(ProcessingStepValue processingStepValue, MutableLong counter)
    {
        if (processingStepValue != null && (StringUtils.isEmpty(processingStepValue.getValueType()) || !processingStepValue.getValueType().equals("constant")))
        {
            processingStepValue.setFormFieldId(invertId(processingStepValue.getFormFieldId(), counter));
        }
    }

    private void invertEventIdProcessingStepValue(ProcessingStep processingStep, MutableLong counter)
    {
        if (StringUtils.isNotEmpty(processingStep.getType())
                && processingStep.getType().equals("FormStepCancelRegistration")
                && StringUtils.isNotEmpty(processingStep.getEventId()))
        {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            try {
                ProcessingStepValue eventId = objectMapper.readValue(processingStep.getEventId(), ProcessingStepValue.class);
                invertProcessingStepValueIds(eventId, counter);
                processingStep.setEventId(objectMapper.writeValueAsString(eventId));
            } catch (IOException e)
            {
                // can't parse event id as processing step value; do nothing
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
