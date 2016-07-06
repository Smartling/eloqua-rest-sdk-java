package com.smartling.connector.eloqua.sdk.rest.model;

public class Email
{
    private String type;
    private String currentStatus;
    private int id;
    private long createdAt;
    private int createdBy;
    private String depth; //minimal / partial / complete
    private int folderId;
    private String name;
    private String permissions; //read / write / fullControl
    private long updatedAt;
    private int updatedBy;
    private String bounceBackEmail;
    private int emailFooterId;
    private int emailGroupId;
    private int emailHeaderId;
    private int encodingId;
    private HtmlContent htmlContent;
    private boolean isPlainTextEditable;
    private boolean isTracked;
    private String subject;
    private String layout;
    private String plainText;
    private String replyToEmail;
    private String replyToName;
    private boolean sendPlainTextOnly;
    private String senderEmail;
    private String senderName;
    private String style;
}
