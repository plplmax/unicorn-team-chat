import axios from 'axios'
import { router } from '@/router'

const instance = axios.create({ baseURL: 'http://localhost:8080' })

instance.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) config.headers.Authorization = `Bearer ${token}`
  return config
})

instance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response.status === 401) {
      localStorage.clear()
      router.replace('/')
    }
    return error
  }
)

export default instance
