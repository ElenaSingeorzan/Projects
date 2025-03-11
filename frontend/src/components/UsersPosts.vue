 <template>
    <div>
      <h1>Postările mele</h1>
  
      <!-- Postări Publicate -->
      <h2>Postări Publicate</h2>
      <div v-if="publishedPosts.length">
        <div v-for="post in publishedPosts" :key="post.id">
          <PostItem
            :post="post"
            @update-post="handleUpdatePost"
            @remove-post="handleRemovePost"
            @add-comment="handleAddComment"
          />
        </div>
      </div>
      <p v-else>Nicio postare publicată.</p>
  
      <!-- Postări Pending -->
      <h2>Postări în Așteptare</h2>
      <div v-if="pendingPosts.length">
        <div v-for="post in pendingPosts" :key="post.id">
          <PostItem
            :post="post"
            @remove-post="handleRemovePost"
          />
        </div>
      </div>
      <p v-else>Nicio postare în așteptare.</p>
 <!-- Postări Șterse -->
 <h2>Postări Șterse</h2>
    <div v-if="removedPosts.length">
      <div v-for="post in removedPosts" :key="post.id">
        <PostItem
          :post="post"
          
        />
      </div>
    </div>
    <p v-else>Nicio postare ștearsă.</p>
  </div>
</template>
  
  <script>
  import { getPostsforUser, updatePost, deletePost, addComment } from "../services/api";
  import PostItem from './PostItem.vue';
  export default {
    components: {
      PostItem
    },
    data() {
      return {
        posts: [], 
        newComment: "",
         message: ""
      };
    },
    computed: {
    publishedPosts() {
      return this.posts.filter(post => post.status === 'PUBLISHED');
    },
    pendingPosts() {
      return this.posts.filter(post => post.status === 'PENDING');
    },
    removedPosts() {
      return this.posts.filter(post => post.status === 'REMOVED');
    }
  },
    methods: {
        fetchPosts() {
      const token = localStorage.getItem('jwtToken'); 

      getPostsforUser(token)
        .then(response => {
          this.posts = response.data;
        })
        .catch(error => {
          console.error('Eroare la preluarea postărilor:', error);
        });
    },
       handleUpdatePost(post) {
        const token = localStorage.getItem("jwtToken");
        try {
         updatePost(post.id, post, token);
          alert("Postare actualizată!");
          this.fetchPosts(); 
        } catch (error) {
          console.error("Eroare la actualizarea postării:", error);
        }
      },
       handleRemovePost(postId) {
        const token = localStorage.getItem("jwtToken");
        try {
           deletePost(postId, token);
          alert("Postare ștearsă!");
          this.fetchPosts(); 
        } catch (error) {
          console.error("Eroare la ștergerea postării:", error);
        }
      },
      handleAddComment(postId, newComment) {
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
    },
  };
  </script>
  