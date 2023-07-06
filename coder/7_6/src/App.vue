<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>>

<style>
* {
  margin: 0;
  padding: 0;
}
.clearfix {
  zoom: 1;
}
.clearfix:after {
  content: ".";
  display: block;
  visibility: hidden;
  height: 0;
  clear: both;
}
</style>
