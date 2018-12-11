package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RuleSet
{
    private String type;
    private Long id;
    private String name;
    private String scope;
    private List<Rule> updateRules;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String depth; //minimal / partial / complete
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
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

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(final String scope)
    {
        this.scope = scope;
    }

    public List<Rule> getUpdateRules()
    {
        return updateRules;
    }

    public void setUpdateRules(final List<Rule> updateRules)
    {
        this.updateRules = updateRules;
    }

    public String getDepth()
    {
        return depth;
    }

    public void setDepth(final String depth)
    {
        this.depth = depth;
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
