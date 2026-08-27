# CapDog - Capital City Finder

Spring Boot API serving selected country data with mini JavaScript front-end for search and retrieval.

![Capital City Finder UI](img.png?v=2)

## Stack

- Spring Boot 3, Java 17, Maven
- PostgreSQL + Spring Data JPA
- Flyway for schema migrations and seed data
- Bootstrap 5 (via CDN)
- Vanilla JavaScript
- Docker and Docker Compose for local orchestration

## How it works

- Flyway owns the database schema. On startup it applies any pending migrations from `backend/src/main/resources/db/migration`: `V1` creates the `country` table, `V2` seeds it with 51 countries. Hibernate runs with `ddl-auto=validate`, so it never modifies the schema — it only checks that the entities match it, and fails fast if they drift.
- REST endpoints expose the dataset: list all countries or fetch a single country by name (case-insensitive).
- The frontend fetches all countries on load, caches them in memory to avoid redundant API calls, and uses a `<datalist>` for native autocomplete suggestions. Country data is rendered in a Bootstrap table, with errors surfaced inline.

## Running with Docker Compose (recommended)

1. Create a `.env` file at the project root with your database credentials:

```
POSTGRES_USER=capital_user
POSTGRES_PASSWORD=capital_pass
POSTGRES_DB=capital_db
```

2. Start the whole stack — database, backend, and frontend:

```
docker compose up --build
```

3. Open the app at `http://localhost:3000` (served by nginx; the port is one of the origins allowed by CORS).
4. Backend API: available at `http://localhost:8080`.

The `frontend/` directory is bind-mounted read-only into the nginx container, so edits to HTML/CSS/JS show up on refresh without a rebuild.

## Running locally without Docker

1. Start PostgreSQL and create a database.
2. Export environment variables for Spring Boot (values should match your local DB):

```
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/capital_db
export SPRING_DATASOURCE_USERNAME=your_db_user
export SPRING_DATASOURCE_PASSWORD=your_db_password
```

3. Boot the API. Flyway creates and seeds the schema on first run:

```
cd backend
./mvnw spring-boot:run
```

4. Serve the frontend (`http://127.0.0.1:5500`, `http://localhost:5500`, or `http://localhost:3000` are allowed for CORS):

```
cd frontend
npx serve .
```

5. Open the served URL and search for a country.

## Database migrations

Migrations live in `backend/src/main/resources/db/migration` and follow Flyway's naming convention, `V<version>__<description>.sql`. They are applied in version order on startup and recorded in the `flyway_schema_history` table.

To change the schema or the data, add a new migration (`V3__...sql`) rather than editing an applied one — Flyway checksums applied migrations and will refuse to start if one changes underneath it.

To wipe the database and replay every migration from scratch (local dev only — this destroys the volume):

```
docker compose down -v && docker compose up --build
```

`backend/scripts/db_seed_script.py` generates the seed migration from `countries.json`, and is how `V2__seed_country.sql` was produced.

## Tests

- Backend unit tests cover `CountryService` (mapping DB results to DTOs) and `CountryController` (JSON shape and status codes via `@WebMvcTest` with mocked service).
- Run all backend tests:

```
cd backend
./mvnw test
```

- Frontend Jest tests cover client helpers (e.g., `getCountry` contract with the API module).

```
cd frontend
npm test
```

## API

- `GET /countries` — returns all 51 country records.
- `GET /countries/{countryName}` — returns a single entry; 404 if not found.

Example:

```
curl http://localhost:8080/countries/France
```

Response:

```json
{
  "country": "France",
  "code": "FR",
  "capital": "Paris",
  "region": "Europe",
  "population": "65273511",
  "currency": "EUR"
}
```

## Frontend use

- Start typing a country name — suggestions appear via native browser autocomplete.
- Click a suggestion or press Enter / click Submit to fetch the country data.
- Validation and errors are surfaced inline (e.g., missing input, country not found).

## Project layout

- `backend/` — Spring Boot service, JPA entities, Flyway migrations, API controllers.
- `backend/src/main/resources/db/migration/` — schema and seed migrations.
- `frontend/` — static HTML/CSS/JS client, served by nginx in Compose.

## Notes

- Swagger UI is available when running the backend at `http://localhost:8080/swagger-ui.html`.

**README generated using Claude Code.
