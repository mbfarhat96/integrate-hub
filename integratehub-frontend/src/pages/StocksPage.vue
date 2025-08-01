<template>
  <div>
    <h1 class="page-title">Stock Quotes</h1>
    <p class="page-subtitle">
      Fetch real-time stock quotes and daily percent change.
    </p>

    <div class="card">
      <label for="symbol">Symbol</label>
      <input
          id="symbol"
          v-model="symbol"
          type="text"
          placeholder="e.g. AAPL"
      />

      <button @click="fetchQuote" :disabled="!symbol.trim() || ui.loading">
        Get Quote
      </button>
    </div>

    <div v-if="quote" class="card">
      <h2>{{ quote.symbol }}</h2>
      <p>
        <strong>Price:</strong>
        {{ quote.price.toFixed(2) }}
      </p>
      <p>
        <strong>Open:</strong>
        {{ quote.open.toFixed(2) }} ·
        <strong>Prev Close:</strong>
        {{ quote.previousClose.toFixed(2) }}
      </p>
      <p>
        <strong>Day Range:</strong>
        {{ quote.low.toFixed(2) }} – {{ quote.high.toFixed(2) }}
      </p>
      <p>
        <strong>Change:</strong>
        <span :class="changeClass">
          {{ quote.percentChange.toFixed(2) }} %
        </span>
      </p>
      <p class="meta">
        Fetched at: {{ quote.fetchedAt.replace("T", " ") }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { stockApi } from "../api";
import { useUIStore } from "../stores/ui";

const ui = useUIStore();
const symbol = ref("AAPL");
const quote = ref(null);

const changeClass = computed(() => {
  if (!quote.value) return "";
  return quote.value.percentChange >= 0 ? "pos" : "neg";
});

async function fetchQuote() {
  ui.clearError();
  quote.value = null;

  if (!symbol.value.trim()) {
    ui.setError("Symbol is required.");
    return;
  }

  ui.setLoading(true);
  try {
    const data = await stockApi.quote(symbol.value.trim());
    quote.value = data;
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
  color: #64748b;
}
.pos {
  color: #16a34a;
}
.neg {
  color: #dc2626;
}
</style>
