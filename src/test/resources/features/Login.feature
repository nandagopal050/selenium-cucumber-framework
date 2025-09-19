Feature: Login Functionality for Sauce Demo Application

  Scenario: User logs in with valid credentials
    Given user is on the login page
    When user enters valid username and password
    And clicks on the login button
    Then user is navigated to the home page