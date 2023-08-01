package com.pictocrawl.http;

import org.apache.hc.client5.http.config.TlsConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http2.HttpVersionPolicy;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.pool.PoolStats;
import org.apache.hc.core5.util.Timeout;

public class DefaultHttpClientSetup implements HttpClientSetup {
    private final int maxConnTotal;
    private final int maxConnPerRoute;

    private SocketConfig socketConfig;
    private PoolingHttpClientConnectionManager connManager;

    public DefaultHttpClientSetup(int maxConnTotal, int maxConnPerRoute) {
        this.maxConnTotal = maxConnTotal;
        this.maxConnPerRoute = maxConnPerRoute;

        setSocketConfig();
        setConnManager();
    }

    private void setSocketConfig() {
        this.socketConfig = SocketConfig.custom()
                .setSoTimeout(Timeout.ofMilliseconds(5000))
                .build();
    }

    public SocketConfig getSocketConfig() {
        return socketConfig;
    }

    public PoolingHttpClientConnectionManager getConnManager() {
        return connManager;
    }

    private void setConnManager() {
        this.connManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setDefaultSocketConfig(socketConfig)
                .setConnPoolPolicy(PoolReusePolicy.FIFO)
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setDefaultTlsConfig(TlsConfig.custom()
                        .setVersionPolicy(HttpVersionPolicy.FORCE_HTTP_2)
                        .build())
                .setMaxConnTotal(maxConnTotal)
                .setMaxConnPerRoute(maxConnPerRoute)
                .build();
    }

    @Override
    public CloseableHttpClient setupHttpClient() {
        //System.out.println("HTTP Pool Size: " + connManager.getTotalStats());
        return HttpClients.custom().setConnectionManager(getConnManager()).build();
    }

}