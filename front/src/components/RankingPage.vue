<template>
  <div class="ranking-page">
    <div class="card-azul">
      <h1 class="titulo">TOP PONTUADORES</h1>
      <div class="ranking-container">
        <img src="../assets/TuxConquista.png" alt="Imagem decorativa" class="imagem-esquerda" />
        <div class="ranking-list">
          <div
            v-for="(usuario, index) in ranking"
            :key="usuario.idUsuario"
            class="ranking-item"
          >
            <span class="posicao">#{{ index + 1 }}</span>
            <span class="nome">{{ usuario.nomeUsuario }}</span>
            <span class="xp">{{ usuario.xp }} XP</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import { onMounted, ref } from "vue";
import { store } from "../store.js";

export default {
  name: "RankingPage",
  setup() {
    const ranking = ref([]);

    onMounted(async () => {
      try {
        const response = await fetch("http://localhost:8080/sala/top10", {
          headers: {
            Authorization: `Bearer ${store.token}`,
          },
        });

        if (!response.ok) {
          throw new Error("Erro ao buscar ranking");
        }

        ranking.value = await response.json();
      } catch (error) {
        console.error("Erro ao carregar ranking:", error.message);
      }
    });

    return { ranking };
  },
};
</script>

<style scoped>
.ranking-page {
  padding: 40px;
  font-family: 'Arial', sans-serif;
  position: relative;
  min-height: 100vh;
}

.titulo {
  font-size: 32px;
  margin-bottom: 30px;
  text-align: center;
  color: white
}

.ranking-container {
  position: relative; /* para posicionar a imagem em relação a este contêiner */
  max-width: 500px;
  margin: 0 auto;
}

.ranking-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
  z-index: 1;
  position: relative;
}

.ranking-item {
  background-color: #f1f5f9;
  border-radius: 12px;
  padding: 16px 24px;
  display: flex;
  justify-content: space-between;
  font-weight: bold;
  font-size: 18px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
}

.posicao {
  color: #3b82f6;
  width: 60px;
}

.nome {
  flex: 1;
  text-align: left;
}

.xp {
  color: #10b981;
}

.imagem-esquerda {
  position: absolute;
  top: 540px; /* altura em relação ao topo da lista */
  left: -120px; /* mais próximo da lista */
  width: 180px;
  height: auto;
  z-index: 2; /* atrás da lista */
  pointer-events: none;
}

.card-azul {
  background-color: #60a5fa; /* azul */
  padding: 2rem;
  border-radius: 16px;
  max-width: 750px;
  margin: 0 auto;
}

</style>
