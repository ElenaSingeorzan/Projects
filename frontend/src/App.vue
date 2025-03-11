<template>
  <div id="app">
    <nav>
      <router-link to="/allPosts">Posts</router-link>
      <router-link to="/create-post">Create Post</router-link>
      <router-link to="/login"> Login</router-link>
      <router-link to="/register">Register</router-link>
      <router-link to="/my-posts">My Posts</router-link>
    </nav>
    <div class="container">
      <router-view></router-view>
    </div>
    <DialogsWrapper />
  </div>
</template>

<script>

</script>

<style>
/* Stilurile pentru aplicație */

body {
  font-family: 'Arial', sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f4f4f9;
}

#app {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

nav {
  background-color: #333;
  padding: 10px;
  text-align: center;
}

nav a {
  color: white;
  margin: 0 15px;
  text-decoration: none;
  font-weight: bold;
}

nav a:hover {
  color: #ff9800;
}

.container {
  background: white;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

h2 {
  color: #333;
}

button {
  background-color: #ff9800;
  border: none;
  color: white;
  padding: 10px 20px;
  margin: 5px 0;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #ffb74d;
}

textarea, input {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #0b0b0b;
  border-radius: 5px;
}
</style>
