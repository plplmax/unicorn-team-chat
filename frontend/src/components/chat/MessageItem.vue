<script setup>
import { ref } from 'vue'

const props = defineProps({
  self: {
    type: Boolean,
    default: false
  },
  author: {
    type: String,
    required: true
  },
  message: {
    type: String,
    required: true
  },
  time: {
    type: String,
    required: true
  },
  editable: {
    type: Boolean,
    required: true
  },
  onEdit: {
    type: Function,
    required: true
  }
})
defineEmits(['update:editable'])

// @todo #48:15m When a message is edited and the user wants to edit the same message,
//  user sees an outdated content in the textarea. Maybe we can use computed() here?
const editedMessage = ref(props.message)
</script>

<template>
  <div :class="{ message: true, 'message-left': !self, 'message-right': self }">
    <p v-if="!self" class="author">{{ author }}</p>
    <div class="content-wrapper">
      <p v-if="!editable" class="content">{{ message }}</p>
      <textarea
        v-else
        v-model="editedMessage"
        autofocus
        class="content"
        @keyup.esc="$emit('update:editable', false)"
        @keyup.exact.enter="onEdit(editedMessage)"
      />
      <p class="time">{{ time }}</p>
    </div>
  </div>
</template>

<style scoped>
.message {
  display: flex;
  position: relative;
  max-width: 26rem;
  width: fit-content;
  margin-top: 1.2rem;
  padding: 0.4rem 0.6rem 0.5rem 0.8rem;
  font-size: 1.4rem;
  border-radius: 0 0.6rem 0.6rem 0.6rem;
  /* @todo #45:15m Replace background-color value with the constant */
  background-color: #f2f2f7;
  overflow-wrap: anywhere;
}

.content {
  padding-bottom: 2rem;
}

.time {
  min-width: fit-content;
  margin-top: auto;
  padding-left: 0.8rem;
  text-align: right;
}

.content-wrapper {
  display: flex;
  margin-top: 0.4rem;
}

.message-right {
  background-color: var(--color-primary);
  color: var(--color-on-primary);
  margin-left: auto;
  border-radius: 0.6rem 0 0.6rem 0.6rem;
}

.message-left {
  flex-direction: column;
}

.message-left::before {
  content: '';
  position: absolute;
  right: 100%;
  top: 0;
  width: 0;
  height: 0;
  border-right: 1.5rem solid #f2f2f7;
  border-bottom: 1.5rem solid transparent;
}

.message-right::after {
  content: '';
  position: absolute;
  left: 100%;
  top: 0;
  width: 0;
  height: 0;
  border-right: 1.5rem solid transparent;
  border-top: 1.5rem solid var(--color-primary);
}
</style>
