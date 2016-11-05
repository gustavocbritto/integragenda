Feature: UsuarioTemAcessoAsInformacoesDaSala

  Scenario: Usuario deseja ver as informacoes da sala
    Given Eu tenho a lista de salas
    When Eu clico em alguma sala
    Then Eu recebo as informacoes sobre a sala selecionada
