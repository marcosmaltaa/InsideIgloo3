<template>
  <div class="text-wrapper">
    <h1 class="section-title">
      Red Hat Enterprise
    </h1>

    <p class="text-content">
      O Red Hat Enterprise Linux (RHEL) é uma distribuição comercial mantida e suportada pela Red Hat, voltada principalmente para servidores em empresas de médio e grande porte. É reconhecida por sua estabilidade, segurança e suporte profissional, sendo amplamente utilizada em ambientes corporativos e críticos.
    </p>
    <p class="text-content">
      Um dos grandes diferenciais do RHEL é a existência de uma certificação específica: a RHCE (Red Hat Certified Engineer). Essa certificação é altamente valorizada no mercado de trabalho e atesta conhecimentos avançados na administração de sistemas baseados em RHEL.
    </p>
    <p class="text-content">
      Se você pretende utilizar o Red Hat, é bem provável que o faça no contexto profissional. Ele não é voltado ao uso doméstico ou casual, mas sim para empresas que necessitam de um sistema operacional robusto, confiável e com suporte empresarial. Por isso, se o seu objetivo é trabalhar com servidores e infraestrutura de TI, o RHEL é uma excelente escolha.
    </p>

    <div class="quiz-block">
      <p class="quiz-question">
        🐧 Qual é a certificação oficial voltada para administradores do Red Hat?
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

  if (respostaLimpa === 'rhce') {
    feedback.value = '✅ Correto! A certificação do Red Hat é a RHCE.'
    correta.value = true

    const url = `http://localhost:8080/sala/concluir?user=${store.id}&room=2&pag=6`

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
    feedback.value = '❌ Resposta incorreta. Tente novamente!'
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
