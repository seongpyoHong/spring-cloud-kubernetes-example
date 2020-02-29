# spring-cloud-kubernetes-example
The Examples for Spring-Cloud-Kubernetes in https://github.com/seongpyoHong/spring-cloud-kubernetes.

### Index
#### 1. kubernetes-hello-world-example
Spring Cloud Kubernetes를 통해 spring-boot application을 k8s 위에 배포하고, k8s의 service를 조회
##### Result

    > curl http://127.0.0.1:<NodePort>
    Hello World!
    
    > curl http://127.0.0.1:<Nodeport>/services
    Avaliable list of services

#### 2. kubernetes-circuitbreaker-ribbon-example	
#### 3. kubernetes-hello-world-example	
#### 4. kubernetes-leader-election-example
#### 5. kubernetes-reload-example	
#### 6. kubernetes-zipkin-example	
