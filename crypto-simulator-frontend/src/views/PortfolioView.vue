<template>
  <div class="portfolio">
    <section class="bg-bg-3 py-4 py-lg-8">
      <div class="container">
        <div class="row">
          <h2 class="m-lg-4">Wallet</h2> <p>${{walletUsd}}</p>
        </div>

      </div>
    </section>
    <section class="bg-bg-3 py-4 py-lg-8">
      <div class="container">
        <div class="row">
          <h2 class="m-lg-4">
            Investments
          </h2>
        </div>
        <div class="accordion" id="custom-id-iek4upnw">
          <InvestmentItem @updated="fetchPortfolio" v-for="investment in investments" v-bind:investment="investment" v-bind:key="investment.id"></InvestmentItem>
        </div>
      </div>
    </section>
    <PortfolioValue v-bind:value-usd="portfolioValueUsd" v-bind:wallet-usd="walletUsd"></PortfolioValue>
    <section class="bg-bg-3 py-4 py-lg-8">
      <div class="container">
        <div class="row">
          <h2 class="m-lg-4">
            Market
          </h2>
        </div>
        <div class="accordion" id="custom-id-z3rq4qge">
          <MarketItem @updated="fetchPortfolio" v-for="currency in currencies" v-bind:currency="currency" v-bind:key="currency.id"></MarketItem>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import PortfolioValue from "../components/PortfolioValue";
import InvestmentItem from "../components/InvestmentItem";
import MarketItem from "../components/MarketItem";

export default {
  name: "PortfolioView",
  components: {
    InvestmentItem,
    PortfolioValue,
    MarketItem
  },
  data() {
    return {
      currencies: [],
      investments: [],
      portfolioValueUsd: 0,
      walletUsd: 0
    }
  },
  computed: {
    token() {
      return this.$store.state.jwtToken
    }
  },
  mounted() {
    this.fetchCurrencies();
    this.fetchPortfolio();
  },
  methods: {
    async fetchCurrencies() {
      const res = await fetch("http://localhost:8080/api/currency");
      this.currencies = await res.json();
    },
    async fetchPortfolio() {
      const res = await fetch("http://localhost:8080/api/portfolio",
          {
            headers: {
              'Authorization': `Bearer ${this.token}`
            }
          }
      )
      const json = await res.json();
      this.investments = json.coins;
      this.portfolioValueUsd = json.valueUsd;
      this.walletUsd = json.walletUsd;
    }
  }
}
</script>

<style scoped>

</style>