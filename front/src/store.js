import { reactive } from "vue";

export const store = reactive({
  id: localStorage.getItem("id") || null,
  apelido: localStorage.getItem("apelido") || null,
  token: localStorage.getItem("token") || null,

  setUser(apelido, token, id) {
    this.apelido = apelido;
    this.token = token;
    this.id = id;
    localStorage.setItem("apelido", apelido);
    localStorage.setItem("token", token);
    localStorage.setItem("id", id);
  },

  clearUser() {
    this.apelido = null;
    this.token = null;
    this.id = null;
    localStorage.removeItem("apelido");
    localStorage.removeItem("token");
    localStorage.removeItem("id");
  },
});