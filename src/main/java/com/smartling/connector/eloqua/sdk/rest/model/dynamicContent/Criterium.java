package com.smartling.connector.eloqua.sdk.rest.model.dynamicContent;

public class Criterium
{
    private String type;
    private Long id;
    private Condition condition;
    private Long fieldId;

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
}
