Feature: UsuarioAdicionaFiltroNaPesquisaDeSala

  Scenario: Usuario adiciona filtro de pesquisa das salas
    Given Eu adiciono o filtro de categoria "Executivo"
    When Eu clico em pesquisar
    Then Eu recebo a lista de salas com aquele filtro