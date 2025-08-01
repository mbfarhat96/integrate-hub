<template>
  <div>
    <h1 class="page-title">News Search</h1>
    <p class="page-subtitle">
      Search for news headlines from multiple sources via IntegrateHub.
    </p>

    <div class="card">
      <label for="query">Search</label>
      <input
          id="query"
          v-model="query"
          type="text"
          placeholder="e.g. technology, markets"
      />

      <button @click="searchNews" :disabled="!query.trim() || ui.loading">
        Search
      </button>
    </div>

    <div v-if="results" class="card">
      <p class="meta">
        Query: "{{ results.query }}" · {{ results.total }} result(s)
      </p>

      <ul class="news-list">
        <li
            v-for="article in results.articles"
            :key="article.url"
            class="news-item"
        >
          <a :href="article.url" target="_blank" rel="noreferrer">
            <strong>{{ article.title }}</strong>
          </a>
          <p v-if="article.description" class="desc">
            {{ article.description }}
          </p>
          <p class="meta">
            {{ article.source }}
          </p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { newsApi } from "../api";
import { useUIStore } from "../stores/ui";

const ui = useUIStore();
const query = ref("technology");
const results = ref(null);

async function searchNews() {
  ui.clearError();
  results.value = null;

  if (!query.value.trim()) {
    ui.setError("Search query is required.");
    return;
  }

  ui.setLoading(true);
  try {
    const data = await newsApi.search(query.value.trim());
    results.value = data;
  } catch (e) {
    ui.setError(e.message);
  } finally {
    ui.setLoading(false);
  }
}
</script>

<style scoped>
.news-list {
  list-style: none;
  margin: 0;
  padding: 0;
}
.news-item {
  border-top: 1px solid #e2e8f0;
  padding: 8px 0;
}
.desc {
  font-size: 13px;
  color: #334155;
}
.meta {
  font-size: 11px;
  color: #94a3b8;
}
</style>
