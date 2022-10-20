<template>
  <div class="portfolio">
    <section class="bg-bg-3 py-4 py-lg-8">
      <div class="container">
        <div class="row">
          <h2 class="m-lg-4">
            Investments
          </h2>
        </div>
        <div class="accordion" id="custom-id-iek4upnw">
          <InvestmentItem></InvestmentItem>
          <InvestmentItem></InvestmentItem>
          <InvestmentItem></InvestmentItem>
        </div>
      </div>
    </section>
    <PortfolioValue></PortfolioValue>
    <section class="bg-bg-3 py-4 py-lg-8">
      <div class="container">
        <div class="row">
          <h2 class="m-lg-4">
            Market
          </h2>
        </div>
        <div class="accordion" id="custom-id-z3rq4qge">
          <MarketItem></MarketItem>
          <MarketItem></MarketItem>
          <MarketItem></MarketItem>
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
  }
}
</script>

<style scoped>

</style>