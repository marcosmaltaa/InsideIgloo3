<template>
  <div class="text-wrapper">
    <h1 class="section-title">
      openSUSE
    </h1>

    <p class="text-content">
      O openSUSE é uma distribuição Linux estável, robusta e voltada tanto para usuários comuns quanto para desenvolvedores e administradores de sistemas. É mantida pela comunidade openSUSE com o apoio da empresa SUSE, uma das mais antigas no mundo Linux corporativo.
    </p>
    <p class="text-content">
      O openSUSE também é conhecido por ferramentas poderosas como o YaST (Yet another Setup Tool), um centro de controle completo que facilita a administração do sistema, instalação de pacotes, gerenciamento de serviços, configuração de rede e muito mais — tudo através de uma interface gráfica intuitiva.
    </p>
    <p class="text-content">
      Se você procura uma distribuição madura, estável e com recursos avançados de gerenciamento, o openSUSE é uma ótima opção a se considerar.
    </p>

    <div class="quiz-block">
      <p class="quiz-question">
        🐧 Qual empresa apoia oficialmente o projeto openSUSE?
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

  if (respostaLimpa === 'suse') {
    feedback.value = '✅ Correto! A empresa que apoia o openSUSE é a SUSE.'
    correta.value = true

    const url = `http://localhost:8080/sala/concluir?user=${store.id}&room=2&pag=5`

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
