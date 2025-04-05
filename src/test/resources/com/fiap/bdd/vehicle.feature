#Feature: Gestão de veículos
#
#  Scenario: Criar um novo veículo com sucesso
#    Given eu tenho os dados de um veículo válido
#    When eu envio uma requisição para criar o veículo
#    Then a resposta deve ter status 200 e conter os dados do veículo criado
#
#  Scenario: Vender um veículo existente
#    Given existe um veículo disponível cadastrado
#    When eu envio uma requisição para vender o veículo
#    Then a resposta deve ter status 200 e status do veículo como "VENDIDO"
