import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store"

// eslint-disable-next-line
import './assets/js/bootstrap.bundle.min.js'
//import './assets/js/plugins.min.js'
//import './assets/js/scripts.min.js'

createApp(App).use(store).use(router).mount('#app')
