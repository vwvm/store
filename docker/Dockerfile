#设置基准镜像
FROM mysql:latest

#设置工作目录
WORKDIR  '/docker-entrypoint-initdb.d'
ENV MYSQL_RANDOM_ROOT_PASSWORD=root
#将当前目录的init.sql文件复制到容器中的以上目录中。
ADD db.sql .
