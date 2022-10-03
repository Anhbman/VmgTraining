// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import system from './modules/system/module'
import { Store } from 'vuex'

Store.registerModule('system', system.store)
router.addRoutes(system.routes)
Store.dispatch('system/initialize', null, {root: true})

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
