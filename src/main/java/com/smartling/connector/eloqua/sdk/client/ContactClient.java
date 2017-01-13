package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.EloquaClientException;
import com.smartling.connector.eloqua.sdk.rest.api.ContactApi;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
import com.smartling.connector.eloqua.sdk.rest.model.Contact;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import org.apache.http.client.HttpClient;

public class ContactClient extends EloquaClient<ContactApi>
{
    public ContactClient(final Configuration configuration)
    {
        super(configuration, ContactApi.class);
    }

    public ContactClient(final Configuration configuration, final LoginApi loginApi, final HttpClient httpClient)
    {
        super(configuration, ContactApi.class, loginApi, httpClient);
    }

    public long getContactIdForPreview()
    {
        Elements<Contact> contacts = executeCall(contactApi -> contactApi.listContacts(EloquaApi.Depth.MINIMAL));
        if (contacts.getTotal() < 1)
        {
            throw new EloquaClientException("No suitable contact found");
        }
        return contacts.getElements().get(0).getId();
    }
}
