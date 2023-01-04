FROM amazoncorretto:18
RUN mkdir /app
WORKDIR /app
ADD ./target/supplier-server-1.0-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "supplier-server-1.0-SNAPSHOT.jar"]