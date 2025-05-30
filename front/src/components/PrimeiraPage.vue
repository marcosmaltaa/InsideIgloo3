<template>
  <div class="container">
    <!-- Sidebar -->
    <div class="sidebar">
      <h2 class="sidebar-title">
        PRIMEIROS PASSOS
      </h2>
      <button
        v-for="tab in tabs"
        :key="tab.key"
        :class="['tab-button', selectedTab === tab.key ? 'active' : '']"
        @click="selectedTab = tab.key"
      >
        {{ tab.label }}
      </button>
    </div>

    <!-- Conteúdo -->
    <div class="content">
      <!-- Usa o componente dinamicamente -->
      <component :is="currentComponent" />
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import HistoriaLinux from './PrimeiraPage/HistoriaLinux.vue'
import DistroLinux from './PrimeiraPage/SobreDistro.vue'
import DebianPage from './PrimeiraPage/DebianPage.vue'
import UbuntuPage from './PrimeiraPage/UbuntuPage.vue'
import OpenSusePage from './PrimeiraPage/OpenSusePage.vue'
import RedHatPage from './PrimeiraPage/RedHatPage.vue'
import FedoraPage from './PrimeiraPage/FedoraPage.vue'
import ArchPage from './PrimeiraPage/ArchPage.vue'

const selectedTab = ref('historia')

const tabs = [
  { key: 'historia', label: 'História do Linux' },
  { key: 'distribuicoes', label: 'Sobre as distribuições' },
  { key: 'debian', label: 'Debian' },
  { key: 'ubuntu', label: 'Ubuntu' },
  { key: 'suse', label: 'SuSE' },
  { key: 'redhat', label: 'Red Hat' },
  { key: 'fedora', label: 'Fedora' },
  { key: 'arch', label: 'Arch Linux' },
]

// Componentes simples (template string)

const Fallback = { template: `<div><h1 class="section-title">Conteúdo não disponível</h1><p class="text-content">Selecione uma aba válida.</p></div>` }

const tabsMap = {
  historia: HistoriaLinux,
  distribuicoes: DistroLinux,
  debian: DebianPage,
  ubuntu: UbuntuPage,
  suse: OpenSusePage,
  redhat: RedHatPage,
  fedora: FedoraPage,
  arch: ArchPage,
}

const currentComponent = computed(() => {
  return tabsMap[selectedTab.value] || Fallback
})
</script>

<style scoped>
.container {
  display: flex;
  height: 100vh;
  background-color: #f3f4f6;
  color: #333;
  font-family: Arial, sans-serif;
  overflow: hidden;
}

/* Sidebar */
.sidebar {
  width: 240px;
  background-color: #60a5fa;
  color: white;
  padding: 20px;
  box-sizing: border-box;
  overflow-y: auto;
}

.sidebar-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
}

.tab-button {
  display: block;
  width: 100%;
  padding: 10px 14px;
  margin-bottom: 8px;
  background-color: transparent;
  border: none;
  border-radius: 6px;
  text-align: left;
  color: white;
  font-size: 15px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.tab-button:hover {
  background-color: #437abe;
}

.tab-button.active {
  background-color: white;
  color: #7090e6;
  font-weight: bold;
}

/* Conteúdo */
.content {
  flex: 1;
  padding: 30px;
  overflow-y: auto;
  box-sizing: border-box;
  display: flex;
  justify-content: flex-start; /* alinha os elementos à esquerda */
}
.text-wrapper {
  width: 65%;
  text-align: left;
}

.section-title {
  font-size: 26px;
  font-weight: bold;
  margin-bottom: 20px;
  width: 100%;
  max-width: 60ch;
}

.text-content {
  text-align: left;
  line-height: 1.6;
  margin-bottom: 1em;
  width: 100%;
  max-width: 60ch; /* ideal para leitura (~60 caracteres por linha) */
}

/* Scrollbar */
::-webkit-scrollbar {
  width: 8px;
}
::-webkit-scrollbar-thumb {
  background-color: #568bff;
  border-radius: 10px;
}

.quiz-block {
  margin-top: 30px;
  padding: 20px;
  background-color: #e0e7ff;
  border-radius: 10px;
  max-width: 60ch;
}

.quiz-question {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.quiz-input {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #b3b3b3;
  border-radius: 6px;
  margin-bottom: 10px;
}

.quiz-button {
  padding: 8px 16px;
  font-size: 15px;
  background-color: #4f46e5;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.quiz-button:hover {
  background-color: #3730a3;
}

.quiz-feedback {
  margin-top: 10px;
  font-weight: bold;
}

</style>
