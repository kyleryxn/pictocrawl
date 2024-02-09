# TODO List

A comprehensive list of items that need to be completed for the project.

## Features

### Website Sitemap Parsing

- [ ] **File:** [SitemapParser][sitemap-todo] 
  - **Description:** Implement website sitemap parsing to extract URLs and metadata from the `sitemap.xml` file.

### Steps:
1. Fetch the sitemap XML file from the website's server.
2. Parse the XML data to extract the URLs and their associated metadata.
3. Store the parsed information in a data structure for further processing.
4. Handle any potential errors or exceptions that may occur during parsing.
5. Integrate the sitemap parsing logic into the website's existing codebase.
6. Test the implementation thoroughly to ensure it correctly captures all URLs and metadata.
7. Optimize the parsing algorithm for performance and efficiency if needed.
8. Document the parsing process and any notable design decisions made.
9. Consider adding error logging and reporting mechanisms to track any parsing issues in the future.
10. Review the final implementation to gather feedback and make necessary improvements.

### Website Class Implementation

- [ ] **File:** [Website][website-class]
  - **Description:** Create a Java class responsible for representing a website.

### Steps:
1. Define the `Website` class with appropriate instance variables to store website information such as URL, title, description, and other relevant metadata.
2. Implement a constructor to initialize the `Website` object with the required information when creating a new instance.
3. Add getter and setter methods for each instance variable to access and modify the website information.
4. Consider implementing methods to handle additional features related to the website, such as adding/removing pages, managing site structure, etc., depending on project requirements.
5. Document the class and its methods with appropriate comments to explain their purpose and usage.
6. Test the `Website` class thoroughly to ensure it works correctly and handles different scenarios.
7. If the website's metadata is obtained from other sources, create methods or mechanisms to populate the `Website` object with the required data.
8. Consider adding validation checks to ensure the integrity of the website information stored in the `Website` object.
9. Review the final implementation to gather feedback and make necessary improvements.

### RobotsTxt Class Implementation

- [ ] **File:** [RobotsTxt][robotstxt-class]
  - **Description:** Create a Java class responsible for representing a website's `robots.txt` file.

### Steps
1. Define the `RobotsTxt` class with appropriate instance variables to store the rules specified in the `robots.txt` file.
2. Implement methods to parse and read the `robots.txt` file from the website's server.
3. Create data structures to store the rules and user-agents defined in the `robots.txt` file.
4. Add methods to check if a given user-agent is allowed to access a specific URL based on the rules defined in the `robots.txt`.
5. Integrate the `RobotsTxt` class with the `Website` class to associate the `robots.txt` data with the corresponding website instance.
6. Handle cases where a website may not have a `robots.txt` file, and set appropriate default behavior.
7. Consider adding caching mechanisms to avoid frequent requests for the `robots.txt` file.
8. Test the `RobotsTxt` class to ensure it correctly interprets the rules and behaves according to the `robots.txt` specifications.
9. Document the class and its methods with appropriate comments to explain their purpose and usage.
10. Review the final implementation to gather feedback and make necessary improvements.

### Statistic Class Implementation

- [ ] **File:** [Statistic][statistic-class]
  - **Description:** Create a Java class called `Statistic` to track the statistics of a web crawler.

### Steps:
1. Define the `Statistic` class with appropriate instance variables to store the statistics of the web crawler. Some example statistics to track include:
   - Total number of pages crawled.
   - Total number of unique pages crawled.
   - Total number of bytes downloaded.
   - Average crawl speed (pages crawled per second or requests per minute).
   - Maximum crawl speed (highest number of pages crawled in a given time interval).
   - Timestamps to record the start and end time of the crawling process.
   - Any other relevant statistics you want to track.
2. Implement methods to update and retrieve the values of these statistics as the web crawler runs.
3. Consider adding methods to reset the statistics for a new crawling session or to clear historical data if needed.
4. If the crawler runs in parallel threads or processes, ensure that the `Statistic` class handles concurrency properly to avoid data corruption.
5. Integrate the `Statistic` class with your web crawler code to periodically update the statistics during the crawling process.
6. Decide how to handle edge cases, such as handling redirects, errors, or timeouts when updating the statistics.
7. Consider implementing mechanisms to export the collected statistics to a file or a database for later analysis and reporting.
8. Test the `Statistic` class extensively to verify that it accurately tracks the web crawler's performance.
9. Document the class and its methods with appropriate comments to explain their purpose and usage.
10. Review the final implementation to gather feedback and make necessary improvements.

### Pub-sub Messaging

- [ ] Establish a pub-sub system using Apache Kafka


## Database

- [ ] Setup database to store crawl results

## Containerization

- [ ] Create Docker container for each new release

## Testing

- [x] Leverage Mockito to make unit tests more robust and efficient

<!-- Links -->
[sitemap-todo]: src/main/java/com/pictocrawl/util/parser/SitemapParser.java
[website-class]: src/main/java/com/pictocrawl/model/website/Website.java
[robotstxt-class]: src/main/java/com/pictocrawl/model/website/RobotsTxt.java
[statistic-class]: src/main/java/com/pictocrawl/model/Statistic.java