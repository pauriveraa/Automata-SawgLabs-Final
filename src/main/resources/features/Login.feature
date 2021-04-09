Feature: Autentication in the page

  Background:
    Given a potential customer knows the authentication path

  Scenario: Autentication success
    When user enters valid credentials
    Then i would have a correct authentication

  Scenario: User locked
    When user aggregate credentials
    Then you are answered with a blocked user message

  Scenario: Usuar o password invalid
    When user aggregate invalid credentials
    Then you are answered with a invalid user message

  Scenario:

