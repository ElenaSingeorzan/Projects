<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="loginUser">
      <div>
        <label for="email">Email</label>
        <input type="email" v-model="email" required />
      </div>
      <div>
        <label for="password">Password</label>
        <input type="password" v-model="password" required />
      </div>
      <button type="submit">Login</button>
    </form>
    <p>Nu ai un cont?    <a @click="goToRegister">Înregistrează-te</a></p>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
import { loginUser as apiLoginUser } from '../services/api';
export default {
  data() {
    return {
      email: '',
      password: '',
      message: ''
    };
  },
  methods: {
    loginUser() {
      const login = {
        email: this.email,
        password: this.password
      };
      apiLoginUser(login)
        .then(response => {
          console.log("Autentificare reusita", response.data);
          // Salvează token-ul în localStorage
          localStorage.setItem('jwtToken', response.data);
          this.message = "Autentificare reușită!";
          this.$router.push('/allPosts');
          
        })
        .catch(error => {
          console.error("Eroare la autentificare", error);
          this.message = "Autentificare eșuată! Verifică email-ul și parola.";
        });
    },
    goToRegister() {
      this.$router.push('/register');
    },
  }
};
</script>

<style>
/* Stil pentru formularul de logare */
a{
  font-weight: bold;
}
a:hover {
  color: #ff9800;
}
</style>
