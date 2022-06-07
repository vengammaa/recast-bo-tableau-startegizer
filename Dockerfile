FROM openjdk
COPY target/*.jar 
EXPOSE 8774
ENTRYPOINT ["java","-jar","/recast-bo-tableau-startegizer.jar"]
