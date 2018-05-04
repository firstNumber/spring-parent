FROM java:8-jre
VOLUME /tmp
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
ADD app.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]