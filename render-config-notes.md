# Render - Configuración sugerida

## Servicios a crear

1. Base de datos MySQL externa/compatible (para `auth-admin-users`).
2. Base de datos PostgreSQL (Render PostgreSQL o externa) para `taller-mecanico`.
3. Web Service: `auth-admin-users`.
4. Web Service: `taller-mecanico`.
5. Static Site o Web Service para `frontend`.

## Auth service en Render

- Root directory: `backend/auth-admin-users`
- Build command: `mvn clean package -DskipTests`
- Start command: `java -jar target/auth-admin-users-0.0.1-SNAPSHOT.jar`
- Variables:
  - `PORT=8080`
  - `SERVER_PORT=${PORT}`
  - `MYSQLHOST=mysql.railway.internal`
  - `MYSQLPORT=3306`
  - `MYSQLDATABASE=railway`
  - `MYSQLUSER=root`
  - `MYSQLPASSWORD=MUKPzToxCxzxsXkBbhExEduxrogBlORR`
  - `JWT_SECRET=...` (base64)
  - `JWT_EXPIRATION=86400000`

## Taller service en Render

- Root directory: `backend/taller-mecanico`
- Build command: `mvn clean package -DskipTests`
- Start command: `java -jar target/taller-mecanico-0.0.1-SNAPSHOT.jar`
- Variables:
  - `PORT=8080`
  - `SERVER_PORT=${PORT}`
  - `PGHOST=dpg-d7v2f7naqgkc73d3dllg-a` (si ambos servicios están en Render, red interna)
  - `POSTGRES_HOST=dpg-d7v2f7naqgkc73d3dllg-a.oregon-postgres.render.com` (si conectas por URL externa)
  - `PGPORT=5432`
  - `PGDATABASE=tallermecanico_db`
  - `PGUSER=tallermecanico_db_user`
  - `PGPASSWORD=OUW8tl9BjzyI7s4i0P8dqIHzTSbf3TJs`
  - `JWT_SECRET=...` (el mismo de auth)

## Frontend en Render

- Root directory: `frontend`
- Build command: `npm install && npm run build`
- Publish directory (Static Site): `dist/frontend/browser`

Para producción, usar URLs públicas de Render:
- `AUTH_API_URL=https://auth-admin-users.onrender.com`
- `TALLER_API_URL=https://taller-mecanico.onrender.com`

Nota: los nombres reales dependen del nombre final de cada servicio en Render.

## Importante

- Nunca usar `localhost` en producción.
- Configurar CORS en backend para dominio del frontend desplegado.
- Ambos microservicios deben compartir exactamente la misma `JWT_SECRET`.
