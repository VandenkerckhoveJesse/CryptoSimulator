const path = require('path');
module.exports = {
    outputDir: path.resolve(__dirname, '../src/main/resources/static'),
    indexPath: '../templates/home.html',
    configureWebpack : {
        name: 'test',
        output: {

        }
    }
}