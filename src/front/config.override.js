const {
  override,
  addWebpackAlias,
  addLessLoader,
  addBabelPlugin
} = require("customize-cra");
const path = require("path");

module.exports = {
  webpack: override(
    addWebpackAlias({
      ['@src']: path.resolve(__dirname, 'src')
    }),
    addBabelPlugin(['import', { libraryName: 'antd', libraryDirectory: 'es', style: true }]),
    addLessLoader({
      modifyVars: {
        'primary-color': '#04c584',
      },
      javascriptEnabled: true
    })
  )
}