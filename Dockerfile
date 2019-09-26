FROM tomcat:7-jre8-alpine
COPY /shop/target/shop.war /usr/local/tomcat/webapps/shop.war
WORKDIR /usr/local/tomcat
EXPOSE 8080