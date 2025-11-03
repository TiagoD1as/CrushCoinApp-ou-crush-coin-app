# 📁 Legacy - Código Antigo

Esta pasta contém o **código legado** do projeto CrushCoin que foi desenvolvido antes da migração para Spring Boot.

## 📋 Conteúdo

- **dao/** - Camada de acesso a dados antiga (JDBC)
- **model/** - Modelos antigos (classes POJO simples)
- **main/** - Classe Main antiga e testes

## ⚠️ Importante

Este código **NÃO é mais utilizado** no projeto atual. O projeto foi migrado para:

- ✅ Spring Boot
- ✅ JPA/Hibernate
- ✅ Estrutura Maven padrão
- ✅ REST Controllers

O código novo está em: `src/main/java/br/com/crushcoin/`

## 🔄 Migração Realizada

| Antigo (legacy) | Novo (src/main/java) |
|----------------|---------------------|
| `model/Despesa.java` | `entity/DespesaEntity.java` |
| `model/Usuario.java` | `entity/UsuarioEntity.java` |
| `model/Investimento.java` | `entity/InvestimentoEntity.java` |
| `dao/DespesaDAO.java` | `repository/DespesaRepository.java` |
| - | `service/DespesaService.java` |
| - | `controller/DespesaController.java` |

Este código é mantido apenas para **referência histórica**.

