# syntax=docker/dockerfile:1

FROM adoptopenjdk/maven-openjdk11:latest

COPY pom.xml .
RUN mvn compile

COPY ./ .

RUN chmod -R 700 ./docker


# If CMD is used to provide default arguments for the ENTRYPOINT instruction, 
# both the CMD and ENTRYPOINT instructions should be specified with the JSON array format.
# https://docs.docker.com/engine/reference/builder/#cmd
ENTRYPOINT ["./docker/start-api-testing.sh"]
CMD ["@cognitive"]
