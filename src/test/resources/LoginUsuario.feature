Feature: LoginUsuario

  Scenario: Usuario comum entra no sistema
    Given Eu entro com meu usuario "Gustavo" e senha "123456"
    When Eu clico em entrar
    Then Meu usuario e logado no sistema
