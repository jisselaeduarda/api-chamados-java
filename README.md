# API de Gerenciamento de Chamados

API REST desenvolvida com Java e Spring Boot para registrar e acompanhar chamados de suporte. O projeto foi pensado como estudo de backend: separa controller, service, repository, entidades e DTOs, valida os dados recebidos e persiste os registros em banco relacional.

> **Status:** versão inicial funcional em desenvolvimento. Banco H2 em memória; os dados são reiniciados quando a aplicação para.

## Problema que resolve
Uma equipe precisa registrar solicitações, acompanhar prioridade e atualizar o andamento sem depender de uma planilha compartilhada.

## Funcionalidades
- Criar chamado com título, descrição, solicitante e prioridade.
- Listar chamados e filtrar por status.
- Consultar um chamado pelo ID.
- Atualizar os dados de um chamado.
- Alterar o status para `ABERTO`, `EM_ANDAMENTO` ou `RESOLVIDO`.
- Excluir chamado.
- Validar campos obrigatórios e limites de tamanho.

## Tecnologias
- Java 17
- Spring Boot 3.5
- Spring Web (endpoints REST)
- Spring Data JPA
- H2 Database
- Bean Validation
- Gradle
- JUnit / Spring Boot Test

## Como executar
É necessário ter Java 17+ e Gradle instalado.

Na pasta do projeto:
```bash
gradle bootRun
```
A API ficará disponível em `http://localhost:8080`.

Executar os testes:
```bash
gradle test
```

## Endpoints

| Método | Endpoint | Resultado |
|---|---|---|
| `POST` | `/api/chamados` | Cria chamado (`201 Created`) |
| `GET` | `/api/chamados` | Lista todos |
| `GET` | `/api/chamados?status=ABERTO` | Filtra por status |
| `GET` | `/api/chamados/{id}` | Busca por ID |
| `PUT` | `/api/chamados/{id}` | Atualiza dados |
| `PATCH` | `/api/chamados/{id}/status` | Atualiza status |
| `DELETE` | `/api/chamados/{id}` | Exclui (`204 No Content`) |

## Exemplo: criar chamado
Requisição `POST /api/chamados`, com `Content-Type: application/json`:
```json
{
  "titulo": "Erro ao acessar o sistema",
  "descricao": "A página de login retorna erro ao enviar as credenciais.",
  "solicitante": "Maria Silva",
  "prioridade": "ALTA"
}
```

Exemplo de alteração de status, `PATCH /api/chamados/1/status`:
```json
{
  "status": "EM_ANDAMENTO"
}
```

Valores aceitos:
- Prioridade: `BAIXA`, `MEDIA`, `ALTA`
- Status: `ABERTO`, `EM_ANDAMENTO`, `RESOLVIDO`

## Decisões técnicas
- **Controller:** recebe requisições HTTP e delega o trabalho.
- **Service:** concentra as regras de negócio e a busca por ID.
- **Repository:** usa Spring Data JPA para persistência.
- **DTOs + validação:** definem o formato de entrada e evitam aceitar campos obrigatórios vazios.
- **Enums:** limitam status e prioridade a valores conhecidos.
- **H2 em memória:** reduz a configuração inicial para facilitar testes locais.

## Limitações conhecidas
- Sem autenticação/autorização.
- H2 em memória; não é banco persistente de produção.
- Sem paginação, migrações ou logs estruturados.
- A resposta usa a entidade diretamente; uma evolução futura é criar DTO de resposta.

## Próximas melhorias
1. Criar testes de integração para os endpoints e regras de validação.
2. Adicionar documentação OpenAPI/Swagger.
3. Migrar para PostgreSQL e configurar variáveis de ambiente.
4. Adicionar paginação e ordenação.
5. Implementar autenticação e autorização.

## Como apresentar em entrevista
Explique o problema, demonstre o ciclo de vida de um chamado e justifique a separação em camadas. Seja transparente: este é um projeto de estudo, e as limitações estão documentadas.
