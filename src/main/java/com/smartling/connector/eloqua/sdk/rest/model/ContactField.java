package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactField
{
    private String type;
    private Long id;
    private String name;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private long createdAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer createdBy;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private long updatedAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer updatedBy;

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public long getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(final long createdAt)
    {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(final Integer createdBy)
    {
        this.createdBy = createdBy;
    }

    public long getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(final long updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(final Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }
}
