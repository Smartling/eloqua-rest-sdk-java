package com.smartling.connector.eloqua.sdk.client;

import com.google.common.collect.ImmutableList;
import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.FormApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
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
import com.smartling.connector.eloqua.sdk.rest.model.login.AccountInfo;
import com.smartling.connector.eloqua.sdk.rest.model.login.Urls;
import org.apache.http.client.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FormClientTest
{
    @Mock
    private FormApi formApi;
    @Mock
    private LoginApi loginApi;
    @Mock
    private Configuration configuration;
    @Mock
    private HttpClient httpClient;

    private FormClient testedInstance;

    @Before
    public void setup()
    {
        when(loginApi.getAccountInfo()).thenReturn(anAccountInfo());

        testedInstance = spy(new FormClient(configuration, FormApi.class, loginApi, httpClient));
        doReturn(formApi).when(testedInstance).getApi();
    }

    @Test
    public void shouldNotInvertIdsIfSanitizeIsFalse()
    {
        Form form = aForm();

        testedInstance.updateForm(1L, form, false);

        FormElement formElement = form.getElements().get(0);
        assertThat(formElement.getId()).isNull();
        assertThat(formElement.getValidations().get(0).getId()).isNull();

        FormElement field = formElement.getFields().get(0);
        assertThat(field.getId()).isNull();
        assertThat(field.getValidations().get(0).getId()).isNull();

        FormElement stage = formElement.getStages().get(0);
        assertThat(stage.getId()).isNull();
        assertThat(stage.getValidations().get(0).getId()).isNull();

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(processingStep.getId()).isNull();
        assertThat(processingStep.getEmailAddressFormFieldId()).isNull();
        assertThat(processingStep.getKeyFieldMappingSourceId()).isNull();
        assertThat(processingStep.getCondition().getId()).isNull();

        FieldCriteria conditionalFieldCriteria = processingStep.getCondition().getConditionalFieldCriteria().get(0);
        assertThat(conditionalFieldCriteria.getId()).isNull();
        assertThat(conditionalFieldCriteria.getFieldId()).isNull();
        assertThat(conditionalFieldCriteria.getCondition().getId()).isNull();
        assertThat(conditionalFieldCriteria.getCondition().getFormFieldId()).isNull();

        assertThat(processingStep.getNewEmailAddressId().getFormFieldId()).isNull();

        assertThat(processingStep.getContactUpdateRuleSet().getId()).isNull();
        Rule updateRule = processingStep.getContactUpdateRuleSet().getUpdateRules().get(0);
        assertThat(updateRule.getId()).isNull();
        assertThat(updateRule.getFormFieldId()).isNull();

        assertThat(processingStep.getKeyFieldMapping().getId()).isNull();
        assertThat(processingStep.getKeyFieldMapping().getSourceFormFieldId()).isNull();

        Mapping mapping = processingStep.getMappings().get(0);
        assertThat(mapping.getId()).isNull();
        assertThat(mapping.getSourceFormFieldId()).isNull();

        assertThat(processingStep.getAccountUpdateRuleSet().getId()).isNull();
        updateRule = processingStep.getAccountUpdateRuleSet().getUpdateRules().get(0);
        assertThat(updateRule.getId()).isNull();
        assertThat(updateRule.getFormFieldId()).isNull();

        assertThat(processingStep.getCustomObjectUpdateRuleSet().getId()).isNull();
        updateRule = processingStep.getCustomObjectUpdateRuleSet().getUpdateRules().get(0);
        assertThat(updateRule.getId()).isNull();
        assertThat(updateRule.getFormFieldId()).isNull();

        assertThat(processingStep.getIntegrationRuleSetId().getFormFieldId()).isNull();
        assertThat(processingStep.getEmailGroupId().getFormFieldId()).isNull();
        assertThat(processingStep.getIsSubscribing().getFormFieldId()).isNull();
        assertThat(processingStep.getEmailId().getFormFieldId()).isNull();
        assertThat(processingStep.getEncodingId().getFormFieldId()).isNull();
        assertThat(processingStep.getRecipientEmailAddress().getFormFieldId()).isNull();
        assertThat(processingStep.getSubject().getFormFieldId()).isNull();
        assertThat(processingStep.getContactListId().getFormFieldId()).isNull();
        assertThat(processingStep.getProgramElementId().getFormFieldId()).isNull();
        assertThat(processingStep.getCampaignElementId().getFormFieldId()).isNull();
        assertThat(processingStep.getExternalUrl().getFormFieldId()).isNull();
        assertThat(processingStep.getLandingPageId().getFormFieldId()).isNull();
        assertThat(processingStep.getPageUrl().getFormFieldId()).isNull();
        assertThat(processingStep.getEventId()).isEqualTo("");
    }

    @Test
    public void shouldInvertIdsIfSanitizeIsTrue()
    {
        Long counter = -500000L;

        Form form = aForm();
        form.getElements().get(0).setId(1L);
        form.getElements().get(0).getFields().get(0).setId(0L);
        form.getElements().get(0).getStages().get(0).setId(-1L);
        form.getProcessingSteps().get(0).setId(0L);
        form.getProcessingSteps().get(0).getKeyFieldMapping().setSourceFormFieldId(2L);
        form.getProcessingSteps().get(0).getContactUpdateRuleSet().setId(-2L);
        form.getProcessingSteps().get(0).getEmailId().setFormFieldId(3L);
        form.getProcessingSteps().get(0).getCondition().getConditionalFieldCriteria().get(0).getCondition().setFormFieldId(4L);

        testedInstance.updateForm(1L, form, true);

        FormElement formElement = form.getElements().get(0);
        assertThat(formElement.getId()).isEqualTo(-1); counter--;
        assertThat(formElement.getValidations().get(0).getId()).isEqualTo(counter--);

        FormElement field = formElement.getFields().get(0);
        assertThat(field.getId()).isEqualTo(counter--);
        assertThat(field.getValidations().get(0).getId()).isEqualTo(counter--);

        FormElement stage = formElement.getStages().get(0);
        assertThat(stage.getId()).isEqualTo(-1); counter--;
        assertThat(stage.getValidations().get(0).getId()).isEqualTo(counter--);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(processingStep.getId()).isEqualTo(counter--);
        assertThat(processingStep.getEmailAddressFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getKeyFieldMappingSourceId()).isEqualTo(counter--);
        assertThat(processingStep.getCondition().getId()).isEqualTo(counter--);

        FieldCriteria conditionalFieldCriteria = processingStep.getCondition().getConditionalFieldCriteria().get(0);
        assertThat(conditionalFieldCriteria.getId()).isEqualTo(counter--);
        assertThat(conditionalFieldCriteria.getFieldId()).isNull();
        assertThat(conditionalFieldCriteria.getCondition().getId()).isEqualTo(counter--);
        assertThat(conditionalFieldCriteria.getCondition().getFormFieldId()).isEqualTo(-4); counter--;

        assertThat(processingStep.getContactUpdateRuleSet().getId()).isEqualTo(-2); counter--;
        Rule updateRule = processingStep.getContactUpdateRuleSet().getUpdateRules().get(0);
        assertThat(updateRule.getId()).isEqualTo(counter--);
        assertThat(updateRule.getFormFieldId()).isNull();

        assertThat(processingStep.getAccountUpdateRuleSet().getId()).isEqualTo(counter--);
        updateRule = processingStep.getAccountUpdateRuleSet().getUpdateRules().get(0);
        assertThat(updateRule.getId()).isEqualTo(counter--);
        assertThat(updateRule.getFormFieldId()).isNull();

        assertThat(processingStep.getCustomObjectUpdateRuleSet().getId()).isEqualTo(counter--);
        updateRule = processingStep.getCustomObjectUpdateRuleSet().getUpdateRules().get(0);
        assertThat(updateRule.getId()).isEqualTo(counter--);
        assertThat(updateRule.getFormFieldId()).isNull();

        assertThat(processingStep.getKeyFieldMapping().getId()).isEqualTo(counter--);
        assertThat(processingStep.getKeyFieldMapping().getSourceFormFieldId()).isEqualTo(-2); counter--;

        Mapping mapping = processingStep.getMappings().get(0);
        assertThat(mapping.getId()).isEqualTo(counter--);
        assertThat(mapping.getSourceFormFieldId()).isEqualTo(counter--);

        assertThat(processingStep.getNewEmailAddressId().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getIntegrationRuleSetId().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getEmailGroupId().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getIsSubscribing().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getEmailId().getFormFieldId()).isEqualTo(-3); counter--;
        assertThat(processingStep.getEncodingId().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getRecipientEmailAddress().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getSubject().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getContactListId().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getProgramElementId().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getCampaignElementId().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getExternalUrl().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getLandingPageId().getFormFieldId()).isEqualTo(counter--);
        assertThat(processingStep.getPageUrl().getFormFieldId()).isEqualTo(counter);
        assertThat(processingStep.getEventId()).isEqualTo("");
    }

    @Test
    public void shouldInvertConditionalFieldCriteriaFieldIdIfTypeIsFormField()
    {
        Form form = aForm();
        form.getProcessingSteps().get(0).getCondition().getConditionalFieldCriteria().get(0).setType("FormFieldComparisonCriteria");
        form.getProcessingSteps().get(0).getCondition().getConditionalFieldCriteria().get(0).setFieldId(1111L);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        FieldCriteria conditionalFieldCriteria = processingStep.getCondition().getConditionalFieldCriteria().get(0);
        assertThat(conditionalFieldCriteria.getFieldId()).isEqualTo(-1111);
    }

    @Test
    public void shouldNotInvertConditionalFieldCriteriaFieldIdIfTypeIsContactField()
    {
        Form form = aForm();
        form.getProcessingSteps().get(0).getCondition().getConditionalFieldCriteria().get(0).setType("ContactFieldComparisonCriteria");
        form.getProcessingSteps().get(0).getCondition().getConditionalFieldCriteria().get(0).setFieldId(1111L);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        FieldCriteria conditionalFieldCriteria = processingStep.getCondition().getConditionalFieldCriteria().get(0);
        assertThat(conditionalFieldCriteria.getFieldId()).isEqualTo(1111);
    }

    @Test
    public void shouldNotInvertProcessingStepValueFormFieldIdIfValueTypeIsConstant()
    {
        Form form = aForm();
        form.getProcessingSteps().get(0).getPageUrl().setValueType("constant");
        form.getProcessingSteps().get(0).getPageUrl().setFormFieldId(1111L);
        form.getProcessingSteps().get(0).getLandingPageId().setFormFieldId(2222L);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(processingStep.getPageUrl().getFormFieldId()).isEqualTo(1111);
        assertThat(processingStep.getLandingPageId().getFormFieldId()).isEqualTo(-2222);
    }

    @Test
    public void shouldInvertProcessingStepValueFormFieldIdIfValueTypeIsNotConstant()
    {
        Form form = aForm();
        form.getProcessingSteps().get(0).getPageUrl().setValueType("optionList");
        form.getProcessingSteps().get(0).getPageUrl().setFormFieldId(1111L);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(processingStep.getPageUrl().getFormFieldId()).isEqualTo(-1111);
    }

    @Test
    public void shouldNotChangeEventIdIfProcessingStepTypeIsEmpty()
    {
        Form form = aForm();
        form.getProcessingSteps().get(0).setEventId("3");

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(processingStep.getEventId()).isEqualTo("3");
    }

    @Test
    public void shouldNotChangeEventIdIfProcessingStepTypeIsFormStepCreateUpdateEventRegistration()
    {
        Form form = aForm();
        form.getProcessingSteps().get(0).setType("FormStepCreateUpdateEventRegistration");
        form.getProcessingSteps().get(0).setEventId("3");

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(processingStep.getEventId()).isEqualTo("3");
    }

    @Test
    public void shouldNotChangeEventIdIfProcessingStepTypeIsFormStepCancelRegistrationAndEventIdIsString()
    {
        Form form = aForm();
        form.getProcessingSteps().get(0).setType("FormStepCancelRegistration");
        form.getProcessingSteps().get(0).setEventId("3");

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(processingStep.getEventId()).isEqualTo("3");
    }

    @Test
    public void shouldChangeEventIdIfProcessingStepTypeIsFormStepCancelRegistration()
    {
        Form form = aForm();
        LinkedHashMap<String, String> eventId = anEventId();
        form.getProcessingSteps().get(0).setType("FormStepCancelRegistration");
        form.getProcessingSteps().get(0).setEventId(eventId);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(((LinkedHashMap)processingStep.getEventId()).get("formFieldId")).isEqualTo("-3333");
    }

    @Test
    public void shouldChangeEventIdIfProcessingStepTypeIsFormStepCancelRegistrationAndFormFieldIdIsAbsentInMap()
    {
        Form form = aForm();
        LinkedHashMap<String, String> eventId = anEventId();
        eventId.remove("formFieldId");
        form.getProcessingSteps().get(0).setType("FormStepCancelRegistration");
        form.getProcessingSteps().get(0).setEventId(eventId);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(((LinkedHashMap)processingStep.getEventId()).get("formFieldId")).isEqualTo("-500037");
    }

    @Test
    public void shouldChangeEventIdIfProcessingStepTypeIsFormStepCancelRegistrationAndFormFieldIdIsEmpty()
    {
        Form form = aForm();
        LinkedHashMap<String, String> eventId = anEventId();
        eventId.put("formFieldId", "");
        form.getProcessingSteps().get(0).setType("FormStepCancelRegistration");
        form.getProcessingSteps().get(0).setEventId(eventId);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(((LinkedHashMap)processingStep.getEventId()).get("formFieldId")).isEqualTo("-500037");
    }

    @Test
    public void shouldNotChangeEventIdIfProcessingStepTypeIsFormStepCancelRegistrationAndValueTypeIsConstant()
    {
        Form form = aForm();
        LinkedHashMap<String, String> eventId = anEventId();
        eventId.put("valueType", "constant");
        form.getProcessingSteps().get(0).setType("FormStepCancelRegistration");
        form.getProcessingSteps().get(0).setEventId(eventId);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(((LinkedHashMap)processingStep.getEventId()).get("formFieldId")).isEqualTo("3333");
    }

    @Test
    public void shouldChangeEventIdIfProcessingStepTypeIsFormStepCancelRegistrationAndValueTypeIsAbsent()
    {
        Form form = aForm();
        LinkedHashMap<String, String> eventId = anEventId();
        eventId.remove("valueType");
        form.getProcessingSteps().get(0).setType("FormStepCancelRegistration");
        form.getProcessingSteps().get(0).setEventId(eventId);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(((LinkedHashMap)processingStep.getEventId()).get("formFieldId")).isEqualTo("-3333");
    }

    @Test
    public void shouldChangeEventIdIfProcessingStepTypeIsFormStepCancelRegistrationAndValueTypeIsEmpty()
    {
        Form form = aForm();
        LinkedHashMap<String, String> eventId = anEventId();
        eventId.put("valueType", "");
        form.getProcessingSteps().get(0).setType("FormStepCancelRegistration");
        form.getProcessingSteps().get(0).setEventId(eventId);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(((LinkedHashMap)processingStep.getEventId()).get("formFieldId")).isEqualTo("-3333");
    }

    @Test
    public void shouldChangeRuleFormFieldIdIfItIsNotNull()
    {
        Form form = aForm();
        form.getProcessingSteps().get(0).getContactUpdateRuleSet().getUpdateRules().get(0).setFormFieldId(4444L);

        testedInstance.updateForm(1L, form, true);

        ProcessingStep processingStep = form.getProcessingSteps().get(0);
        assertThat(processingStep.getContactUpdateRuleSet().getUpdateRules().get(0).getFormFieldId()).isEqualTo(-4444);
        assertThat(processingStep.getAccountUpdateRuleSet().getUpdateRules().get(0).getFormFieldId()).isNull();
    }

    private static AccountInfo anAccountInfo()
    {
        Urls urls = new Urls();
        urls.setBase("http://form-endpoint.eloqua.com");
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUrls(urls);

        return accountInfo;
    }

    private static Form aForm()
    {
        FormElement formElement = new FormElement();
        formElement.setValidations(ImmutableList.of(new Validation()));

        FormElement field = new FormElement();
        field.setValidations(ImmutableList.of(new Validation()));
        formElement.setFields(ImmutableList.of(field));

        FormElement stage = new FormElement();
        stage.setValidations(ImmutableList.of(new Validation()));
        formElement.setStages(ImmutableList.of(stage));

        FieldCriteria fieldCriteria = new FieldCriteria();
        fieldCriteria.setCondition(new FieldCriteriaCondition());

        ProcessingStepCondition condition = new ProcessingStepCondition();
        condition.setConditionalFieldCriteria(ImmutableList.of(fieldCriteria));

        RuleSet contactUpdateRuleSet = new RuleSet();
        contactUpdateRuleSet.setUpdateRules(ImmutableList.of(new Rule()));

        RuleSet accountUpdateRuleSet = new RuleSet();
        accountUpdateRuleSet.setUpdateRules(ImmutableList.of(new Rule()));

        RuleSet customObjectUpdateRuleSet = new RuleSet();
        customObjectUpdateRuleSet.setUpdateRules(ImmutableList.of(new Rule()));

        ProcessingStep processingStep = new ProcessingStep();
        processingStep.setCondition(condition);
        processingStep.setNewEmailAddressId(new ProcessingStepValue());
        processingStep.setContactUpdateRuleSet(contactUpdateRuleSet);
        processingStep.setKeyFieldMapping(new Mapping());
        processingStep.setMappings(ImmutableList.of(new Mapping()));
        processingStep.setAccountUpdateRuleSet(accountUpdateRuleSet);
        processingStep.setCustomObjectUpdateRuleSet(customObjectUpdateRuleSet);
        processingStep.setIntegrationRuleSetId(new ProcessingStepValue());
        processingStep.setEmailGroupId(new ProcessingStepValue());
        processingStep.setIsSubscribing(new ProcessingStepValue());
        processingStep.setEmailId(new ProcessingStepValue());
        processingStep.setEncodingId(new ProcessingStepValue());
        processingStep.setRecipientEmailAddress(new ProcessingStepValue());
        processingStep.setSubject(new ProcessingStepValue());
        processingStep.setContactListId(new ProcessingStepValue());
        processingStep.setProgramElementId(new ProcessingStepValue());
        processingStep.setCampaignElementId(new ProcessingStepValue());
        processingStep.setExternalUrl(new ProcessingStepValue());
        processingStep.setLandingPageId(new ProcessingStepValue());
        processingStep.setPageUrl(new ProcessingStepValue());
        processingStep.setEventId("");

        Form form = new Form();
        form.setElements(ImmutableList.of(formElement));
        form.setProcessingSteps(ImmutableList.of(processingStep));

        return form;
    }

    private static LinkedHashMap<String, String> anEventId()
    {
        LinkedHashMap<String, String> eventId = new LinkedHashMap<>();
        eventId.put("type", "ProcessingStepValueWithLookup");
        eventId.put("formFieldId", "3333");
        eventId.put("optionListId", "26");
        eventId.put("valueType", "optionList");

        return eventId;
    }
}