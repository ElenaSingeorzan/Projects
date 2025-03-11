<template>
  <div>
    <h1>Lista Postări</h1>
    <div v-for="post in posts" :key="post.id">
      <PostItem1
        :post="post"
        @add-comment="handleAddComment"
      />
    </div>
  </div>
</template>

<script>
import PostItem1 from './PostItem1.vue';
import { getPosts, addComment } from '../services/api'; 

export default {
  components: {
    PostItem1
  },
  data() {
    return {
      posts: [],
      newComment: "",
      message: '',
    };
  },
  methods: {
  fetchPosts() {
      const token = localStorage.getItem('jwtToken'); 

      getPosts(token)
        .then(response => {
          this.posts = response.data;
        })
        .catch(error => {
          console.error('Eroare la preluarea postărilor:', error);
        });
    },
  handleAddComment(postId,newComment) {
    const token = localStorage.getItem('jwtToken');
        console.log("Comentariu trimis:", newComment);
        const commentData = {
        content: newComment,
      };
      
      addComment(postId, commentData, token)
    .then(response => {
      console.log("Comentariu creat:", response.data);
      alert('Comentariu adăugat!');
      this.fetchPosts();
    })
    .catch(error => {
      console.error('Eroare la adăugarea comentariului:', error);
    });
}
    },
  mounted() {
    this.fetchPosts();
  }
};


</script>

<style scoped>
/* Stiluri pentru a arăta postările și detaliile acestora */
.post {
  margin-bottom: 20px;
}

h3 {
  cursor: pointer;
  color: #007BFF;
}

h3:hover {
  text-decoration: underline;
}

ul {
  list-style-type: none;
  padding-left: 0;
}

li {
  margin-bottom: 10px;
}

small {
  font-size: 0.9em;
  color: gray;
}

p {
  font-size: 1.1em;
}

h4 {
  margin-top: 20px;
}
</style>
