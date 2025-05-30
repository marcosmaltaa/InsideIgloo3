<template>
  <div v-if="show" class="modal-overlay">
    <div class="modal-box">
      <button class="modal-close" @click="$emit('close')">×</button>
      <h2 class="modal-title">{{ nome }}</h2>
      <p class="modal-description">{{ descricao }}</p>
    </div>
  </div>
</template>

<script setup>
defineProps({
  show: Boolean,
  nome: String,
  descricao: String
})
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5); /* fundo escuro */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

.modal-box {
  background-color: white;
  border-radius: 12px;
  padding: 30px;
  width: 400px;
  max-width: 90%;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.2);
  position: relative;
}

.modal-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 15px;
}

.modal-description {
  font-size: 16px;
  line-height: 1.5;
}

/* Botão de fechar */
.modal-close {
  position: absolute;
  top: 12px;
  right: 16px;
  background: transparent;
  border: none;
  font-size: 24px;
  font-weight: bold;
  cursor: pointer;
}
</style>
