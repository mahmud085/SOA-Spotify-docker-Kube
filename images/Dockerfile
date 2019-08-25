FROM openjdk:8-slim
COPY . /PROJECT
WORKDIR /PROJECT
CMD gradlew shadowJar

EXPOSE 8080

CMD ["java", "-jar" , "/PROJECT/build/libs/ImageApi.jar"]


