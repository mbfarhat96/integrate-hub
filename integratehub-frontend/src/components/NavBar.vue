<template>
  <nav class="navbar">
    <div class="navbar-inner container">
      <div class="navbar-left">
        <router-link to="/system" class="navbar-logo">IntegrateHub</router-link>
      </div>
      <div class="navbar-links">
        <router-link to="/weather">Weather</router-link>
        <router-link to="/github">GitHub</router-link>
        <router-link to="/currency">Currency</router-link>
        <router-link to="/stocks">Stocks</router-link>
        <router-link to="/news">News</router-link>
        <router-link to="/system">System</router-link>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  background: #0f172a;
  color: #e5e7eb;
}
.navbar-inner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 16px;
}
.navbar-logo {
  font-weight: 600;
  letter-spacing: 0.03em;
}
.navbar-links {
  display: flex;
  gap: 14px;
}
.navbar-links a {
  font-size: 14px;
  color: #e5e7eb;
  opacity: 0.85;
}
.navbar-links a.router-link-active {
  opacity: 1;
  border-bottom: 2px solid #3b82f6;
  padding-bottom: 2px;
}
@media (max-width: 640px) {
  .navbar-inner {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px;
  }
  .navbar-links {
    flex-wrap: wrap;
  }
}
</style>
