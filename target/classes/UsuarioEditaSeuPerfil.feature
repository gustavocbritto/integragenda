Feature: UsuarioEditaSeuPerfil

  Scenario: Usuario cadastrado e logado deseja alterar informacoes do perfil
    Given Eu tenho cadastrado como nome "Gustavo" e desejo alterar para "Bruno"
    When Eu clico em salvar
    Then Meu nome e alterado para "Bruno"
