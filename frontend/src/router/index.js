import { createRouter, createWebHistory } from 'vue-router';
import PostList from '../components/PostList.vue';
import PostItem from '@/components/PostItem.vue';
import PostForm from '@/components/PostForm.vue';
import UserRegister from '../components/UserRegister.vue';
import UserLogin from '../components/UserLogin.vue';
import UsersPosts from '@/components/UsersPosts.vue';


const routes = [
  { path: '/', component: UserLogin }, 
  { path: '/login',component: UserLogin },
  { path: '/register',component: UserRegister},
  { path: '/allPosts', component: PostList },
  { path: '/posts/:id', component: PostItem },
  { path: '/create-post', component: PostForm },
  { path: '/my-posts',component:UsersPosts},

];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router; 
