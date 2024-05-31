var executeCommand = require('./wc');

const main = async () => {
    if (process.argv.length < 3) {
        console.log('Please input commands');
        return;
    }
    await executeCommand(process.argv);
};

main();