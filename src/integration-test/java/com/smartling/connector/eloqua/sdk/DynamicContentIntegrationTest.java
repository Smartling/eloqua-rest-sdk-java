package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.DynamicContentClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.DynamicContent;
import org.junit.Test;

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

        Elements<DynamicContent> dynamicContents = dynamicContentClient.listDynamicContents(1, 10, "createdAt", "DESC", "");

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
}
