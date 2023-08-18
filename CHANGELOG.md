# Changelog

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## **1.0.1 - 17 Aug 2023**

### Added
**Features:**

- Introduced a new crawl results page template to display images obtained from crawling.
- Refactored the `getType` method to consistently return a string representation of image types.
- Added the `processCrawl` method to the `CrawlController` class to handle crawl results and filtering.

**Enhancements:**

- Configured the `ServletInitializer` class to include the `/crawl/result/` path for enhanced routing.
- Updated the `crawl.html` form to include Thymeleaf prefixes in the `name` attributes.
- Enhanced styles in the `_crawl.scss` stylesheet for a more refined crawl page appearance.
- Improved overall styles across the application by updating the main stylesheet.

**Bug Fixes:**

- None.

**Documentation:**

- None.

**Miscellaneous:**

- None.

[This version includes enhancements related to the crawl page, processing crawl results, and improving the user interface. 
The addition of the crawl results page template, refinement of image type handling, and the introduction of the `processCrawl` 
method enhance the crawl functionality. The update to the `ServletInitializer` enhances routing, while the styling 
improvements contribute to a better overall user experience.]

## 1.0.0 (Initial Release) - 27 Jul 2023