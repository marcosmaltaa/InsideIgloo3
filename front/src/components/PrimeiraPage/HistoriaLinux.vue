<template>
  <div class="text-wrapper">
    <h1 class="section-title">
      História do Linux
    </h1>
    <p class="text-content">
      Tudo começou em 1969, com a criação do sistema UNIX, desenvolvido por Ken Thompson e Dennis Ritchie nos laboratórios AT&T Bell. Devido à sua alta disponibilidade e portabilidade, o UNIX rapidamente se espalhou por diversas universidades, onde passou por inúmeras modificações e aprimoramentos.
    </p>
    <p class="text-content">
      Em 1983, Richard Stallman deu início ao projeto GNU (GNU's Not Unix), com o objetivo de criar um sistema operacional completamente gratuito e livre. Dessa iniciativa nasceu a GNU General Public License (GPL), uma licença que garante liberdade de uso, modificação e distribuição do software. Até 1990, o projeto GNU estava praticamente completo, exceto por um componente essencial: o kernel, que ainda não havia despertado o interesse dos desenvolvedores.
    </p>
    <p class="text-content">
      Foi então que, em 1991, surgiu o kernel Linux, criado por Linus Torvalds, um estudante universitário na época. Linus queria aprender mais sobre sistemas operacionais, então decidiu desenvolver seu próprio kernel inspirado no UNIX. Ele compartilhou sua ideia em fóruns online da época, recebendo grande apoio e colaboração da comunidade. O nome "Linux" surgiu da junção entre seu nome e o sistema UNIX, que serviu de base para o projeto.
    </p>
    <p class="text-content">
      Com o tempo, o kernel Linux foi integrado ao restante do projeto GNU, dando origem ao que hoje conhecemos como o sistema operacional GNU/Linux. Em 1994, foi lançada oficialmente a versão 1.0 do kernel Linux, marcando o início de uma nova era no mundo do software livre.
    </p>

    <div class="quiz-block">
      <p class="quiz-question">
        📅 Em qual ano foi lançado o Linux?
      </p>
      <div class="quiz-input-group">
        <input
          v-model="resposta"
          class="quiz-input"
          type="text"
          placeholder="Digite o ano..."
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
      >
        {{ feedback }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { store } from '@/store' // ajuste conforme seu caminho

const resposta = ref('')
const feedback = ref('')

function verificarResposta() {
  const respostaLimpa = resposta.value.trim()
  if (respostaLimpa === '1991') {
    feedback.value = '✅ Correto!'

    // Requisição com token
    const url = `http://localhost:8080/sala/concluir?user=${store.id}&room=2&pag=1`

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
