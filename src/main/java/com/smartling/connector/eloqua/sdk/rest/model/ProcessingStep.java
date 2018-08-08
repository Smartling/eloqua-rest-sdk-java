package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProcessingStep
{
    private String type;
    private Long id;
    private String name;
    private String execute;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> permissions;
    private ProcessingStepCondition condition;
    private RuleSet ruleSet;
    private Mapping keyFieldMapping;

    private Long keyFieldId;
    private Long keyFieldMappingSourceId;
    private List<Mapping> mappings;

    private Long emailAddressFormFieldId;
    private ProcessingStepValue newEmailAddressId;

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

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getExecute()
    {
        return execute;
    }

    public void setExecute(final String execute)
    {
        this.execute = execute;
    }

    public List<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(final List<String> permissions)
    {
        this.permissions = permissions;
    }

    public ProcessingStepCondition getCondition()
    {
        return condition;
    }

    public void setCondition(final ProcessingStepCondition condition)
    {
        this.condition = condition;
    }

    public RuleSet getRuleSet()
    {
        return ruleSet;
    }

    public void setRuleSet(final RuleSet ruleSet)
    {
        this.ruleSet = ruleSet;
    }

    public Mapping getKeyFieldMapping()
    {
        return keyFieldMapping;
    }

    public void setKeyFieldMapping(final Mapping keyFieldMapping)
    {
        this.keyFieldMapping = keyFieldMapping;
    }

    public Long getKeyFieldId()
    {
        return keyFieldId;
    }

    public void setKeyFieldId(final Long keyFieldId)
    {
        this.keyFieldId = keyFieldId;
    }

    public Long getKeyFieldMappingSourceId()
    {
        return keyFieldMappingSourceId;
    }

    public void setKeyFieldMappingSourceId(final Long keyFieldMappingSourceId)
    {
        this.keyFieldMappingSourceId = keyFieldMappingSourceId;
    }

    public List<Mapping> getMappings()
    {
        return mappings;
    }

    public void setMappings(final List<Mapping> mappings)
    {
        this.mappings = mappings;
    }

    public Long getEmailAddressFormFieldId()
    {
        return emailAddressFormFieldId;
    }

    public void setEmailAddressFormFieldId(final Long emailAddressFormFieldId)
    {
        this.emailAddressFormFieldId = emailAddressFormFieldId;
    }

    public ProcessingStepValue getNewEmailAddressId()
    {
        return newEmailAddressId;
    }

    public void setNewEmailAddressId(final ProcessingStepValue newEmailAddressId)
    {
        this.newEmailAddressId = newEmailAddressId;
    }
}
