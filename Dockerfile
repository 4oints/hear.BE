#실행하기 위한 환경만 필요하면 jre, 개발까지면 jdk
FROM openjdk:11-jre

#컨테이너 안에 jar 파일은 app.jar 될꺼임
COPY build/libs/heardot.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]