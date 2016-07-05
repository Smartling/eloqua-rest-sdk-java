package com.smartling.connector.eloqua.sdk.rest.model;

public class Email
{
    String type;
    String currentStatus;
    int id;
    long createdAt;
    int createdBy;
    String depth; //minimal / partial / complete
    int folderId;
    String name;
    String permissions; //read / write / fullControl
    long updatedAt;
    int updatedBy;
    String bounceBackEmail;
    int emailFooterId;
    int emailGroupId;
    int emailHeaderId;
    int encodingId;
    HtmlContent htmlContent;
    boolean isPlainTextEditable;
    boolean isTracked;
    String subject;
    String layout;
    String plainText;
    String replyToEmail;
    String replyToName;
    boolean sendPlainTextOnly;
    String senderEmail;
    String senderName;
    String style;
}
