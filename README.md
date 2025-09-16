# 📚 PROJETO DE GESTÃO DE LIVROS, AUTORES E CATEGORIAS

Este projeto tem como objetivo **gerenciar livros, autores e categorias**,  
incluindo **importação de livros via scraping da Amazon**, utilizando **Java + Spring Boot + Jsoup**.  

> ⚠️ **Atenção:** Usar URLs válidas da Amazon para o scraping de livros.  

--------------------------

## 1️⃣ AUTORES

### Endpoints:
- **Listar todos os autores (paginado):**  
`GET http://localhost:8080/api/autores?page=0&size=10`

- **Criar autor:**  
`POST http://localhost:8080/api/autores`

- **Buscar autor por ID:**  
`GET http://localhost:8080/api/autores/{id}`

- **Atualizar autor:**  
`PUT http://localhost:8080/api/autores/{id}`

- **Deletar autor:**  
`DELETE http://localhost:8080/api/autores/{id}`

- **Listar livros de um autor:**  
`GET http://localhost:8080/api/autores/{id}/livros`

### Exemplo de requisição para criar autor:
POST http://localhost:8080/api/autores  
Body (JSON):
### Exemplo de payload (JSON)

```json
{
  "nome": "Gustavo Gadelha",
  "email": "gustavo@gmail.com",
  "dataNascimento": "2003-10-31"
}
```
--------------------------

## 2️⃣ CATEGORIAS

### Endpoints:
- **Listar todas as categorias:**  
GET http://localhost:8080/api/categoria

- **Criar categoria:**  
POST http://localhost:8080/api/categoria

- **Buscar categoria por ID:**  
GET http://localhost:8080/api/categoria/{id}

### Exemplo de requisição para criar categoria:
POST http://localhost:8080/api/categoria  
Body (JSON):
```json
{
  "nome": "Tecnologia",
  "descricao":"avanços tecnológicos"
}
```

### Resposta esperada:
 ```json
 {
  "id": 1,
  "nome": "Tecnologia",
  "livros": []
 }
 ```

--------------------------

## 3️⃣ LIVROS

### Endpoints:
- **Listar livros com filtros opcionais:**  
GET http://localhost:8080/api/livros?categoria=1&ano=2024&autor=1

- **Criar livro manualmente:**  
POST http://localhost:8080/api/livros

- **Buscar livro por ID:**  
GET http://localhost:8080/api/livros/{id}

- **Atualizar livro:**  
PUT http://localhost:8080/api/livros/{id}

- **Deletar livro:**  
DELETE http://localhost:8080/api/livros/{id}

- **Buscar livros por título:**  
GET http://localhost:8080/api/livros/search?titulo=Spring

- **Criar livro via scraping da Amazon:**  
POST http://localhost:8080/api/livros/scrap

### Exemplo de requisição para criar livro manualmente:
POST http://localhost:8080/api/livros  
Body (JSON):
```json
{
  "titulo": "Spring Boot na Prática",
  "isbn": "9781234567890",
  "anoPublicacao": 2024,
  "preco": 59.90,
  "autor": { "id": 1 },
  "categoria": { "id": 1 }
}
```

### Exemplo de requisição para criar livro via scraping:
POST http://localhost:8080/api/livros/scrap  
Body (JSON):
```json
{
  "url": "https://www.amazon.com.br/dp/0747591059",
  "autorId": 1,
  "categoriaId": 1
}
```

### Resposta esperada para scraping:
```json
{
  "id": 1,
  "titulo": "Harry Potter e a Pedra Filosofal",
  "isbn": "9780747591054",
  "anoPublicacao": 2000,
  "preco": 59.90,
  "autor": { "id": 1, "nome": "J.K. Rowling" },
  "categoria": { "id": 1, "nome": "Ficção" },
  "url": "https://www.amazon.com.br/dp/0747591059"
}
```
### Endpoints de livros para scrapping:

- **Livro 1 – Harry Potter e a Pedra Filosofal:**  
`https://www.amazon.com.br/dp/0747591059`

- **Livro 2 – Meditações (Marco Aurélio):**  
`https://www.amazon.com.br/dp/0140449132`

- **Livro 3 – Sapiens: Uma Breve História da Humanidade:**  
`https://www.amazon.com.br/dp/0062316095`

- **Livro 4 – Effective Java:**  
`https://www.amazon.com.br/dp/0134685997`

- **Livro 5 – Harry Potter e a Câmara Secreta:**  
`https://www.amazon.com.br/dp/059035342X`

- **Livro 6 – C# in Depth:**  
`https://www.amazon.com.br/dp/0321356683`

- **Livro 7 – Python Crash Course:**  
`https://www.amazon.com.br/dp/1491950358`

- **Livro 8 – A Culpa é das Estrelas:**  
`https://www.amazon.com.br/dp/0307346602`

- **Livro 9 – O Homem mais Rico da Babilônia:**  
`https://www.amazon.com.br/dp/0553380168`

- **Livro 10 – O Sol é para Todos:**  
`https://www.amazon.com.br/dp/0446310786`

 ---

  
> ⚠️ Regras de validação do livro:  
> - titulo não pode ser vazio  
> - isbn deve ter 10 ou 13 caracteres  
> - preco deve ser positivo  
> - anoPublicacao não pode ser futuro  
> - autor e categoria devem existir no banco  
> - não pode existir outro livro com o mesmo isbn  

--------------------------

## ⚠️ Observações importantes

✅ Paginação disponível nos endpoints de listagem de autores.  
✅ Filtros de listagem de livros (categoria, ano, autor) são opcionais.  
✅ Para atualizar recursos, envie todos os campos obrigatórios.  
✅ Teste usando Postman ou outro cliente HTTP enviando JSON no corpo da requisição (raw → JSON).  
✅ Banco H2 rodando na porta 8080.  
✅ **Validações obrigatórias:**  
   - Título do livro não pode ser vazio  
   - ISBN deve ter formato válido (10 ou 13 dígitos)  
   - Email do autor deve ter formato válido  
   - Preço deve ser positivo  
   - Ano de publicação não pode ser futuro

--------------------------

## 🚀 Tecnologias utilizadas

- Java 21  
- Spring Boot 3  
- Spring Data JPA  
- H2 Database  
- Jsoup (scraping)  
- Lombok  

--------------------------

Feito com 💻 e ☕ por Gustavo Gadelha

