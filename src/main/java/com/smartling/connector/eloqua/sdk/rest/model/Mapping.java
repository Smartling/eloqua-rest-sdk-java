package com.smartling.connector.eloqua.sdk.rest.model;

public class Mapping
{
    private String type;
    private Long id;
    private Long sourceFormFieldId;
    private Long targetEntityFieldId;
    private String updateType;

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

    public Long getSourceFormFieldId()
    {
        return sourceFormFieldId;
    }

    public void setSourceFormFieldId(final Long sourceFormFieldId)
    {
        this.sourceFormFieldId = sourceFormFieldId;
    }

    public Long getTargetEntityFieldId()
    {
        return targetEntityFieldId;
    }

    public void setTargetEntityFieldId(final Long targetEntityFieldId)
    {
        this.targetEntityFieldId = targetEntityFieldId;
    }

    public String getUpdateType()
    {
        return updateType;
    }

    public void setUpdateType(final String updateType)
    {
        this.updateType = updateType;
    }
}
