package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Validation
{
    private Condition condition;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String depth; //minimal / partial / complete
    private String description;
    private Long id;
    @JsonProperty("isEnabled")
    private boolean enabled;
    private String message;
    private String name;
    private String type;

    public Condition getCondition()
    {
        return condition;
    }

    public void setCondition(final Condition condition)
    {
        this.condition = condition;
    }

    public String getDepth()
    {
        return depth;
    }

    public void setDepth(final String depth)
    {
        this.depth = depth;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(final boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }
}
