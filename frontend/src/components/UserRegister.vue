<template>
    <div >
      <h2>Register</h2>
      <form @submit.prevent="createUser">
        <div>
          <label for="name">Name</label>
          <input type="text" v-model="name" required />
        </div>
        <div>
          <label for="email">Email</label>
          <input type="email" v-model="email" required />
        </div>
        <div>
          <label for="password">Password</label>
          <input type="password" v-model="password" required />
        </div>
        <button type="submit">Register</button>
      </form>
      <p v-if="message">{{ message }}</p>
    </div>
  </template>
  
  <script>
 
 import { registerUser } from '../services/api';

  export default {
    data() {
      return {
        name: '',
        email: '',
        password: '',
        message: ''
      };
    },
    methods: {
       createUser() {
           const newUser= {
            name: this.name,
            email: this.email,
            password: this.password
          };
        registerUser(newUser)
        .then(response => {
            console.log("Utilizator creat:", response.data);
            this.$router.push('/login');
          })
          .catch(error => {
            console.error("Eroare la crearea utilizatorului:", error);
          });
      }
    }
  };
  </script>

  