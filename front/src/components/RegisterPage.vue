<template>
  <div class="register-container">
    <form
      class="register-form"
      @submit.prevent="handleRegister"
    >
      <h2>REGISTRO</h2>

      <label for="nickname">Apelido</label>
      <input
        id="nickname"
        v-model="nickname"
        type="text"
      >

      <label for="email">Email</label>
      <input
        id="email"
        v-model="email"
        type="email"
      >
      <p
        v-if="emailExists"
        class="error-message"
      >
        Este e-mail já está cadastrado.
      </p>

      <label for="password">Senha</label>
      <input
        id="password"
        v-model="password"
        type="password"
      >

      <label for="confirm-password">Confirme a senha</label>
      <input
        id="confirm-password"
        v-model="confirmPassword"
        type="password"
      >

      <button type="submit">
        Registrar
      </button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "RegisterPage",
  data() {
    return {
      nickname: "",
      email: "",
      password: "",
      confirmPassword: "",
      emailExists: false, // flag para controle da mensagem
    };
  },
  methods: {
    async handleRegister() {
      if (this.password !== this.confirmPassword) {
        alert("As senhas não coincidem!");
        return;
      }

      this.emailExists = false; // resetar aviso

      try {
        const response = await axios.post("http://localhost:8080/login/criar", {
          nome: this.nickname,
          email: this.email,
          senha: this.password,
          confirmarSenha: this.confirmPassword,
        });

        if (response.status === 200) {
          alert("Registrado com sucesso!");
          this.$router.push("/login");
        }
      } catch (error) {
        console.error(error);

        // Verificar se o erro foi devido a email já existente
        if (
          error.response &&
          error.response.data &&
          typeof error.response.data === "string" &&
          error.response.data.toLowerCase().includes("email")
        ) {
          this.emailExists = true;
        } else {
          alert("Erro ao registrar. Verifique os dados e tente novamente.");
        }
      }
    },
  },
};
</script>

<style scoped>
.register-container {
  display: flex;
  height: calc(100vh - 70px);
  width: 100%;
  justify-content: center;
  align-items: center;
  background: #f0f4f8;
  font-family: sans-serif;
}

.register-form {
  background: white;
  padding: 2rem 3rem;
  border-radius: 10px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  width: 600px;
  height: 600px;
  display: flex;
  flex-direction: column;
}

.register-form h2 {
  margin-bottom: 3.5rem;
  text-align: center;
  color: #2c3e50;
  font-size: 2.5rem;
}

.register-form label {
  margin-bottom: 0.5rem;
  color: #34495e;
  font-weight: 600;
}

.register-form input {
  padding: 0.5rem;
  margin-bottom: 1.5rem;
  border: 1px solid #ccc;
  border-radius: 25px;
  font-size: 1rem;
  outline-offset: 2px;
  outline-color: #3498db;
}

.register-form input:focus {
  border-color: #3498db;
  outline: 2px solid #3498db;
}

.register-form button {
  width: 150px;
  padding: 0.7rem;
  background-color: #3498db;
  color: white;
  border: none;
  font-weight: bold;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: auto;
  align-self: center;
}

.register-form button:hover {
  background-color: #2980b9;
}

.error-message {
  color: red;
  font-size: 0.9rem;
  margin-top: -1rem;
  margin-bottom: 1rem;
}
</style>
