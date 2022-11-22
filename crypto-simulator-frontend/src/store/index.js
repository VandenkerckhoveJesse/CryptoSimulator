import { createStore } from 'vuex'

export default createStore({
  state () {
    return {
      jwtToken: null,
      authenticated: false
    }
  },
  mutations: {
    setJwtToken(state, token) {
      state.jwtToken = token;
      state.authenticated = true;
    }
  },
  getters: {
  },
  actions: {
  },
  modules: {
  }
})
