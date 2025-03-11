<template>
  <div>
    <h2>Creează o Postare</h2>
    <form @submit.prevent="createPost">
      <div>
        <label for="title">Titlu</label>
        <input type="text" v-model="title" required />
      </div>
      <div>
        <label for="content">Conținut</label>
        <textarea v-model="content" required></textarea>
      </div>
      <button type="submit">Creează Postarea</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
import { createPost as apiCreatePost } from '../services/api';

export default {
  data() {
    return {
      title: '',
      content: '',
      message: ''
    };
  },
  methods: {
    createPost() {
      const token = localStorage.getItem('jwtToken'); 

      const post = {
        title: this.title,
        content: this.content,
        status: 'PENDING'
      };

      apiCreatePost(post, token)
        .then(response => {
          console.log("Postare creată:", response.data);
          this.message = "Postare creată cu succes!";
        })
        .catch(error => {
          console.error("Eroare la crearea postării", error);
          this.message = "Eroare la crearea postării.";
        });
    }
  }
};
</script>


