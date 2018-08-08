package com.smartling.connector.eloqua.sdk.rest.model;

public class Rule
{
    private String type;
    private Long id;
    private Long formFieldId;
    private Long targetFieldId;
    private Long sourceFieldId;
    private String value;
    private String delimiter;

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

    public Long getFormFieldId()
    {
        return formFieldId;
    }

    public void setFormFieldId(final Long formFieldId)
    {
        this.formFieldId = formFieldId;
    }

    public Long getTargetFieldId()
    {
        return targetFieldId;
    }

    public void setTargetFieldId(final Long targetFieldId)
    {
        this.targetFieldId = targetFieldId;
    }

    public Long getSourceFieldId()
    {
        return sourceFieldId;
    }

    public void setSourceFieldId(final Long sourceFieldId)
    {
        this.sourceFieldId = sourceFieldId;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(final String value)
    {
        this.value = value;
    }

    public String getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(final String delimiter)
    {
        this.delimiter = delimiter;
    }
}
