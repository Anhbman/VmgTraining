// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import axios from 'axios'
import Vuex from 'vuex'
import store from './store'

Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(Vuex)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  components: { App },
  template: '<App/>'
})
