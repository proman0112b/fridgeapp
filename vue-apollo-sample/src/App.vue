<template>
  <div id="app">
<!--    <RwvHeader />-->
    <router-view></router-view>
<!--    <Post/>-->
<!--    <RwvFooter/>-->

<!--    <div>-->
<!--      <Home/>-->
<!--    </div>-->
<!--    <div>hello world</div>-->

<!--    <div>-->
<!--      <Post/>-->
<!--    </div>-->
  </div>
</template>

<script>
import Post from './components/Post.vue'
// import Home from './views/Home.vue'

// import RwvHeader from "@/components/TheHeader"
// import RwvFooter from "@/components/TheFooter"

export default {
  name: 'app',
  components: {
 //   RwvHeader,
 //   RwvFooter

    // Header,
     Post
    // Home
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.header {
  display: flex;
  justify-content: space-between;
  background-color: fuchsia;
  height: 25%;
  padding: 1rem;
}
.app-name {
  font-weight: 900;
  font-size: 3rem;
}
.auth {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.auth h3 {
  margin-top: 2rem;
}
form {
  max-width: 50%;
  margin-top: 1rem;
  padding: 4rem;
  border: 1px solid #c4c4ce;
}
form input {
  display: block;
  margin-bottom: 1.2rem;
  padding: 0.4rem 1.2rem;
  background-color: white;
}
.auth-submit {
  margin-top: .5rem;
  padding: .5rem 1rem;
  border: none;
  background-color: fuchsia;
  color: white;
  font-weight: bold;
  text-transform: capitalize;
  border-radius: 0.3rem;
}
.auth-text a {
  color: black;
  text-decoration: none;
}
.auth-text a:visited {
  color: inherit;
}
.auth-text a:hover {
  text-decoration: underline;
}
.auth-text {
  margin-top: .5rem;
}
.auth-button{
  margin: .7rem 2rem 0 0;
  padding: .5rem 2rem;
  background-color: white;
  border: none;
  border-radius: .3rem;
}
main{
  margin-top: 5rem;
  display: flex;
  justify-content: center;
}
</style>
