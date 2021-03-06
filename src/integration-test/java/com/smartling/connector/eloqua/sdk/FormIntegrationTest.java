package com.smartling.connector.eloqua.sdk;

import com.google.common.collect.ImmutableList;
import com.smartling.connector.eloqua.sdk.client.FormClient;
import com.smartling.connector.eloqua.sdk.rest.model.Condition;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Form;
import com.smartling.connector.eloqua.sdk.rest.model.FormElement;
import com.smartling.connector.eloqua.sdk.rest.model.Validation;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FormIntegrationTest extends BaseIntegrationTest
{
    private static final Long INTEGRATION_TESTS_FOLDER_ID = 2766L;
    private static final long INTEGRATION_TESTS_FORM_ID   = 359;
    private static final long INTEGRATION_TESTS_FORM_ID_WITH_PROCESSING_STEPS = 502;
    private static final Long FORM_ELEMENT_ID = 1L;
    private static final String FORM_NAME = "formName";
    private static final String FORM_ELEMENT_NAME = "formElementName";
    private static final String FORM_ELEMENT_TYPE = "FormField";

    @Test
    public void shouldThrowAuthenticationExceptionIfPasswordIncorrect()
    {
        FormClient client = new FormClient(new Configuration(siteName, username, "invalid"));

        assertThatThrownBy(() -> client.listForms(1, 10, "", "", ""))
                .isInstanceOf(EloquaAuthenticationException.class);
    }

    @Test
    public void shouldThrowObjectNotFoundException()
    {
        FormClient client = new FormClient(configuration);
        assertThatThrownBy(() -> client.getForm(100500)).isInstanceOf(EloquaObjectNotFoundException.class);
    }

    @Test
    public void shouldThrowApiExceptionIfApiCallFailed()
    {
        FormClient client = new FormClient(configuration);

        // 0 is invalid parameter so emulating an API error
        assertThatThrownBy(() -> client.listForms(0, 10, "", "", ""))
                .isExactlyInstanceOf(EloquaClientException.class);
    }

    @Test
    public void shouldWorkWithFormsCorrectly() throws Exception
    {
        FormClient formClient = new FormClient(configuration);

        FormElement formElement = new FormElement();

        formElement.setName(FORM_ELEMENT_NAME);
        formElement.setOptionListId(FORM_ELEMENT_ID);
        formElement.setType(FORM_ELEMENT_TYPE);

        final String formName1 = FORM_NAME + RandomStringUtils.random(5);
        Form form1 = new Form();
        form1.setName(formName1);
        form1.setHtmlName(formName1);
        form1.setElements(ImmutableList.of(formElement));

        final String formName2 = FORM_NAME + RandomStringUtils.random(5);
        Form form2 = new Form();
        form2.setName(formName2);
        form2.setHtmlName(formName2);
        form2.setElements(ImmutableList.of(formElement));

        Form newForm1 = formClient.createForm(form1, true);
        Form newForm2 = formClient.createForm(form2, true);

        Form foundForm1 = formClient.getForm(newForm1.getId());
        assertThat(foundForm1).isNotNull();
        assertThat(foundForm1.getName()).isEqualTo(formName1);
        assertThat(foundForm1.getHtmlName()).isEqualTo(formName1);
        assertThat(foundForm1.getElements()).isNotEmpty();
        assertThat(foundForm1.getElements().get(0)).isNotNull();
        assertThat(foundForm1.getElements().get(0).getId()).isGreaterThan(0);
        assertThat(foundForm1.getElements().get(0).getName()).isEqualTo(FORM_ELEMENT_NAME);

        Form foundForm2 = formClient.getForm(newForm2.getId());
        assertThat(foundForm2).isNotNull();
        assertThat(foundForm2.getName()).isEqualTo(formName2);
        assertThat(foundForm2.getHtmlName()).isEqualTo(formName2);
        assertThat(foundForm2.getElements()).isNotEmpty();
        assertThat(foundForm2.getElements().get(0)).isNotNull();
        assertThat(foundForm1.getElements().get(0).getId()).isGreaterThan(0);
        assertThat(foundForm2.getElements().get(0).getName()).isEqualTo(FORM_ELEMENT_NAME);

        final String updatedFormName = formName1 + POSTFIX;
        foundForm1.setName(updatedFormName);
        foundForm1.setHtmlName(updatedFormName);

        // This delay for correct sorting by updateAt field
        Thread.sleep(1000);
        formClient.updateForm(foundForm1.getId(), foundForm1, false);

        Elements<Form> updatedForms = formClient.searchForForm(updatedFormName);
        assertThat(updatedForms).isNotNull();
        assertThat(updatedForms.getElements()).isNotEmpty();
        assertThat(updatedForms.getTotal()).isEqualTo(1);

        Form updatedForm = updatedForms.getElements().get(0);
        assertThat(updatedForm.getName()).isEqualTo(updatedFormName);
        assertThat(updatedForm.getHtmlName()).isEqualTo(updatedFormName);

        Elements<Form> sortedForms = formClient.listForms(1, 10, "updatedAt", "DESC", FORM_NAME + "*");
        assertThat(sortedForms).isNotNull();
        assertThat(sortedForms.getPage()).isEqualTo(1);
        assertThat(sortedForms.getPageSize()).isEqualTo(10);
        assertThat(sortedForms.getTotal()).isGreaterThan(1);
        assertThat(sortedForms.getElements()).isNotEmpty();
        assertThat(sortedForms.getElements().get(0)).isNotNull();
        assertThat(sortedForms.getElements().get(1)).isNotNull();

        assertThat(sortedForms.getElements().get(0).getUpdatedAt().getTime().after(sortedForms.getElements().get(1).getUpdatedAt().getTime())).isTrue();

        formClient.deleteForm(foundForm1.getId());
        formClient.deleteForm(foundForm2.getId());

        Elements<Form> forms = formClient.searchForForm(updatedFormName);
        assertThat(forms).isNotNull();
        assertThat(forms.getElements()).isEmpty();

        forms = formClient.searchForForm(formName2);
        assertThat(forms).isNotNull();
        assertThat(forms.getElements()).isEmpty();
    }

    @Test
    public void shouldWorkWithFormsAndElementsCorrectly()
    {
        FormClient formClient = new FormClient(configuration);

        FormElement formField = aFormField("Form Field", "singleLineText");

        FormElement pickList = new FormElement();
        pickList.setHtmlName("dropdownMenu");
        pickList.setName("Single Picklist");
        pickList.setOptionListId(1L);
        pickList.setType("FormField");

        FormElement formFieldGroup = new FormElement();
        formFieldGroup.setFields(ImmutableList.of(aFormField("FF1 in Group", "customFieldOne"), aFormField("FF2 in group", "customFieldTwo")));
        formFieldGroup.setName("Custom Two Column");
        formFieldGroup.setType("FormFieldGroup");

        FormElement progressiveProfileStage = new FormElement();
        progressiveProfileStage.setFields(ImmutableList.of(aFormField("FF in ProgressiveProfileStage", "ffInProgressiveProfileStage")));
        progressiveProfileStage.setType("ProgressiveProfileStage");

        FormElement progressiveProfile = new FormElement();
        progressiveProfile.setStages(ImmutableList.of(progressiveProfileStage));
        progressiveProfile.setName("Progressive Profile");
        progressiveProfile.setType("ProgressiveProfile");

        List<FormElement> elements = new ArrayList<>();
        elements.add(formField);
        elements.add(pickList);
        elements.add(formFieldGroup);
        elements.add(progressiveProfile);

        final String formName = "Form" + RandomStringUtils.random(5);
        Form form = new Form();
        form.setName(formName);
        form.setHtmlName(formName);
        form.setElements(elements);
        form.setType("Form");
        form.setCurrentStatus("Draft");
        form.setDepth("complete");

        Form newForm = formClient.createForm(form, true);

        Form foundForm = formClient.getForm(newForm.getId());
        assertThat(foundForm).isNotNull();
        assertThat(foundForm.getName()).isEqualTo(form.getName());
        assertThat(foundForm.getHtmlName()).isEqualTo(form.getHtmlName());
        assertThat(foundForm.getElements()).isNotEmpty();
        assertThat(foundForm.getElements().size()).isEqualTo(elements.size());

        Optional<FormElement> optionalFormField = findByName(formField.getName(), foundForm.getElements());
        assertThat(optionalFormField.isPresent()).isTrue();
        FormElement foundFormField = optionalFormField.get();
        assertThat(foundFormField.getHtmlName()).isEqualTo(formField.getHtmlName());
        assertThat(foundFormField.getType()).isEqualTo(formField.getType());
        assertThat(foundFormField.getDefaultValue()).isEqualTo(formField.getDefaultValue());
        assertThat(foundFormField.getValidations()).isNotEmpty();
        assertThat(foundFormField.getValidations().size()).isEqualTo(1);
        Validation foundValidation = foundFormField.getValidations().get(0);
        assertThat(foundValidation.getCondition()).isNotNull();
        assertThat(foundValidation.getCondition().getType()).isEqualTo(formField.getValidations().get(0).getCondition().getType());
        assertThat(foundValidation.getCondition().getMinimum()).isEqualTo(formField.getValidations().get(0).getCondition().getMinimum());
        assertThat(foundValidation.getCondition().getMaximum()).isEqualTo(formField.getValidations().get(0).getCondition().getMaximum());
        assertThat(foundValidation.getDescription()).isEqualTo(formField.getValidations().get(0).getDescription());
        assertThat(foundValidation.isEnabled()).isTrue();
        assertThat(foundValidation.getMessage()).isEqualTo(formField.getValidations().get(0).getMessage());
        assertThat(foundValidation.getName()).isEqualTo(formField.getValidations().get(0).getName());
        assertThat(foundValidation.getType()).isEqualTo(formField.getValidations().get(0).getType());

        Optional<FormElement> optionalPickList = findByName(pickList.getName(), foundForm.getElements());
        assertThat(optionalPickList.isPresent()).isTrue();
        FormElement foundPickList = optionalPickList.get();
        assertThat(foundPickList.getHtmlName()).isEqualTo(pickList.getHtmlName());
        assertThat(foundPickList.getOptionListId()).isEqualTo(pickList.getOptionListId());
        assertThat(foundPickList.getType()).isEqualTo(pickList.getType());

        Optional<FormElement> optionalFormFieldGroup = findByName(formFieldGroup.getName(), foundForm.getElements());
        assertThat(optionalFormFieldGroup.isPresent()).isTrue();
        FormElement foundFormFieldGroup = optionalFormFieldGroup.get();
        assertThat(foundFormFieldGroup.getFields()).isNotEmpty();
        assertThat(foundFormFieldGroup.getFields().size()).isEqualTo(formFieldGroup.getFields().size());
        assertThat(foundFormFieldGroup.getFields().get(0).getName()).isEqualTo(formFieldGroup.getFields().get(0).getName());
        assertThat(foundFormFieldGroup.getFields().get(0).getHtmlName()).isEqualTo(formFieldGroup.getFields().get(0).getHtmlName());
        assertThat(foundFormFieldGroup.getFields().get(1).getName()).isEqualTo(formFieldGroup.getFields().get(1).getName());
        assertThat(foundFormFieldGroup.getFields().get(1).getHtmlName()).isEqualTo(formFieldGroup.getFields().get(1).getHtmlName());
        assertThat(foundFormFieldGroup.getType()).isEqualTo(formFieldGroup.getType());

        Optional<FormElement> optionalProgressiveProfile = findByName(progressiveProfile.getName(), foundForm.getElements());
        assertThat(optionalProgressiveProfile.isPresent()).isTrue();
        FormElement foundProgressiveProfile = optionalProgressiveProfile.get();
        assertThat(foundProgressiveProfile.getStages()).isNotEmpty();
        assertThat(foundProgressiveProfile.getStages().size()).isEqualTo(1);
        FormElement foundProgressiveProfileStage = foundProgressiveProfile.getStages().get(0);
        assertThat(foundProgressiveProfileStage.getFields()).isNotEmpty();
        assertThat(foundProgressiveProfileStage.getFields().size()).isEqualTo(progressiveProfileStage.getFields().size());
        assertThat(foundProgressiveProfileStage.getFields().get(0).getName()).isEqualTo(progressiveProfileStage.getFields().get(0).getName());
        assertThat(foundProgressiveProfileStage.getFields().get(0).getHtmlName()).isEqualTo(progressiveProfileStage.getFields().get(0).getHtmlName());
        assertThat(foundProgressiveProfileStage.getType()).isEqualTo(progressiveProfileStage.getType());
        assertThat(foundProgressiveProfile.getType()).isEqualTo(progressiveProfile.getType());

        // clone form
        Form clonedForm = newForm;
        clonedForm.setId(null);
        clonedForm.setHtmlName(null);
        Form newClonedForm = formClient.createForm(clonedForm, true);

        Form foundClonedForm = formClient.getForm(newClonedForm.getId());
        assertThat(foundClonedForm).isNotNull();
        assertThat(foundClonedForm.getId()).isNotEqualTo(foundForm.getId());
        assertThat(foundClonedForm.getName()).isEqualTo(clonedForm.getName());
        assertThat(foundClonedForm.getElements()).isNotEmpty();
        assertThat(foundClonedForm.getElements().size()).isEqualTo(elements.size());

        formClient.deleteForm(foundForm.getId());
        formClient.deleteForm(foundClonedForm.getId());
    }

    @Test
    @Ignore
    public void shouldCopyForm()
    {
        FormClient formClient = new FormClient(configuration);
        String newName = "cloned form " + UUID.randomUUID();
        Form clonedForm = formClient.copyForm(INTEGRATION_TESTS_FORM_ID, newName, INTEGRATION_TESTS_FOLDER_ID);

        assertThat(clonedForm.getId()).isNotEqualTo(INTEGRATION_TESTS_FORM_ID);
        assertThat(clonedForm.getName()).isEqualTo(newName);
        assertThat(clonedForm.getFolderId()).isEqualTo(INTEGRATION_TESTS_FOLDER_ID);

        formClient.deleteForm(clonedForm.getId());
    }

    @Test
    public void shouldFindFormByFullName()
    {
        FormClient formClient = new FormClient(configuration);
        Elements<Form> forms = formClient.searchForForm("Test Form for Integration Tests");
        assertThat(forms).isNotNull();
        assertThat(forms.getElements()).isNotEmpty();
        assertThat(forms.getElements()).hasSize(1);
        assertThat(forms.getElements().get(0).getId()).isEqualTo(INTEGRATION_TESTS_FORM_ID);
    }

    @Test
    public void shouldUpdateForm()
    {
        String newName = "updated element name " + UUID.randomUUID();

        FormClient formClient = new FormClient(configuration);
        Form form = formClient.getForm(INTEGRATION_TESTS_FORM_ID_WITH_PROCESSING_STEPS);
        form.getElements().get(0).setName(newName);

        formClient.updateForm(INTEGRATION_TESTS_FORM_ID_WITH_PROCESSING_STEPS, form, true);

        Form updatedForm = formClient.getForm(INTEGRATION_TESTS_FORM_ID_WITH_PROCESSING_STEPS);

        assertThat(updatedForm.getId()).isEqualTo(INTEGRATION_TESTS_FORM_ID_WITH_PROCESSING_STEPS);
        assertThat(updatedForm.getElements().get(0).getName()).isEqualTo(newName);
    }

    private static FormElement aFormField(String name, String htmlName)
    {
        Condition condition = new Condition();
        condition.setType("TextLengthCondition");
        condition.setMinimum(0);
        condition.setMaximum(35);

        Validation validation = new Validation();
        validation.setCondition(condition);
        validation.setDescription("Form Field Validation Rule");
        validation.setEnabled(true);
        validation.setMessage("Invalid length for field value Form Field");
        validation.setName("Form Field Validation Rule");
        validation.setType("FieldValidation");

        FormElement formField = new FormElement();
        formField.setName(name);
        formField.setHtmlName(htmlName);
        formField.setType("FormField");
        formField.setDefaultValue("Form Field default value");
        formField.setValidations(ImmutableList.of(validation));

        return formField;
    }

    private static Optional<FormElement> findByName(String name, List<FormElement> elements) {
        return elements.stream().filter(e -> (e.getName().equals(name))).findFirst();
    }
}
