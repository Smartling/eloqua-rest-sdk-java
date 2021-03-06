package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Calendar;
import java.util.List;

public class Form
{
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Calendar createdAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer createdBy;
    private String currentStatus;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String depth; //minimal / partial / complete
    private String description;
    private List<FormElement> elements;
    private Long emailAddressFormFieldId;
    private String html;
    private String htmlName;
    private Long id;
    private Long folderId;
    private String name;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private List<String> permissions;
    private String style;
    private String customCSS;
    private String type;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Calendar updatedAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer updatedBy;
    private Integer submitFailedLandingPageId;
    private List<ProcessingStep> processingSteps;

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

    public List<FormElement> getElements()
    {
        return elements;
    }

    public void setElements(final List<FormElement> elements)
    {
        this.elements = elements;
    }

    public Long getEmailAddressFormFieldId()
    {
        return emailAddressFormFieldId;
    }

    public void setEmailAddressFormFieldId(final Long emailAddressFormFieldId)
    {
        this.emailAddressFormFieldId = emailAddressFormFieldId;
    }

    public String getHtml()
    {
        return html;
    }

    public void setHtml(final String html)
    {
        this.html = html;
    }

    public String getHtmlName()
    {
        return htmlName;
    }

    public void setHtmlName(final String htmlName)
    {
        this.htmlName = htmlName;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
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

    public List<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(final List<String> permissions)
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

    public String getCustomCSS()
    {
        return customCSS;
    }

    public void setCustomCSS(final String customCSS)
    {
        this.customCSS = customCSS;
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

    @JsonDeserialize(using = CustomJsonCalendarDeserializer.class)
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

    public Integer getSubmitFailedLandingPageId()
    {
        return submitFailedLandingPageId;
    }

    public void setSubmitFailedLandingPageId(Integer submitFailedLandingPageId)
    {
        this.submitFailedLandingPageId = submitFailedLandingPageId;
    }

    public List<ProcessingStep> getProcessingSteps()
    {
        return processingSteps;
    }

    public void setProcessingSteps(final List<ProcessingStep> processingSteps)
    {
        this.processingSteps = processingSteps;
    }
}
