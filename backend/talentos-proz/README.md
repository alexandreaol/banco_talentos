# Documentação

---

## Documentação de Estrutura de Dados – Alunos e Cursos

Este projeto fornece uma estrutura de banco de dados que pode ser consumida por aplicações front-end para exibição e manipulação de informações relacionadas a alunos e cursos.

### Recursos Disponíveis

Abaixo estão os principais dados disponíveis para consumo. 

### Tabela Curso

~~~
{
  "id_curso": 1,
  "nome": "Desenvolvimento Web",
  "codigo": "DW2025"
}
~~~ 
#### Campos: 

| Campo     | Tipo    | Descrição                |
| --------- | ------- | ------------------------ |
| id\_curso | Inteiro | ID único do curso        |
| nome      | Texto   | Nome completo do curso   |
| codigo    | Texto   | Código resumido do curso |


### Tabela Aluno

~~~
{
  "id_aluno": 101,
  "nome": "Lucas",
  "sobrenome": "Silva",
  "idade": 22,
  "descricao": "Desenvolvedor focado em back-end e APIs REST.",
  "telefone": 11987654321,
  "email": "lucas.silva@email.com",
  "github_link": "https://github.com/lucas-silva",
  "data_formatura": "12/2025",
  "id_curso": 1,
}
~~~

#### Campos: 

| Campo            | Tipo    | Descrição                                       |
| ---------------- | ------- | ----------------------------------------------- |
| id\_aluno        | Inteiro | ID único do aluno                               |
| nome             | Texto   | Primeiro nome do aluno                          |
| sobrenome        | Texto   | Sobrenome do aluno                              |
| idade            | Inteiro | Idade                                           |
| descricao        | Texto   | Breve resumo ou biografia do aluno              |
| telefone         | Inteiro | Número de telefone (somente dígitos)            |
| email            | Texto   | E-mail para contato                             |
| github\_link     | Texto   | Link para o GitHub do aluno                     |
| data\_formatura  | Texto   | Data prevista para formatura (ex: "12/2025")    |
| id\_curso        | Inteiro | ID do curso ao qual está vinculado              |


### Relacionamento entre as Tabelas  

* Cada aluno está vinculado a um curso através do campo id_curso.
* Um curso pode ter vários alunos.

# USO DA API


# API – Banco de Talentos

Esta é a documentação da API do projeto **Banco de Talentos**, que permite gerenciar e visualizar alunos e cursos da escola. Ela será utilizada pelo time de front-end para consumir os dados e montar o site.

---

## Endpoints disponíveis

- [Alunos](#-alunos)
    - GET /aluno
    - GET /aluno/{id}
    - POST /aluno
    - PUT /aluno/{id}
    - DELETE /aluno/{id}
    - GET /aluno/filtro
    - GET /aluno/filters
- [Cursos](#-cursos)
    - GET /curso
    - GET /curso/{id}
    - POST /curso
    - PUT /curso/{id}
    - DELETE /curso/{id}

> **Todas as rotas começam com o prefixo `/api`**
>
> Exemplo: `GET /api/aluno`

---

## Alunos

### GET `/aluno`

Retorna todos os alunos cadastrados.

**Exemplo de resposta:**
```json
[
  {
    "id": 2,
    "nome": "Joao",
    "sobrenome": "Geraldo",
    "idade": 25,
    "descricao": "Teste",
    "telefone": 1234565545,
    "email": "joao@gmail",
    "github": "https://batman.com",
    "dataFormatura": "30/03/2026",
    "cursoId": 1
  },
]
```

---

### GET `/aluno/{id}`

Retorna os dados de um único aluno pelo ID.

**Exemplo:**
```json
{
  "id": 2,
  "nome": "Joao",
  "sobrenome": "Geraldo",
  "idade": 25,
  "descricao": "Teste",
  "telefone": 1234565545,
  "email": "joao@gmail",
  "github": "https://batman.com",
  "dataFormatura": "30/03/2026",
  "cursoId": 1
}
```

---

### POST `/aluno`

Cria um novo aluno.

**Corpo da requisição (exemplo):**
```json
{
  "nome": "Maria",
  "sobrenome": "Fernandes",
  "idade": 21,
  "descricao": "Estudante de design gráfico",
  "telefone": 11999998888,
  "email": "maria@email.com",
  "github": "https://github.com/maria",
  "dataFormatura": "12/2025",
  "cursoId": 1
}
```

---

### PUT `/aluno/{id}`

Atualiza um aluno existente.

**Corpo igual ao do POST**, sem o campo `id`.

---

### DELETE `/aluno/{id}`

Remove um aluno do sistema, com base no ID.

---

### GET `/aluno/filtro`

Retorna **apenas um aluno** que combine com os filtros informados.

**Parâmetros opcionais:**
- `nome`
- `formatura` (mesmo valor de `dataFormatura`, como `"12/2025"`)
- `email`

**Exemplo:**
```
GET /api/aluno/filtro?nome=Joao&formatura=30/03/2026
```

---

### GET `/aluno/filters`

Retorna uma **lista de alunos** que combinem com os filtros.

**Parâmetros opcionais:**
- `nome`
- `formatura`
- `email`

**Exemplo:**
```
GET /api/aluno/filters?formatura=12/2025
```

---

## Cursos

### GET `/curso`

Retorna todos os cursos cadastrados.

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "Desenvolvimento Web",
    "codigo": "DW2025",
    "alunos": [
      {
        "id": 3,
        "nome": "Joao",
        "sobrenome": "Geraldo",
        "idade": 25,
        "descricao": "Teste",
        "telefone": 1234565545,
        "email": "joao@gmail",
        "github": "https://batman.com",
        "dataFormatura": "30/03/2026",
        "cursoId": 1
      }
    ]
  },
  {
    "id": 2,
    "nome": "Design Gráfico",
    "codigo": "DG2025",
    "alunos": [
      {
        "id": 3,
        "nome": "Maria",
        "sobrenome": "Santos",
        "idade": 25,
        "descricao": "Teste",
        "telefone": 1234565545,
        "email": "Maria@gmail",
        "github": "https://batman.com",
        "dataFormatura": "30/03/2026",
        "cursoId": 2
      }
    ]
  }
]
```

---

### GET `/curso/{id}`

Retorna os dados de um curso específico pelo ID.

```json
{
  "id": 2,
  "nome": "Design Gráfico",
  "codigo": "DG2025",
  "alunos": [
    {
      "id": 3,
      "nome": "Maria",
      "sobrenome": "Santos",
      "idade": 25,
      "descricao": "Teste",
      "telefone": 1234565545,
      "email": "Maria@gmail",
      "github": "https://batman.com",
      "dataFormatura": "30/03/2026",
      "cursoId": 1
    }
  ]
}
```

---

### POST `/curso`

Cria um novo curso.

**Corpo da requisição:**
```json
{
  "nome": "Análise e Desenvolvimento de Sistemas",
  "codigo": "ADS2025"
}
```

---

### PUT `/curso/{id}`

Atualiza um curso com base no ID.

**Corpo igual ao POST.**

---

### DELETE `/curso/{id}`

Remove um curso do sistema com base no ID.

---

## Códigos de Resposta

| Código | Significado                |
|--------|----------------------------|
| 200    | Requisição OK              |
| 201    | Criado com sucesso         |
| 400    | Erro nos dados enviados    |
| 404    | Recurso não encontrado     |
| 500    | Erro no servidor           |

---

## Observações Finais

- A data de formatura deve seguir o formato `"DD/MM/YYYY"`.
- Os campos `idade` e `telefone` devem ser enviados como número (sem aspas).
- Os filtros de aluno funcionam com **qualquer um dos parâmetros**, e ignoram maiúsculas/minúsculas.
- Nenhuma autenticação é necessária por enquanto.

---
