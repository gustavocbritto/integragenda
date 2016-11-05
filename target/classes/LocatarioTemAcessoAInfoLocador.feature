Feature: LocatarioTemAcessoAInfoLocador

  Scenario: Locatario quer ver as informacoes disponiveis sobre o locador
    Given Eu estou olhando a sala de uma outra pessoa
    When Eu clico em informacoes de locador
    Then Eu recebo as informacoes sobre o locador
