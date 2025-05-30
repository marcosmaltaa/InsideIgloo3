import { createApp } from 'vue'
import App from './App.vue'
import router from './router'  // importa seu roteador

const app = createApp(App)

app.use(router)   // registra o router no Vue

app.mount('#app') // monta a aplicação
