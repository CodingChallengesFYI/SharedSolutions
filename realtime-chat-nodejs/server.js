// server.js
const WebSocket = require('ws')

const server = new WebSocket.Server({port: 7007})
const clients = new Set()

server.on('connection', (socket) => {
    console.log('new client connected')
    clients.add(socket)

    socket.send('welcome to chatroom')

    socket.on('message', (message) => {
        console.log(`Received: ${message}`)

        clients.forEach((client) => {
            if(client !== socket && client.readyState === WebSocket.OPEN) {
                client.send(message.toString())
            }
        }) 

        // socket.send(`Echo: ${message}`)
    })
    socket.on('close', () => {
        console.log('client disconnected')
        clients.delete(socket)
    })
})

console.log('Chat server running on ws://localhost:/007')