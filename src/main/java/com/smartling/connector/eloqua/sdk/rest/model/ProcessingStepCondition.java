package com.smartling.connector.eloqua.sdk.rest.model;

import java.util.List;

public class ProcessingStepCondition
{
    private String type;
    private Long id;
    private List<FieldCriteria> conditionalFieldCriteria;
    private boolean isConditionallyNegated;

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

    public List<FieldCriteria> getConditionalFieldCriteria()
    {
        return conditionalFieldCriteria;
    }

    public void setConditionalFieldCriteria(final List<FieldCriteria> conditionalFieldCriteria)
    {
        this.conditionalFieldCriteria = conditionalFieldCriteria;
    }

    public boolean isConditionallyNegated()
    {
        return isConditionallyNegated;
    }

    public void setConditionallyNegated(boolean conditionallyNegated)
    {
        isConditionallyNegated = conditionallyNegated;
    }
}
