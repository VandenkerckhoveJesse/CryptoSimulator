<template>
  <NavHeader></NavHeader>
  <router-view/>
  <WebFooter></WebFooter>
</template>

<script>
  // @ is an alias to /src
  import NavHeader from '@/components/NavHeader.vue'
  import WebFooter from "./components/WebFooter";

  export default {
    name: 'HomeView',
    components: {
      NavHeader,
      WebFooter
    }
  }
</script>

<style>
@import './assets/css/styles.min.css';
@import './assets/css/fonts.css';
</style>
