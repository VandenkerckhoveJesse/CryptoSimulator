<template>
    <div class="accordion-item border-0 rounded-0">
        <div class="accordion-header">
            <div class="row justify-content-between align-items-center my-lg-3 my-xl-3 my-md-3 my-sm-3 my-3">
                <div class="col-auto">
                    <div class="row justify-content-start align-items-center">
                        <div class="col-auto aos-init aos-animate" data-aos="fade-down" data-aos-delay="250">
                            <h5 class="mb-1">
                                {{investment.amount}} {{investment.symbol}}
                            </h5>
                            <p class="mb-0">
                                ${{investment.valueUsd}} (1 {{investment.symbol}}=${{investment.priceUsd}})
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-auto">
                    <button class="collapsed btn btn-action-1 btn-sm" type="button" data-bs-toggle="collapse" :data-bs-target="'#investment_collapse_'+investment.id" aria-expanded="false">
                        Sell
                    </button>
                </div>
            </div>
        </div>
        <div :id="'investment_collapse_'+investment.id" class="accordion-collapse collapse" data-bs-parent="#custom-id-iek4upnw">
            <div class="accordion-body text-start">
                <div class="row">
                    <div class="col-4">
                        <h5>
                            1 {{ investment.symbol }}
                        </h5>
                        <p>
                          ${{ investment.priceUsd }}
                        </p>
                    </div>

                  <form @submit.prevent="sellInvestment" class="row js-ajax-form">
                    <div class="col-6">
                      <input v-model="sellAmount" type="number" name="amount" min="0" :placeholder="investment.symbol" class="form-control form-control-sm" step="any">
                    </div>
                    <div class="col-6">
                      <button class="btn btn-action-1 btn-sm" type="submit">
                        Confirm
                      </button>
                    </div>
                  </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "InvestmentItem",
        props: ["investment"],
        data() {
          return {
            sellAmount : 0
          }
        },
        computed: {
          token() {
            return this.$store.state.jwtToken
          }
        },
        methods: {
          sellInvestment() {
            fetch("http://localhost:8080/api/portfolio/coins/sell",
                {
                  method: "POST",
                  headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${this.token}`
                  },
                  body: JSON.stringify({id: this.investment.id, amount: this.sellAmount})
                })
                .then((response) => {
                  if(response.status !== 200) throw Error(response.statusText)
                  this.$emit('updated')
                })
                .catch(e => console.log(e))
          }
        }
    }
</script>

<style scoped>
    .accordion-item {
        margin-top: 35px;
    }

</style>