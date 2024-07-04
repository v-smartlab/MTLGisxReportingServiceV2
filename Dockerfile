# Use the official Tomcat image with JDK 21
#FROM tomcat:11.0-jdk17
FROM tomcat:11.0-jdk17

# Create an environment variable for the application name
ENV APP_NAME mtl_gisc_poc
#ENV CATALINA_OPTS="-Dkey=value"
# Set the working directory
WORKDIR /data
COPY /data/ /data/

WORKDIR /usr/local/tomcat
# Copy the built WAR file to the Tomcat webapps directory
COPY /target/MtlGisxPocDocker02-0.0.1-SNAPSHOT.war webapps/${APP_NAME}.war

# Expose the default Tomcat port
EXPOSE 6969

# Run Tomcat
CMD ["catalina.sh", "run"]