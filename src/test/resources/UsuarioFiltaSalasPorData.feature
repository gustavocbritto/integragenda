Feature: UsuarioFiltaSalasPorData

  Scenario: Usuario deseja adicionar intervalo de data que deseja alugar uma sala
    Given Eu desejo filtrar as salas com a data livre de "2016-05-08" a "2016-05-09"
    When Clico em pesquisar
    Then Eu recebo uma lista com todas as salas disponiveis no intervalo desejado
