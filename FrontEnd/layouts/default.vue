<template>
<v-app>
    <v-app-bar class="white--text" color="#26A69A" app>
      <v-app-bar-nav-icon color="white" @click="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Menu</v-toolbar-title>
    </v-app-bar>
  
    <v-main>
      <br><br>
      <v-row
        align="center"
        justify="center"
      >
      <Nuxt/>
      </v-row>
    </v-main>

    <v-navigation-drawer 
      v-model="drawer"
      absolute
      temporary
    >
      <v-list-item>
        <v-list-item-avatar>
          <v-img src="/images/fabiha-fabs.jpg"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>Fabiha Tabassum</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      
      <v-list dense>
        <div v-for="item in menus" :key="item.route">
        <v-list-item
          link :to="item.route"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        </div>
      </v-list>
    </v-navigation-drawer>
    </v-app>
</template>


<style>
</style>
<script>
export default {
  data() {
    return {
     drawer: false,
        menus: [
          { title: 'Home', icon: 'mdi-home-city', route: '/' },
          { title: 'Register New Gateway', icon: 'mdi-gate-nor', route: '/gateway'  },
          { title: 'Register New Peripheral', icon: 'mdi-monitor-cellphone-star', route: '/peripheral'  },
          { title: 'All Gateways', icon: 'mdi-format-list-text', route: '/availablegateways'  },
          { title: 'All Peripherals', icon: 'mdi-clipboard-list-outline', route: '/availableperipherals'  },
        ],
        mini: true,
    };
  },
};
</script>