# PictoCrawl

[![CC License][license-badge]][license]
[![Java CI with Maven][ci]][ci-passing]

A multithreaded web crawler designed to extract images from a given website.

# Features

- Reads, parses, and obeys a website's `robots.txt` file
- Does not scan the same page twice
- User can select the type of image to grab (GIF, PNG, SVG, etc.)

# GitHub Actions Workflow for Java CI with Maven

We have integrated a GitHub Actions workflow to enable continuous integration (CI) for this project using Maven. 
The workflow is triggered on pushes and pull requests to the "master" branch.

The workflow uses the latest JDK 17 from GraalVM and caches Maven dependencies to improve the execution time of the CI process. 
It automatically builds the project with Maven and packages it based on the `pom.xml` file.

Please note that some actions used in this workflow are provided by third-party sources, and they are not officially certified by GitHub. 
However, they come with their terms of service, privacy policy, and support documentation.

# Contributing

Contributions to this project are welcome. To contribute, please follow these steps:

1. Fork the repository
2. Create a new branch for your feature or bug fix
3. Commit your changes and push them to your fork
4. Submit a pull request, providing a detailed description of your changes

Please follow the provided [Git templates][git-templates] when contributing.

# Notes


# Sources

Here are some articles and tutorials that helped me figure out some things I didn't quite understand, and helped me with
various research purposes:

- [How to Read robots.txt for Web Scraping](https://www.zenrows.com/blog/robots-txt-web-scraping)

<!-- Links -->
[license]: http://creativecommons.org/licenses/by/4.0/
[ci-passing]: https://github.com/kyleryxn/pictocrawl/actions/workflows/maven.yml
[git-templates]: ./docs/git
[oracle-java]: https://www.oracle.com/java/technologies/downloads/#java17
[jakarta]: https://jakarta.ee/
[spring]: https://spring.io/
[thymeleaf]: https://www.thymeleaf.org/
[robots-txt]: https://www.zenrows.com/blog/robots-txt-web-scraping

<!-- Badges -->
[license-badge]: https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg
[ci]: https://github.com/kyleryxn/pictocrawl/actions/workflows/maven.yml/badge.svg
[java-badge]: https://img.shields.io/badge/Java-JDK%2017-007396
[jakarta-badge]: https://img.shields.io/badge/jakarta%20ee-10-FDB940
[spring-badge]: https://img.shields.io/badge/Spring-6.0.11-6DB33F
[thymeleaf-badge]: https://img.shields.io/badge/thymeleaf-3.1.1-005F0F?logo=thymeleaf
