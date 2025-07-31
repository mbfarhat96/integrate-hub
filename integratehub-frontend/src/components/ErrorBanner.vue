<template>
  <div v-if="message" class="error-banner">
    {{ message }}
  </div>
</template>

<script setup>
defineProps({
  message: {
    type: String,
    default: null,
  },
});
</script>

<style scoped>
.error-banner {
  background: #fee2e2;
  color: #b91c1c;
  padding: 10px 12px;
  border-radius: 8px;
  font-size: 13px;
  margin-bottom: 12px;
  border: 1px solid #fecaca;
}
</style>
