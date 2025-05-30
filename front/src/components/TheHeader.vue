<template>
  <header class="navbar">
    <!-- Div vazia para ocupar a esquerda e permitir centralização -->
    <div class="nav-spacer" />

    <!-- Links centralizados -->
    <div class="nav-center">
      <router-link to="/">
        Home
      </router-link>
      <template v-if="store.apelido">
        <router-link to="/aprender">
          Aprender
        </router-link>
        <router-link to="#">
          Praticar
        </router-link>
        <router-link to="/ranking">
          Ranking
        </router-link>
      </template>
    </div>

    <!-- Usuário na direita -->
    <div class="nav-right">
      <router-link
        v-if="store.apelido"
        to="/perfil"
        class="user-link"
      >
        Olá, {{ store.apelido }}
      </router-link>
      <router-link
        v-else
        to="/login"
      >
        Login/Registro
      </router-link>
    </div>
  </header>
</template>

<script>
import { store } from '../store.js';

export default {
  name: "TheHeader",
  setup() {
    return { store };
  },
};
</script>

<style scoped>
.navbar {
  background-color: #60a5fa;
  color: black;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.8rem 4rem;
  font-weight: bold;
  height: 70px;
  position: relative;
}

/* Espaço à esquerda para alinhar o centro corretamente */
.nav-spacer {
  width: 150px; /* mesmo valor da .nav-right para balancear */
}

/* Links centralizados */
.nav-center {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 1.5rem;
}

/* Área direita (login ou perfil) */
.nav-right {
  width: 150px;
  display: flex;
  justify-content: flex-end;
}

/* Estilização dos links */
.navbar a {
  text-decoration: none;
  color: black;
  padding: 0.4rem 0.8rem;
  border-radius: 4px;
  font-weight: bold;
}

.navbar a:hover {
  color: white;
}
</style>
