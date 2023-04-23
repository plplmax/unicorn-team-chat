<script setup>
import HeaderItem from './components/common/HeaderItem.vue'
</script>

<template>
  <header>
    <HeaderItem />
  </header>

  <main>
    <router-view></router-view>
  </main>
</template>