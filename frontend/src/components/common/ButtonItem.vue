<script setup>
defineProps({
  type: {
    type: String,
    default: 'button'
  },
  text: {
    type: String,
    required: true
  }
})
</script>

<template>
  <button :type="type">{{ text }}</button>
</template>

<style scoped>
button {
  font-family: 'Abel', sans-serif;
  font-size: 2rem;
  color: var(--color-on-primary);
  background-color: var(--color-primary);
  padding: 0.2rem 3.5rem;
  border: 0;
}

button:hover {
  cursor: pointer;
}
</style>
