# Teste manual com Postman

Base URL: `http://localhost:8080`

## 1. Criar chamado
- Método: `POST`
- URL: `/api/chamados`
- Header: `Content-Type: application/json`
- Body (raw / JSON):
```json
{
  "titulo": "Sistema lento",
  "descricao": "A tela inicial demora para carregar.",
  "solicitante": "Eduarda",
  "prioridade": "MEDIA"
}
```

## 2. Listar chamados
- `GET /api/chamados`

## 3. Filtrar abertos
- `GET /api/chamados?status=ABERTO`

## 4. Consultar pelo ID
- `GET /api/chamados/1`

## 5. Atualizar status
- `PATCH /api/chamados/1/status`
- Body:
```json
{ "status": "EM_ANDAMENTO" }
```

## 6. Validar erro
Envie um POST com título vazio. A API deve responder com erro de validação em vez de criar o chamado.
