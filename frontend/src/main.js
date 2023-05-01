import { createApp } from 'vue'
import App from './App.vue'

import './assets/main.css'
import LoginView from '@/views/LoginView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import ChatView from '@/views/ChatView.vue'
import VueToastificationPlugin from 'vue-toastification'

const routes = [
  { path: '/', component: LoginView },
  { path: '/chat', component: ChatView }
]

const router = createRouter({ history: createWebHistory(), routes })

const app = createApp(App)

app.use(router)
app.use(VueToastificationPlugin)
app.mount('#app')
