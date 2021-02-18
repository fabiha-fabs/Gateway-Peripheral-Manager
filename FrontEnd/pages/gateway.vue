<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="4"> </v-col>
      <v-col cols="12" md="4">
        <h1>Register New Gateway</h1>
        <v-divider></v-divider><br/><v-btn @click="allGateways()" color="#80CBC4" elevation="11">Available Gateways</v-btn><br/><br/>
      <br/>
        <form>
          <v-text-field
            v-model="serialNo"
            label="Serial No"
            :counter="10"
            :rules="serialNoRules"
            required
          ></v-text-field>
          <v-text-field
            v-model="name"
            label="Name"
            :counter="10"
            :rules="nameRules"
            required
          ></v-text-field>
          <v-text-field
            v-model="ipAddress"
            label="IP Address"
            :rules="ipAddressRules"
            required
          ></v-text-field>
          <v-btn @click="submit" color="#80CBC4" elevation="9">submit</v-btn>
          <v-btn @click="clear" color="#80CBC4" elevation="9">clear</v-btn>

        </form>
      </v-col>
      <v-col cols="12" md="4"></v-col>
    </v-row>
  </v-container>
</template>

<script>

  export default {

    data: () => ({
      serialNo: '',
      name: '',
      ipAddress: '',

    serialNoRules: [v => !!v || "Serial No is required",
                    v => (v && v.length <= 10) || "Serial No must be less than 10 characters"
    ],
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 10) || "Name must be less than 10 characters",
    ],
    
    ipAddressRules: [
      v => !!v || "IP Address is required",
      v => /.+(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)+/.test(v) || "IP Address must be valid",
    ],
    }),
 
 methods: {
     async submit () {
          
      console.log(this.$refs)
        this.$axios
            .post('http://localhost:8989/RestApiProject/addGateway/',{
                serialNo : this.serialNo,
                name : this.name,
                ipAddress : this.ipAddress
            },this.config)
            .then((res) => {
              if (res.status === 200) {
                alert('Added Gateway successfully...!!')
              }
            })
            .catch((error) => alert('Gateway failed to add..!!'))
      },
      clear () {
        this.serialNo = ''
        this.name = ''
        this.ipAddress = ''
      },

      allGateways() {
            //path: '/availablegateways'
            this.$router.push('/availablegateways')

      }
    } 
  }
</script>