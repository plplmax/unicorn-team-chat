import LoginView from '@/views/LoginView.vue'
import ChatView from '@/views/ChatView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: LoginView,
    beforeEnter: () => (!localStorage.getItem('token') ? true : '/chat')
  },
  {
    path: '/chat',
    component: ChatView,
    beforeEnter: () => (!localStorage.getItem('token') ? '/' : true)
  }
]

export const router = createRouter({ history: createWebHistory(), routes })
