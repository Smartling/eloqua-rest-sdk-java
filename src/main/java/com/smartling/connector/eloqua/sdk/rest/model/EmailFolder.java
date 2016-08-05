package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailFolder
{
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String type;
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long createdAt;
    private String depth; //minimal / partial / complete
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long folderId;
    private String description;
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long updatedAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer updatedBy;
    private boolean isSystem;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public long getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(long createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getDepth()
    {
        return depth;
    }

    public void setDepth(String depth)
    {
        this.depth = depth;
    }

    public Long getFolderId()
    {
        return folderId;
    }

    public void setFolderId(Long folderId)
    {
        this.folderId = folderId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public boolean isSystem()
    {
        return isSystem;
    }

    public void setSystem(boolean system)
    {
        isSystem = system;
    }
}
