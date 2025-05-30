module.exports = {
  env: {
    browser: true,
    es2021: true,
    node: true,
  },
  extends: [
    'eslint:recommended',
    'plugin:vue/vue3-recommended'
  ],
  parser: 'vue-eslint-parser', // parser para Vue
  parserOptions: {
    parser: '@babel/eslint-parser', // parser JS que entende import/export
    ecmaVersion: 2021,
    sourceType: 'module',
    requireConfigFile: false // para @babel/eslint-parser não precisar de babel.config.js
  },
  globals: {
    defineProps: 'readonly',
    defineEmits: 'readonly',
    defineExpose: 'readonly',
    withDefaults: 'readonly',
  },
  rules: {
    // suas regras personalizadas aqui
  }
}
