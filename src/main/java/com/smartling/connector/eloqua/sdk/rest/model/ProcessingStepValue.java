package com.smartling.connector.eloqua.sdk.rest.model;

public class ProcessingStepValue
{
    private String type;
    private Long formFieldId;
    private String valueType;
    private String constantValue;
    private Long optionListId;

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public Long getFormFieldId()
    {
        return formFieldId;
    }

    public void setFormFieldId(final Long formFieldId)
    {
        this.formFieldId = formFieldId;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(final String valueType)
    {
        this.valueType = valueType;
    }

    public String getConstantValue()
    {
        return constantValue;
    }

    public void setConstantValue(final String constantValue)
    {
        this.constantValue = constantValue;
    }

    public Long getOptionListId()
    {
        return optionListId;
    }

    public void setOptionListId(final Long optionListId)
    {
        this.optionListId = optionListId;
    }
}
