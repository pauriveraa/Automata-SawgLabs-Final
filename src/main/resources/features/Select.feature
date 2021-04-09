Feature: Seleccion de productos

  Scenario: Seleccion de productos
    Given que un cliente potencial conoce la ruta de autenticacion de la pagina
    When el usuario ingresa credenciales validas para comprar
    And escoge los productos que desea comprar
    Then se visualizaran en el carrito de compras