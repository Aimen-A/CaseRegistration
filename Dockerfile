FROM openjdk:8
ADD target/case-registration-app.jar case-registration-app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "case-registration-app.jar"]
