import axios from '@/axios'

export const getMessages = () => {
  return axios.get('/message').then((response) => {
    return response.data.map((message) => {
      const date = new Date(message.date)
      const zonedDate = new Date(date.getTime() - date.getTimezoneOffset() * 60 * 1000)
      const dateString = zonedDate.toLocaleTimeString('en-us', {
        hour: '2-digit',
        minute: '2-digit'
      })
      return { ...message, date: dateString }
    })
  })
}
