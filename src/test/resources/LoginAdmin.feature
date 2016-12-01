Feature: LoginAdmin

  Scenario: Usuario administrador entra no sistema
    Given Eu entro com o nome "Gustavo" e senha "123456"
    When Clico em entrar
    Then Eu entro no sistema como administrador
