<script setup>
import { onBeforeMount, onBeforeUnmount, ref } from 'vue'
import * as messageService from '@/services/message.service'
import MessageItem from '@/components/chat/MessageItem.vue'
import MessageInput from '@/components/chat/MessageInput.vue'
import { useToast } from 'vue-toastification'
import 'vue-toastification/dist/index.css'

const toaster = useToast()
const messages = ref([])
const messageForSending = ref('')
const userId = parseInt(localStorage.getItem('userId'))
const sendMessage = () => {
  const message = messageForSending.value.trim()
  if (!message) return
  const added = messageService.add(message)
  if (added) messageForSending.value = ''
}

onBeforeMount(() => {
  messageService
    .getMessages()
    .then((response) => (messages.value = response))
    .catch(() => toaster.error('Cannot load history. Reload the page'))
    .then(() => {
      const token = localStorage.getItem('token')
      messageService.connect(token, (message) => messages.value.unshift(message))
    })
})
onBeforeUnmount(() => messageService.disconnect())
</script>

<template>
  <div class="message-container">
    <MessageItem
      v-for="message in messages"
      :key="message.id"
      :author="message.sender"
      :message="message.message"
      :self="message.senderId === userId"
      :time="message.date"
    />
    <MessageInput v-model="messageForSending" :send="sendMessage" class="message-input" />
  </div>
</template>

<style scoped>
.message-container {
  display: flex;
  flex-direction: column-reverse;
  max-width: 60rem;
  height: calc(100vh - 6.65rem);
  margin: auto;
  padding: 2.5rem 2.5rem 5.5rem 2.5rem;
  overflow: auto;
  box-shadow: 0 0 5rem #e5e5ea;
}

.message-input {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
}
</style>
