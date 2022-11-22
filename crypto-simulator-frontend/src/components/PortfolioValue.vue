<template>
    <section class="py-10 py-lg-20 bg-bg-3">
        <div class="container">
            <div class="p-6 p-sm-12 bg-bg-2 rounded-2 text-center text-lg-start">
                <div class="row flex-column-reverse flex-lg-row align-items-center justify-content-center justify-content-lg-between">
                    <div class="col-md-8 col-lg-6 col-xxl-5">
                      <p>Portfolio Value</p>
                        <h2 class="display-5 aos-init aos-animate" data-aos="fade-down" data-aos-delay="0">
                          ${{ valueUsd }}
                        </h2>

                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "PortfolioValue",
        props: ["valueUsd"]
    }
</script>

<style scoped>

</style>