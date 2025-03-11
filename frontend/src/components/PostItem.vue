
<template>
  <div class="post-item">
    <h2 @click="toggleDetails">{{ post.title }}</h2>

    <div v-if="showDetails">
      <!-- Afișare formular dacă este în modul de editare -->
      <div v-if="isEditing">
        <h3>Edit Post</h3>
        <input v-model="editedTitle" placeholder="Edit title" />
        <textarea v-model="editedContent" placeholder="Edit content"></textarea>
        <button @click="saveEdit">Save</button>
        <button @click="cancelEdit">Cancel</button>
      </div>
      <!-- Afișare detalii postare dacă nu este în modul de editare -->
      <div v-else>
        <p>{{ post.content }}</p>

        <!-- Afișează butoanele doar pentru postările cu statusul 'PUBLISHED' -->
        <div v-if="post.status === 'PUBLISHED'">
          <button @click="startEdit">Update Post</button>
          <button @click="removePost">Remove</button>
          <button @click="toggleCommentForm">Add Comment</button>
        </div>
        <div v-else>
          <!-- Butoane pentru postările care nu sunt publicate -->
          <button @click="removePost">Remove</button>
        </div>
      </div>

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
      type: Object,
      required: true
    }
  },
  data() {
    return {
      showDetails: false,
      showCommentForm: false,
      isEditing: false, 
      editedTitle: '', 
      editedContent: '', 
      newComment: '' 
    };
  },
  methods: {
    toggleDetails() {
      this.showDetails = !this.showDetails;
    },
    toggleCommentForm() {
      this.showCommentForm = !this.showCommentForm;
    },
    startEdit() {
      // Activează modul de editare și pre-populează datele
      this.isEditing = true;
      this.editedTitle = this.post.title;
      this.editedContent = this.post.content;
    },
    cancelEdit() {
      // Dezactivează modul de editare fără a salva modificările
      this.isEditing = false;
    },
    saveEdit() {
      // Verifică validitatea datelor și trimite modificările
      if (this.editedTitle.trim() && this.editedContent.trim()) {
        const updatedPost = {
          id: this.post.id,
          title: this.editedTitle,
          content: this.editedContent,
          status: 'PUBLISHED'
        };
        this.$emit('update-post', updatedPost); 
        this.isEditing = false; 
      } else {
        alert('Title and content cannot be empty!');
      }
    },
    removePost() {
      this.$emit('remove-post', this.post.id);
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
  .post-item {
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
  