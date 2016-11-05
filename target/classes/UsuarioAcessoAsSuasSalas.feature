Feature: UsuarioAcessoAsSuasSalas

  Scenario: Um Usuario deseja visualizar suas salas
    Given Eu estou logado no sistema com algumas salas cadastradas
    When Eu clico em minhas Salas
    Then Eu recebo uma lista com minhas salas
