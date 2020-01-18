FROM openjdk:11-jdk
EXPOSE 8080
ADD ./build/libs/app.jar apps/app.jar
ENTRYPOINT ["java","-jar","apps/app.jar"]