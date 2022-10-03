import Store from '../../../services/store'
import Router from '../../../services/router'

export default {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {
    initialize ({ dispatch }) {
      console.info('System initialized...')
      console.info('System initialized')
    },

    initialzeModule ({ dispatch }, module) {
      Store.registerModule(module.name, module.Store)
      Router.addRoutes(module.routes)
      dispatch(module.name + '/initilaze', null, {root: true})
    }
  }
}
