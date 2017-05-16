package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.ContactFieldClient;
import com.smartling.connector.eloqua.sdk.rest.model.ContactField;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactFieldIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void shouldListContactFields()
    {
        ContactFieldClient contactFieldClient = new ContactFieldClient(configuration);

        Elements<ContactField> dynamicContents = contactFieldClient.listContentFields(1, 10, "createdAt", "ASC", "Country");
        assertThat(dynamicContents).isNotNull();
    }
}

