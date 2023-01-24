<script setup lang="ts">
import { ref, defineComponent, Events } from 'vue'
import axios from 'axios'
import { get_image } from '../http-api'
import Vue from "vue";


const selectedImageId = ref(0)
const file = ref<Blob | null>(null)
const images = ref([])

axios({
  method: 'get',
  url: 'http://localhost:4000/images/',
  responseType: 'json'
})
.then(res => {
  images.value = res.data
})

function handleFileUpload(event){
  file.value = new Blob([event.target.files[0]], { type: event.target.files[0].type });
}

function submitFile() {
  let formData = new FormData();
  formData.append('file', file.value.value);
  axios.post('/single-file', formData, {
    headers: {'Content-Type': 'multipart/form-data'}
  })
  .then(function(){
    console.log('SUCCESS!!');
  })
  .catch(function(){
    console.log('FAILURE!!');
  });
}

</script>

<template>
    <div class="selecteur">
    <h1 style="color: #646cff; text-align: center;">Selecteur d'images</h1>
    <select class="common-class" v-model="selectedImageId">
    <option v-for="image in images" :value="image.id">{{ image.name }}</option>
  </select>
  <button class="common-class" v-on:click="get_image(selectedImageId, images[selectedImageId].name)">Download</button>
  </div>

  <div class="image-container">
    <img v-bind:src="'http://localhost:4000/images/' + selectedImageId" alt="image"> 
  </div>

  <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" id="file" ref="file" @change="handleFileUpload"/>
      </label>
      <button @click="submitFile">Submit</button>
    </div>
  </div>
</template>

<style scoped>
.selecteur {
  text-align: center;
  top : 40px;
}

.image-container {
  margin-top: 20px;
  display: flex;
  width: 100%;
  justify-content: center;
}

.image-container img{
  height: 500px;
  object-fit: cover;
  max-width:30%;
  max-height:30%;
}

.common-class  {
  border-radius: 8px;
  border: 1px solid transparent;
  padding: 0.6em 1em;
  margin: 1em;
  font-size: 1em;
  font-weight: 500;
  font-family: inherit;
  cursor: pointer;
  transition: border-color 0.5s;
  color: #646cff;
}
.common-class:hover {
  border-color: #646cff;
}

</style>
