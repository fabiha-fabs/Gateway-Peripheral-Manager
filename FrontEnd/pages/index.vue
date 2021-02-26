<template>
     <v-container> 
      <v-row>
      <v-col cols="12" md="4">
          
        </v-col>
        <v-col cols="12" md="4"> 
         <h1>Gateway Peripheral Manager</h1>
          <h3>Front End with VUE js</h3>
          <v-divider></v-divider><br />
          </v-col>
          
          <v-col cols="12" md="6">
            <v-p>
            <h4>Project Outline</h4>
            Rest API Project allows you to manage gateways and their associated devices. 
            You can create gateways and peripheral devices. 
            Then connect/disconnect peripherals from gateways.
          </v-p>
           <v-divider></v-divider>
         
          </v-col> 
           <v-col cols="12" md="6">
          
          <v-p>
            <h4>Technologies</h4>
            <ul>
              <li>Backend is built by Spring framework with server side language Java.</li>
              <li>For creating frontend, I used here VUE js.</li>
              <li>Rest Service is associated with it.</li>
              <li>This product will run for in memory database.</li>
            </ul>
          </v-p>
          <v-divider></v-divider> <br/>
           <v-p>
          <h4>How to use</h4>
            <ul>
              <li>Check the menu to perform different operations.</li>
              <li>You can connect peripheral devices to a gateway.</li>
              <li>Gateways which already have 10 peripheral devices connected, 
                do not appear as gateway suggestion while creating or connecting peripherals.</li>
              <li>While adding gateways, provide a VALID IPv4 address.</li>
            </ul>
          </v-p>
         <v-divider></v-divider>
        </v-col> 
         
      </v-row>
    </v-container> 

</template>

<script>
export default {
  data: () => ({
    test: "test again",
  }),
};
</script>

<style>
</style>
