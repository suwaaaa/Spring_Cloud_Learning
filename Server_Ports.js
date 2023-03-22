// Set up 2023-03-22

// Vmware CentOS 7 VMnet8  IP: 192.168.3.128
// MySQL :          3306
// RabbitMQ : Client:5672 Server:15672
// Kibana:     5601 (Connect Elasticsearch)
// Elasticsearch:  9200


//Localhost 127.0.0.1
// Eureka_Manager_Module 9000  (To Eureka)
// Eureka_Manager_Replicas_Module 9001 (To Eureka)
// Provider_Module 9020  (To Eureka)
// Consumer_Module 9030 (To Eureka)
// Feign_Module 9041 (To Eureka, Sentinel)
// Zuul_API_Module 9005 (To Eureka)
// Provider_Replicas_Module 9025 (To Eureka, H2, Actuator)
// Provider_Replicas_Mail_Module 9040 (To Eureka. QQ Mail Server, Spring Admin)

// Spring_Gateway_Module 10000 (To Nacos)
// Consumer_Replicas_Consume_Module  8084 (Connect RabbitMQ)(To Nacos, Sentinel)
// Provider_Replicas_Elasticsearch_Module 8089 (Connect RabbitMQ, MySQL, Elasticsearch)(To Nacos, Sentinel)
// Provider_Replicas_Publisher_Module 8099 (Connect RabbitMQ, MySQL)(To Nacos, Sentinel)

// Spring_Boot_Admin_Module 8995 (To Spring Admin)
// Hystrix_Module 9040
// Consumer_Replicas_Module Null



//Open with Jar
// Nacos 8848
// Sentinel 8850