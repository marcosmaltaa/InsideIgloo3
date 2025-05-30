<template>
  <div class="profile-wrapper">
    <div class="profile-card">
      <h1>Bem-vindo, {{ store.apelido }}!</h1>

      <div class="profile-info">
        <img class="profile-photo" src="../assets/tux1-1.png" alt="Foto de perfil" />
        <div class="user-details">
          <p><strong>Usuário:</strong> {{ store.apelido }}</p>
          <p><strong>ID:</strong> {{ store.id }}</p>
        </div>
      </div>

      <div class="section">
        <h2>Conquistas</h2>
        <div v-if="medalhas.length > 0" class="medal-list">
          <div v-for="medalha in medalhas" :key="medalha.id" class="medal">
            <img
              :src="medalhas.length === 1 ? require('../assets/medalha1png.png') : medalha.imagem"
              alt="Medalha"
              class="medal-img"
            />
            <p>{{ medalha.nomeMedalha }}</p>
          </div>
        </div>
        <p v-else class="empty-msg">Nenhuma medalha conquistada ainda.</p>
      </div>

      <div class="section">
        <h2>Ranking</h2>
        <div v-if="meuRanking" class="ranking-info">
          <p>
            <strong>Ranking: </strong>
            <span v-if="meuRanking.rank === 1">🥇 # 1</span>
            <span v-else-if="meuRanking.rank === 2">🥈 # 2</span>
            <span v-else-if="meuRanking.rank === 3">🥉 # 3</span>
            <span v-else># {{ meuRanking.rank }}</span>
          </p>
          <p><strong>XP:</strong> {{ meuRanking.xp }}</p>
        </div>
        <p v-else class="empty-msg">Carregando ranking...</p>
      </div>

      <button @click="logout">Sair</button>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { store } from "../store.js";

export default {
  name: "ProfilePage",
  setup() {
    const router = useRouter();
    const medalhas = ref([]);
    const meuRanking = ref(null);

    function logout() {
      localStorage.removeItem("apelido");
      store.apelido = null;
      router.push("/login");
    }

    onMounted(() => {
      // Carregar medalhas
      fetch(`http://localhost:8080/medalha/medalhasUsuario?user=${store.id}`, {
        headers: {
          Authorization: `Bearer ${store.token}`,
        },
      })
        .then((res) => res.json())
        .then((data) => {
          medalhas.value = data;
        })
        .catch((err) => {
          console.error("Erro ao carregar medalhas:", err);
        });

      // Carregar ranking
      fetch(`http://localhost:8080/sala/rankingUsuario?user=${store.id}`, {
        headers: {
          Authorization: `Bearer ${store.token}`,
        },
      })
        .then((res) => res.json())
        .then((data) => {
          meuRanking.value = data;
        })
        .catch((err) => {
          console.error("Erro ao carregar ranking:", err);
        });
    });

    return { store, logout, medalhas, meuRanking };
  },
};
</script>

<style scoped>
.profile-wrapper {
  display: flex;
  justify-content: center;
  padding: 2rem;
  background-color: #f0f4f8;
  min-height: 100vh;
}

.profile-card {
  background-color: #60a5fa;
  color: white;
  border-radius: 16px;
  padding: 2rem;
  width: 50%;
  max-width: 700px;
  height: 50%;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

h1 {
  margin-bottom: 1.5rem;
  text-align: center;
}

.profile-info {
  display: flex;
  align-items: center;
  gap: 1.2rem;
  margin-bottom: 2rem;
  background-color: rgba(255, 255, 255, 0.1);
  padding: 1rem;
  border-radius: 12px;
}

.profile-photo {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid white;
}

.user-details p {
  margin: 0.3rem 0;
}

.section {
  margin-top: 2rem;
}

.medal-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 1rem;
  margin-top: 1rem;
  background-color: #4f97f0;
  border-radius: 20px;
}

.medal {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0.8rem;
  border-radius: 10px;
  width: 200px;
}

.medal-img {
  width: 40px;
  height: 60px;
  margin-bottom: 0.5rem;
}

.ranking-info {
  background-color: rgba(255, 255, 255, 0.1);
  padding: 1rem;
  border-radius: 10px;
  margin-top: 1rem;
}

.empty-msg {
  font-style: italic;
  color: #d1d5db;
}

button {
  margin-top: 2rem;
  padding: 0.6rem 1.5rem;
  background-color: #ffffff;
  color: rgb(0, 0, 0);
  border: none;
  border-radius: 8px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.2s ease;
  width: 100%;
}

button:hover {
  background-color: #ffe7e7;
  color: black
}
</style>
