package com.smartling.connector.eloqua.sdk.rest.model.dynamicContent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Criterium
{
    private String type;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private Condition condition;
    private Long fieldId;

    private List<Criterium> fieldConditions;
    private Long customObjectId;

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

    public Condition getCondition()
    {
        return condition;
    }

    public void setCondition(final Condition condition)
    {
        this.condition = condition;
    }

    public Long getFieldId()
    {
        return fieldId;
    }

    public void setFieldId(final Long fieldId)
    {
        this.fieldId = fieldId;
    }

    public List<Criterium> getFieldConditions()
    {
        return fieldConditions;
    }

    public void setFieldConditions(final List<Criterium> fieldConditions)
    {
        this.fieldConditions = fieldConditions;
    }

    public Long getCustomObjectId() {
        return customObjectId;
    }

    public void setCustomObjectId(final Long customObjectId)
    {
        this.customObjectId = customObjectId;
    }
}
