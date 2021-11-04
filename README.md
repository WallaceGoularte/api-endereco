# API para consultar endereço por CEP

#### Objetivos
- Construir uma API com 4 endpoints.
- Método GET para retornar todos endereços persistidos na base interna
- Método GET para retornar endereço por CEP, caso não exista na base, a consulta é realizada em uma API pública/externa.
- Método POST que recebe um objeto Endereco para persistir na base.
- Método POST que pelo CEP informado, pesquisa o endereço na API pública/externa e persiste os dados na base.

#### Requisitos

- Java 11
- Spring Boot
- Spring Data
- Banco de Dados H2
- Utilização de DTO
- Utilização de Collections
- Lambda
- Swagger


#### API externa/pública utilizada:
- https://viacep.com.br/ws/{cep}/json/
