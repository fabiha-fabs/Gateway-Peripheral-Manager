<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="4"></v-col>
      <v-col cols="12" md="4">
        <h1>Register New Peripheral</h1>
        <v-divider></v-divider><br />
        <v-btn @click="allPeripherals" color="#80CBC4">Available Peripherals</v-btn><br /><br />
      </v-col>
      <v-col cols="12" md="4"></v-col>
    </v-row>
    
    <form>
      <v-row>
        <v-col cols="12" md="4"></v-col>
        <v-col cols="12" md="4">
          <v-text-field
            v-model="uid"
            label="Uid"
            :counter="10"
            :rules="uidRules"
            required
          ></v-text-field
        ></v-col>
        <v-col cols="12" md="4"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="4"></v-col>
        <v-col cols="12" md="4">
          <v-text-field
            v-model="vendor"
            label="Vendor"
            :counter="20"
            :rules="vendorRules"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="4"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="4"></v-col>
        <v-col cols="12" md="4">
          <datepickercomponent v-on:updateCreationDate="updateCreationDate($event)"></datepickercomponent>
        </v-col>
        <v-col cols="12" md="4"></v-col>
      </v-row>
      <v-row>
          <v-col cols="12" md="4"></v-col>
          <v-col cols="12" md="4">
         <v-select :items="statusItems" label="Status" v-model="status"  dense></v-select> 
           </v-col>
          <v-col cols="12" md="4"></v-col>
      </v-row>
      <v-row>
          <v-col cols="12" md="4"></v-col>
          <v-col cols="12" md="4">
          <v-select
            :items="gatewayLists"
            label="Available Gateways"
            item-text="name"
            item-value="serialNo"
            v-model="serialNo" 
            :change="onChangeSerialNo()"
            clearable
            dense
          ></v-select> 
          </v-col>
          <v-col cols="12" md="4"></v-col>
      </v-row>
      
      <v-row>
        <v-col cols="12" md="4"></v-col>
        <v-col cols="12" md="4">
          <v-btn @click="submit" depressed color="#80CBC4" elevation="9">submit</v-btn>
          <v-btn @click="clear" depressed color="#80CBC4" elevation="9">clear</v-btn>
        </v-col>
        <v-col cols="12" md="4"></v-col>
      </v-row>
    </form>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    uid: "",
    vendor: "",
    statusItems: ["Online", "Offline"],
    status: [],
    date: '',
    menu: false,
    modal: false,
    menu2: false,
    uidRules: [
      (v) => !!v || "User Id is required",
      (v) => (v && v.length <= 10) || "User Id must be less than 10 characters",
    ],
    vendorRules: [
      (v) => !!v || "Vendor is required",
      (v) => (v && v.length <= 20) || "Vendor must be less than 20 characters",
    ],
    gatewayLists: [],
    serialNo: ''
  }),
    created() {
        this.gatewayListsToAddPeripheral()
    },
  methods: {
    async gatewayListsToAddPeripheral() {
      //if (this.$refs.form.validate()) {
      this.$axios
        .get("http://localhost:8989/RestApiProject/availableGateways/")
        .then((res) => {
          this.gatewayLists = res.data;
          console.log(this.gatewayLists);
        })
        .catch((error) => alert("Fetching Gateway is failed to add..!!"));
      //}
    },
    updateCreationDate(createdDate) {
        //year, month, day
        let dateArr = []
        dateArr = createdDate.split('-')
        this.date = dateArr[0] + '-' + dateArr[2] + '-' + dateArr[1]
        alert(this.date)
    },
    clear() {
      this.uid = "";
      this.vendor = "";
      this.status = "";
      this.gatewayLists = "";
    },
    allPeripherals() {
      //path: '/availablegateways'
      this.$router.push("/availableperipherals");
    },
    onChangeSerialNo() {
      console.log(this.serialNo)
    },
    submit() {

        console.log(this.serialNo);
    
        if(this.serialNo === null || this.serialNo === ''){
            this.$axios
            .post('http://localhost:8989/RestApiProject/addDiscritPeripheral',{
                uid : this.uid,
                vendor : this.vendor,
                createdDate : this.date,
                status: this.status,
                
            },this.config)
            .then((res) => {
              if (res.status === 200) {
                alert('Created Peripheral successfully...!!')
              }
            })
            .catch((error) => alert('Peripheral failed to create..!!'))
        }else{
            this.$axios
            .post('http://localhost:8989/RestApiProject/gateway/'+ this.serialNo +'/addPeripheral',{
                uid : this.uid,
                vendor : this.vendor,
                date : this.date,
                status: this.status,
                
            },this.config)
            .then((res) => {
              if (res.status === 200) {
                alert('Created Peripheral successfully...!!')
              }
            })
            .catch((error) => alert('Peripheral failed to create..!!'))
        }
        
    },
  },
};
</script>