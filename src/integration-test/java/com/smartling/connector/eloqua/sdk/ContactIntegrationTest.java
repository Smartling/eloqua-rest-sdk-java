package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.ContactClient;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void shouldGetContactIdForPreview()
    {
        ContactClient contactClient = new ContactClient(configuration);

        assertThat(contactClient.getContactIdForPreview()).isGreaterThan(0);
    }
}
