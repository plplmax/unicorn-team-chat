<template>
  <div class="form-button" v-bind="$attrs">
    <slot name="button" />
  </div>
</template>

<style scoped>
.form-button {
  width: 11.1rem;
  margin: 2.5em auto 0 auto;
}
</style>
<script setup></script>
