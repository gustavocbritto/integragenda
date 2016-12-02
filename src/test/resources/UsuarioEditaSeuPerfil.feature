Feature: UsuarioEditaSeuPerfil

  Scenario: Usuario cadastrado e logado deseja alterar informacoes do perfil
    Given Eu tenho cadastrado como telefone "3333333" e desejo alterar para "2222222"
    When Eu clico em alterar
    Then Meu telefone e alterado de "3333333" para "2222222"
