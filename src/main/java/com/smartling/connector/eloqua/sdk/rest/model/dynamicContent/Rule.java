package com.smartling.connector.eloqua.sdk.rest.model.dynamicContent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Rule
{
    private String type;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private ContentSection contentSection;
    private List<Criterium> criteria;
    private String depth;
    private String statement;

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

    public ContentSection getContentSection()
    {
        return contentSection;
    }

    public void setContentSection(final ContentSection contentSection)
    {
        this.contentSection = contentSection;
    }

    public List<Criterium> getCriteria()
    {
        return criteria;
    }

    public void setCriteria(final List<Criterium> criteria)
    {
        this.criteria = criteria;
    }

    public String getDepth()
    {
        return depth;
    }

    public void setDepth(final String depth)
    {
        this.depth = depth;
    }

    public String getStatement()
    {
        return statement;
    }

    public void setStatement(final String statement)
    {
        this.statement = statement;
    }
}
