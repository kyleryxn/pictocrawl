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
import org.apache.hc.core5.util.Timeout;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("DefaultHttpClientSetup Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultHttpClientSetupTest {
    private DefaultHttpClientSetup clientSetup;
    private SocketConfig socketConfig;
    private PoolingHttpClientConnectionManager connManager;
    private CloseableHttpClient httpClient;

    @BeforeEach
    void setup() {
        clientSetup = new DefaultHttpClientSetup(20, 20);
    }

    @DisplayName("Test: Return Equal for Successful HttpClientSetup Socket Configuration")
    @Test
    @Order(1)
    void givenCustomSocketConfig_whenConfiguring_thenSocketConfigConfiguredCorrectly() {
        socketConfig = SocketConfig.custom()
                .setSoTimeout(Timeout.ofMilliseconds(5000))
                .build();

        assertEquals(socketConfig.getSoTimeout(), clientSetup.getSocketConfig().getSoTimeout());
    }


    @DisplayName("Test: Return Equal for Successful HttpClientSetup PoolingManager Configuration")
    @Test
    @Order(2)
    void givenCustomPoolingManager_whenConfiguring_thenPoolingManagerConfiguredCorrectly() {
        connManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setDefaultSocketConfig(socketConfig)
                .setConnPoolPolicy(PoolReusePolicy.FIFO)
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setDefaultTlsConfig(TlsConfig.custom()
                        .setVersionPolicy(HttpVersionPolicy.FORCE_HTTP_2)
                        .build())
                .setMaxConnTotal(20)
                .setMaxConnPerRoute(20)
                .build();

        assertEquals(connManager.getDefaultMaxPerRoute(), clientSetup.getConnManager().getDefaultMaxPerRoute());
    }

    @DisplayName("Test: Return ")
    @Test
    @Order(3)
    void givenCustomConnectionManager_whenConfiguring_thenHttpClientSetupSuccessful() {
        httpClient = HttpClients.custom().setConnectionManager(connManager).build();
        assertEquals(httpClient.getClass(), clientSetup.setupHttpClient().getClass());
    }

}