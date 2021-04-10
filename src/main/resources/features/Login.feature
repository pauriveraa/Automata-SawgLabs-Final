Feature: Autentication in the page

  Background:
    Given a potential customer knows the authentication path

  Scenario: Autentication success
    When user enters valid credentials
    Then i would have a correct authentication

  Scenario: User locked
    When user aggregate credentials
    Then you are answered with a blocked user message

  Scenario: User password invalid
    When user aggregate invalid credentials
    Then you are answered with a invalid user message

  Scenario: Empty username and password
    When does not add username and password
    Then you are answered with a message of empty fields

  Scenario: Empty password
    When does not add password
    Then you are answered with an empty password field message

