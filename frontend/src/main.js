import { createApp } from 'vue'
import App from './App.vue'

import './assets/main.css'
import VueToastificationPlugin from 'vue-toastification'
import { router } from '@/router'

const app = createApp(App)

app.use(router)
app.use(VueToastificationPlugin)
app.mount('#app')
