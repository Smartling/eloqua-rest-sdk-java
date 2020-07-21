package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.ContactClient;
import com.smartling.connector.eloqua.sdk.client.LandingPageClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.HtmlContent;
import com.smartling.connector.eloqua.sdk.rest.model.LandingPage;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

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
    public void shouldThrowObjectNotFoundException() throws Exception
    {
        LandingPageClient client = new LandingPageClient(configuration);
        assertThatThrownBy(() -> client.getLandingPage(100500)).isInstanceOf(EloquaObjectNotFoundException.class);
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
    @Ignore
    public void shouldWorkWithEmailsCorrectly()
    {
        LandingPageClient landingPageClient = new LandingPageClient(configuration);

        Elements<LandingPage> landingPages = landingPageClient.listLandingPages(1, 10, "createdAt", "DESC", "");

        assertThat(landingPages).isNotNull();
        assertThat(landingPages.getPage()).isEqualTo(1);
        assertThat(landingPages.getPageSize()).isEqualTo(10);
        assertThat(landingPages.getTotal()).isGreaterThan(0);
        assertThat(landingPages.getElements()).isNotEmpty();
        assertThat(landingPages.getElements().get(0)).isNotNull();

        if(landingPages.getTotal() > 1)
        {
            assertThat(landingPages.getElements().get(0).getUpdatedAt().after(landingPages.getElements().get(1).getUpdatedAt()));
        }

        LandingPage landingPage = landingPageClient.getLandingPage(425);

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
        assertThat(newLandingPages.getElements()).isNotEmpty();
        assertThat(newLandingPages.getTotal()).isEqualTo(1);

        LandingPage testLandingPage = newLandingPages.getElements().get(0);

        assertThat(testLandingPage.getName()).isEqualTo(oldTitle);

        landingPageClient.deleteLandingPage(testLandingPage.getId());
    }

    @Test
    public void shouldReadFormsFromLandingPage()
    {
        LandingPageClient landingPageClient = new LandingPageClient(configuration);
        LandingPage landingPage = landingPageClient.getLandingPage(346);

        assertThat(landingPage.getForms()).hasSize(1);
    }

    @Test
    public void getPreviewHtml()
    {
        LandingPageClient landingPageClient = new LandingPageClient(configuration);
        ContactClient contactClient = new ContactClient(configuration);

        Elements<LandingPage> landingPages = landingPageClient.listLandingPages(1, 10, "createdAt", "DESC", "");

        assertThat(landingPages.getPage()).isEqualTo(1);
        assertThat(landingPages.getElements().get(0).getUpdatedAt().after(landingPages.getElements().get(1).getUpdatedAt()));

        final String htmlPreview = landingPageClient.getHtmlPreviewLink(landingPages.getElements().get(0).getId(), contactClient.getContactIdForPreview());
        assertThat(htmlPreview).isNotEmpty();
    }

    @Test
    public void shouldCopyLandingPage()
    {
        LandingPageClient landingPageClient = new LandingPageClient(configuration);
        String newName = "cloned landingPage " + UUID.randomUUID();
        LandingPage clonedLandingPage = landingPageClient.copyLandingPage(322, newName);

        assertThat(clonedLandingPage.getId()).isNotEqualTo(322);
        assertThat(clonedLandingPage.getName()).isEqualTo(newName);

        landingPageClient.deleteLandingPage(clonedLandingPage.getId());
    }
}
