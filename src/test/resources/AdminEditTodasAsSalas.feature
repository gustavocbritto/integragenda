Feature: AdminEditTodasAsSalas

  Scenario: Administrador lista todas as salas
    Given Eu entrei no sistema como adminstrador
    When Eu clico em minhas salas
    Then Eu posso editar todas as salas listadas
