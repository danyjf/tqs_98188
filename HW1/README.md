# HW1: Mid-term assignment

## Objectives

Develop a web applicatino, in Spring Boot, with automated tests.

## Required elements

### Project scope

Application should provide details on Covid incidence data for a certain country/territory. Consider at least the number of new cases per day or period.

The project must include different components:

1. A web app which allows users to enter or select a region/country and access its Covid metrics, e.g.: last 3 days.
2. Integration with a third party API.
3. Implement a cache. When a request is made to the API, should cache the results, if the same data is requested then the cached values should be used. The cache should define a time-to-live policy and produce some basic statistics (count of requests, hits/misses). The data related to the cache does not have to be persistent, can use a HashMap for example.
4. Create my own API to expose Covid monitoring data to be used by external clients. The API should allow to ask my backend for covid tracking data (e.g.: filtering by region, days, ...). The API should also have an endpoint for the cache statistics.
5. Implement a standard logging library to produce useful evidence of actions/events that happened while using the software.

### Technologies stack

The solution should use Spring Boot for the services/backend. Frontend may be done with a HTML/javascript framework or with a templating system that integrates with Spring Boot like thymeleaf.

### Tests to implement

The project should include the automation of different types of tests:

1. Unit tests (tests on the cache behaviour; tests on data validation, converters or utils in general).
2. Service level tests with dependency isolation using mocks.
3. Integration tests on my API.
4. Functional testing on the web interface. Adopt a Behaviour-driven approach (features with scenarios).

### Quality metrics

The project should include code quality metrics form Sonar Qube for example. Should implement:

1. Integration of analysis with Sonar Qube.

### Optional

For extra points:

1. The project works with more than one remote API. It switches from one to the other, either by configuration or by unavailability of the sources.
2. The project uses a Continuous Integration framework, with the automation of testing and static code analysis (e.g.: GitHub Actions, GitLab CI/CD)

## Submission

The submission consists of a report and a code project, including:

1. A technical report that explains the strategy that was adopted and offer evidence of the results obtained (e.g.: which tests per testing level, screenshots of the representative steps, code snippets of the key parts, screenshots with the test results, etc.). The results of the Sonar Qube dashboard should be included (and discussed).
2. The code project, committed on my TQS git repository. Include in the repository a short video with a demostration of the solution.
3. Oral presentation.
