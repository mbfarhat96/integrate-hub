<template>
  <div>
    <h1 class="page-title">GitHub Profile</h1>
    <p class="page-subtitle">
      View summary stats and repositories for a GitHub user.
    </p>

    <div class="card">
      <label for="gh-user">GitHub Username</label>
      <input
          id="gh-user"
          v-model="username"
          type="text"
          placeholder="e.g. torvalds"
      />
      <button @click="fetchUser" :disabled="!username.trim() || ui.loading">
        Fetch Profile
      </button>
    </div>

    <div v-if="user" class="card">
      <div class="profile-header">
        <img
            v-if="user.avatarUrl"
            :src="user.avatarUrl"
            alt="avatar"
            class="avatar"
        />
        <div>
          <h2>{{ user.name || user.username }}</h2>
          <p class="meta">@{{ user.username }}</p>
          <p class="meta">
            Public repos: {{ user.publicRepos }} · Followers: {{ user.followers }}
            · Following: {{ user.following }}
          </p>
          <p v-if="user.topLanguage" class="meta">
            Top language: {{ user.topLanguage }}
          </p>
        </div>
      </div>

      <h3 style="margin-top: 16px; margin-bottom: 8px;">Repositories</h3>
      <ul class="repo-list">
        <li v-for="repo in user.repositories" :key="repo.name" class="repo-item">
          <div class="repo-main">
            <strong>{{ repo.name }}</strong>
            <span v-if="repo.language" class="chip">{{ repo.language }}</span>
          </div>
          <div class="meta">
            ⭐ {{ repo.stargazersCount }} · Forks: {{ repo.forksCount }}
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { githubApi } from "../api";
import { useUIStore } from "../stores/ui";

const ui = useUIStore();
const username = ref("");
const user = ref(null);

async function fetchUser() {
  ui.clearError();
  user.value = null;

  if (!username.value.trim()) {
    ui.setError("Username is required.");
    return;
  }

  ui.setLoading(true);
  try {
    const data = await githubApi.getUser(username.value.trim());
    user.value = data;
  } catch (e) {
    ui.setError(e.message);
  } finally {
    ui.setLoading(false);
  }
}
</script>

<style scoped>
.profile-header {
  display: flex;
  gap: 12px;
  align-items: center;
}
.avatar {
  width: 52px;
  height: 52px;
  border-radius: 999px;
}
.meta {
  font-size: 12px;
  color: #64748b;
}
.repo-list {
  list-style: none;
  padding: 0;
  margin: 0;
}
.repo-item {
  border-top: 1px solid #e2e8f0;
  padding: 8px 0;
}
.repo-main {
  display: flex;
  align-items: center;
  gap: 8px;
}
.chip {
  font-size: 11px;
  background: #e0f2fe;
  color: #0369a1;
  border-radius: 999px;
  padding: 2px 8px;
}
</style>
