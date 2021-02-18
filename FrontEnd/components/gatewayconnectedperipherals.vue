<template>
  <div>
    <v-btn
      small
      color="#776A7F"
      text-color="white"
      @click="connectedPeripherals"
      id="peripheral"
    >
      peripheral
    </v-btn>
    <v-timeline align-top dense>
      <v-timeline-item
        v-for="peripheral in connectedPeripheralList"
        :key="peripheral.uid"
        color="#B59DD9"
        small
      >
        <div>
          <div class="font-weight-normal">
            <strong>{{ peripheral.uid }}</strong>
            {{ peripheral.vendor }} - {{ peripheral.date }} -
            {{ peripheral.status }}

            <v-btn color="white" fab class="orange--text" x-small 
                    @click="disconnectPeripheralFromGateway(peripheral)">
                <v-tooltip right >
                <template v-slot:activator="{ on, attrs }">
                <v-icon v-bind="attrs" v-on="on">mdi-delete-off</v-icon>
                </template>
                 <span>Disconnect Peripheral</span>
                </v-tooltip>
            </v-btn>
          </div>
        </div>
      </v-timeline-item>
    </v-timeline>
  </div>
</template>
<script>
export default {
  data: () => ({
    connectedPeripheralList: [],
  }),
  props: ["gatewaySerialNo"],
  methods: {
    connectedPeripherals() {
      console.log("serialNo = " + this.gatewaySerialNo);
      this.$axios
        .get(
          "http://localhost:8989/RestApiProject/gateways/" +
            this.gatewaySerialNo +
            "/peripherals"
        )
        .then((res) => {
          this.connectedPeripheralList = res.data;
          console.log();
        });
    },
    disconnectPeripheralFromGateway(peripheral){
        this.$axios
        .put(
          "http://localhost:8989/RestApiProject/" +
            peripheral.uid +
            "/disconnectPeripheral"
        )
        .then((res) => {
          if (res.status === 200) {
            peripheral.gateway = null;
            //peripheral = null;
            this.connectedPeripheralList = this.connectedPeripheralList.splice(peripheral.uid)
            alert("Peripheral disconnected..!!");
          }
        })
        .catch((error) => alert("peripheral failed to disconnect..!!"));
    },
  },
};
</script>