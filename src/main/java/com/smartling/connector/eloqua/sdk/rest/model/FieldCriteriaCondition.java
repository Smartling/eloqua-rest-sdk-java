package com.smartling.connector.eloqua.sdk.rest.model;

public class FieldCriteriaCondition
{
    private String type;
    private Long id;
    private String operator;
    private Long formFieldId;
    private String valueType;
    private String value;

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

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(final String operator)
    {
        this.operator = operator;
    }

    public Long getFormFieldId()
    {
        return formFieldId;
    }

    public void setFormFieldId(final Long formFieldId)
    {
        this.formFieldId = formFieldId;
    }

    public String getValueType()
    {
        return valueType;
    }

    public void setValueType(final String valueType)
    {
        this.valueType = valueType;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(final String value)
    {
        this.value = value;
    }
}
