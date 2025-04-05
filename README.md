
# Vehicle Service 🚗

Microserviço responsável pelo gerenciamento de veículos na solução do Tech Challenge SOAT.

## 📦 Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Gradle
- PostgreSQL
- Docker
- Kubernetes (Docker Desktop Kubernetes)
- Clean Architecture
- Bean Validation
- Swagger OpenAPI 3
- ConfigMap e Secrets no K8s

## 🧩 Arquitetura

Este projeto segue os princípios da **Clean Architecture**, garantindo separação clara de responsabilidades:

```
domain/
├── entity
application/
├── usecase
├── gateway
infrastructure/
├── controller
├── gateway
├── exception
└── config
```

- **Domain**: Entidades do negócio e regras essenciais.
- **Application**: Casos de uso da aplicação.
- **Infrastructure**: Camadas externas — Controllers, Configurações e Gateways de persistência.
- **Exception Handler global**: Respostas consistentes e profissionais para exceções.

## 🚀 Como executar localmente (Docker Compose)

1. Gere o build da aplicação:

```bash
./gradlew clean build
```

2. Suba os containers com Docker Compose:

```bash
docker-compose up --build
```

3. Acesse o Swagger:

```
http://localhost:8081/swagger-ui/index.html
```

## ☸️ Como executar no Kubernetes (Docker Desktop)

1. Certifique-se que o Kubernetes está habilitado no Docker Desktop.

2. Construa a imagem da aplicação:

```bash
docker build -t vehicle-service:latest .
```

3. Aplique os manifests do Kubernetes:

```bash
kubectl apply -f postgres-secret.yaml
kubectl apply -f postgres-configmap.yaml
kubectl apply -f postgres-deployment.yaml
kubectl apply -f vehicle-deployment.yaml
```

4. Verifique os serviços e pegue a porta NodePort:

```bash
kubectl get svc
```

Acesse no navegador usando a porta exibida:

```
http://localhost:<NODE_PORT>/swagger-ui/index.html
```

## 🔐 Configuração de ambiente

As configurações sensíveis estão gerenciadas por **Secrets** e **ConfigMap** no Kubernetes.

**Secrets:**
- Usuário e senha do banco PostgreSQL.

**ConfigMap:**
- Nome do banco de dados.

## 🧪 Testes

Para rodar os testes automatizados (se incluídos):

```bash
./gradlew test
```

## 📖 Documentação da API

Acesse a interface do Swagger para explorar os endpoints disponíveis:

```
http://localhost:<NODE_PORT>/swagger-ui/index.html
```

Principais endpoints:
- `POST /vehicles` - Criar novo veículo
- `PUT /vehicles/{id}` - Atualizar veículo
- `GET /vehicles/available` - Listar veículos disponíveis
- `GET /vehicles/sold` - Listar veículos vendidos
- `PATCH /vehicles/{id}/sell` - Marcar veículo como vendido

## ✅ Status do Projeto

- [x] Clean Architecture implementada
- [x] API REST documentada com Swagger
- [x] Dockerfile e Docker Compose funcionando
- [x] Kubernetes local funcionando com ConfigMap e Secrets
- [x] Bean Validation e Exception Handler global configurados
- [ ] Testes automatizados (opcional, recomendável)
- [ ] Ingress Controller (opcional para URL amigável)

## 👨‍💻 Autor

**Felipe**

## 📝 Licença

Este projeto está sob a licença MIT. Sinta-se à vontade para usar e aprimorar!
