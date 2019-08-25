FROM openjdk:8-slim
COPY . /PROJECT
WORKDIR /PROJECT
CMD gradlew shadowJar

EXPOSE 8081

CMD ["java", "-jar" , "/PROJECT/build/libs/ChartApi.jar"]


