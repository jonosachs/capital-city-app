module.exports = {
  testEnvironment: "node", // or 'jsdom' if DOM APIs in test
  transform: { "^.+\\.m?jsx?$": "babel-jest" }, // transpile ESM → CJS for Jest
};
