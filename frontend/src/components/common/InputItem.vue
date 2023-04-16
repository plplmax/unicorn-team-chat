<script setup>
defineProps({
  id: {
    type: String,
    required: true
  },
  name: {
    type: String,
    required: true
  },
  type: {
    type: String,
    default: 'text'
  },
  label: {
    type: String,
    required: true
  },
  error: {
    type: String,
    default: ''
  },
  modelValue: {}
})
defineEmits(['update:modelValue'])
</script>

<template>
  <div>
    <label :for="id">{{ label }}</label>
    <input
      :id="id"
      :name="name"
      :type="type"
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
    />
    <p v-if="error" class="error">{{ error }}</p>
  </div>
</template>

<style scoped>
label {
  font-size: 2em;
  display: block;
}

input {
  width: 100%;
  height: 3.2rem;
  margin-top: 0.6em;
  border: 1px solid #818182;
}

.error {
  color: red;
  font-size: 1.2em;
  margin-top: 0.6em;
}
</style>
