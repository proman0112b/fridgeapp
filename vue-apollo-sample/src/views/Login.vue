<template>
  <div class="auth-page">
    <div class="container page">
      <div class="row">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h1 class="text-xs-center">Sign in</h1>
          <p class="text-xs-center">
            <router-link :to="{ name: 'register' }">
              Need an account?
            </router-link>
          </p>
          <ul v-if="errors" class="error-messages">
            <li v-for="(v, k) in errors" :key="k">{{ k }} {{ v | error }}</li>
          </ul>
          <form action="POST" @submit.prevent="loginUser">
            <!--                @submit.prevent="onSubmit(email, password)"-->

            <fieldset class="form-group">
              <input
                  class="form-control form-control-lg"
                  type="text"
                  name="email"
                  v-model="authDetails.email"
                  placeholder="Email"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                  class="form-control form-control-lg"
                  type="password"
                  v-model="password"
                  placeholder="Password"
              />
            </fieldset>
            <button class="btn btn-lg btn-primary pull-xs-right">
              Sign in
            </button>
            <p class="auth-text"> Don't have an account? <router-link to="/"> Register </router-link> </p>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
// import { LOGIN } from "@/store/actions.type";
export default {
  name: "Login",
  data() {
    return {
      authDetails: {
        email: '',
        password: ''
      }
    };
  },
  methods: {
    loginUser: function () {

    }
//    onSubmit(email, password) {
//      this.$store
//          .dispatch(LOGIN, { email, password })
//          .then(() => this.$router.push({ name: "home" }));
//    }
  },
  computed: {
    ...mapState({
      errors: state => state.auth.errors
    })
  }
};
</script>
