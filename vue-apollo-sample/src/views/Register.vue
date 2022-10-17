<template>
  <div class="auth-page">
    <div class="container page">
      <div class="row">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h1 class="text-xs-center">Sign up</h1>
          <p class="text-xs-center">
            <router-link :to="{ name: 'login' }">
              Have an account?
            </router-link>
          </p>
          <ul v-if="errors" class="error-messages">
            <li v-for="(v, k) in errors" :key="k">{{ k }} {{ v | error }}</li>
          </ul>
          <form @submit.prevent="registerUser">
            <fieldset class="form-group">
              <input
                  class="form-control form-control-lg"
                  type="text"
                  name="username"
                  v-model="authDetails.username"
                  placeholder="Username"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                  class="form-control form-control-lg"
                  type="email"
                  name="email"
                  v-model="authDetails.email"
                  placeholder="Email"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                  class="form-control form-control-lg"
                  type="password"
                  name="password"
                  v-model="authDetails.password"
                  placeholder="Password"
              />
            </fieldset>
            <button class="btn btn-lg btn-primary pull-xs-right">
              Sign up
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
// import { REGISTER } from "@/store/actions.type";
export default {
  name: "Register",
  data() {
    return {
      authDetails: {
        username: "",
        email: "",
        password: ""
      }
    };
  },
  computed: {
    ...mapState({
      errors: state => state.auth.errors
    })
  },
  methods: {
    registerUser: function() {
    }
//    onSubmit() {
//      this.$store
//          .dispatch(REGISTER, {
//            email: this.email,
//            password: this.password,
//            username: this.username
//          })
//          .then(() => this.$router.push({ name: "home" }));
  }
};
</script>
