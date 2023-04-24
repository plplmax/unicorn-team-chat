<script setup>
import MessageItem from '@/components/chat/MessageItem.vue'
import { onBeforeMount, ref } from 'vue'
import * as messageService from '@/services/message.service'

const messages = ref([])

// @todo #46:15m Implement error handling when the fetching messages failed.
//  It's recommended to add the ability to retry request by the user.
onBeforeMount(() => {
  messageService.getMessages().then((response) => (messages.value = response))
})
</script>

<template>
  <div class="message-container">
    <MessageItem
      v-for="message in messages"
      :key="message.id"
      :author="message.sender"
      :message="message.message"
      :time="message.date"
    />
  </div>
</template>

<style scoped>
.message-container {
  display: flex;
  flex-direction: column-reverse;
  max-width: 60rem;
  height: calc(100vh - 6.65rem);
  margin: auto;
  padding: 2.5rem;
  overflow: auto;
  box-shadow: 0 0 5rem #e5e5ea;
}
</style>
