# START CLUSTER

ensure that hadoop, zookeeper and accumulo instance is running

# Build

Clean and install project dependency
``` bash
    mvn clean && mvn install
```

# Run
The maven build will provide two jar files (target folder)

    - Bundle-app.jar
        - It is bundled with all its dependencies

    - slim-app.jar
        - Dependencies is in the slim-app.lib folder

 ``` bash
    java -jar target/slim-app.jar
```

``` bash
    java -jar target/bundle-app.jar
```
