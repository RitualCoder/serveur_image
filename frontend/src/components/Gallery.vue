<script setup lang="ts">
import { ref, defineComponent } from 'vue'
import axios from 'axios'
import { get_image } from '../http-api'

const images = ref([])
const selectedImageId = ref(0)
const imageName = ref("")

axios({
  method: 'get',
  url: 'http://localhost:4000/images/',
  responseType: 'json'
  })
  .then(res => {
    images.value = res.data
  })


</script>

<template>
    <h1 style="text-align: center; color: #646cff" >Galerie des images</h1>
    <div class="image-container">
        <img v-for="image in images" :key="image.id" v-bind:src="'http://localhost:4000/images/' + image.id" alt="image">
    </div>

</template>

<style scoped>
.image-container {
    margin-top: 20px;
    display: flex;
    width: 100%;
    justify-content: center;
    border: solid;
}

.image-container img{
    display: block;
    height: 200px;
    object-fit: cover;
    max-width:20%;
    max-height:20%;
    margin: 3px;
}
</style>
