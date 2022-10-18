const path = require('path');
module.exports = {
  outputDir: path.resolve(__dirname, '../src/main/resources/static'),
  indexPath: '../templates/home.html',
  configureWebpack : {
    name: 'test',
    output: {

    }
  },
  devServer: {
    open: process.platform === 'darwin',
    host: '0.0.0.0',
    port: 8084, // CHANGE YOUR PORT HERE!
    https: false,
  }
}