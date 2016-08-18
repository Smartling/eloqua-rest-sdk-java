package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.FolderClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Folder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FolderIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void shouldListEmailFolders()
    {
        FolderClient folderClient = new FolderClient(configuration);

        Elements<Folder> folders = folderClient.listFolders("email", 1, 10, "name", "DESC", "");

        assertThat(folders).isNotNull();
        assertThat(folders.getPage()).isEqualTo(1);
        assertThat(folders.getPageSize()).isEqualTo(10);
        assertThat(folders.getTotal()).isGreaterThan(0);
        assertThat(folders.getElements()).isNotEmpty();
        assertThat(folders.getElements().get(0)).isNotNull();

        Folder folder = folderClient.getFolder("email", folders.getElements().get(0).getId());
        assertThat(folder).isNotNull();
    }

    @Test
    public void shouldListLandingPageFolders()
    {
        FolderClient folderClient = new FolderClient(configuration);

        Elements<Folder> folders = folderClient.listFolders("landingPage", 1, 10, "name", "DESC", "");

        assertThat(folders).isNotNull();
        assertThat(folders.getPage()).isEqualTo(1);
        assertThat(folders.getPageSize()).isEqualTo(10);
        assertThat(folders.getTotal()).isGreaterThan(0);
        assertThat(folders.getElements()).isNotEmpty();
        assertThat(folders.getElements().get(0)).isNotNull();

        Folder folder = folderClient.getFolder("landingPage", folders.getElements().get(0).getId());
        assertThat(folder).isNotNull();
    }
}
