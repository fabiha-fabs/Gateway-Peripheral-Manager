<template>
  <v-hover v-slot="{ hover }">
    <v-card
      :elevation="hover ? 12 : 2"
      :class="{ 'on-hover': hover }"
      color="#607D8B"
      dark
    >
      <v-card-title class="headline">
        Device: {{ peripheral.uid }} - {{ peripheral.vendor }}
      </v-card-title>

      <v-card-subtitle>
        Created date: {{ peripheral.createdDate }} <br>
        Status: 
        <v-btn
          small
          color="#616161"
          v-model="status"
          @click="updateStatus(peripheral)"
        >
          {{ peripheral.status }}</v-btn><br/>
        Connected Gateway: {{ peripheral.gateway }}
      </v-card-subtitle>

      <v-card-actions>
        <v-btn
          absolute
          color="black"
          class="white--text"
          fab
          small
          right
          bottom
          v-if="peripheral.gateway !== null"
          @click="disconnectPeripheral(peripheral)"
        >
          <v-icon>mdi-lan-disconnect</v-icon>
        </v-btn>
        <v-btn
          absolute
          color="black"
          class="white--text"
          fab
          small
          right
          bottom
          v-if="peripheral.gateway === null"
          @click="gatewayListsToAddPeripheral"
        >
          <v-icon>mdi-lan-connect</v-icon>
        </v-btn>
        <v-dialog v-model="openGatewayDialog" max-width="500px">
          <v-card>
            <v-card-title> Add Gateway to Peripheral </v-card-title>
            <v-card-text>
              <v-select
                :items="gatewayLists"
                label="--Select Gateway--"
                item-value="serialNo"
                item-text="name"
                v-model="gatewaySerialNoSelected"
                @change="connectPeripheral(peripheral)"
              ></v-select>
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" text @click="openGatewayDialog = false">
                Close
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-card-actions>
    </v-card>
  </v-hover>
</template>
<script>
export default {
  data: () => ({
    openGatewayDialog: false,
    gatewaySerialNoSelected: '',
    gatewayLists: [],
    status: '',
  }),
  //props: ["gatewaySerialNo"],
  props: ["peripheral"],
  methods: {
    disconnectPeripheral(peripheral) {
      this.$axios
        .put(
          "http://localhost:8989/RestApiProject/" +
            this.peripheral.uid +
            "/disconnectPeripheral"
        )
        .then((res) => {
          if (res.status === 200) {
            this.peripheral.gateway = null;
            alert("Gateway disconnected..!!");
          }
        })
        .catch((error) => alert("Gateway failed to disconnect..!!"));
    },
    gatewayListsToAddPeripheral() {
        this.openGatewayDialog = true
      this.$axios
        .get('http://localhost:8989/RestApiProject/availableGateways/')
        .then((res) => {
          this.gatewayLists = res.data;
          console.log(this.gatewayLists);
        })
        .catch((error) => alert("Fetching Gateway is failed to add..!!"));
    },
    connectPeripheral(peripheral) {
         //console.log(peripheral.uid + '-->' + this.gatewaySerialNoSelected)
         this.$axios
        .put('http://localhost:8989/RestApiProject/' + peripheral.uid 
                    +'/connectPeripheral/' + this.gatewaySerialNoSelected +'/')
        .then((res) => {
          if (res.status === 200) {
            peripheral.gateway = res.data.gateway
            console.log(peripheral.gateway)
            alert("Gateway connected successfully..!!")
            this.openGatewayDialog = false
            //this.gatewaySerialNoSelected = -1
          }
        })
        .catch((error) => alert("Gateway failed to connect..!!"));
    },
    updateStatus(peripheral){
        if(peripheral.status === 'Online')
            this.status = 'Offline'
        else if(peripheral.status === 'Offline')
            this.status = 'Online'    
        this.$axios
        .put('http://localhost:8989/RestApiProject/' + peripheral.uid 
                    +'/peripheralStatus/' + this.status +'/')
        .then((res) => {
          if (res.status === 200) {
            peripheral.status = res.data.status
          }
        })
        .catch((error) => alert("status failed to update..!!"+error));
    },
  },
};
</script>