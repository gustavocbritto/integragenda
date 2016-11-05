Feature: AdminVisualizarTodasAsSalas

  Scenario: Adminstrador consegue olhar todas as salas
    Given Eu entro no sistema como Administrador
    When Eu clico em todas as salas
    Then Eu recebo uma lista com todas as salas
