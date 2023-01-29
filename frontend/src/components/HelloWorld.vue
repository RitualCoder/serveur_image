<script setup lang="ts">
import axios from 'axios'
import { Ref, ref } from 'vue'
import { getImages } from '../http-api'


const selectedImageId = ref(0)
const file = ref()
var images: Ref<Array<{id: number, name: string}>> = ref([]);

getImages(images);

function download_image(ImageId: number, name : string){
  axios({
      method: 'get',
      url: 'http://localhost:4000/images/'+ ImageId,
      responseType: 'blob'
    }).then(response => {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', name);
      document.body.appendChild(link);
      link.click();
    });
}

function handleFileUpload(event: any){
  file.value = event.target.files[0]
}

function submitFile() {
  let formData = new FormData();
  formData.append('file', file.value);
  axios.post('/images', formData, {
    headers: {'Content-Type': 'multipart/form-data'}
  })
  .then(function(){
    console.log('SUCCESS!!');
  })
  .catch(function(){
    console.log('FAILURE!!');
  });
  window.location.reload();
}

</script>

<template>
  <div class="wrapper-options">
    <div class="wrapper-select">
      <h1 class="title1">Choose an image</h1>
      <div class="select-container">
        <select class="selecteur" v-model="selectedImageId">
          <option v-for="image in images" :value="image.id">{{ image.name }}</option>
        </select>
      </div>
      
      <div class="download-container">
        <button v-on:click="download_image(selectedImageId, images[selectedImageId].name)">Download</button>
      </div>
    </div>

    <div class="wrapper-post">
      <div class="choosefile-container">
        <label class="btn-file">
          <input class="input-file" type="file" id="file" ref="file" @change="handleFileUpload"/>
        </label>
      </div>
      <div class="submit-container">
        <button  class="sub-btn" @click="submitFile">Submit</button>
      </div>
    </div>
    
    <div class="wrapper-image">
      <div class="img-ctr">
        <img class="image-container" v-bind:src="'http://localhost:4000/images/' + selectedImageId" alt="image"> 
      </div>
    </div>

  </div>
  
</template>

<style scoped>

button{
  width: 75%;
  height: 50px;
  border: 1px solid #E8EAED;
  border-radius: 10px;
  background: white;
  box-shadow: 0 1px 3px -2px #9098A9;
  cursor: pointer;
  font-family: inherit;
  font-size: 16px;
}

.title1 {
    grid-area: Title1;
    text-align: center;
    color: white;
    margin: 0px;
    font-family: 'Montserrat';
    padding-top: 40px;
}

.select-container {
    display: flex;
    align-items: center;
    justify-content: center;
}

.selecteur {
    grid-area: Select;
    padding: 7px 40px 7px 12px;
    width: 75%;
    height: 50px;
    border: 1px solid #E8EAED;
    border-radius: 10px;
    background: white;
    box-shadow: 0 1px 3px -2px #9098A9;
    cursor: pointer;
    font-family: inherit;
    font-size: 16px;
    transition: all 150ms ease;
}

.img-ctr {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    min-width: 525px;
}

.img-ctr img {
    max-width: 60%;
    max-height: 400px;
    backdrop-filter: blur(2px);
}

.download-container, .post-image {
  display: flex;
  align-items: center;
  justify-content: center;
}

.post-image label {
  width: 100%;
}





</style>
