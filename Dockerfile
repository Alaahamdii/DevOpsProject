FROM openjdk:11
WORKDIR /app
EXPOSE 8082
ARG JAR_FILE=target/DevOps_Project-1.0.jar
ADD ${JAR_FILE} DevOpsBack.jar
ENTRYPOINT ["java","-jar","/DevOpsBack.jar"]