import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// eslint-disable-next-line
import './assets/js/bootstrap.bundle.min.js'
//import './assets/js/plugins.min.js'
//import './assets/js/scripts.min.js'

createApp(App).use(router).mount('#app')
