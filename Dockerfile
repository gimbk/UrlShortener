
#
# Package stage
#
FROM openjdk:11 as build
COPY --from=build /target/url-0.0.1-SNAPSHOT.jar url.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]