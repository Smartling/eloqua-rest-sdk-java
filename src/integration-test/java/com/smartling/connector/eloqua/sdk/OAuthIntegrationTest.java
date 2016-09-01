package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.EmailClient;
import com.smartling.connector.eloqua.sdk.client.OAuthClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.login.GrantCodeDTO;
import com.smartling.connector.eloqua.sdk.rest.model.login.RefreshTokenDTO;
import com.smartling.connector.eloqua.sdk.rest.model.login.TokenInfo;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

//for manual usage
@Ignore
public class OAuthIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void shouldGetOAuthToken() throws Exception
    {
        GrantCodeDTO grantCodeDTO = new GrantCodeDTO("MTU5NDc0NzMyMjoxaWhsbVB4ZFJFMENPUExlN0M3QlNQQm1nZHEzbFZMZjBVeUk2VGlYMmlYY283cnlNUElHNnhHa2FJdXgzUEJMLXN0a3FBRjNyTXVCVW1jcEQtOFNQZXo1NmZEfnMxMDRGUHhN", "http://requestb.in/1lepse11");
        OAuthClient oAuthClient = new OAuthClient(new OAuthConfiguration());
        TokenInfo tokenInfo = oAuthClient.getTokenUsingGrantCode(grantCodeDTO);
        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO(tokenInfo.getRefreshToken(), "http://requestb.in/1lepse11");
        tokenInfo = oAuthClient.getTokenUsingRefreshToken(refreshTokenDTO);
        System.out.println(tokenInfo.toString());
    }

    @Test
    public void shouldWorkWithOAuthAuthentication()
    {
        EmailClient emailClient = new EmailClient(configuration);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setAccessToken("MTU5NDc0NzMyMjoxMmF2YzZ0UDMxRS1zbExpeGsyWWd2OC02QmR0dll2TFVPYW9PNlFaQkdRQTFSUXBuek9jUEdpd0pvZ0VOWjIwNW9BUzlGemZPeXRrU3JMSHk4MXpEczZXUm9tdnJrc2pVYVh6");
        tokenInfo.setTokenType("Bearer");
        configuration.setTokenInfo(tokenInfo);
        Elements<Email> emails = emailClient.listEmails(1, 10, "createdAt", "DESC", "");
        assertThat(emails).isNotNull();
        assertThat(emails.getPage()).isEqualTo(1);
        assertThat(emails.getPageSize()).isEqualTo(10);
        assertThat(emails.getTotal()).isGreaterThan(0);
        assertThat(emails.getElements()).isNotEmpty();
        assertThat(emails.getElements().get(0)).isNotNull();
    }
}
