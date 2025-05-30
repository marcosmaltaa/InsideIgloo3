<template>
  <div class="text-wrapper">
    <h1 class="section-title">
      Sobre as Distribuições
    </h1>

    <p class="text-content">
      O kernel, por si só, não é suficiente para que um sistema operacional funcione completamente — mas, sem dúvida, é a parte mais essencial. O GNU/Linux, na verdade, é um kernel, ou seja, o componente central responsável por intermediar a comunicação entre o hardware e o software.
    </p>
    <p class="text-content">
      Por ser apenas o núcleo do sistema, diversas empresas e comunidades passaram a montar sistemas completos ao redor do kernel Linux, combinando-o com diferentes softwares, ferramentas e interfaces. Esses sistemas completos são conhecidos como distribuições Linux (ou distros). Cada distribuição tem suas características, objetivos e público-alvo específicos — por isso, a escolha da distro ideal pode variar muito de pessoa para pessoa.
    </p>
    <p class="text-content">
      Neste capítulo, faremos um panorama geral das distribuições Linux mais conhecidas. É importante lembrar que não será possível abordar todas, já que existem centenas delas.
    </p>
    <p class="text-content">
      Como a escolha da distribuição é algo bastante pessoal, recomenda-se pesquisar bem antes de adotar uma sugestão feita por outra pessoa. O que funciona bem para alguém pode não se encaixar nas suas necessidades — e o contrário também é verdade.
    </p>

    <div class="quiz-block">
      <p class="quiz-question">
        🐧 Qual é o nome do kernel usado pelas distribuições Linux?
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

  if (respostaLimpa === 'linux') {
    feedback.value = '✅ Correto! O kernel é o Linux.'
    correta.value = true

    const url = `http://localhost:8080/sala/concluir?user=${store.id}&room=2&pag=2`

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
    feedback.value = '❌ Resposta incorreta. Dica: começa com "L"...'
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
