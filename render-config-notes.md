# Render - Configuracion sugerida

## Servicios a crear

1. Base de datos MySQL externa/compatible (para `auth-admin-users`).
2. Base de datos PostgreSQL (Render PostgreSQL o externa) para `taller-mecanico`.
3. Web Service: `auth-admin-users`.
4. Web Service: `taller-mecanico`.
5. Static Site para `frontend`.

## Auth service en Render

- Root directory: `backend/auth-admin-users`
- Build command: `mvn clean package -DskipTests`
- Start command: `java -jar target/auth-admin-users-0.0.1-SNAPSHOT.jar`
- Variables:
  - `PORT=8080`
  - `SERVER_PORT=${PORT}`
  - `MYSQLHOST=turntable.proxy.rlwy.net`
  - `MYSQLPORT=54977`
  - `MYSQLDATABASE=railway`
  - `MYSQLUSER=root`
  - `MYSQLPASSWORD=MUKPzToxCxzxsXkBbhExEduxrogBl0RR`
  - `JWT_SECRET=...` (base64)
  - `JWT_EXPIRATION=86400000`
  - `APP_CORS_ORIGINS=https://parcial-2-programacion-ii-2.onrender.com`

## Taller service en Render

- Root directory: `backend/taller-mecanico`
- Build command: `mvn clean package -DskipTests`
- Start command: `java -jar target/taller-mecanico-0.0.1-SNAPSHOT.jar`
- Variables:
  - `PORT=8080`
  - `SERVER_PORT=${PORT}`
  - `PGHOST=dpg-d7v2f7naqgkc73d3dllg-a.oregon-postgres.render.com`
  - `PGPORT=5432`
  - `PGDATABASE=tallermecanico_db`
  - `PGUSER=tallermecanico_db_user`
  - `PGPASSWORD=OUW8tl9BjzyI7s4i0P8dqIHzTSbf3TJs`
  - `JWT_SECRET=...` (el mismo de auth)
  - `APP_CORS_ORIGINS=https://parcial-2-programacion-ii-2.onrender.com`

## Frontend en Render

- Root directory: `frontend`
- Build command: `npm install && npm run build`
- Publish directory (Static Site): `dist/frontend/browser`

URLs publicas usadas en este proyecto:
- `AUTH_API_URL=https://parcial-2-programacion-ii.onrender.com`
- `TALLER_API_URL=https://parcial-2-programacion-ii-1.onrender.com`
- `FRONTEND_URL=https://parcial-2-programacion-ii-2.onrender.com`

## Importante

- Nunca usar `localhost` en produccion.
- Configurar CORS en backend para dominio del frontend desplegado.
- Ambos microservicios deben compartir exactamente la misma `JWT_SECRET`.
