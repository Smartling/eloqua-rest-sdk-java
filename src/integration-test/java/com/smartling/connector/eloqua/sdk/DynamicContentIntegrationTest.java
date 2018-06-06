package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.DynamicContentClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.DynamicContent;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DynamicContentIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void shouldThrowAuthenticationExceptionIfPasswordIncorrect() throws Exception
    {
        DynamicContentClient client = new DynamicContentClient(new Configuration(siteName, username, "invalid"));

        assertThatThrownBy(() -> client.listDynamicContents(1, 10, "", "", ""))
                .isInstanceOf(EloquaAuthenticationException.class);
    }

    @Test
    public void shouldThrowObjectNotFoundException() throws Exception
    {
        DynamicContentClient client = new DynamicContentClient(configuration);
        assertThatThrownBy(() -> client.getDynamicContent(100500)).isInstanceOf(EloquaObjectNotFoundException.class);
    }

    @Test
    public void shouldThrowApiExceptionIfApiCallFailed() throws Exception
    {
        DynamicContentClient client = new DynamicContentClient(configuration);

        // 0 is invalid parameter so emulating an API error
        assertThatThrownBy(() -> client.listDynamicContents(0, 10, "", "", ""))
                .isExactlyInstanceOf(EloquaClientException.class);
    }

    @Test
    public void shouldWorkWithDynamicContentsCorrectly()
    {
        DynamicContentClient dynamicContentClient = new DynamicContentClient(configuration);

        Elements<DynamicContent> dynamicContents = dynamicContentClient.listDynamicContents(1, 10, "createdAt", "ASC", "");

        assertThat(dynamicContents).isNotNull();
        assertThat(dynamicContents.getPage()).isEqualTo(1);
        assertThat(dynamicContents.getPageSize()).isEqualTo(10);
        assertThat(dynamicContents.getTotal()).isGreaterThan(0);
        assertThat(dynamicContents.getElements()).isNotEmpty();
        assertThat(dynamicContents.getElements().get(0)).isNotNull();

        if(dynamicContents.getTotal() > 1)
        {
            assertThat(dynamicContents.getElements().get(0).getUpdatedAt().after(dynamicContents.getElements().get(1).getUpdatedAt()));
        }

        DynamicContent dynamicContent = dynamicContentClient.getDynamicContent(dynamicContents.getElements().get(0).getId());

        assertThat(dynamicContent).isNotNull();
        assertThat(dynamicContent.getDefaultContentSection().getContentHtml()).isNotNull();
        assertThat(dynamicContent.getDefaultContentSection().getContentHtml()).isNotNull();
        dynamicContent.getDefaultContentSection().setContentHtml(HTML);
        dynamicContent.getDefaultContentSection().setContentText(TEXT);
        dynamicContent.getRules().get(0).getContentSection().setContentHtml(HTML);
        dynamicContent.getRules().get(0).getContentSection().setContentText(TEXT);

        final String oldTitle = dynamicContent.getName() + POSTFIX;

        DynamicContent createdDynamicContent = dynamicContentClient.createDynamicContent(dynamicContent);
        createdDynamicContent.setName(oldTitle);

        dynamicContentClient.updateDynamicContent(createdDynamicContent.getId(), createdDynamicContent);

        Elements<DynamicContent> newDynamicContents = dynamicContentClient.searchForDynamicContent(oldTitle);
        assertThat(newDynamicContents).isNotNull();
        assertThat(newDynamicContents.getElements()).isNotEmpty();
        assertThat(newDynamicContents.getTotal()).isEqualTo(1);

        DynamicContent testDynamicContent = newDynamicContents.getElements().get(0);

        assertThat(testDynamicContent.getName()).isEqualTo(oldTitle);

        dynamicContentClient.deleteDynamicContent(testDynamicContent.getId());
    }

    //test email was deleted
    @Ignore
    @Test
    public void testRulesTranslation()
    {
        DynamicContentClient dynamicContentClient = new DynamicContentClient(configuration);

        Elements<DynamicContent> dynamicContents = dynamicContentClient.listDynamicContents(1, 10, "createdAt", "ASC", "RuleTranslationTest");

        assertThat(dynamicContents).isNotNull();

        DynamicContent dynamicContent = dynamicContentClient.getDynamicContent(dynamicContents.getElements().get(0).getId());

        assertThat(dynamicContent).isNotNull();
        assertThat(dynamicContent.getDefaultContentSection().getContentHtml()).isNotNull();

        int initualRulesSize = dynamicContent.getRules().size();

        dynamicContentClient.saveTranslatedRule(dynamicContent.getId(), 100012, "Spain", "<body>Spanish</body>");
        dynamicContents = dynamicContentClient.listDynamicContents(1, 10, "createdAt", "ASC", "RuleTranslationTest");
        assertThat(dynamicContents).isNotNull();
        dynamicContent = dynamicContentClient.getDynamicContent(dynamicContents.getElements().get(0).getId());
        assertThat(dynamicContent).isNotNull();
        assertThat(dynamicContent.getRules().size()).isEqualTo(initualRulesSize + 1);
        dynamicContent.getRules().remove(dynamicContent.getRules().size() - 1);
        dynamicContentClient.updateDynamicContent(dynamicContent.getId(), dynamicContent);
    }

    @Test
    public void shouldCopyDynamicContent()
    {
        DynamicContentClient dynamicContentClient = new DynamicContentClient(configuration);
        String newName = "cloned landingPage " + UUID.randomUUID();
        DynamicContent clonedDynamicContent = dynamicContentClient.copyDynamicContent(15, newName);

        assertThat(clonedDynamicContent.getId()).isNotEqualTo(15);
        assertThat(clonedDynamicContent.getName()).isEqualTo(newName);

        dynamicContentClient.deleteDynamicContent(clonedDynamicContent.getId());
    }
}
