package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.EmailFolderClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.EmailFolder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailFolderIntegrationTest extends EmailIntegrationTest
{
    @Test
    public void shouldListEmailFolders()
    {
        EmailFolderClient emailFolderClient = new EmailFolderClient(configuration);

        Elements<EmailFolder> folders = emailFolderClient.listEmailFolders(1, 10, "name", "DESC", "");

        assertThat(folders).isNotNull();
        assertThat(folders.page).isEqualTo(1);
        assertThat(folders.pageSize).isEqualTo(10);
        assertThat(folders.total).isGreaterThan(0);
        assertThat(folders.elements).isNotEmpty();
        assertThat(folders.elements.get(0)).isNotNull();

        EmailFolder folder = emailFolderClient.getEmailFolder(folders.elements.get(0).getId());
        assertThat(folder).isNotNull();
    }
}
