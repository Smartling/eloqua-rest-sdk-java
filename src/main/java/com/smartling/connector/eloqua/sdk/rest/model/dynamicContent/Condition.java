package com.smartling.connector.eloqua.sdk.rest.model.dynamicContent;

public class Condition
{
    private String type;
    private String operator;
    private String value;

    private String start;
    private String end;

    private Long optionListId;
    private String quickListString;

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(final String operator)
    {
        this.operator = operator;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(final String value)
    {
        this.value = value;
    }

    public String getStart()
    {
        return start;
    }

    public void setStart(final String start)
    {
        this.start = start;
    }

    public String getEnd()
    {
        return end;
    }

    public void setEnd(final String end)
    {
        this.end = end;
    }

    public Long getOptionListId()
    {
        return optionListId;
    }

    public void setOptionListId(final Long optionListId)
    {
        this.optionListId = optionListId;
    }

    public String getQuickListString()
    {
        return quickListString;
    }

    public void setQuickListString(final String quickListString)
    {
        this.quickListString = quickListString;
    }
}
