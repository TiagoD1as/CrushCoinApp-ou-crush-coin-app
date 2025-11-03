# 🔧 Configuração do Oracle - FIAP

## ⚠️ ATENÇÃO - Credenciais Necessárias

Para conectar ao Oracle da FIAP, você precisa atualizar as credenciais no arquivo:

**`src/main/resources/application.properties`**

### Credenciais a ser atualizadas:

```properties
spring.datasource.username=RM99999    # Substitua RM99999 pelo seu RM
spring.datasource.password=password123 # Substitua pela sua senha da FIAP
```

### 📝 Como descobrir suas credenciais:

1. **RM (Registro de Matrícula)**: Geralmente no formato RM99999
2. **Senha**: A senha que você usa para acessar os sistemas da FIAP

### 🔍 Exemplo de configuração completa:

```properties
# Configuração do Oracle Database - FIAP
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=RM12345    # SEU RM AQUI
spring.datasource.password=minhasenha # SUA SENHA AQUI
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```

### ⚡ Passos para configurar:

1. Abra o arquivo `src/main/resources/application.properties`
2. Localize as linhas de `spring.datasource.username` e `spring.datasource.password`
3. Substitua pelos seus dados reais
4. Salve o arquivo

### ✅ Teste a conexão:

Após configurar, execute o projeto:
```bash
mvn spring-boot:run
```

Se a conexão estiver correta, você verá logs como:
```
HikariPool-1 - Starting...
HikariPool-1 - Start completed.
```

### ❌ Problemas comuns:

- **Erro de autenticação**: Verifique se o RM e senha estão corretos
- **Erro de conexão**: Verifique sua conexão com a rede da FIAP/VPN
- **Tabelas não criadas**: Verifique se o `spring.jpa.hibernate.ddl-auto=update` está configurado



