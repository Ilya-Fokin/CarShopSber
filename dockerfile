FROM openjdk:21-jdk

WORKDIR /app

COPY target/CarShopSber-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "CarShopSber-0.0.1-SNAPSHOT.jar"]