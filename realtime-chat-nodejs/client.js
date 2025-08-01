const WebSocket = require('ws')
const readline  = require('readline')

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    prompt: ''
})

let username
let socket

function setupChat() {
    console.log('type your message (or exit/quit)')

    socket.on('message', (message) => {
        process.stdout.write('\n')

        console.log(message.toString())

        rl.prompt(true)
    })

    rl.on('line', (input) => {
        if(input === 'exit' || input === 'quit') {
            socket.close()
            rl.close()
            return
        }

        const fullMessage = `${username}: ${input}`
        socket.send(fullMessage)
        console.log(`You sent: ${username}: ${input}`)
        rl.prompt(true)
    })

    socket.on('close', () => {
        console.log('\nDisconnected')
        rl.close()
    })

    rl.prompt()
}

rl.question('enter your name: ', (name) => {
    username = name 
    socket = new WebSocket('ws://localhost:7007')

    socket.on('open', () => {
        console.log(`welcome ${username}`)
        setupChat()
    })

    socket.on('error', (err) => {
        console.error('connection error', err)
        rl.close()
    })
})