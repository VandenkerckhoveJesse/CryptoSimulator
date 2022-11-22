<template>
    <section class="py-10 py-lg-20 bg-bg-3">
        <div class="container">
            <div class="row">
                <div v-for="currency in currencies" v-bind:key="currency.id" class="col-xl-4 col-md-6 mb-12 aos-init aos-animate" data-aos="fade-down" data-aos-delay="250">
                    <div class="row">
                        <!--div class="col-auto">
                            <img src="../assets/testimonial-1-2.jpg" srcset="i/testimonial-1-1@2x.jpg 2x" alt="" class="img-fluid rounded-circle">
                        </div
                        //todo add the pictures later
                        -->
                        <div class="col ps-2">
                            <h6 class="mb-0 ">
                                {{currency.name}}
                            </h6>
                            <p class="fs-4">
                                ${{currency.priceUsd}} <!---//todo 20% add the change later-->
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "CryptoValues",
        data() {
          return {
            currencies: []
          }
        },
        mounted() {
          this.fetchData()
        },
        methods: {
          async fetchData() {
            const res = await fetch("http://localhost:8080/api/currency");
            this.currencies = await res.json();
          }
        }
    }
</script>

<style scoped>

</style>