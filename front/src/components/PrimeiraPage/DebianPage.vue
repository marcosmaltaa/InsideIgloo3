<template>
  <div class="text-wrapper">
    <h1 class="section-title">
      Debian
    </h1>

    <p class="text-content">
      O Debian é uma distribuição desenvolvida com a colaboração de voluntários do mundo todo, o que reflete seu forte compromisso com os ideais do software livre. Por esse motivo, foi adotado como a distribuição oficial do projeto GNU.
    </p>
    <p class="text-content">
      Antes do lançamento de uma nova versão, o Debian passa por inúmeros testes rigorosos para garantir um alto nível de estabilidade e confiabilidade. Caso um usuário encontre algum problema, ele pode reportá-lo por meio de um sistema de rastreamento de falhas, que encaminha diretamente o relato ao desenvolvedor responsável pela avaliação e correção. Essa base de dados com falhas e sugestões é pública e qualquer pessoa pode acessá-la — basta se cadastrar no sistema.
    </p>
    <p class="text-content">
      Se você está em busca de um sistema operacional extremamente estável e confiável, o Debian é uma das melhores escolhas disponíveis.
    </p>

    <div class="quiz-block">
      <p class="quiz-question">
        🐧 Qual é a principal característica do Debian?
      </p>
      <div class="quiz-input-group">
        <input
          v-model="resposta"
          class="quiz-input"
          type="text"
          placeholder="Digite sua resposta..."
        >
        <button
          class="quiz-button"
          @click="verificarResposta"
        >
          Enviar
        </button>
      </div>
      <p
        v-if="feedback"
        class="quiz-feedback"
        :style="{ color: correta ? 'green' : 'red' }"
      >
        {{ feedback }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { store } from '@/store'

const resposta = ref('')
const feedback = ref('')
const correta = ref(false)

function verificarResposta() {
  const respostaLimpa = resposta.value.toLowerCase().trim()

  if (
    respostaLimpa === 'estabilidade' ||
    respostaLimpa === 'estavel'
  ) {
    feedback.value = 'Correto! O Debian é conhecido por sua estabilidade.'
    correta.value = true

    const url = `http://localhost:8080/sala/concluir?user=${store.id}&room=2&pag=3`

    fetch(url, {
      method: 'PUT',
      headers: {
        Authorization: `Bearer ${store.token}`,
      },
    })
      .then((res) => {
        if (!res.ok) throw new Error('Erro ao concluir página')
        console.log('✔️ Página marcada como concluída.')
      })
      .catch((err) => {
        console.error('Erro na requisição:', err)
      })
  } else {
    feedback.value = 'Resposta incorreta. Dica: começa com "es"...'
    correta.value = false
  }
}
</script>

<style scoped>
.text-content {
  margin-bottom: 1.8em;
}

.quiz-block {
  margin-top: 2em;
}

.quiz-question {
  font-weight: bold;
  margin-bottom: 0.5em;
}

.quiz-input-group {
  display: flex;
  align-items: center;
  gap: 0.5em;
  margin-bottom: 1em;
}

.quiz-input {
  padding: 0.5em;
  font-size: 1em;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 200px;
}

.quiz-button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 0.5em 1.2em;
  font-size: 1em;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.quiz-button:hover {
  background-color: #45a049;
}

.quiz-feedback {
  font-weight: bold;
}
</style>
