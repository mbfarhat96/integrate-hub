<template>
  <div class="app-root">
    <NavBar />

    <main class="app-main">
      <div class="container">
        <ErrorBanner :message="ui.error" />

        <router-view />

        <div v-if="ui.loading" class="loading-overlay">
          <LoadingSpinner />
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { useUIStore } from "./stores/ui";
import NavBar from "./components/NavBar.vue";
import ErrorBanner from "./components/ErrorBanner.vue";
import LoadingSpinner from "./components/LoadingSpinner.vue";

const ui = useUIStore();
</script>

<style>
.app-root {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.app-main {
  flex: 1;
}
.loading-overlay {
  margin-top: 16px;
  display: flex;
  justify-content: center;
}
</style>
