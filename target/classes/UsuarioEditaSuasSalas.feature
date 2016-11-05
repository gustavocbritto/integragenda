Feature: UsuarioEditaSuasSalas

  Scenario: Usuario cadastrado e logado, com algumas salas cadastradas deseja editar suas salas
    Given Eu desejo editar minhas salas
    When Clico em minhas salas
    Then Eu recebo uma lista com minhas salas com o editar habilitado
