Feature: RF001 - CadastroUsuario

  Scenario: User Registration
    Given I am not registered yet and my username "gustavo" and password "12345"
    When I click in register
    Then my user are regitered
