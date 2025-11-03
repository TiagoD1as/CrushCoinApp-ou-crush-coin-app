# 💰 CrushCoin App

Sistema de gestão financeira desenvolvido em Java com Spring Boot e integração Oracle Database.

## 🚀 Funcionalidades

- **Gestão de Usuários**: Cadastro, autenticação e gerenciamento de usuários
- **Controle de Despesas**: Registro, consulta e relatórios de gastos
- **Investimentos**: Cadastro e acompanhamento de investimentos
- **REST API**: Endpoints completos para todas as operações
- **Relatórios**: Consultas por período e totais de investimentos

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.2.0**
- **Spring Data JPA / Hibernate**
- **Oracle Database** (JDBC Driver)
- **Maven** (Gerenciamento de dependências)
- **IntelliJ IDEA** (Recomendado)

## 📋 Pré-requisitos

- Java 21 ou superior
- Maven 3.6+ ou SDKMAN (para instalar: `sdk install maven`)
- Oracle Database (instância FIAP ou local)
- IntelliJ IDEA ou IDE compatível
- Credenciais Oracle da FIAP (RM e senha)

## 🚀 Como Executar

### 1. Clone o repositório

```bash
git clone https://github.com/TiagoD1as/CrushCoinApp-ou-crush-coin-app.git
cd CrushCoinApp-ou-crush-coin-app
```

### 2. Configure o Oracle Database

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.username=SEU_RM_AQUI
spring.datasource.password=SUA_SENHA_AQUI
```

### 3. Compile o projeto

```bash
mvn clean install
```

### 4. Execute a aplicação

**Opção A - Via Maven:**
```bash
mvn spring-boot:run
```

**Opção B - Via IntelliJ IDEA:**
1. Abra o projeto no IntelliJ
2. Execute `CrushCoinApplication.java`
3. Clique direito → Run 'CrushCoinApplication'

### 5. Acesse a API

A aplicação estará rodando em: `http://localhost:8080`

## 📁 Estrutura do Projeto

```
src/
└── main/
    ├── java/
    │   └── br/com/crushcoin/
    │       ├── CrushCoinApplication.java    # Classe principal
    │       ├── entity/                      # Entidades JPA
    │       │   ├── UsuarioEntity.java
    │       │   ├── DespesaEntity.java
    │       │   └── InvestimentoEntity.java
    │       ├── repository/                  # Repositories JPA
    │       │   ├── UsuarioRepository.java
    │       │   ├── DespesaRepository.java
    │       │   └── InvestimentoRepository.java
    │       ├── service/                     # Services (regras de negócio)
    │       │   ├── UsuarioService.java
    │       │   ├── DespesaService.java
    │       │   └── InvestimentoService.java
    │       └── controller/                  # REST Controllers
    │           ├── UsuarioController.java
    │           ├── DespesaController.java
    │           └── InvestimentoController.java
    └── resources/
        └── application.properties           # Configurações
```

## 🔌 Endpoints da API

### Usuários (`/api/usuarios`)
- `GET /api/usuarios` - Listar todos os usuários
- `GET /api/usuarios/{id}` - Buscar usuário por ID
- `POST /api/usuarios` - Criar novo usuário
- `PUT /api/usuarios/{id}` - Atualizar usuário
- `DELETE /api/usuarios/{id}` - Deletar usuário

### Despesas (`/api/despesas`)
- `GET /api/despesas` - Listar todas as despesas
- `GET /api/despesas/{id}` - Buscar despesa por ID
- `GET /api/despesas/usuario/{usuarioId}` - Buscar despesas por usuário
- `GET /api/despesas/usuario/{usuarioId}/periodo?dataInicio=YYYY-MM-DD&dataFim=YYYY-MM-DD` - Buscar por período
- `POST /api/despesas/usuario/{usuarioId}` - Criar nova despesa
- `PUT /api/despesas/{id}` - Atualizar despesa
- `DELETE /api/despesas/{id}` - Deletar despesa

### Investimentos (`/api/investimentos`)
- `GET /api/investimentos` - Listar todos os investimentos
- `GET /api/investimentos/{id}` - Buscar investimento por ID
- `GET /api/investimentos/usuario/{usuarioId}` - Buscar investimentos por usuário
- `GET /api/investimentos/usuario/{usuarioId}/periodo?dataInicio=YYYY-MM-DD&dataFim=YYYY-MM-DD` - Buscar por período
- `GET /api/investimentos/usuario/{usuarioId}/total` - Calcular total de investimentos
- `POST /api/investimentos/usuario/{usuarioId}` - Criar novo investimento
- `PUT /api/investimentos/{id}` - Atualizar investimento
- `DELETE /api/investimentos/{id}` - Deletar investimento

## 🔧 Configuração do Banco de Dados

### Oracle FIAP

O projeto está configurado para conectar automaticamente ao Oracle da FIAP:

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=RM561438
spring.datasource.password=sua_senha
```

### Criar Tabelas

As tabelas são criadas automaticamente pelo Hibernate ao iniciar a aplicação (configuração `spring.jpa.hibernate.ddl-auto=update`).

## 📊 Regras de Negócio

### Usuários
- Email deve ser único
- Email e senha são obrigatórios

### Despesas
- Valor deve ser maior que zero
- Data não pode ser futura
- Deve estar associada a um usuário válido

### Investimentos
- Valor mínimo de R$ 100,00
- Valor deve ser maior que zero
- Deve estar associado a um usuário válido

## 🧪 Testando a API

### Exemplo com curl:

**Criar usuário:**
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@example.com",
    "senhaHash": "senha123"
  }'
```

**Listar usuários:**
```bash
curl http://localhost:8080/api/usuarios
```

## 📚 Documentação Adicional

- [Guia de Configuração IntelliJ](GUIA_CONFIGURACAO_INTELLIJ.md)
- [Configuração Oracle FIAP](CONFIGURACAO_ORACLE_FIAP.md)
- [Como Executar Maven](COMO_EXECUTAR_MAVEN.md)

## 🔐 Segurança

⚠️ **IMPORTANTE**: Este é um projeto acadêmico. Em produção, implemente:
- Autenticação e autorização (Spring Security)
- Hash de senhas mais seguro (BCrypt)
- Validação de entrada mais robusta
- Tratamento de erros adequado
- CORS configurado

## 👥 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

**Tiago Dias dos Santos**
- GitHub: [@TiagoD1as](https://github.com/TiagoD1as)

## 📞 Contato

Para dúvidas ou sugestões, abra uma [issue](https://github.com/TiagoD1as/CrushCoinApp-ou-crush-coin-app/issues) no repositório.

---

⭐ Se este projeto te ajudou, considere dar uma estrela!
