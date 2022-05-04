FROM openjdk:8-jdk-slim
COPY "./target/banking-card-service-0.1.jar" "card-service.jar"
ENTRYPOINT ["java","-jar","card-service.jar"]