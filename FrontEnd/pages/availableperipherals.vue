<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="4"> </v-col>
      <v-col cols="12" md="4">
        <h1>Peripheral Devices</h1>
        <v-divider></v-divider><br /><br />
        <v-btn @click="register" color="#80CBC4" elevation="9">Register Peripheral</v-btn><br /><br />
      </v-col>
      <v-col cols="12" md="4"> </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="4" v-for="peripheral in listPeripherals" :key="peripheral.name">
          <peripheralconnecttogateway :peripheral="peripheral"></peripheralconnecttogateway>
      </v-col>  
    </v-row>
  </v-container>
</template>

<script>

export default {

  data: () => ({
    listPeripherals: [],
    gatewayLists: [],
    status: '',
  }),
created() {
  //      this.gatewayListsToAddPeripheral()
  this.allPeripherals()
    },
  methods: {
    async allPeripherals() {
      this.$axios
        .get("http://localhost:8989/RestApiProject/allPeripherals")
        .then((res) => {
          this.listPeripherals = res.data;
          console.log(this.listPeripherals); 
        });
    },
    register() {
      this.$router.push("/peripheral");
    },
    disconnectPeripheral(peripheral) {
      this.$axios
        .put(
          "http://localhost:8989/RestApiProject/" +
            peripheral.uid +
            "/disconnectPeripheral"
        )
        .then((res) => {
          if (res.status === 200) {
            peripheral.gateway = null
            alert("Gateway disconnected..!!");
          }
        })
        .catch((error) => alert("Gateway failed to disconnect..!!"));
    },
    async gatewayListsToAddPeripheral() {
      this.$axios
        .get('http://localhost:8989/RestApiProject/allGateways/')
        .then((res) => {
          this.gatewayLists = res.data;
          console.log(this.gatewayLists);
        })
        .catch((error) => alert("Fetching Gateway is failed to add..!!"));
    },
    
  },
};
</script>