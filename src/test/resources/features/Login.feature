Feature: Login Functionality for Sauce Demo Application

  Scenario: User logs in with valid credentials
    Given user is on the login page
    When user enters valid username and password
    And clicks on the login button
    Then user is navigated to the home page

  Scenario: User fails to log in with invalid credentials
    Given user is on the login page
    When user enters invalid username and password
    And clicks on the login button
    Then user should see an error message indicating invalid credentials

  Scenario: User completes an end-to-end product purchase
    Given user is on the login page
    When user logs in with valid credentials
    And user adds a backpack to the cart
    And user completes the checkout process
    Then user should see the order confirmation message