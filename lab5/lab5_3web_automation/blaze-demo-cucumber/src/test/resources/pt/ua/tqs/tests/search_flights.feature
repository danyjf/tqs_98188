Feature: Purchase a flight in a demo site

  Scenario: Select flight from Portland to Dublin
    When I navigate to "https://blazedemo.com"
    And I select flights from "Portland" to "Dublin"
    And I click Find Flights
    Then page title should be "BlazeDemo - reserve"
