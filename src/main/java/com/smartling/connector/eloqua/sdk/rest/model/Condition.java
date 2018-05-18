package com.smartling.connector.eloqua.sdk.rest.model;

public class Condition
{
    private Integer maximum;
    private Integer minimum;
    private String type;

    public Integer getMaximum()
    {
        return maximum;
    }

    public void setMaximum(final Integer maximum)
    {
        this.maximum = maximum;
    }

    public Integer getMinimum()
    {
        return minimum;
    }

    public void setMinimum(final Integer minimum)
    {
        this.minimum = minimum;
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
