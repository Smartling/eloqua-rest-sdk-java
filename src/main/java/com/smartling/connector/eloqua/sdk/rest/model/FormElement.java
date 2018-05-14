package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Calendar;

public class FormElement
{
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Calendar createdAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer createdBy;
    private String currentStatus;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String depth; //minimal / partial / complete
    private String description;
    private Long id;
    private Long optionListId;
    private String instructions;
    private String name;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String[] permissions;
    private String style;
    private String type;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Calendar updatedAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer updatedBy;

    public Calendar getCreatedAt()
    {
        return createdAt;
    }

    @JsonDeserialize(using = CustomJsonCalendarDeserializer.class)
    public void setCreatedAt(final Calendar createdAt)
    {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(final Integer createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getCurrentStatus()
    {
        return currentStatus;
    }

    public void setCurrentStatus(final String currentStatus)
    {
        this.currentStatus = currentStatus;
    }

    public String getDepth()
    {
        return depth;
    }

    public void setDepth(final String depth)
    {
        this.depth = depth;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public Long getOptionListId() {
        return optionListId;
    }

    public void setOptionListId(final Long optionListId) {
        this.optionListId = optionListId;
    }

    public String getInstructions()
    {
        return instructions;
    }

    public void setInstructions(final String instructions)
    {
        this.instructions = instructions;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String[] getPermissions()
    {
        return permissions;
    }

    public void setPermissions(final String[] permissions)
    {
        this.permissions = permissions;
    }

    public String getStyle()
    {
        return style;
    }

    public void setStyle(final String style)
    {
        this.style = style;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
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

    public Integer getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(final Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }
}
