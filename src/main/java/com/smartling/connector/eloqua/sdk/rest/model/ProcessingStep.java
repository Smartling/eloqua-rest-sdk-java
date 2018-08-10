package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProcessingStep
{
    // common parameters
    private String type;
    private Long id;
    private String name;
    private String execute;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> permissions;

    // if execute = conditional
    private ProcessingStepCondition condition;

    // type = FormStepUpdateContactEmailAddress
    private Long emailAddressFormFieldId;
    private ProcessingStepValue newEmailAddressId;

    // type = ContactUpdateRuleSet
    private RuleSet contactUpdateRuleSet;
    private Mapping keyFieldMapping;

    // type = FormStepCreateUpdateContactFromFormField
    private Long keyFieldId;
    private Long keyFieldMappingSourceId;
    private List<Mapping> mappings;

    // type = FormStepCreateUpdateAccountFromFormField
    // keyFieldId
    // keyFieldMappingSourceId
    // mappings

    // type = FormStepCreateUpdateAccount
    private RuleSet accountUpdateRuleSet;
    // keyFieldMapping

    // type = FormStepCreateUpdateCustomObjectFromFormField
    private Long customObjectId;
    private Long uniqueFieldId;
    // keyFieldId
    // mappings

    // type = FormStepCreateUpdateCustomObject
    private RuleSet customObjectUpdateRuleSet;
    // customObjectId
    // keyFieldMapping

    // type = FormStepRunIntegrationRules
    private ProcessingStepValue integrationRuleSetId;
    // keyFieldMapping

    // type = FormStepGroupSubscription
    private ProcessingStepValue emailGroupId;
    private ProcessingStepValue isSubscribing;
    // emailAddressFormFieldId

    // type = FormStepSendEmail
    private String allowResend;
    private Integer resendLimit;
    private ProcessingStepValue emailId;
    // emailAddressFormFieldId

    // type = FormStepSendNotificationEmail
    private Long notificationConfigurationId;
    private ProcessingStepValue encodingId;
    private ProcessingStepValue recipientEmailAddress;
    private ProcessingStepValue subject;

    // type = FormStepGlobalUnsubscribe
    // emailAddressFormFieldId

    // type = FormStepGlobalSubscribe
    // emailAddressFormFieldId

    // type = FormStepAddToContactList
    private ProcessingStepValue contactListId;
    // keyFieldMapping

    // type = FormStepRemoveFromContactList
    // contactListId
    // keyFieldMapping

    // type = FormStepCreateUpdateEventRegistration
    private Object eventId;
    // mappings

    // type = FormStepAddToProgramBuilder
    private Long programId;
    private ProcessingStepValue programElementId;
    // keyFieldMapping

    // type = FormStepAddToCampaign
    private Long campaignId;
    private ProcessingStepValue campaignElementId;
    // keyFieldMapping

    // type = FormStepAddToProgram
    // programId
    // programElementId
    // keyFieldMapping

    // type = FormStepCancelRegistration
    // eventId (as processing step value)
    // emailAddressFormFieldId

    // type = FormStepPostData
    private ProcessingStepValue externalUrl;
    // mappings

    // type = FormStepRedirectToWebPage
    private ProcessingStepValue landingPageId;
    private ProcessingStepValue pageUrl;

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

    public RuleSet getContactUpdateRuleSet()
    {
        return contactUpdateRuleSet;
    }

    public void setContactUpdateRuleSet(final RuleSet contactUpdateRuleSet)
    {
        this.contactUpdateRuleSet = contactUpdateRuleSet;
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

    public RuleSet getAccountUpdateRuleSet()
    {
        return accountUpdateRuleSet;
    }

    public void setAccountUpdateRuleSet(final RuleSet accountUpdateRuleSet)
    {
        this.accountUpdateRuleSet = accountUpdateRuleSet;
    }

    public Long getCustomObjectId()
    {
        return customObjectId;
    }

    public void setCustomObjectId(final Long customObjectId)
    {
        this.customObjectId = customObjectId;
    }

    public Long getUniqueFieldId()
    {
        return uniqueFieldId;
    }

    public void setUniqueFieldId(final Long uniqueFieldId)
    {
        this.uniqueFieldId = uniqueFieldId;
    }

    public RuleSet getCustomObjectUpdateRuleSet()
    {
        return customObjectUpdateRuleSet;
    }

    public void setCustomObjectUpdateRuleSet(final RuleSet customObjectUpdateRuleSet)
    {
        this.customObjectUpdateRuleSet = customObjectUpdateRuleSet;
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

    public ProcessingStepValue getIsSubscribing()
    {
        return isSubscribing;
    }

    public void setIsSubscribing(final ProcessingStepValue isSubscribing)
    {
        this.isSubscribing = isSubscribing;
    }

    public String getAllowResend()
    {
        return allowResend;
    }

    public void setAllowResend(final String allowResend)
    {
        this.allowResend = allowResend;
    }

    public Integer getResendLimit()
    {
        return resendLimit;
    }

    public void setResendLimit(final Integer resendLimit)
    {
        this.resendLimit = resendLimit;
    }

    public ProcessingStepValue getEmailId()
    {
        return emailId;
    }

    public void setEmailId(final ProcessingStepValue emailId)
    {
        this.emailId = emailId;
    }

    public Long getNotificationConfigurationId()
    {
        return notificationConfigurationId;
    }

    public void setNotificationConfigurationId(final Long notificationConfigurationId)
    {
        this.notificationConfigurationId = notificationConfigurationId;
    }

    public ProcessingStepValue getEncodingId()
    {
        return encodingId;
    }

    public void setEncodingId(final ProcessingStepValue encodingId)
    {
        this.encodingId = encodingId;
    }

    public ProcessingStepValue getRecipientEmailAddress()
    {
        return recipientEmailAddress;
    }

    public void setRecipientEmailAddress(final ProcessingStepValue recipientEmailAddress)
    {
        this.recipientEmailAddress = recipientEmailAddress;
    }

    public ProcessingStepValue getSubject()
    {
        return subject;
    }

    public void setSubject(final ProcessingStepValue subject)
    {
        this.subject = subject;
    }

    public ProcessingStepValue getContactListId()
    {
        return contactListId;
    }

    public void setContactListId(final ProcessingStepValue contactListId)
    {
        this.contactListId = contactListId;
    }

    public Object getEventId()
    {
        return eventId;
    }

    public void setEventId(final Object eventId)
    {
        this.eventId = eventId;
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

    public ProcessingStepValue getCampaignElementId()
    {
        return campaignElementId;
    }

    public void setCampaignElementId(final ProcessingStepValue campaignElementId)
    {
        this.campaignElementId = campaignElementId;
    }

    public ProcessingStepValue getExternalUrl()
    {
        return externalUrl;
    }

    public void setExternalUrl(final ProcessingStepValue externalUrl)
    {
        this.externalUrl = externalUrl;
    }

    public ProcessingStepValue getLandingPageId()
    {
        return landingPageId;
    }

    public void setLandingPageId(final ProcessingStepValue landingPageId)
    {
        this.landingPageId = landingPageId;
    }

    public ProcessingStepValue getPageUrl()
    {
        return pageUrl;
    }

    public void setPageUrl(final ProcessingStepValue pageUrl)
    {
        this.pageUrl = pageUrl;
    }
}
