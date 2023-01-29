<script setup lang="ts">
import { Ref, ref } from 'vue'
import { getImages } from '../http-api'

const selectedImageId = ref(0)
const imageName = ref("")
var images: Ref<Array<{id: number, name: string}>> = ref([]);

getImages(images)

</script>
<template>
  <div class="wrapper-gallery">
    <h1 class="Title2">Image gallery</h1>
    <div class="image-container">
        <img v-for="image in images" :key="image.id" v-bind:src="'http://localhost:4000/images/' + image.id" alt="image">
    </div>
  </div>
</template>

<style scoped>
.image-container {
    margin: 0px;
    padding: 20px 0px 40px 0px;
    display: block;
    width: 100%;
    text-align: center;
}

.image-container img{
    object-fit: scale-down;
    max-width:10%;
    display: inline-block;
    margin: 5px;
}

.Title2 {
  margin: 0px;
  padding-top: 40px;
  text-align: center;
  color: white;
}
</style>
