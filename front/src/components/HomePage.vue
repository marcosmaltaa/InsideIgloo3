<template>
  <div class="container">
    <!-- Conteúdo principal -->
    <section class="hero">
      <div class="hero-text">
        <h1>
          Tem dúvidas <span class="highlight">sobre utilizar comandos do shell?</span><br>
          Quer aprender Linux <span class="highlight">sem fazer bagunça no seu sistema?</span>
        </h1>
      </div>
      <div class="hero-image">
        <img
          src="@/assets/blue-cat.png"
          alt="Mascote"
        >
      </div>
    </section>

    <!-- Footer -->
    <footer class="footer">
      <div class="footer-column">
        <h2>Guias úteis</h2>
        <ul>
          <li><a href="#">GuiaFoca</a></li>
          <li><a href="#">Vídeo tutorial</a></li>
          <li><a href="#">História do Linux</a></li>
          <li><a href="#">Linux Journey</a></li>
          <li><a href="#">Viva o Linux</a></li>
        </ul>
      </div>

      <div class="footer-column about">
        <h2>Sobre o projeto</h2>
        <p>
          Bem-vindo ao “InsideTheIgloo”, um trabalho de conclusão de curso da UNIPAC Barbacena, desenvolvido por Marcos André da Silva Malta. Nosso objetivo é ensinar Linux através da gamificação, tornando o aprendizado mais interativo e envolvente.
        </p>
        <p>
          Acreditamos que aprender deve ser divertido e gratificante. Por isso, criamos uma plataforma que combina educação com mecânicas de jogo.
        </p>
      </div>

      <div class="footer-column">
        <h2>Contato</h2>
        <p>Encontrou algum bug?<br>Sabe de algum complemento?<br>Quer enviar uma nota?</p>
        <a href="mailto:email@exemplo.com">Envie um email</a>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
};
</script>

<style scoped>
/* Container usa flex vertical e cresce para empurrar footer pra baixo */
.container {
  display: flex;
  flex-direction: column;
  flex: 1; /* ocupa todo o espaço disponível dentro do app */
  font-family: sans-serif;
  min-height: 0; /* para permitir o flex crescer corretamente */
}

/* Conteúdo principal pode crescer e empurrar o footer */
.hero {
  flex-grow: 1;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 2rem;
  flex-wrap: wrap;
}

.hero-text {
  max-width: 1000px;
  font-size: 2.6rem;
  color: #2c3e50;
}

.highlight {
  color: #3498db;
  font-weight: bold;
}

.hero-image img {
  max-width: 600px;
}

.footer {
  background-color: #111827;
  color: white;
  display: flex;
  justify-content: space-around;
  padding: 1rem;
  flex-wrap: wrap;
  font-size: 0.9rem;
  /* Não precisa height fixa */
}

.footer-column {
  flex: 1;
  margin: 1rem;
}

.footer-column h2 {
  margin-bottom: 0.5rem;
  font-weight: bold;
}

.footer-column ul {
  list-style: none;
  padding: 0;
}

.footer-column ul li {
  margin-bottom: 0.3rem;
}

.footer-column a {
  color: #60a5fa;
  text-decoration: none;
}

.footer-column a:hover {
  text-decoration: underline;
}

.about p {
  margin-top: 0.5rem;
}
</style>
