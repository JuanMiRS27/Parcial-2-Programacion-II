# Taller Mecánico Parcial - Microservicios

## 1) Estructura general

```text
taller-mecanico-parcial/
├── backend/
│   ├── auth-admin-users/
│   └── taller-mecanico/
├── frontend/
├── docker-compose.yml
└── render-config-notes.md
```

## 2) Arquitectura breve

- Arquitectura en capas en cada microservicio:
  - `controller`
  - `service` + `service.impl`
  - `repository`
  - `model`
  - `dto/request` y `dto/response`
  - `mapper`
  - `security`
  - `config`
  - `exception`
- JWT compartido por ambos microservicios usando la misma `JWT_SECRET`.

## 3) Puertos

- Frontend: `4200`
- Auth service: `8081`
- Taller service: `8082`
- MySQL: `3306`
- PostgreSQL: `5432`

## 4) Bases de datos

- `auth-admin-users` usa MySQL (`auth_db`).
- `taller-mecanico` usa PostgreSQL (`taller_db`).

## 5) Flujo de autenticación

1. Frontend hace login a `auth-admin-users`.
2. Auth devuelve JWT con `subject=email`, `role`, `userId`.
3. Frontend guarda token en `localStorage`.
4. Interceptor envía `Authorization: Bearer <token>`.
5. Ambos microservicios validan JWT.

## Tecnologías

- Java 21, Spring Boot, Maven, Spring Security, JWT, Spring Data JPA, Validation, Lombok.
- Angular 21.
- Docker + Docker Compose.
- MySQL + PostgreSQL.

## Levantar con Docker

```bash
docker compose up --build
```

URLs locales:
- Frontend: `http://localhost:4200`
- Auth API: `http://localhost:8081`
- Taller API: `http://localhost:8082`

## Levantar manualmente

### Auth service
```bash
cd backend/auth-admin-users
mvn spring-boot:run
```

### Taller service
```bash
cd backend/taller-mecanico
mvn spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm start
```

## Credenciales iniciales

- ADMIN:
  - `admin@taller.com / admin123`
- USER:
  - `user@taller.com / user123`

## Endpoints principales

### auth-admin-users
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/auth/me`
- `GET /api/admin/users` (ADMIN)
- `GET /api/admin/users/{id}` (ADMIN)
- `DELETE /api/admin/users/{id}` (ADMIN)

### taller-mecanico
- `GET /api/carros`
- `GET /api/carros/{id}`
- `POST /api/carros`
- `PUT /api/carros/{id}` (ADMIN)
- `DELETE /api/carros/{id}` (ADMIN)
- `GET /api/carros/estado/{estado}`
- `GET /api/carros/placa/{placa}`

## Pruebas Postman

1. Registrar usuario:
   - `POST http://localhost:8081/api/auth/register`
2. Login admin:
   - `POST http://localhost:8081/api/auth/login`
3. Login user:
   - `POST http://localhost:8081/api/auth/login`
4. Copiar token.
5. `GET http://localhost:8082/api/carros` con Bearer.
6. `POST http://localhost:8082/api/carros` con ADMIN.
7. `DELETE http://localhost:8082/api/carros/{id}` con ADMIN.
8. `DELETE http://localhost:8082/api/carros/{id}` con USER -> `403`.
9. `GET http://localhost:8081/api/admin/users` con ADMIN.
10. `GET http://localhost:8081/api/admin/users` con USER -> `403`.

## Variables de entorno

### auth-admin-users
- `SERVER_PORT`
- `MYSQLHOST` o `MYSQL_HOST`
- `MYSQLPORT` o `MYSQL_PORT`
- `MYSQLDATABASE` o `MYSQL_DATABASE`
- `MYSQLUSER` o `MYSQL_USER`
- `MYSQLPASSWORD` o `MYSQL_PASSWORD`
- `JWT_SECRET`
- `JWT_EXPIRATION`

### taller-mecanico
- `SERVER_PORT`
- `PGHOST` o `POSTGRES_HOST`
- `PGPORT` o `POSTGRES_PORT`
- `PGDATABASE` o `POSTGRES_DATABASE`
- `PGUSER` o `POSTGRES_USER`
- `PGPASSWORD` o `POSTGRES_PASSWORD`
- `JWT_SECRET`

## Despliegue en Render

Ver archivo [render-config-notes.md](./render-config-notes.md).
