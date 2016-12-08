Feature: UsuarioQualificaSala

  Scenario: Usuario deseja avaliar uma sala
    Given Eu desejo avaliar uma sala que eu ja tenha alugado
    When Eu seleciono a pontuacao 5 para a sala
    Then A pontuacao da sala e atualizada com meu voto