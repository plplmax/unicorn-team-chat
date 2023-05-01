<script setup>
import IconSend from '../icons/IconSend.vue'

defineProps({
  modelValue: {},
  send: {
    type: Function,
    required: true
  }
})
defineEmits(['update:modelValue'])
</script>

<template>
  <div class="send-message-container">
    <input
      :value="modelValue"
      placeholder="Start typing..."
      @input="$emit('update:modelValue', $event.target.value)"
      @keyup.enter="send()"
    />
    <a @click.prevent="send()" href="#"><IconSend /></a>
  </div>
</template>

<style scoped>
input {
  width: 100%;
  font-size: 1.4rem;
  padding: 1.4rem 4.4rem 1.4rem 5.2rem;
  box-shadow: inset 0 0.1rem 0 #e5e5ea;
  border: unset;
}

a {
  position: absolute;
  padding: 1.4rem 1.7rem 1.4rem 0;
  right: 0;
  cursor: pointer;
}
</style>