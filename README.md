# 🧮 Calculadora — Java + Spring Boot 

Bem vindo a minha calculadora! Esta é a minha aplicação fullstack de calculadora minimalista desenvolvida para praticar conceitos de **Spring Boot**, **Jakarta Bean Validation**, tratamento global de exceções e integração com **JavaScript puro**.

---

## 🎨 Design & Funcionalidades

* **Visual Minimalista**: Interface inspirada na One UI da Samsung com tema em preto e branco.
* **Validação de Payload**: Verificação no backend para garantir que expressões vazias ou com caracteres inválidos sejam rejeitadas antes do cálculo. (Validation API)
* **Modal Informativo**: Botão de informação (`?`) no canto superior esquerdo com dados do projeto.

---

## 🛠️ Tecnologias Utilizadas

### Backend
## 🛠️ Tecnologias Utilizadas

### Backend
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

E também utilizei a **Jakarta Bean Validation** para validar o payload enviado pelo cliente, garantindo que expressões vazias ou com caracteres inválidos sejam rejeitadas antes do cálculo.

### Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

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

## Como Executar o Projeto?

### Pré-requisitos
* **JDK 25** ou superior instalado
* **Maven** configurado
* Qualquer navegador web moderno

### 1. Iniciar o Backend (Spring Boot)
```bash
# Clone o repositório
git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)

# Acesse a pasta raiz da aplicação Java
cd calculadora

# Execute o servidor Spring Boot
./gradlew bootRun
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
│   └── build.gradle
│
└── frontend/
    ├── index.html               # Estrutura HTML da calculadora e do modal
    ├── style.css                # Estilização visual (preto e branco)
    └── script.js                # Manipulação de eventos e chamadas à API
```