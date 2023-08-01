package com.pictocrawl.http;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

public interface HttpClientSetup {

    CloseableHttpClient setupHttpClient();

}