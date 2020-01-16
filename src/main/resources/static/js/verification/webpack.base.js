const path = require('path')

module.exports = {
  entry: {
    'jigsaw': './js/verification/jigsaw'
  },
  output: {
    path: path.resolve(__dirname, '../js'),
    filename: '[name].js',
    publicPath: '',
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        include: path.resolve(__dirname, '../js'),
        use: 'babel-loader'
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"]
      }
    ]
  }
}
