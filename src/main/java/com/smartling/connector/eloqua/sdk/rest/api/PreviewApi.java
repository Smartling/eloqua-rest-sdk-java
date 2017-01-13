package com.smartling.connector.eloqua.sdk.rest.api;

import feign.Param;
import feign.RequestLine;

public interface PreviewApi
{
    @RequestLine ("GET {url}")
    String getPreviewHtml (@Param ("url")String url);
}
