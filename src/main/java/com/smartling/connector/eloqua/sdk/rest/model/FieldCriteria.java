package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FieldCriteria
{
    private String type;
    private Long id;
    private FieldCriteriaCondition condition;
    private Long fieldId;
    private Long conditionalCustomObjectId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> permissions;

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

    public FieldCriteriaCondition getCondition()
    {
        return condition;
    }

    public void setCondition(final FieldCriteriaCondition condition)
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

    public Long getConditionalCustomObjectId()
    {
        return conditionalCustomObjectId;
    }

    public void setConditionalCustomObjectId(final Long conditionalCustomObjectId)
    {
        this.conditionalCustomObjectId = conditionalCustomObjectId;
    }

    public List<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(final List<String> permissions)
    {
        this.permissions = permissions;
    }
}
