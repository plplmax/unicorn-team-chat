import axios from '@/axios'
import jwtDecode from 'jwt-decode'

export const authenticate = (username, password) => {
  return axios.post('/auth', { username, password }).then((response) => {
    localStorage.setItem('token', response.data.token)
    localStorage.setItem('userId', jwtDecode(response.data.token).sub)
    return response
  })
}

export const register = (username, password, confirmedPassword) => {
  return axios.post('/register', { username, password, confirmedPassword })
}
