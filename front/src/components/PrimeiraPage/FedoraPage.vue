<template>
  <div class="text-wrapper">
    <h1 class="section-title">
      Fedora
    </h1>

    <p class="text-content">
      O Fedora Linux é uma distribuição de desenvolvimento aberto, patrocinada pela Red Hat e mantida por uma comunidade ativa de colaboradores. Surgiu em 2002, a partir da antiga linha de produtos Red Hat Linux, e desde então se consolidou como uma das distribuições mais modernas e inovadoras do ecossistema Linux.
    </p>
    <p class="text-content">
      Embora seja patrocinado pela Red Hat, o Fedora não é suportado oficialmente como produto empresarial — esse papel é desempenhado pelo Red Hat Enterprise Linux (RHEL). O suporte ao Fedora é oferecido principalmente pela comunidade e por fóruns especializados.
    </p>
    <p class="text-content">
      Uma de suas maiores vantagens é o foco em tecnologia de ponta: o Fedora frequentemente adota rapidamente novos recursos e padrões do mundo Linux, o que o torna uma excelente opção para quem deseja experimentar o que há de mais recente em software livre.
    </p>
    <p class="text-content">
      Se você procura um sistema operacional moderno, seguro, atualizado e com uma forte base comunitária, o Fedora é uma escolha excelente — especialmente para desenvolvedores, estudantes e profissionais de TI que valorizam inovação e liberdade tecnológica.
    </p>

    <div class="quiz-block">
      <p class="quiz-question">
        🐧 Qual é uma das principais características do Fedora?
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

  if (respostaLimpa === 'inovação' || respostaLimpa === 'inovacao') {
    feedback.value = '✅ Correto! O Fedora é conhecido por sua inovação.'
    correta.value = true

    const url = `http://localhost:8080/sala/concluir?user=${store.id}&room=2&pag=7`

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
