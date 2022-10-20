<template>
    <div class="accordion-item border-0 rounded-0">
        <div class="accordion-header">
            <div class="row justify-content-between align-items-center my-lg-3 my-xl-3 my-md-3 my-sm-3 my-3">
                <div class="col-auto">
                    <div class="row justify-content-start align-items-center">
                        <div class="col-auto aos-init aos-animate" data-aos="fade-down" data-aos-delay="0">
                            <img src="../assets/testimonial-1-2.jpg" srcset="i/blog-17@2x.jpg 2x" alt="" class="img-fluid rounded-circle">
                        </div>
                        <div class="col-auto aos-init aos-animate" data-aos="fade-down" data-aos-delay="250">
                            <h5 class="mb-1">
                                8.63 ETH
                            </h5>
                            <p class="mb-0">
                                $852 +43%
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-auto">
                    <button @click="sell" class="collapsed btn btn-action-1 btn-sm" type="button" data-bs-toggle="collapse" data-bs-target="#accordion_collapse_1" aria-expanded="false">
                        Sell
                    </button>
                </div>
            </div>
        </div>
        <div id="accordion_collapse_1" class="accordion-collapse collapse" data-bs-parent="#custom-id-iek4upnw">
            <div class="accordion-body text-start">
                <div class="row">
                    <div class="col-4">
                        <h5>
                            1 ETH
                        </h5>
                        <p>
                            $854
                        </p>
                    </div>
                    <div class="col-xl-2">
                        <div class="row">
                            <div class="col-6">
                                <input type="number" name="name" placeholder="Your name" class="form-control form-control-sm ">
                                <p>
                                    ETH
                                </p>
                            </div>
                            <div class="col-6">
                                <div class="p-4 text-center bg-bg-2 text-dark-1">
                                    Column 2 inner
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="p-4 text-center bg-bg-2 text-dark-1">
                            Column 2 inner
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "MarketItem",
    }
</script>

<style scoped>
    .accordion-item {
        margin-top: 35px;
    }

</style>