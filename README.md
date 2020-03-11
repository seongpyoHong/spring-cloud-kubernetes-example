# spring-cloud-kubernetes-example
The Examples for Spring-Cloud-Kubernetes in https://github.com/seongpyoHong/spring-cloud-kubernetes.

## Index
### 1. kubernetes-hello-world-example
Spring Cloud Kubernetes를 통해 스프링 부트 어플리케이션을 쿠버네티스 위에 배포하고, `DiscoveryClient`를 통해 쿠버네티스에 등록된 서비스를 조회
#### Result

    > curl <External-Ip>:<NodePort>
    Hello World!
    
    > curl <External-Ip>:<NodePort>/services
    Avaliable list of services

### 2. kubernetes-circuitbreaker-ribbon-example
- `Netflix Ribbon`을 통해 Client-Side Loadbalancing 제공
- `Netfilx Hystrix`를 통해 Circuit Breaker Pattern 제공

#### Execution

- Build docker image & Push to GCR
```
./gradlew jib
```
- Create k8s resources
```
kubectl apply -f resources/
```

#### **result**

- Pods
```
NAME                              READY   STATUS    RESTARTS   AGE
greeting-service-78b54b56-9495b   1/1     Running   0          6m25s
name-service-1-6f84bbd478-6v69z   1/1     Running   0          3m54s
name-service-1-6f84bbd478-wdzz9   1/1     Running   0          20m
name-service-2-df9b9bf59-wkvvw    1/1     Running   0          20m
```

- Get list of service
```
❯ curl http://34.64.104.57:30411/services
    [greeting-service, kubernetes, name-service-1, name-service-2]
```
- Verify loadbalance by calling name-service-1 (replicas=2)
```
❯ curl http://<External-Ip>:<NodePort>/greeting1
    Hello from name-service-1-6f84bbd478-6v69z! 
❯ curl http://<External-Ip>:<NodePort>/greeting1
    Hello from name-service-1-6f84bbd478-wdzz9!
```

- Call name-service-2 (replicas=1)
```
❯ curl http://<External-Ip>:<NodePort>/greeting2
    Hello from name-service-2-df9b9bf59-wkvvw!
```

- Test Circuit Breaker by calling name-service-2(replicas=0)
    - Timeout : 1000 ms
```
❯ kubectl scale --replicas=0 deployment name-service-2
    deployment.extensions/name-service-2 scaled

❯ curl http://<External-Ip>:<NodePort>/greeting2
    Hello from fallback!
```

### 3. kubernetes-reload-example
- ConfigMap / Secret 변경에 따른 auto-reload 제공

#### Execution

- Build docker image & Push to GCR
```
./gradlew jib
```
- Create k8s resources
```
kubectl apply -f resources/
```
- Change ConfigMap
```
kubectl apply -f src/configMap.yml
```

#### Result

**Before Changing the ConfigMap**

- **ConfigMap**

        apiVersion: v1
        kind: ConfigMap
        metadata:
          name: reload-example
        data:
          application.properties: |-
            bean.message=Hello World!
            another.property=value

- Output

        2020-03-11 13:51:21.844  INFO 1 --- [main] c.s.e.KubernetesReloadExampleApplication : Started KubernetesReloadExampleApplication in 8.698 seconds (JVM running for 9.357)
        The first message is: Hello World!
        The other message is: Dummy Message

**After Changing the ConfigMap**

- ConfigMap

        apiVersion: v1
        kind: ConfigMap
        metadata:
          name: reload-example
        data:
          application.properties: |-
            bean.message=Changed Version!
            another.property=value

- Output

        2020-03-11 13:55:52.554  INFO 1 --- [scheduling-1] o.s.boot.SpringApplication               : Started application in 0.436 seconds (JVM running for 280.067)
        The first message is: Changed Version!
        The other message is: Dummy Message
#### 4. kubernetes-leader-election-example
#### 5. kubernetes-zipkin-example	
