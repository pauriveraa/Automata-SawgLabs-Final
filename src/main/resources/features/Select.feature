Feature: Seleccion de productos
  Background:
    Given a potential customer knows the authentication path
    When user enters valid credentials

  Scenario: Select products
    And choose the products you want to buy
    Then will then be displayed in the shopping cart