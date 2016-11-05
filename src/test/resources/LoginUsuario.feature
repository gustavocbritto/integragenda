Feature: LoginUsuario

  Scenario: Usuario comum entra no sistema
    Given Eu entro com meu usuario "gustavo" e senha "12345"
    When Eu clico em entrar
    Then Meu usuario e logado no sistema
