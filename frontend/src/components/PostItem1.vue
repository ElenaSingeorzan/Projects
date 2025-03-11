<template>
    <div class="post-item1">
      <h2 @click="toggleDetails">{{ post.title }}</h2>
  
      <div v-if="showDetails">
        <!-- Afișare formular dacă este în modul de editare -->
          <p>{{ post.content }}</p>
          <p><strong>Autor:</strong> {{ post?.user?.name || 'Unknown Author' }}</p>
  
          <!-- Butoane pentru acțiuni -->
          <button @click="toggleCommentForm">Add Comment</button>
  
        <!-- Formular de adăugare comentariu -->
        <div v-if="showCommentForm">
          <textarea v-model="newComment" placeholder="Scrie un comentariu"></textarea>
          <button @click="emitAddComment">Submit Comment</button>
        </div>
  
        <!-- Listă comentarii -->
      <div v-if="post.comments && post.comments.length">
        <h4>Comments:</h4>
        <ul>
          <li v-for="(comment, index) in post.comments" :key="index">
            <div>
              <strong>{{ comment.user.name }}</strong>: {{ comment.content }}
            </div>
          </li>
        </ul>
      </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      post: {
        type: Object ,
        required: true
      }
    },
    data() {
      return {
        showDetails: false,
        showCommentForm: false,
        newComment: ''
      };
    },
    mounted() {
    console.log('Post received in component:', this.post);
  },
    methods: {
      toggleDetails() {
        this.showDetails = !this.showDetails;
      },
      toggleCommentForm() {
        this.showCommentForm = !this.showCommentForm;
      },
      emitAddComment() {
    if (this.newComment.trim()) {
      this.$emit('add-comment', this.post.id, this.newComment);
      this.newComment = ""; 
    } else {
      alert("Comentariul nu poate fi gol!");
    }
  }
}
};
  </script>
  
   <style scoped>
    .post-item1 {
      border: 1px solid #ccc;
      padding: 10px;
      margin: 10px 0;
    }
    h2 {
      cursor: pointer;
    }
    textarea {
      width: 100%;
      margin-top: 10px;
    }
    /* Adaugă stiluri pentru formularul de editare */
  textarea, input {
    display: block;
    margin: 10px 0;
    width: 100%;
    padding: 8px;
    font-size: 1rem;
  }
  button {
    margin-right: 10px;
  }
    </style>
    