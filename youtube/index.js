const fs = require('fs');
const ytdl = require('ytdl-core');
const express = require('express');
const app = express();
const cors = require('cors');
const bodyParser = require('body-parser');

app.use(cors());
app.use(bodyParser.json());

app.get('/data', (req, res) => {
    // res.send('hello');
    console.log(req.query.url);

    // const videoStream = 
    // ytdl(req.query.url)
    ytdl('https://www.youtube.com/watch?v=zj9Xo8k-GmU')
  .pipe(fs.createWriteStream('video.mp4'));

//   videoStream.on('finish', ()=>{
//     res.download(videoPath, 'video.mp4', (err) => {
//       if(err) {
        
//       }
//     })
//   });
// });

app.listen(3000, () => {
    console.log("Server is listening to port 3000");
});