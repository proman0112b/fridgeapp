import Vue from 'vue'
import App from './App.vue'
import apolloProvider from './vue-apollo'
import router from "./router";
import store from "./store";
// import { CHECK_AUTH } from "./store/actions.type";
import './plugins/element.js';
import './plugins/avue.js';

Vue.config.productionTip = false

// router.beforeEach((to, from, next) =>
//    Promise.all([store.dispatch(CHECK_AUTH)]).then(next)
// );

new Vue({
  router,
  store,
  apolloProvider,
  render: h => h(App)
}).$mount('#app')
