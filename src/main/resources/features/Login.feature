Feature: Autenticacion en la pagina

  Scenario: Autenticacion exitosa
    Given que un cliente potencial conoce la ruta de autenticacion
    When el usuario ingresa credenciales validas
    Then tendria una autenticacion correcta

  Scenario: Usuario bloqueado
    Given que un cliente potencial conoce la ruta para ingresar a la aplicacion
    When ingresa sus credenciales
    Then se le responde con un mensaje de usuario bloqueado

  Scenario: Usuario o contrasena invalida
    Given que un cliente conoce la ruta para ingresar a la aplicacion
    When ingresa credenciales incorrectas
    Then se le responde con un mensaje de usuario o contrasena invalida

