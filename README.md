# Projeta.dev

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-21-red)](https://angular.io/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

## 📋 Descrição

Projeta.dev é uma plataforma web desenvolvida para desenvolvedores que buscam criar e gerenciar projetos em grupo. O sistema permite a formação de equipes, gerenciamento de projetos colaborativos, compartilhamento de habilidades e conteúdos, facilitando a conexão entre profissionais de tecnologia.

## 🚀 Funcionalidades

- **Autenticação e Autorização**: Sistema de login e registro com JWT
- **Gerenciamento de Usuários**: Perfis com nível de experiência e stack principal
- **Projetos Colaborativos**: Criação e gerenciamento de projetos em equipe
- **Sistema de Habilidades**: Cadastro e compartilhamento de competências técnicas
- **Conteúdo Educacional**: Compartilhamento de artigos e tutoriais
- **Notificações**: Sistema de notificações para solicitações de participação em projetos
- **Dashboard**: Interface intuitiva para acompanhar atividades

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21**
- **Spring Boot 4.0.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** (migrações de banco)
- **JWT** (autenticação)

### Frontend
- **Angular 21**
- **TypeScript**
- **Tailwind CSS**
- **RxJS**
- **JWT Decode**

### Ferramentas de Desenvolvimento
- **Maven** (gerenciamento de dependências Java)
- **npm** (gerenciamento de dependências Node.js)
- **Angular CLI**
- **Vitest** (testes unitários)

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- **Java 21** ou superior
- **Node.js** (versão 18 ou superior) e **npm**
- **PostgreSQL** 15 ou superior
- **Maven** 3.6 ou superior
- **Angular CLI** 21

## 🔧 Instalação

### 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/projeta.dev.git
cd projeta.dev
```

### 2. Configuração do Banco de Dados

Crie um banco de dados PostgreSQL chamado `projection_dev` e configure as variáveis de ambiente:

```bash
# No arquivo .env ou variáveis de ambiente do sistema
DATABASE_USERNAME=seu_usuario_postgres
DATABASE_PASSWORD=sua_senha_postgres
JWT_SECRET_USER=sua_chave_secreta_jwt
```

### 3. Backend (Spring Boot)

```bash
# Na raiz do projeto
mvn clean install
mvn spring-boot:run
```

O backend estará rodando em `http://localhost:8080`

### 4. Frontend (Angular)

```bash
cd frontend/projeta_dev
npm install
ng serve
```

O frontend estará rodando em `http://localhost:4200`

## 🐳 Testando a Aplicação via Docker

Para testar a aplicação utilizando Docker, siga os passos abaixo. Isso permite executar toda a stack (backend, frontend e banco de dados) em containers isolados.

### Pré-requisitos
- **Docker** e **Docker Compose** instalados em sua máquina.

### Passos

1. **Clone o repositório** (se ainda não fez):
   ```bash
   git clone https://github.com/seu-usuario/projeta.dev.git
   cd projeta.dev
   ```

2. **Configure as variáveis de ambiente no docker-compose.yml**:
   
   No arquivo `docker-compose.yml`, descomente e configure as variáveis de ambiente para o serviço `back`:
   ```yaml
   environment:
     SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/projection_dev
     DATABASE_USERNAME: #Seu user postgres
     DATABASE_PASSWORD: #sua senha postgres
     JWT_SECRET_USER: u+qgVg7APPXDVGVadCCFzMAiHiF7/Gr0Lhsxn4GC0Ys=
   ```

3. **Execute o Docker Compose**:
   ```bash
   docker-compose up
   ```

   Isso irá baixar as imagens e iniciar os containers para o banco de dados (PostgreSQL), backend (Spring Boot) e frontend (Angular).

4. **Acesse a aplicação**:
   - Frontend: `http://localhost:4200`
   - Backend: `http://localhost:8080` (para APIs)
   - Banco de dados: `localhost:5432` (se precisar conectar externamente)

5. **Para parar os containers**:
   ```bash
   docker-compose down
   ```

Essa configuração utiliza as imagens Docker pré-construídas do projeto, facilitando o teste sem necessidade de instalar todas as dependências localmente.

## 🏗️ Estrutura do Projeto

```
projeta.dev/
├── src/main/java/santzin/projeta/dev/     # Código fonte Java
│   ├── controller/                        # Controllers REST
│   ├── model/                            # Entidades JPA
│   ├── repository/                       # Repositórios
│   ├── service/                          # Lógica de negócio
│   ├── DTOs/                             # Data Transfer Objects
│   ├── exception/                        # Tratamento de exceções
│   ├── infra/                            # Configurações de infraestrutura
│   ├── mapper/                           # Mapeadores
│   └── security/                         # Configurações de segurança
├── src/main/resources/                   # Recursos
│   ├── db/migrations/                    # Scripts de migração Flyway
│   └── application.properties            # Configurações da aplicação
├── frontend/projeta_dev/                 # Aplicação Angular
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/               # Componentes Angular
│   │   │   ├── service/                  # Serviços Angular
│   │   │   ├── types/                    # Tipos TypeScript
│   │   │   └── security/                 # Interceptores e segurança
│   │   └── assets/                       # Assets estáticos
│   └── package.json                      # Dependências Node.js
└── pom.xml                               # Configuração Maven
```

## 🧪 Testes

### Backend
```bash
mvn test
```

### Frontend
```bash
cd frontend/projeta_dev
ng test
```

## 📚 API Endpoints

### Autenticação
- `POST /auth/login` - Login de usuário
- `POST /auth/register` - Registro de novo usuário

### Usuários
- `GET /users` - Listar usuários
- `GET /users/{id}` - Obter usuário por ID
- `PUT /users/{id}` - Atualizar usuário

### Projetos
- `GET /projects` - Listar projetos
- `POST /projects` - Criar novo projeto
- `GET /projects/{id}` - Obter projeto por ID
- `PUT /projects/{id}` - Atualizar projeto
- `DELETE /projects/{id}` - Deletar projeto

### Habilidades
- `GET /habilities` - Listar habilidades
- `POST /habilities` - Criar nova habilidade

### Conteúdos
- `GET /contents` - Listar conteúdos
- `POST /contents` - Criar novo conteúdo

## ⚠️ Avisos e Funcionalidades Pendentes

Este projeto ainda está em desenvolvimento e apresenta algumas funcionalidades incompletas ou que necessitam de melhorias. Abaixo, uma lista dos principais itens pendentes:

### Frontend
- **Área de Registro**: Faltam campos adicionais no formulário de registro (como confirmação de senha, validações mais robustas)
- **Edição de Projetos**: A função de editar projetos não está implementada na interface
- **Exclusão de Projetos**: A função de apagar projetos não está disponível
- **Edição de Usuário**: Não há interface para editar informações do perfil do usuário
- **Página de Erro**: Página genérica de erro 404 ou tratamento de erros não implementada
- **Login e Register**: 
  - Falta validação em tempo real nos campos
  - Não há alertas visuais para erros de autenticação (ex: senha incorreta, email já cadastrado)
  - Mensagens de erro não são exibidas adequadamente ao usuário

### Backend
- **Validações**: Algumas validações de entrada podem ser aprimoradas
- **Tratamento de Exceções**: Melhorar o tratamento de erros e respostas padronizadas

### Geral
- **Testes**: Cobertura de testes unitários e de integração pode ser expandida
- **Documentação**: Documentação da API (Swagger/OpenAPI) não está configurada

Essas funcionalidades serão implementadas nas próximas versões. Contribuições são bem-vindas!

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 👥 Autor

**Santzin** - *Desenvolvedor Principal*

⭐ Se este projeto foi útil para você, dê uma estrela!</content>
<parameter name="filePath">e:\FACEMA\ESTUDOS\JAVA\projeta.dev\README.md