FROM openjdk:13-ea-7-jdk-oraclelinux7

WORKDIR /usr/app
ADD . /usr/app

EXPOSE 8080

CMD ["./gradlew", "bootRun"]

