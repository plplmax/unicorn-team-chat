<script setup>
import MessageItem from '@/components/chat/MessageItem.vue'
</script>

<template>
  <div class="message-container">
    <MessageItem
      author="Janet"
      message="That works. I was actually planning to get a smoothie anyways 👍"
      time="12:03 PM"
    />
    <MessageItem :self="true" author="Maks" message="Agreed" time="11:52 AM" />
    <MessageItem author="Aubrey" message="I was thinking the cafe downtown" time="11:45 AM" />
    <MessageItem :self="true" author="Maks" message="I am down for whatever!" time="11:36 AM" />
    <MessageItem author="Jav" message="I’m down! Any ideas??" time="11:35 AM" />
    <MessageItem :self="true" author="Maks" message="Anyone on for lunch today" time="11:31 AM" />
    <MessageItem :self="true" author="Maks" message="Hi team 👋" time="11:31 AM" />
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
