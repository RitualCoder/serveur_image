import { Ref, ref, VueElement } from 'vue'
import Axios, { AxiosResponse } from 'axios'

export function getImages(images: Ref<Array<{id: number, name: string}>>) {
  Axios.get('/images')
  .then(function (response) {
      images.value = response.data;
  });
}

