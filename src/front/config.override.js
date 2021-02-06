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
  ),
  devServer: (configFunction) => {
    return (_proxy, allowdHost) => {
      const proxy = [
        ..._proxy,
        {
          target: 'http://localhost:8080',
          context: ['/api']
        }
      ]
      const config = configFunction(proxy, allowdHost);
      return config;
    }
  }
}