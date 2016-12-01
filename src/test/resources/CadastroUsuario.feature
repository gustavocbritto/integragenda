Feature: CadastroUsuario

  Scenario: User Registration
    Given I am not registered yet and my username "Paulo" and password "123456"
    When I click in register
    Then my user are regitered
