import { getFiled, updateFiled } from 'vuex-map-fields'

import { ADD_ROW } from '../../mutation-types'
import { Contact } from '../../models/Contact'

const mutations = {
  updateFiled,
  [ADD_ROW] (state) {
    state.rows.push(new Contact())
  }
}

const getters = {
  getFiled
}

const state = () => ({
  rows: [new Contact()]
})

export default {
  namespaced: true,
  mutations,
  getters,
  state
}
