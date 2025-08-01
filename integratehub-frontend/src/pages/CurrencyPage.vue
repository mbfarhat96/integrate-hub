<template>
  <div>
    <h1 class="page-title">Currency Converter</h1>
    <p class="page-subtitle">
      Convert amounts between currencies via the IntegrateHub currency integration.
    </p>

    <div class="card">
      <div class="grid">
        <div>
          <label for="from">From</label>
          <input
              id="from"
              v-model="from"
              type="text"
              placeholder="e.g. USD"
          />
        </div>

        <div>
          <label for="to">To</label>
          <input
              id="to"
              v-model="to"
              type="text"
              placeholder="e.g. PKR"
          />
        </div>
      </div>

      <label for="amount">Amount</label>
      <input
          id="amount"
          v-model.number="amount"
          type="number"
          min="0"
          step="0.01"
          placeholder="e.g. 10"
      />

      <button @click="convert" :disabled="!canConvert || ui.loading">
        Convert
      </button>
    </div>

    <div v-if="result" class="card">
      <p>
        <strong>{{ result.amount }}</strong>
        {{ result.fromCurrency }} =
        <strong>{{ result.convertedAmount }}</strong>
        {{ result.toCurrency }}
      </p>
      <p class="meta">
        Rate: {{ result.rate }} · Fetched at:
        {{ result.fetchedAt.replace("T", " ") }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { currencyApi } from "../api";
import { useUIStore } from "../stores/ui";

const ui = useUIStore();
const from = ref("USD");
const to = ref("PKR");
const amount = ref(10);
const result = ref(null);

const canConvert = computed(
    () => from.value.trim() && to.value.trim() && amount.value > 0
);

async function convert() {
  ui.clearError();
  result.value = null;

  if (!canConvert.value) {
    ui.setError("Please provide valid currencies and amount.");
    return;
  }

  ui.setLoading(true);
  try {
    const data = await currencyApi.convert(
        from.value.trim(),
        to.value.trim(),
        amount.value
    );
    result.value = data;
  } catch (e) {
    ui.setError(e.message);
  } finally {
    ui.setLoading(false);
  }
}
</script>

<style scoped>
.grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 12px;
  margin-bottom: 12px;
}
.meta {
  font-size: 12px;
  color: #64748b;
}
@media (max-width: 640px) {
  .grid {
    grid-template-columns: 1fr;
  }
}
</style>
