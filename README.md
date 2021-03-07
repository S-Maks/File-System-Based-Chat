# File-System-Based-Chat

Hi! This is tutorial about docker!

1. First of all, you have to make a jar file. You can use Maven functionality.
   You must do the following: ```mvn package```

2. Then you'll have to create a Dockerfile, but in this repository I created it for you. 
   It looks like something like this:
   ```
   FROM openjdk:8-jdk-alpine
   VOLUME /tmp
   ADD target/File-System-Based-Chat-1.0-SNAPSHOT.jar app.jar
   ENTRYPOINT ["java","-jar","/app.jar"]
   ```
   (don't forget to put a space after the keywords)
   
We created Dockerfile. Next step, create an image.

3. Create image: ```docker build -t file-system-based-chat .```
4. If everything was successful, enter the command:```docker images```

You should see the name of your file in the list.

5. It remains only to run, you can do it like this: ```docker run --rm -p 8080:8080 file-system-based-chat```

**It works!**

* View all working ones: ```docker ps```
* Stop it: ```docker stop <Conteiner ID>```
