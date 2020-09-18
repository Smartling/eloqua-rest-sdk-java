package com.smartling.connector.eloqua.sdk.rest.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HtmlContentTest
{
    private static final String HTML = "html";
    private static final String DOC_TYPE = "doc type";
    private static final String CSS_HEADER = "css header";
    private static final String SYSTEM_HEADER = "system header";
    private static final String HTML_BODY = "html body";
    private static final String MERGED_HTML_PATTERN = "%s<html><head>%s%s</head>%s</html>";
    private static final String MERGED_HTML = String.format(MERGED_HTML_PATTERN, DOC_TYPE, CSS_HEADER, SYSTEM_HEADER, HTML_BODY);

    @Test
    public void getPlainHtmlWithRawHtmlContent()
    {
        HtmlContent htmlContent = getHtmlContent(HtmlContent.RAW_HTML_CONTENT);

        assertThat(htmlContent.getPlainHtml()).isEqualTo(HTML);
    }

    @Test
    public void getPlainHtmlWithStructuredHtmlContent()
    {
        HtmlContent htmlContent = getHtmlContent(HtmlContent.STRUCTURED_HTML_CONTENT);

        assertThat(htmlContent.getPlainHtml()).isEqualTo(MERGED_HTML);
    }

    @Test
    public void getPlainHtmlWithResponsiveHtmlContent()
    {
        HtmlContent htmlContent = getHtmlContent(HtmlContent.RESPONSIVE_HTML_CONTENT);

        assertThat(htmlContent.getPlainHtml()).isEqualTo(MERGED_HTML);
    }

    @Test
    public void getPlainHtmlWithUnknownHtmlContent()
    {
        HtmlContent htmlContent = getHtmlContent("UnknownHtmlContent");

        assertThat(htmlContent.getPlainHtml()).isEqualTo(HTML);
    }

    @Test
    public void getMergedPlainHtmlIfFieldsAreAbsent()
    {
        HtmlContent htmlContent = new HtmlContent();
        htmlContent.setType(HtmlContent.RESPONSIVE_HTML_CONTENT);

        assertThat(htmlContent.getPlainHtml()).isEqualTo("<html><head></head></html>");
    }

    private HtmlContent getHtmlContent(String htmlContentType)
    {
        HtmlContent htmlContent = new HtmlContent();
        htmlContent.setType(htmlContentType);
        htmlContent.setHtml(HTML);
        htmlContent.setDocType(DOC_TYPE);
        htmlContent.setCssHeader(CSS_HEADER);
        htmlContent.setSystemHeader(SYSTEM_HEADER);
        htmlContent.setHtmlBody(HTML_BODY);

        return htmlContent;
    }
}
