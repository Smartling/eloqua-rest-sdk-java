package com.smartling.connector.eloqua.sdk;

import com.google.common.collect.ImmutableList;
import com.smartling.connector.eloqua.sdk.client.FormClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Form;
import com.smartling.connector.eloqua.sdk.rest.model.FormElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FormIntegrationTest extends BaseIntegrationTest
{
    private static String FORM_NAME = "formName";
    private static String FORM_ELEMENT_NAME = "formElementName";

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
    public void shouldWorkWithFormsCorrectly()
    {
        FormClient formClient = new FormClient(configuration);

        FormElement formElement = new FormElement();

        formElement.setName(FORM_ELEMENT_NAME);
        formElement.setType("FormField");

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

        Form newForm1 = formClient.createForm(form1);
        Form newForm2 = formClient.createForm(form2);

        Form foundForm1 = formClient.getForm(newForm1.getId());
        assertThat(foundForm1).isNotNull();
        assertThat(foundForm1.getName()).isEqualTo(formName1);
        assertThat(foundForm1.getHtmlName()).isEqualTo(formName1);
        assertThat(foundForm1.getElements()).isNotEmpty();
        assertThat(foundForm1.getElements().get(0)).isNotNull();
        assertThat(foundForm1.getElements().get(0).getName()).isEqualTo(FORM_ELEMENT_NAME);

        Form foundForm2 = formClient.getForm(newForm2.getId());
        assertThat(foundForm2).isNotNull();
        assertThat(foundForm2.getName()).isEqualTo(formName2);
        assertThat(foundForm2.getHtmlName()).isEqualTo(formName2);
        assertThat(foundForm2.getElements()).isNotEmpty();
        assertThat(foundForm2.getElements().get(0)).isNotNull();
        assertThat(foundForm2.getElements().get(0).getName()).isEqualTo(FORM_ELEMENT_NAME);

        final String updatedFormName = formName1 + POSTFIX;
        foundForm1.setName(updatedFormName);
        foundForm1.setHtmlName(updatedFormName);

        formClient.updateForm(foundForm1.getId(), foundForm1);

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
}
