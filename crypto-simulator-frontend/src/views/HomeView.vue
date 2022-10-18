<template>
  <div class="home">
    <WelcomeComponent></WelcomeComponent>
    <CryptoValues></CryptoValues>
    <SignUp></SignUp>
  </div>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'

import WelcomeComponent from "../components/WelcomeComponent";
import CryptoValues from "../components/CryptoValues";
import SignUp from "../components/SignUp";

export default {
  name: 'HomeView',
  components: {
    WelcomeComponent,
    CryptoValues,
    SignUp
  }
}
</script>
