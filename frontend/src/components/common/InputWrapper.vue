<template>
  <div class="form-input" v-bind="$attrs">
    <slot name="input" />
  </div>
</template>

<style scoped>
.form-input {
  margin-top: 2.5em;
}
</style>
