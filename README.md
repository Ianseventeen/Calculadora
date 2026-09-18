# 🧮 Calculadora Web — Spring Boot & Vanilla UI

Aplicação fullstack de calculadora minimalista desenvolvida para praticar conceitos de **Spring Boot**, **Jakarta Bean Validation**, tratamento global de exceções e integração com **JavaScript puro**.

---

## 🎨 Design & Funcionalidades

* **Visual Minimalista**: Interface inspirada na One UI da Samsung com tema em preto e branco.
* **Entrada Híbrida**: Suporte a cliques nos botões da tela e atalhos pelo teclado físico (números, operadores, `Enter`, `Backspace` e `ESC`).
* **Validação de Payload**: Verificação no backend para garantir que expressões vazias ou com caracteres inválidos sejam rejeitadas antes do cálculo.
* **Modal Informativo**: Botão de informação (`?`) no canto superior esquerdo com dados do projeto.

---

## 🛠️ Tecnologias Utilizadas

### Backend
* **Java 17+** / **Spring Boot 3**
* **Spring Web** (Construção da API REST)
* **Spring Boot Starter Validation** (Bean Validation com `@NotBlank` e `@Pattern`)
* **Lombok** (Redução de código boilerplate)

### Frontend
* **HTML5** & **CSS3** (CSS Grid, Flexbox, efeitos de Blur e animações)
* **JavaScript (ES6+)** (Requisições assíncronas via `fetch` API e manipulação do DOM)

---

## 🔌 Documentação da API

### `POST /calculations/calculate`

Processa uma expressão matemática enviada pelo cliente e retorna o resultado.

#### Requisição
* **Header**: `Content-Type: application/json`
* **Body**:
```json
{
  "expression": "10+20*3"
}
```

#### Respostas

| Status Code | Condição | Exemplo de Retorno |
| :--- | :--- | :--- |
| `200 OK` | Expressão válida e calculada com sucesso | `70` |
| `400 Bad Request` | Expressão vazia ou com caracteres inválidos | `"A expressão contém caracteres ou símbolos inválidos."` |

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* **JDK 17** ou superior instalado
* **Maven** configurado
* Qualquer navegador web moderno

### 1. Iniciar o Backend (Spring Boot)
```bash
# Clone o repositório
git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)

# Acesse a pasta raiz da aplicação Java
cd calculadora

# Execute o servidor Spring Boot
mvn spring-boot:run
```
O backend iniciará na porta `8080` (`http://localhost:8080`).

### 2. Iniciar o Frontend
Abra o arquivo `index.html` diretamente no seu navegador ou utilize a extensão **Live Server** no VS Code.

---

## 📁 Estrutura do Projeto

```text
├── backend/
│   ├── src/main/java/com/ian/calculadora/
│   │   ├── controller/          # Endpoints REST e mapeamento de rotas
│   │   ├── exception/           # Interceptador global de exceções (@RestControllerAdvice)
│   │   ├── service/             # Lógica de avaliação e cálculo da expressão
│   │   └── CalculationRequest.java # DTO com anotações de validação (@NotBlank, @Pattern)
│   └── pom.xml
│
└── frontend/
    ├── index.html               # Estrutura HTML da calculadora e do modal
    ├── style.css                # Estilização visual (preto e branco)
    └── script.js                # Manipulação de eventos e chamadas à API
```