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
    private RuleSet contactUpdateRuleSet;
    private RuleSet accountUpdateRuleSet;
    private Mapping keyFieldMapping;

    private Long keyFieldId;
    private Long keyFieldMappingSourceId;
    private List<Mapping> mappings;

    private Long emailAddressFormFieldId;
    private ProcessingStepValue newEmailAddressId;

    private String allowResend;
    private ProcessingStepValue emailId;
    private Integer resendLimit;

    private Long notificationConfigurationId;
    private ProcessingStepValue subject;
    private ProcessingStepValue recipientEmailAddress;
    private ProcessingStepValue encodingId;

    private Long programId;
    private ProcessingStepValue programElementId;

    private Long campaignId;
    private ProcessingStepValue campaignElementId;

    private ProcessingStepValue landingPageId;
    private ProcessingStepValue pageUrl;

    private ProcessingStepValue contactListId;

    private ProcessingStepValue externalUrl;

    private ProcessingStepValue integrationRuleSetId;

    private ProcessingStepValue emailGroupId;
    private ProcessingStepValue isSubscribing;

    private String eventId;

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

    public RuleSet getContactUpdateRuleSet()
    {
        return contactUpdateRuleSet;
    }

    public void setContactUpdateRuleSet(final RuleSet contactUpdateRuleSet)
    {
        this.contactUpdateRuleSet = contactUpdateRuleSet;
    }

    public RuleSet getAccountUpdateRuleSet()
    {
        return accountUpdateRuleSet;
    }

    public void setAccountUpdateRuleSet(final RuleSet accountUpdateRuleSet)
    {
        this.accountUpdateRuleSet = accountUpdateRuleSet;
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

    public String getAllowResend()
    {
        return allowResend;
    }

    public void setAllowResend(final String allowResend)
    {
        this.allowResend = allowResend;
    }

    public ProcessingStepValue getEmailId()
    {
        return emailId;
    }

    public void setEmailId(final ProcessingStepValue emailId)
    {
        this.emailId = emailId;
    }

    public Integer getResendLimit()
    {
        return resendLimit;
    }

    public void setResendLimit(final Integer resendLimit)
    {
        this.resendLimit = resendLimit;
    }

    public Long getNotificationConfigurationId()
    {
        return notificationConfigurationId;
    }

    public void setNotificationConfigurationId(final Long notificationConfigurationId)
    {
        this.notificationConfigurationId = notificationConfigurationId;
    }

    public ProcessingStepValue getSubject()
    {
        return subject;
    }

    public void setSubject(final ProcessingStepValue subject)
    {
        this.subject = subject;
    }

    public ProcessingStepValue getRecipientEmailAddress()
    {
        return recipientEmailAddress;
    }

    public void setRecipientEmailAddress(final ProcessingStepValue recipientEmailAddress)
    {
        this.recipientEmailAddress = recipientEmailAddress;
    }

    public ProcessingStepValue getEncodingId()
    {
        return encodingId;
    }

    public void setEncodingId(final ProcessingStepValue encodingId)
    {
        this.encodingId = encodingId;
    }

    public Long getProgramId()
    {
        return programId;
    }

    public void setProgramId(final Long programId)
    {
        this.programId = programId;
    }

    public ProcessingStepValue getProgramElementId()
    {
        return programElementId;
    }

    public void setProgramElementId(final ProcessingStepValue programElementId)
    {
        this.programElementId = programElementId;
    }

    public Long getCampaignId()
    {
        return campaignId;
    }

    public void setCampaignId(final Long campaignId)
    {
        this.campaignId = campaignId;
    }

    public ProcessingStepValue getCampaignElementId() {
        return campaignElementId;
    }

    public void setCampaignElementId(final ProcessingStepValue campaignElementId) {
        this.campaignElementId = campaignElementId;
    }

    public ProcessingStepValue getLandingPageId()
    {
        return landingPageId;
    }

    public void setLandingPageId(final ProcessingStepValue landingPageId)
    {
        this.landingPageId = landingPageId;
    }

    public ProcessingStepValue getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(ProcessingStepValue pageUrl) {
        this.pageUrl = pageUrl;
    }

    public ProcessingStepValue getContactListId()
    {
        return contactListId;
    }

    public void setContactListId(final ProcessingStepValue contactListId)
    {
        this.contactListId = contactListId;
    }

    public ProcessingStepValue getExternalUrl()
    {
        return externalUrl;
    }

    public void setExternalUrl(final ProcessingStepValue externalUrl)
    {
        this.externalUrl = externalUrl;
    }

    public ProcessingStepValue getIntegrationRuleSetId()
    {
        return integrationRuleSetId;
    }

    public void setIntegrationRuleSetId(final ProcessingStepValue integrationRuleSetId)
    {
        this.integrationRuleSetId = integrationRuleSetId;
    }

    public ProcessingStepValue getEmailGroupId()
    {
        return emailGroupId;
    }

    public void setEmailGroupId(final ProcessingStepValue emailGroupId)
    {
        this.emailGroupId = emailGroupId;
    }

    public ProcessingStepValue getIsSubscribing() {
        return isSubscribing;
    }

    public void setIsSubscribing(ProcessingStepValue isSubscribing) {
        this.isSubscribing = isSubscribing;
    }

    public String getEventId()
    {
        return eventId;
    }

    public void setEventId(final String eventId)
    {
        this.eventId = eventId;
    }
}
