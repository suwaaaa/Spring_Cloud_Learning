// Rabbit MQ  适用于Provider_Replicas_Publisher_Module 以及 Consumer_Replicas_Consume_Module 两个module
// 1.安装
// 1.1 拉取镜像  docker pull rabbitmq:3-management、
// 1.2 也可以本地上传 docker load -i mq.tar
//2. 配置容器
/*
docker run \
 -e RABBITMQ_DEFAULT_USER=Javon \
 -e RABBITMQ_DEFAULT_PASS=123 \
 --name mq \
 --hostname mq1 \
 -p 15672:15672 \
 -p 5672:5672 \
 -d \
 rabbitmq:3-management
 */