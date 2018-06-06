package com.smartling.connector.eloqua.sdk.rest.model;

public class CopyRequest
{
    private String name;
    private Long folderId;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getFolderId()
    {
        return folderId;
    }

    public void setFolderId(Long folderId)
    {
        this.folderId = folderId;
    }
}
