<template>
  <div>
    <h1 class="page-title">Stock Quotes</h1>
    <p class="page-subtitle">
      Fetch real-time stock quotes and daily percent change.
    </p>

    <div class="card">
      <label for="search">Search Stocks By Company Name</label>
      <div class="search-row">
        <input
            id="search"
            v-model="searchTerm"
            type="text"
            placeholder="Find a company or ticker"
        />
        <button
            @click="searchStocks"
            :disabled="!searchTerm.trim() || ui.loading"
            class="ghost"
        >
          Search
        </button>
      </div>
      <ul v-if="results.length" class="results">
        <li v-for="stock in results" :key="stock.symbol" @click="selectSymbol(stock.symbol)">
          <div class="result-main">
            <strong>{{ stock.symbol }}</strong>
            <span class="muted">{{ stock.exchange || "–" }}</span>
          </div>
          <div class="muted">
            {{ stock.shortName || stock.longName || stock.type || "Stock" }}
          </div>
        </li>
      </ul>
    </div>

    <div class="card">
      <label for="symbol">Search Stocks By Symbol</label>
      <div class="search-row">
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
const searchTerm = ref("");
const results = ref([]);
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

async function searchStocks() {
  ui.clearError();
  results.value = [];

  if (!searchTerm.value.trim()) {
    ui.setError("Search query is required.");
    return;
  }

  ui.setLoading(true);
  try {
    results.value = await stockApi.search(searchTerm.value.trim());
    if (results.value.length) {
      symbol.value = results.value[0].symbol;
    }
  } catch (e) {
    ui.setError(e.message);
  } finally {
    ui.setLoading(false);
  }
}

function selectSymbol(sym) {
  symbol.value = sym;
  fetchQuote();
}
</script>

<style scoped>
.meta {
  font-size: 12px;
  color: #64748b;
}
.muted {
  color: #64748b;
}
.pos {
  color: #16a34a;
}
.neg {
  color: #dc2626;
}
.search-row {
  display: flex;
  gap: 12px;
  align-items: center;
}

.search-row input {
  flex: 1;
}

.results {
  list-style: none;
  padding: 0;
  margin: 12px 0 0;
  display: grid;
  max-height: 250px;
  overflow-y: auto;
  gap: 12px;
}

.results li {
  padding: 10px 12px;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  cursor: pointer;
}

.results li:hover {
  background: #f8fafc;
}

.result-main {
  display: flex;
  align-items: center;
  gap: 8px;
}

.ghost {
  background: #e2e8f0;
  color: #0f172a;
}
</style>