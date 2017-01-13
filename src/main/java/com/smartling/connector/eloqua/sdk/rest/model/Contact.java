package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;

public class Contact
{
    private String type;
    private Long id;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Calendar updatedAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer createdBy;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String depth; //minimal / partial / complete
    private String name;

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

    public Calendar getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(final Calendar updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(final Integer createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getDepth()
    {
        return depth;
    }

    public void setDepth(final String depth)
    {
        this.depth = depth;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }
}
