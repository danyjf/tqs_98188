Feature: Search covid statistics

  Scenario: Search covid statistics of Portugal in 2020-06-09
    When I navigate to "https://covid-incidence.herokuapp.com/country-date.html"
    And I select the country "Portugal" and the date "2020-06-09"
    And I click the Search button
    Then total number of cases should be "34885"
    And total number of deaths should be "1485"
    And total number of tests should be "873998"
