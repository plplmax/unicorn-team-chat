import { createApp } from 'vue'
import App from './App.vue'

import './assets/main.css'
import LoginView from '@/views/LoginView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import ChatView from '@/views/ChatView.vue'
import VueToastificationPlugin from 'vue-toastification'
import axios from '@/axios'

const routes = [
  {
    path: '/',
    component: LoginView,
    beforeEnter: () =>
      axios
        .get('/')
        .then(() => '/chat')
        .catch(() => true)
  },
  {
    path: '/chat',
    component: ChatView,
    beforeEnter: () => {
      if (!localStorage.getItem('token')) return '/'
    }
  }
]

const router = createRouter({ history: createWebHistory(), routes })

const app = createApp(App)

app.use(router)
app.use(VueToastificationPlugin)
app.mount('#app')
