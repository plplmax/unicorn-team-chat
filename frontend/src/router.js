import LoginView from '@/views/LoginView.vue'
import ChatView from '@/views/ChatView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import RegistrationView from "@/views/RegistrationView.vue";

const routes = [
  {
    path: '/',
    component: LoginView,
    beforeEnter: () => (!localStorage.getItem('token') ? true : '/chat')
  },
  {
    path: '/registration',
    component: RegistrationView,
    beforeEnter: () => (!localStorage.getItem('token') ? true : '/chat')
  },
  {
    path: '/chat',
    component: ChatView,
    beforeEnter: () => (!localStorage.getItem('token') ? '/' : true)
  },
  {
    path: '/logout',
    component: LoginView,
    beforeEnter: () => {
      localStorage.clear()
      return '/'
    }
  }
]

export const router = createRouter({ history: createWebHistory(), routes })
