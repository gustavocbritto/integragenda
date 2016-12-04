Feature: UsuarioEditaSeuPerfil

  Scenario: Usuario cadastrado e logado deseja alterar informacoes do perfil
    Given Eu tenho cadastrado como telefone "2222222" e desejo alterar para "3333333"
    When Eu clico em alterar
    Then Meu telefone e alterado de "2222222" para "3333333"
