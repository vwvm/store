

// Instantiate the minio client with the endpoint
// and access keys as shown below.

const upFile = (file)=>{
    import { createRequire } from 'module';
    const require = createRequire(import.meta.url);
    const Minio = require('minio');
    const minioClient = new Minio.Client({
        endPoint: 'localhost',
        port: 49090,
        useSSL: true,
        accessKey: 'root1235',
        secretKey: 'root1235'
    });
    if (!file){
        file = '/tmp/photos-europe.tar';
    }


    minioClient.makeBucket('paper', 'us-east-1', function(err) {

        if (err) return console.log(err)

        console.log('Bucket created successfully in "us-east-1".')

        const metaData = {
            'Content-Type': 'application/octet-stream',
            'X-Amz-Meta-Testing': 1234,
            'example': 5678
        };
        // Using fPutObject API upload your file to the bucket europetrip.
        minioClient.fPutObject('europetrip', 'photos-europe.tar', file, metaData, function(err, etag) {
            if (err) return console.log(err)
            console.log('File uploaded successfully.')
        });
    });
}

export const getFile = () =>{
    const Minio = require('minio');

    const minioClient = new Minio.Client({
        endPoint: 'localhost',
        port: 49090,
        useSSL: true,
        accessKey: 'root1235',
        secretKey: 'root1235'
    });

    const bucketName = 'paper';
    const objectName = '2121&2023届毕业论文(设计）指导手册（印刷稿）&1682556400915.docx';
    const filePath = '/download/file';

    minioClient.fGetObject(bucketName, objectName, filePath, function(err) {
        if (err) {
            return console.log(err)
        }
        console.log('File downloaded successfully.')
    })
}

