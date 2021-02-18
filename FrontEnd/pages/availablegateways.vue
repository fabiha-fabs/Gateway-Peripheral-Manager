<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="4"> </v-col>
      <v-col cols="12" md="4">
        <h1>Gateway Dashboard</h1>
        <v-divider></v-divider><br />
      </v-col>
      <v-col cols="12" md="4"> </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="4"> </v-col>
      <v-col cols="12" md="4">
        <v-btn @click="register" color="#80CBC4" elevation="9">Register Gateway</v-btn>
      </v-col>
        
      <v-col cols="12" md="4"> </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="4"> </v-col>
      <v-col cols="12" md="4">
        <div v-for="gateway in listGateways" :key="gateway.serialNo" >
          <v-card width="400">
            <v-img
              height="200px"
              
              src="https://cdn.pixabay.com/photo/2020/07/12/07/47/bee-5396362_1280.jpg"
            >
              <v-app-bar flat color="rgba(0, 0, 0, 0)">
                <v-app-bar-nav-icon color="white"></v-app-bar-nav-icon>

                <v-toolbar-title class="title white--text pl-0"><br>
                  Serial No: {{ gateway.serialNo }} <br />
                  IP Address: {{ gateway.ipAddress }}
                </v-toolbar-title>

                <v-spacer></v-spacer>
              </v-app-bar>

              <v-card-title class="white--text mt-8">
                <v-avatar size="56">
                  <img
                    alt="user"
                    src="https://cdn.pixabay.com/photo/2020/06/24/19/12/cabbage-5337431_1280.jpg"
                  />
                </v-avatar>
                <p class="ml-3">
                  {{ gateway.name }}
                </p>
              </v-card-title>
            </v-img>
          
            <v-card-text>
              <div class="font-weight-bold ml-8 mb-2">Connected devices with gateway</div>
              
                  <gatewayconnectedperipherals :gatewaySerialNo="gateway.serialNo"></gatewayconnectedperipherals>
            </v-card-text>
          </v-card>
          <br />
        </div>
      </v-col>
      <v-col cols="12" md="4"></v-col>
    </v-row>
  </v-container>
</template>

<script>
import gatewayconnectedperipherals from '~/components/gatewayconnectedperipherals.vue';
export default {
  components: { gatewayconnectedperipherals },
  data: () => ({
    listGateways: [],
    gatewaySerialNo: ''
  }),
  created() {
  this.allGateways()
    },
  methods: {
    allGateways() {
      this.$axios
        .get("http://localhost:8989/RestApiProject/allGateways")
        .then((res) => {
          this.listGateways = res.data
          this.listGateways.forEach(gateway => {
            gateway.connectedPeripheralLists = []
          })
          console.log(this.listGateways);
        });
    },
    register() {
      this.$router.push("/gateway");
    },
    },
};
</script>