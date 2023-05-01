import axios from '@/axios'

let socket
let timeoutId

export const connect = (token, added, edited, deleted) => {
  if (socket) return
  socket = new WebSocket('ws://localhost:8080/message')
  socket.addEventListener('open', () => {
    console.log('WebSocket connection opened')
    socket.send(token)
  })
  socket.addEventListener('message', (event) => {
    console.log('Received message:', event.data)
    const parsedData = JSON.parse(event.data)
    const type = parsedData.type
    if (type === 'add') {
      added(toMessage(parsedData.data))
    } else if (type === 'edit') {
      edited(toMessage(parsedData.data))
    } else {
      console.error('undefined type of message: ' + type)
    }
  })
  socket.addEventListener('close', () => {
    console.log('WebSocket connection closed')
    if (!socket) return
    socket = null
    timeoutId = setTimeout(() => {
      console.log('Reconnecting...')
      connect(token, added, edited, deleted)
    }, 5000)
  })
}

export const add = (message) => {
  if (!socket || socket.readyState !== WebSocket.OPEN) return false
  socket.send(JSON.stringify({ type: 'add', message }))
  return true
}

export const update = (id, message) => {
  if (!socket || socket.readyState !== WebSocket.OPEN) return false
  socket.send(JSON.stringify({ type: 'edit', id, message }))
  return true
}

export const disconnect = () => {
  console.log('Disconnect initiated by the user')
  if (socket) socket.close()
  socket = null
  clearTimeout(timeoutId)
}

export const getMessages = () => {
  return axios.get('/message').then((response) => {
    return response.data.map((response) => toMessage(response))
  })
}

export const toZonedTime = (dateString) => {
  const date = new Date(dateString)
  const zonedDate = new Date(date.getTime() - date.getTimezoneOffset() * 60 * 1000)
  return zonedDate.toLocaleTimeString('en-us', {
    hour: '2-digit',
    minute: '2-digit'
  })
}

const toMessage = (response) => ({
  ...response,
  editable: false,
  date: toZonedTime(response.date)
})
