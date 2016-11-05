Feature: LoginAdmin

  Scenario: Usuario administrador entra no sistema
    Given Eu entro com o nome "admin" e senha "admin123"
    When Clico em entrar
    Then Eu entro no sistema como administrador
