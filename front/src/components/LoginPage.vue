<template>
  <div class="login-container">
    <form
      class="login-form"
      @submit.prevent="login"
    >
      <h2>LOGIN</h2>

      <label for="username">Email</label>
      <input
        id="username"
        v-model="email"
        type="text"
      >

      <label for="password">Senha</label>
      <input
        id="password"
        v-model="senha"
        type="password"
      >

      <p class="register-link">
        Novo aqui? <a href="/registro">Clique aqui para registrar</a>
      </p>

      <button type="submit">
        Entrar
      </button>

      <p
        v-if="erro"
        style="color: red; text-align: center"
      >
        {{ erro }}
      </p>
    </form>
  </div>
</template>

<script>
import { store } from '../store.js';

export default {
  name: "LoginPage",
  data() {
    return {
      email: "",
      senha: "",
      erro: null,
    };
  },
  methods: {
    async login() {
      this.erro = null;
      try {
        const response = await fetch("http://localhost:8080/login/logar", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ email: this.email, senha: this.senha }),
        });

        if (!response.ok) {
          const mensagem = await response.text();
          this.erro = mensagem || "Falha no login. Verifique suas credenciais.";
          return;
        }

        const data = await response.json();
        const { token, apelido, id } = data;

        store.setUser(apelido, token, id); // <-- aqui está o segredo

        this.$router.push("/");
      } catch (e) {
        this.erro = "Erro ao conectar com o servidor.";
        console.error(e);
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  height: calc(100vh - 70px); /* considerando o header */
  width: 100%;
  justify-content: center;
  align-items: center;
  background: #f0f4f8;
  font-family: sans-serif;
}

.login-form {
  background: white;
  padding: 2rem 3rem;
  border-radius: 10px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  width: 600px;
  height: 500px;
  display: flex;
  flex-direction: column;
}

.login-form h2 {
  margin-bottom: 3.5rem;
  text-align: center;
  color: #2c3e50;
  font-size: 2.5rem; /* aumenta o tamanho do "LOGIN" */
}

.login-form label {
  margin-bottom: 0.5rem;
  color: #34495e;
  font-weight: 600;
}

.login-form input {
  padding: 0.5rem;
  margin-bottom: 2rem; /* mais espaço entre os campos */
  border: 1px solid #ccc;
  border-radius: 25px;
  font-size: 1rem;
  outline-offset: 2px;
  outline-color: #3498db;
}

.login-form input:focus {
  border-color: #3498db;
  outline: 2px solid #3498db;
}

.register-link {
  font-size: 0.95rem;
  color: #34495e;
  margin-bottom: 2rem;
  text-align: center;
}

.register-link a {
  color: #3498db;
  text-decoration: none;
  font-weight: bold;
}

.register-link a:hover {
  text-decoration: underline;
}

.login-form button {
    width: 150px;
  padding: 0.7rem;
  background-color: #3498db;
  color: white;
  border: none;
  font-weight: bold;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: auto; /* empurra o botão pra baixo */
  align-self: center;
}

.login-form button:hover {
  background-color: #2980b9;
}
</style>
