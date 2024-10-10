var fs = require('fs');
const { spawn } = require('child_process');

executeCommand = async(argv) => {
    if (argv.slice(2).join(' ')?.includes('|')) {
        return  handleCombinedCommands(argv.slice(2).join(' '));
    }
    const filePath = argv[2]?.length > 2 ? argv[2] : argv[3];
    const content =  await readFile(filePath, (err, data) => {
        if (err) {
            return 0;
        }
        return data;
    });
    return executeSingleCommand(argv[2], content, filePath);
}

executeSingleCommand = async (argv1, content, argv2) => {
    switch(argv1) {
        case '-c':
            {
                const fileSize = fileSizeInBytes(content);
                console.log(`${fileSize} ${argv2}`);
            }
            break;
        case '-l':
            {
                const lines = await lineCount(content);
                console.log(`${lines} ${argv2}`);
            }
            break;
        case '-w':
            {
                const words = await wordCount(content);
                console.log(`${words} ${argv2}`);
            }
            break;
        case '-m':
            {
                const chars = await charCount(content);
                console.log(`${chars} ${argv2}`);
            }
            break;
        default:
            {
                const fileSize = fileSizeInBytes(content);
                const lines = await lineCount(content);
                const words = await wordCount(content);
                console.log(`${lines} ${words} ${fileSize} ${argv1}`)
        }
    }
}

fileSizeInBytes = (content) => Buffer.byteLength(content, 'utf8');

lineCount = async (content) => {
    return content?.split('\n')?.length;
};

wordCount = async(content) => {
    return content?.trim().split(/\s+/)?.length
}

charCount = async(content) => {
    return Array.from(content)?.length;
}

handleCombinedCommands = async(command) => {
    const parts = command?.split('|').map((part) => part.trim());
    if (parts.length !== 2) {
        console.error('This script currently supports only one pipe operation.');
        process.exit(1);
    }
    const [cmd1, cmd2] = parts.map((part) => part.split(' '));
    const firstCmd = spawn(cmd1[0], cmd1.slice(1));
    let content = '';
    firstCmd.stdout.on('data', (data) => {
        content += data.toString();
    });
    firstCmd.on('close', async (code) => {
        if (code !== 0) {
            console.error(`${cmd1[0]} process exited with code ${code}`);
            return;
        }
        await executeSingleCommand(cmd2[0], content, cmd1[1]);
    });
}

readFile = async(filepath, callback) => {
    return fs.readFileSync(filepath, 'utf-8', (err, data) => {
        if (err) {
            callback(err, null);
            return;
        }
        callback(null, data);
    });
}

module.exports = executeCommand;