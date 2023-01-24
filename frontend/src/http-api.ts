import axios from 'axios'

export function get_image(selectedImageId: number, name : string) {
    axios({
      method: 'get',
      url: 'http://localhost:4000/images/'+selectedImageId,
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