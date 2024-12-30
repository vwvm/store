# -*- coding: utf-8 -*-
# flake8: noqa
import os
from qiniu import Auth, BucketManager, put_file
from qiniu.services.storage import bucket


class QiniuServer:
    def __init__(self,
                 access_key: str = "zknxbe6OotPFY3ZfbcdCCjRY_ftPAYx-A55PECW9",
                 secret_key: str = "g0sbqyvnaX5_lFg_iBy1_CXPdJ46Kcqpw8TBvHYO",
                 bucket_name: str = "saas135",
                 folder: str = "src/",
                 base_url: str = "https://cdn.qn.saas135.com/"):
        self.access_key = access_key
        self.secret_key = secret_key
        self.bucket_name = bucket_name
        self.folder = folder
        self.base_url = base_url
        self.q = Auth(self.access_key, self.secret_key)
        self.bucket_manager = BucketManager(self.q)

    def file_upload(self, file_path: str) -> str:
        """
        上传指定的本地文件到 OSS
        @param file_path: 要上传的本地文件的路径
        @return 存储桶key的值
        """
        # 使用传入的文件路径
        localfile = file_path

        # 获取文件的名称
        filename = os.path.basename(file_path)

        # 组合目标 key，模拟目录结构，将文件名称拼接到 key 中
        key = self.folder + filename  # 使用类的 folder 变量

        mime_type = "text/plain"
        params = {'x:a': 'a'}

        # 获取上传 token
        token = self.q.upload_token(self.bucket_name, key)  # 使用类的 bucket_name 变量

        # 使用七牛云 put_file 上传文件
        ret, info = put_file(token, key, localfile, mime_type=mime_type, check_crc=True)

        # 打印上传后的信息并进行断言检查
        print(info)
        print(ret)

        return key

    def get_object_url(self, key: str) -> str:
        return self.base_url + key

    def list_all(self, prefix: str = None, limit: int = None):
        marker = None
        eof = False
        while eof is False:
            ret, eof, info = self.bucket_manager.list(self.bucket_name, prefix=prefix, marker=marker, limit=limit)
            marker = ret.get('marker', None)
            for item in ret['items']:
                print(item['key'])
                pass
        if eof is not True:
            # 错误处理
            pass


# Initialize the QiniuUploader class
uploader = QiniuServer()

# Upload a file and get its key
key = uploader.file_upload(r"C:\Users\BlackBox\Desktop\3adca6332e774cab97aa8923fb52261b.mp4")

# Generate and print the URL for the uploaded file
url = uploader.get_object_url(key)
print(f"File URL: {url}")
