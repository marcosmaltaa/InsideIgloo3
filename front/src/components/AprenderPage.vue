<template>
  <div class="aprender-page">
    <h1 class="titulo">
      Kernel Novato
    </h1>

    <div class="cards-container">
      <div
        v-for="card in cards"
        :key="card.id"
        class="card"
        :class="[
          card.bgClass,
          isSalaConcluida(card.id) ? 'card-concluida' : ''
        ]"
        @click="navegarPara(card.id)"
      >
        <img
          :src="card.img"
          :alt="card.titulo"
          class="card-img"
        >
        <h2 class="card-titulo">
          {{ card.titulo }}
        </h2>
        <p
          v-if="card.texto"
          class="card-texto"
          v-html="card.texto"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { store } from "@/store";

export default {
  name: "AprenderPage",
  data() {
    return {
      salasConcluidas: [],
      cards: [
        {
          id: 2,
          titulo: "PRIMEIROS PASSOS",
          img: require("@/assets/tux1.png"),
          texto:
            "Pretende aprender do zero!?<br />que tal entender as distribuições?",
          bgClass: "card-branco",
        },
        {
          id: 3,
          titulo: "MANIPULAÇÃO DE ARQUIVOS/DIRETÓRIOS",
          img: require("@/assets/tux2.png"),
          texto:
            "Pretende aprender do zero!?<br />que tal entender as distribuições?",
          bgClass: "card-branco",
        },
        {
          id: 4,
          titulo: "ALGUNS DETALHES",
          img: require("@/assets/tux3.png"),
          texto: null,
          bgClass: "card-branco",
        },
        {
          id: 5,
          titulo: "SOBRE O USUARIO",
          img: require("@/assets/tux4.png"),
          texto: null,
          bgClass: "card-branco",
        },
      ],
    };
  },
  async mounted() {
    try {
      const response = await axios.get(
        "http://localhost:8080/sala/salaConcluida",
        {
          params: { user: store.id },
          headers: { Authorization: `Bearer ${store.token}` },
        }
      );
      this.salasConcluidas = response.data;
    } catch (error) {
      console.error("Erro ao buscar salas concluídas:", error);
    }
  },
  methods: {
    isSalaConcluida(idSala) {
      return this.salasConcluidas.some(
        (sala) => sala.idSala === idSala && sala.concluido === true
      );
    },
    navegarPara(id) {
      if (id === 2) {
        this.$router.push("/historiaLinux");
      }
    },
  },
};
</script>

<style scoped>
.aprender-page {
  padding: 40px;
  font-family: "Arial", sans-serif;
}

.titulo {
  font-size: 36px;
  font-weight: bold;
  margin-bottom: 40px;
}

.cards-container {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 48px;
  flex-wrap: wrap;
}

.card {
  width: 260px;
  border-radius: 16px;
  padding: 20px;
  text-align: center;
  background-color: #ffffff;
  transition: box-shadow 0.3s ease;
  cursor: pointer;
  height: 290px;
}

.card:hover {
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2);
}

.card-img {
  width: 120px;
  height: 120px;
  object-fit: cover;
  border-radius: 50%;
  margin: 0 auto;
}

.card-titulo {
  font-weight: bold;
  margin-top: 16px;
  font-size: 16px;
}

.card-texto {
  font-size: 14px;
  margin-top: 8px;
  color: #333;
}

.card-verde {
  background-color: #e0f7e9;
}

.card-branco {
  background-color: #ffffff;
}

.card-sem-fundo {
  background-color: transparent;
  border: 2px dashed #ccc;
}

.card-concluida {
  background-color: #10e9c5;
}
</style>
