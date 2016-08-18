package com.smartling.connector.eloqua.sdk.rest.model.dynamicContent;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.smartling.connector.eloqua.sdk.rest.model.CustomJsonCalendarDeserializer;

import java.util.Calendar;
import java.util.List;

public class DynamicContent
{
    private String type;
    private Long id;
    private Calendar createdAt;
    private String createdBy;
    private String depth;
    private Long folderId;
    private String name;
    private Calendar updatedAt;
    private String updatedBy;
    private DefaultContentSection defaultContentSection;
    private List<Rule> rules;

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

    public Calendar getCreatedAt()
    {
        return createdAt;
    }

    @JsonDeserialize (using = CustomJsonCalendarDeserializer.class)
    public void setCreatedAt(final Calendar createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getDepth()
    {
        return depth;
    }

    public void setDepth(final String depth)
    {
        this.depth = depth;
    }

    public Long getFolderId()
    {
        return folderId;
    }

    public void setFolderId(final Long folderId)
    {
        this.folderId = folderId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public Calendar getUpdatedAt()
    {
        return updatedAt;
    }

    @JsonDeserialize (using = CustomJsonCalendarDeserializer.class)
    public void setUpdatedAt(final Calendar updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(final String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public DefaultContentSection getDefaultContentSection()
    {
        return defaultContentSection;
    }

    public void setDefaultContentSection(final DefaultContentSection defaultContentSection)
    {
        this.defaultContentSection = defaultContentSection;
    }

    public List<Rule> getRules()
    {
        return rules;
    }

    public void setRules(final List<Rule> rules)
    {
        this.rules = rules;
    }
}
