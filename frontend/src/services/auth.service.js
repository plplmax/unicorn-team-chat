import axios from '@/axios'

export const authenticate = (username, password) => {
  return axios.post('/auth', { username, password }).then((response) => {
    localStorage.setItem('token', response.data.token)
    return response
  })
}
