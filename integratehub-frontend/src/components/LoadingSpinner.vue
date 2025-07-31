<template>
  <div class="spinner"></div>
</template>

<style scoped>
.spinner {
  width: 28px;
  height: 28px;
  border-radius: 9999px;
  border: 3px solid #e2e8f0;
  border-top-color: #2563eb;
  animation: spin 0.7s linear infinite;
}
@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}
</style>
