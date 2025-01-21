FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/my-bookingservice-app.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "app.jar"]