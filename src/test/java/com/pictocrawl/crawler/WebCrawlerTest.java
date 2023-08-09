package com.pictocrawl.crawler;

import com.pictocrawl.model.image.GIFImage;
import com.pictocrawl.model.image.Image;
import com.pictocrawl.util.parser.HtmlParser;
import com.pictocrawl.util.parser.RobotsTxtParser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@DisplayName("WebCrawler Tests")
class WebCrawlerTest {
    private String url;

    @Nested
    @DisplayName("Method: Constructor")
    class Constructor {
        private WebCrawler crawler;

        @DisplayName("Test: Return Equal for Start URL")
        @Test
        void givenValidUrl_whenCreatingCrawler_thenReturnEqualUrlValue() {
            url = "https://www.example.com/";
            crawler = new WebCrawler(url);

            assertEquals(url, crawler.getStartUrl());
        }

        @DisplayName("Test: Throw IllegalArgumentException for Null and Empty URL Values")
        @ParameterizedTest(name = "Test {index}: {0}")
        @NullAndEmptySource
        void givenNullEmptyUrlValues_whenCreatingCrawler_thenThrowIllegalArgumentException(String url) {
            assertThrows(IllegalArgumentException.class, () -> crawler = new WebCrawler(url));
        }

    }

    @Nested
    @DisplayName("Method: checkRobotsTxt")
    class CheckRobotsTxt {
        private AutoCloseable closeable;

        @Mock
        private HtmlParser mockedHtmlParser;

        @Mock
        private RobotsTxtParser mockedRobotsTxtParser;

        @BeforeEach
        void setup() {
            closeable = openMocks(this);
        }

        @AfterEach
        void tearDown() throws Exception {
            closeable.close();
        }

        @DisplayName("Test: Verify Robots.txt was Checked and Parsed")
        @Test
        void givenValidRobotsTxtFile_whenCrawling_thenVerifyRobotsTxt() {
            when(mockedRobotsTxtParser.checkAndGet()).thenReturn(Map.of("*", List.of("/login", "/logout")));
            WebCrawler crawler = new WebCrawler("https://www.example.com/", mockedHtmlParser, mockedRobotsTxtParser);

            ConcurrentMap<String, Set<Image>> crawledData = crawler.crawl();

            verify(mockedRobotsTxtParser).checkAndGet();
        }

        @DisplayName("Test: Return True for RobotsTxt Containing '*' Key")
        @Test
        void givenValidRobotsTxtFile_whenCheckingRobotsTxt_thenVerifyKey() {
            when(mockedRobotsTxtParser.checkAndGet()).thenReturn(Map.of("*", List.of("/login", "/logout")));
            WebCrawler crawler = new WebCrawler("https://www.example.com/", mockedHtmlParser, mockedRobotsTxtParser);

            ConcurrentMap<String, Set<Image>> crawledData = crawler.crawl();

            assertTrue(crawler.getRobotsTxt().containsKey("*"));
        }

        @DisplayName("Test: Return True for RobotsTxt Containing ('*'-'/') Value for Key-Value Pair")
        @Test
        void givenValidRobotsTxtFile_whenCheckingRobotsTxt_thenVerifyKeyValuePair() {
            when(mockedRobotsTxtParser.checkAndGet()).thenReturn(Map.of("*", List.of("/")));
            WebCrawler crawler = new WebCrawler("https://www.example.com/", mockedHtmlParser, mockedRobotsTxtParser);

            ConcurrentMap<String, Set<Image>> crawledData = crawler.crawl();

            //assertTrue(crawler.getRobotsTxt().get("*").contains("/"));
            assertEquals("/", crawler.getRobotsTxt().get("*").get(0));
        }

        @DisplayName("Test: Return False for RobotsTxt Containing ('*'-'/') Value for Key-Value Pair")
        @Test
        void givenValidRobotsTxtFile_whenCheckingRobotsTxt_thenVerifyMissingKeyValuePair() {
            when(mockedRobotsTxtParser.checkAndGet()).thenReturn(Map.of("google-bot", List.of("/")));
            WebCrawler crawler = new WebCrawler("https://www.example.com/", mockedHtmlParser, mockedRobotsTxtParser);

            ConcurrentMap<String, Set<Image>> crawledData = crawler.crawl();

            assertFalse(crawler.getRobotsTxt().containsKey("*"));
        }

    }

    @Nested
    @DisplayName("Method: crawl")
    class Crawl {
        private AutoCloseable closeable;

        @Mock
        private HtmlParser mockedHtmlParser;

        @Mock
        private RobotsTxtParser mockedRobotsTxtParser;

        @BeforeEach
        void setup() {
            closeable = openMocks(this);
        }

        @AfterEach
        void tearDown() throws Exception {
            closeable.close();
        }

        @DisplayName("Test: Return True for Crawled Data Containing a URL")
        @Test
        void givenValidUrlsImages_whenCrawling_thenVerifyCrawledData() {
            when(mockedHtmlParser.getUrls(anyString())).thenReturn(Set.of("https://example.com/path1", "https://example.com/path2"));
            when(mockedHtmlParser.getImages(anyString())).thenReturn(Set.of(new GIFImage(0, "Test Image", false,
                    "https://example.com/images/testimage.gif", 0)));

            ConcurrentMap<String, Set<Image>> crawledData = new WebCrawler("https://www.example.com/", mockedHtmlParser, mockedRobotsTxtParser).crawl();

            assertTrue(crawledData.containsKey("https://example.com/path1"));
        }

    }

}