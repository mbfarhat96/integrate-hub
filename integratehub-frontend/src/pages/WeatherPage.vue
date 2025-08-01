<template>
  <div>
    <h1 class="page-title">Weather</h1>
    <p class="page-subtitle">
      Fetch current weather for a city using the IntegrateHub weather integration.
    </p>

    <div class="card">
      <label for="city">City</label>
      <input
          id="city"
          v-model="city"
          type="text"
          placeholder="e.g. Chicago"
      />

      <button @click="fetchWeather" :disabled="!city.trim() || ui.loading">
        Get Weather
      </button>
    </div>

    <div v-if="weather" class="card">
      <h2>{{ weather.city }}, {{ weather.country }}</h2>
      <p>
        <strong>Temperature:</strong>
        {{ weather.temperatureCelsius.toFixed(1) }} °C
      </p>
      <p>
        <strong>Feels like:</strong>
        {{ weather.feelsLikeCelsius.toFixed(1) }} °C
      </p>
      <p>
        <strong>Humidity:</strong>
        {{ weather.humidity }} %
      </p>
      <p>
        <strong>Description:</strong>
        {{ weather.description }}
      </p>
      <p class="meta">
        Fetched at: {{ formattedFetchedAt }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { weatherApi } from "../api";
import { useUIStore } from "../stores/ui";

const ui = useUIStore();
const city = ref("");
const weather = ref(null);

const formattedFetchedAt = computed(() => {
  if (!weather.value?.fetchedAt) return "";
  return weather.value.fetchedAt.replace("T", " ");
});

async function fetchWeather() {
  ui.clearError();
  weather.value = null;
  if (!city.value.trim()) {
    ui.setError("City is required.");
    return;
  }

  ui.setLoading(true);
  try {
    const data = await weatherApi.getWeather(city.value.trim());
    weather.value = data;
  } catch (e) {
    ui.setError(e.message);
  } finally {
    ui.setLoading(false);
  }
}
</script>

<style scoped>
.meta {
  font-size: 12px;
  color: #94a3b8;
  margin-top: 8px;
}
</style>
