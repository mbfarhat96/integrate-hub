<template>
  <div>
    <h1 class="page-title">System Health</h1>
    <p class="page-subtitle">
      Overview of IntegrateHub backend services and connectivity.
    </p>
    <div class="card">
      <button @click="loadHealth" :disabled="ui.loading">
        Refresh Health
      </button>
    </div>
    <div v-if="health" class="card">
      <p>
        <strong>Application:</strong>
        <span :class="health.applicationUp ? 'ok' : 'bad'">
          {{ health.applicationUp ? "UP" : "DOWN" }}
        </span>
      </p>
      <p class="meta">
        Version: {{ health.version }} ·
        {{ health.timestamp.replace("T", " ") }}
      </p>

      <h3 style="margin-top: 12px; margin-bottom: 8px;">Services</h3>
      <ul class="status-list">
        <li v-for="(status, name) in health.serviceStatuses" :key="name">
          <span class="service-name">{{ name }}</span>
          <span :class="status === 'OK' ? 'ok' : 'bad'">{{ status }}</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { healthApi } from "../api";
import { useUIStore } from "../stores/ui";

const ui = useUIStore();
const health = ref(null);

async function loadHealth() {
  ui.clearError();
  ui.setLoading(true);
  try {
    const data = await healthApi.systemHealth();
    health.value = data;
  } catch (e) {
    ui.setError(e.message);
  } finally {
    ui.setLoading(false);
  }
}

onMounted(() => {
  loadHealth();
});
</script>

<style scoped>
.status-list {
  list-style: none;
  margin: 0;
  padding: 0;
}
.status-list li {
  display: flex;
  justify-content: space-between;
  border-top: 1px solid #e2e8f0;
  padding: 6px 0;
}
.service-name {
  text-transform: capitalize;
}
.ok {
  color: #16a34a;
  font-weight: 500;
}
.bad {
  color: #dc2626;
  font-weight: 500;
}
.meta {
  font-size: 12px;
  color: #64748b;
}
</style>
