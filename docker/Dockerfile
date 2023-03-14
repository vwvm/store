FROM quay.io/minio/minio:latest
# 设置环境变量
ENV BASE_PATH "/root"
# 指定工作路径 
WORKDIR $BASE_PATH
# 声明端口
EXPOSE 49000
# 声明工作目录
VOLUME $BASE_PATH
# 添加文件到工作目录
ADD "vwvm.png" "./"
# 容器启动后执行
ENTRYPOINT ["ls"]
# 容器启动后执行 仅最后一条生效
CMD ["/root"]
