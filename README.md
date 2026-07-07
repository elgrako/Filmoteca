# Filmoteca

Aplicación Android para catalogar una colección personal de películas: título, director, año, género, formato (DVD, Blu-ray, digital), enlace a IMDb y comentarios propios.

## Funcionalidades

- Listado de películas con carátula, título y año.
- Alta, edición y borrado de fichas de película.
- Detalle de cada película con enlace directo a su ficha en IMDb.
- Pantalla "Acerca de" y splash screen de inicio.

## Tecnologías

Android nativo en Java, con las librerías estándar de Jetpack (AppCompat, Material Components, ConstraintLayout). Interfaz basada en `Activity` + `ListView`/`ArrayAdapter`.

## Estructura

- `SplashActivity` — pantalla de bienvenida.
- `FilmListActivity` — listado principal, con menú contextual para editar o borrar.
- `FilmDataActivity` — detalle de una película.
- `FilmEditActivity` — alta y edición de fichas.
- `AboutActivity` — información de la app.
- `Film` — modelo de datos de una película.
- `FilmDataSource` — catálogo de películas de ejemplo, cargado en memoria.
- `FilmAdapter` — adaptador para pintar el listado.

## Cómo ejecutarlo

Proyecto estándar de Android Studio (Gradle). Basta con abrir la carpeta raíz en Android Studio y ejecutar sobre un emulador o dispositivo con Android 8.0 (API 26) o superior.

## Notas técnicas

Los datos de ejemplo se cargan en memoria al arrancar la app (`FilmDataSource.Initialize()`) y no se persisten: al cerrar la aplicación se pierden los cambios. Sería el primer punto a resolver si el proyecto avanzara hacia una versión con datos reales.
