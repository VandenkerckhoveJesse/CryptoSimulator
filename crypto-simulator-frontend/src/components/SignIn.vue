<template>
  <section class="bg-bg-3 py-10 py-lg-20 text-center">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-xl-4 col-lg-5 col-md-7 col-sm-10">
          <h1 class="mb-6 aos-init aos-animate" data-aos="fade-down" data-aos-delay="0">
            Sign In
          </h1>
          <form @submit.prevent="signIn" class="js-ajax-form">
            <!-- forms alerts -->
            <div :class="{show: hasError}" class="alert alert-action-5 fixed-top invisible fade js-form-result" data-result="error"
                 role="alert">
									<span class="js-form-alert-text">
										Error!
									</span>
            </div>
            <!-- forms alerts end -->
            <div class="input-group mb-6 aos-init aos-animate" data-aos="fade-down" data-aos-delay="400">
              <div class="input-group-text border-end-0 border-dark-3 ps-4 pe-2">
                <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg"
                     class="fill-dark-3">
                  <path fill-rule="evenodd" clip-rule="evenodd"
                        d="M12.075,10.812c1.358-0.853,2.242-2.507,2.242-4.037c0-2.181-1.795-4.618-4.198-4.618S5.921,4.594,5.921,6.775c0,1.53,0.884,3.185,2.242,4.037c-3.222,0.865-5.6,3.807-5.6,7.298c0,0.23,0.189,0.42,0.42,0.42h14.273c0.23,0,0.42-0.189,0.42-0.42C17.676,14.619,15.297,11.677,12.075,10.812 M6.761,6.775c0-2.162,1.773-3.778,3.358-3.778s3.359,1.616,3.359,3.778c0,2.162-1.774,3.778-3.359,3.778S6.761,8.937,6.761,6.775 M3.415,17.69c0.218-3.51,3.142-6.297,6.704-6.297c3.562,0,6.486,2.787,6.705,6.297H3.415z"></path>
                </svg>
              </div>
              <input v-model="username" type="text" name="username" placeholder="Username"
                     class="form-control border-start-0 border-dark-3 ps-1" required="required">
            </div>
            <div class="input-group mb-6 aos-init aos-animate" data-aos="fade-down" data-aos-delay="250">
              <div class="input-group-text border-end-0 border-dark-3 ps-4 pe-2">
                <svg width="20" height="20" viewBox="0 0 20 20" fill="none"
                     xmlns="http://www.w3.org/2000/svg" class="fill-dark-3">
                  <path fill-rule="evenodd" clip-rule="evenodd"
                        d="M14.375 10H5.625C5.29348 10 4.97554 10.1317 4.74112 10.3661C4.5067 10.6005 4.375 10.9185 4.375 11.25V17.5C4.375 17.8315 4.5067 18.1495 4.74112 18.3839C4.97554 18.6183 5.29348 18.75 5.625 18.75H14.375C14.7065 18.75 15.0245 18.6183 15.2589 18.3839C15.4933 18.1495 15.625 17.8315 15.625 17.5V11.25C15.625 10.9185 15.4933 10.6005 15.2589 10.3661C15.0245 10.1317 14.7065 10 14.375 10ZM5.625 8.75C4.96196 8.75 4.32607 9.01339 3.85723 9.48223C3.38839 9.95107 3.125 10.587 3.125 11.25V17.5C3.125 18.163 3.38839 18.7989 3.85723 19.2678C4.32607 19.7366 4.96196 20 5.625 20H14.375C15.038 20 15.6739 19.7366 16.1428 19.2678C16.6116 18.7989 16.875 18.163 16.875 17.5V11.25C16.875 10.587 16.6116 9.95107 16.1428 9.48223C15.6739 9.01339 15.038 8.75 14.375 8.75H5.625ZM5.625 5C5.625 3.83968 6.08594 2.72688 6.90641 1.90641C7.72688 1.08594 8.83968 0.625 10 0.625C11.1603 0.625 12.2731 1.08594 13.0936 1.90641C13.9141 2.72688 14.375 3.83968 14.375 5V8.75H13.125V5C13.125 4.1712 12.7958 3.37634 12.2097 2.79029C11.6237 2.20424 10.8288 1.875 10 1.875C9.1712 1.875 8.37634 2.20424 7.79029 2.79029C7.20424 3.37634 6.875 4.1712 6.875 5V8.75H5.625V5Z">
                  </path>
                </svg>
              </div>
              <input v-model="password" type="password" name="password" placeholder="Password"
                     class="form-control border-start-0 border-dark-3 ps-1" required="required">
            </div>
            <button class="btn btn-action-1 w-100 mb-6 aos-init aos-animate" data-aos="fade-down"
                    data-aos-delay="400">
              Sign In
            </button>
            <div data-aos="fade-down" data-aos-delay="250" class="aos-init aos-animate">
              <p class="d-inline-block text-dark-3 mb-6">
                Don’t have an account?
              </p>
              <router-link to="signup" class="ms-1">
                Sign up
              </router-link>
            </div>
            <div class="w-50 border-bottom border-bg-2 mx-auto mb-6 aos-init aos-animate"
                 data-aos="fade-down" data-aos-delay="400">
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
    export default {
        name: "SignIn",
        data () {
          return {
            username: "",
            password: "",
            hasError: false
          }
        },
        methods: {
          signIn() {
            fetch("http://localhost:8080/api/auth/token",
                {
                  method: "POST",
                  headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Basic ${btoa(`${this.username}:${this.password}`)}`
                  },
                })
                .then((response) => {
                  if(response.status !== 200) throw Error(response.statusText)
                  console.log(response)
                  return response.text()
                })
                .then((data) => {
                  this.$store.commit("setJwtToken", data)
                  this.$router.push("portfolio")
                })
                .catch(() => {
                  this.hasError = true
                })
          }
        }
    }
</script>

<style scoped>

</style>