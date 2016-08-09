package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.LandingPageClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.HtmlContent;
import com.smartling.connector.eloqua.sdk.rest.model.LandingPage;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LandingPageIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void shouldThrowAuthenticationExceptionIfPasswordIncorrect() throws Exception
    {
        LandingPageClient client = new LandingPageClient(new Configuration(siteName, username, "invalid"));

        assertThatThrownBy(() -> client.listLandingPages(1, 10, "", "", ""))
                .isInstanceOf(EloquaAuthenticationException.class);
    }

    @Test
    public void shouldThrowApiExceptionIfApiCallFailed() throws Exception
    {
        LandingPageClient client = new LandingPageClient(configuration);

        // 0 is invalid parameter so emulating an API error
        assertThatThrownBy(() -> client.listLandingPages(0, 10, "", "", ""))
                .isExactlyInstanceOf(EloquaClientException.class);
    }

    @Test
    public void shouldWorkWithEmailsCorrectly()
    {
        LandingPageClient landingPageClient = new LandingPageClient(configuration);

        Elements<LandingPage> landingPages = landingPageClient.listLandingPages(1, 10, "createdAt", "DESC", "");

        assertThat(landingPages).isNotNull();
        assertThat(landingPages.page).isEqualTo(1);
        assertThat(landingPages.pageSize).isEqualTo(10);
        assertThat(landingPages.total).isGreaterThan(0);
        assertThat(landingPages.elements).isNotEmpty();
        assertThat(landingPages.elements.get(0)).isNotNull();

        if(landingPages.total > 1)
        {
            assertThat(landingPages.elements.get(0).getUpdatedAt().after(landingPages.elements.get(1).getUpdatedAt()));
        }

        LandingPage landingPage = landingPageClient.getLandingPage(landingPages.elements.get(0).getId());

        assertThat(landingPage).isNotNull();
        assertThat(landingPage.getHtmlContent()).isNotNull();
        assertThat(landingPage.getHtmlContent().getPlainHtml()).isNotNull();
        if((landingPage.getHtmlContent().getType().equals(HtmlContent.RAW_HTML_CONTENT)))
        {
            landingPage.getHtmlContent().setHtml(HTML);
        }else{
            landingPage.getHtmlContent().setHtmlBody(HTML);
        }
        final String oldTitle = landingPage.getName() + POSTFIX;

        LandingPage createdLandingPage = landingPageClient.createLandingPage(landingPage);
        createdLandingPage.setName(oldTitle);

        landingPageClient.updateLandingPage(createdLandingPage.getId(), createdLandingPage);

        Elements<LandingPage> newLandingPages = landingPageClient.searchForLandingPage(oldTitle);
        assertThat(newLandingPages).isNotNull();
        assertThat(newLandingPages.elements).isNotEmpty();
        assertThat(newLandingPages.total).isEqualTo(1);

        LandingPage testLandingPage = newLandingPages.elements.get(0);

        assertThat(testLandingPage.getName()).isEqualTo(oldTitle);

        landingPageClient.deleteLandingPage(testLandingPage.getId());
    }
}
