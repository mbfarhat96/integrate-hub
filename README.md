# Integrate Hub

Integrate Hub is a single-page dashboard that bundles multiple public APIs behind one Spring Boot backend and a Vite/Vue frontend. Users can search stocks, convert currencies, check weather by city, view developer GitHub stats, and read current news results through a single UI and API surface.

## Features
- **Weather:** Geocode a city, fetch the forecast from Open-Meteo, and display the temperature alongside the detected country.
- **Stocks:** Search symbols and request live quotes powered by the Alpha Vantage free tier.
- **Currency conversion:** Convert amounts using cross-rates from exchangeratesapi.io (EUR-based free tier).
- **News:** Retrieve recent headlines from the free GNews API for user-provided queries.
- **GitHub stats:** Show GitHub user profile details.

## Tech stack
- **Backend:** Spring Boot 3 (Java 17) with Web MVC, WebFlux clients for external APIs, caching, and validation.
- **Frontend:** Vue 3 with Vite, Pinia for state management, Axios for API calls, and Vue Router for navigation.
- **Integrations:**
    - Weather: https://api.open-meteo.com
    - Stocks: https://www.alphavantage.co
    - Currency: http://api.exchangeratesapi.io (free tier, EUR base)
    - News: https://gnews.io
    - GitHub: https://api.github.com
- **Containerization:** Multi-stage Docker build that compiles the frontend and packages it into the Spring Boot JAR for a single runtime container.

## Run from a terminal (Docker)
The easiest way to run everything is via Docker. Ensure you have docker installed, then from the repository root run:

```bash
docker run -d -p 8080:8080 mbfarhat/integrate-hub
```

Then open http://localhost:8080 in a browser. The frontend is served by the backend, and all APIs are available under `/api/*` on the same host.