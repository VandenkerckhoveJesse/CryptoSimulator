<template>
    <div class="accordion-item border-0 rounded-0">
        <div class="accordion-header">
            <div class="row justify-content-between align-items-center my-lg-3 my-xl-3 my-md-3 my-sm-3 my-3">
                <div class="col-auto">
                    <div class="row justify-content-start align-items-center">
                        <div class="col-auto aos-init aos-animate" data-aos="fade-down" data-aos-delay="250">
                            <h5 class="mb-1">
                              {{ currency.name }}
                            </h5>
                            <p class="mb-0">
                              ${{ currency.priceUsd }}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-auto">
                    <button class="collapsed btn btn-action-1 btn-sm" type="button" data-bs-toggle="collapse" :data-bs-target="'#market_collapse_'+currency.id" aria-expanded="false">
                        Buy
                    </button>
                </div>
            </div>
        </div>
        <div :id="'market_collapse_'+currency.id" class="accordion-collapse collapse" data-bs-parent="#custom-id-iek4upnw">
          <div class="accordion-body text-start">
            <div class="row">
              <div class="col-4">
                <h5>
                  1 {{ currency.symbol }}
                </h5>
                <p>
                  ${{ currency.priceUsd }}
                </p>
              </div>

              <form @submit.prevent="buyCurrency" class="row js-ajax-form">
                <div class="col-6">
                  <input v-model="buyAmount" type="number" name="amount" min="0" :placeholder="currency.symbol" class="form-control form-control-sm" step="any">
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
        name: "MarketItem",
        props: ["currency"],
        data() {
          return {
            buyAmount: 0
          }
        },
        computed: {
          token() {
            return this.$store.state.jwtToken
          }
        },
        methods: {
          buyCurrency() {
            fetch("http://localhost:8080/api/portfolio/coins/buy",
                {
                  method: "POST",
                  headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${this.token}`
                  },
                  body: JSON.stringify({id: this.currency.id, amount: this.buyAmount})
                })
                .then((response) => {
                  if(response.status !== 200) throw Error(response.statusText)
                  this.$emit("updated")
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