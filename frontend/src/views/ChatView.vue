<script setup>
import { onBeforeMount, onBeforeUnmount, onMounted, onUnmounted, ref } from 'vue'
import * as messageService from '@/services/message.service'
import MessageItem from '@/components/chat/MessageItem.vue'
import MessageInput from '@/components/chat/MessageInput.vue'
import { useToast } from 'vue-toastification'
import 'vue-toastification/dist/index.css'

const toaster = useToast()
const userId = parseInt(localStorage.getItem('userId'))

const messages = ref([])
const messageForSending = ref('')
const currentEditingMessage = ref(0)

const showMenu = ref(false)
const position = ref({ left: 0, top: 0 })

const sendMessage = () => {
  const message = messageForSending.value.trim()
  if (!message) return
  const added = messageService.add(message)
  if (added) messageForSending.value = ''
}

const updateMessage = (message) => {
  const trimmed = message.trim()
  if (!trimmed) return
  const updated = messageService.update(currentEditingMessage.value, trimmed)
  if (updated) stopEditingMessage()
}

const startEditingMessage = () => {
  messages.value.find((value) => value.id === currentEditingMessage.value).editable = true
}

const stopEditingMessage = () => {
  messages.value.find((value) => value.id === currentEditingMessage.value).editable = false
}
const showContextMenu = (event, messageId, self) => {
  if (!self) return
  currentEditingMessage.value = messageId
  position.value.left = event.clientX
  position.value.top = event.clientY - 50
  showMenu.value = true
}

const hideContextMenu = () => (showMenu.value = false)

onBeforeMount(() => {
  messageService
    .getMessages()
    .then((response) => (messages.value = response))
    .then(() => {
      const token = localStorage.getItem('token')
      messageService.connect(
        token,
        (message) => messages.value.unshift(message),
        (message) => {
          const index = messages.value.findIndex((value) => value.id === message.id)
          messages.value.splice(index, 1, message)
        }
      )
    })
    .catch(() => toaster.error('Cannot load history. Reload the page'))
})
onMounted(() => document.addEventListener('click', hideContextMenu))
onBeforeUnmount(() => messageService.disconnect())
onUnmounted(() => document.removeEventListener('click', hideContextMenu))
</script>

<template>
  <div class="message-container">
    <MessageItem
      v-for="message in messages"
      :key="message.id"
      v-model:editable="message.editable"
      :author="message.sender"
      :message="message.message"
      :on-edit="updateMessage"
      :self="message.senderId === userId"
      :time="message.date"
      @contextmenu.prevent="showContextMenu($event, message.id, message.senderId === userId)"
    />
    <MessageInput v-model="messageForSending" :send="sendMessage" class="message-input" />
  </div>
  <ul
    v-show="showMenu"
    id="myContextMenu"
    :style="{ left: position.left + 'px', top: position.top + 'px' }"
    class="context-menu"
  >
    <li @click="startEditingMessage()">Edit</li>
    <li>Delete</li>
  </ul>
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

.context-menu {
  position: absolute;
  background-color: white;
  list-style: none;
  box-shadow: 0 0 5rem #e5e5ea;
  z-index: 100;
  font-size: 1.8rem;
}

ul {
  padding: 0;
}

li {
  padding: 5px 10px;
}

li:hover {
  background-color: #e5e5ea;
}

.context-menu li:hover {
  cursor: pointer;
}
</style>