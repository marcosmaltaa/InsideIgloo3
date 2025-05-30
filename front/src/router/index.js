import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../components/HomePage.vue'
import LoginPage from '../components/LoginPage.vue'
import RegisterPage from '@/components/RegisterPage.vue'
import ProfilePage from '@/components/ProfilePage.vue'
import AprenderPage from '@/components/AprenderPage.vue'
import RankingPage from '@/components/RankingPage.vue'
import PrimeiraPage from '@/components/PrimeiraPage.vue'

const routes = [
  { path: '/', component: HomePage },
  { path: '/login', component: LoginPage },
  { path: '/registro', component: RegisterPage},
  { path: '/perfil', component: ProfilePage},
  { path: '/aprender', component: AprenderPage},
  { path: '/ranking', component: RankingPage},
  { path: '/historiaLinux', component: PrimeiraPage}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
