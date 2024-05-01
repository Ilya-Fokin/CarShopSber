FROM alpine:3.19

RUN apk update && \
    apk add openjdk21

WORKDIR /app

COPY target/CarShopSber-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "CarShopSber-0.0.1-SNAPSHOT.jar"]