<template>
  <div class="text-wrapper">
    <h1 class="section-title">
      Arch Linux
    </h1>

    <p class="text-content">
      O Arch Linux é uma distribuição leve, flexível e totalmente conduzida pela comunidade. Seu principal foco está na simplicidade, no controle total do sistema e no princípio KISS (Keep It Simple, Stupid), que valoriza soluções diretas e funcionais.
    </p>
    <p class="text-content">
      O Arch adota o modelo de atualização rolling release, o que significa que ele está sempre atualizado — sem a necessidade de reinstalar novas versões do sistema. No entanto, isso também exige que o usuário esteja atento às mudanças e mantenha o sistema com regularidade.
    </p>
    <p class="text-content">
      Ao contrário de distribuições que vêm prontas para uso, no Arch você começa com um sistema básico e precisa “sujar as mãos” para configurar tudo manualmente: desde o particionamento do disco até a escolha do ambiente gráfico e pacotes essenciais. Isso proporciona uma curva de aprendizado mais acentuada, mas também uma excelente oportunidade para quem deseja entender a fundo o funcionamento do Linux.
    </p>
    <p class="text-content">
      Se você está em busca de um desafio, quer aprender profundamente sobre o sistema e deseja montar um ambiente exatamente do seu jeito, o Arch Linux é uma escolha ideal.
    </p>

    <div class="quiz-block">
      <p class="quiz-question">
        🐧 Qual princípio o Arch Linux segue para manter soluções diretas e funcionais?
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
  <!-- ✅ Aqui está o modal -->
  <ModalConcluido
    :show="mostrarModal"
    :nome= "nomeMedalha"
    :descricao="descricaoMedalha"
    @close="fecharModal"
  />
</template>

<script setup>
import { ref } from 'vue'
import { store } from '@/store'
import ModalConcluido from '../Modais/ModalConcluido.vue'

const resposta = ref('')
const feedback = ref('')
const correta = ref(false)

const mostrarModal = ref(false)
const nomeMedalha = ref('')
const descricaoMedalha = ref('')

function verificarResposta() {
  const respostaLimpa = resposta.value.toLowerCase().trim()

  if (respostaLimpa === 'simplicidade') {
    feedback.value = '✅ Correto! O Arch Linux segue o princípio da simplicidade.'
    correta.value = true

    const urlConcluir = `http://localhost:8080/sala/concluir?user=${store.id}&room=2&pag=8`

    fetch(urlConcluir, {
      method: 'PUT',
      headers: {
        Authorization: `Bearer ${store.token}`,
      },
    })
      .then(res => res.json())
      .then(resultado => {
        if (resultado === true) {
          const urlMedalha = `http://localhost:8080/medalha/darMedalha?user=${store.id}&medal=1`

          fetch(urlMedalha, {
            method: 'POST',
            headers: {
              Authorization: `Bearer ${store.token}`,
            },
          })
            .then(res => res.json())
            .then(dados => {
              nomeMedalha.value = dados.nomeMedalha
              descricaoMedalha.value = dados.descricaoMedalha
              mostrarModal.value = true
            })
        }
      })
      .catch((err) => {
        console.error('Erro:', err)
      })
  } else {
    feedback.value = '❌ Resposta incorreta. Tente novamente!'
    correta.value = false
  }
}

function fecharModal() {
  mostrarModal.value = false
}
</script>

<style scoped>
.text-content {
  margin-bottom: 1.8em;
}
.text-wrapper {
  text-align: left;
  width: 65%;
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
  flex-wrap: wrap;
  gap: 0.5em;
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
