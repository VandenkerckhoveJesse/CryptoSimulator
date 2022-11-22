<template>
    <footer class="bg-bg-3 py-8 text-center">
        <div class="container">
            <div class="row justify-content-between">
                <div class="col-lg-2 col-md-3 mb-6 mb-md-0 aos-init aos-animate" data-aos="fade-down" data-aos-delay="0">
                    <router-link to="/">
                      <img src="../assets/logo.png" alt="" class="img-fluid">
                    </router-link>
                </div>
            </div>
            <div class="mt-6 mb-8 border-bottom border-dark-3 aos-init aos-animate" data-aos="fade-down" data-aos-delay="400">
            </div>
            <nav class="row mb-10">
              <router-link class="col-2 offset-2" to="/">Home</router-link>
              <router-link class="col-2" to="login">Login</router-link>
              <router-link class="col-2" to="signup">Sign up</router-link>
              <router-link class="col-2" to="portfolio">Portfolio</router-link>
            </nav>
            <p class="fs-5 mb-0 aos-init aos-animate" data-aos="fade-down" data-aos-delay="500">
                Made with <span>&#10084;</span>  by Jesse Vandenkerckhove
            </p>
        </div>
    </footer>
</template>

<script>
    export default {
        name: "WebFooter"
    }
</script>

<style scoped>

</style>