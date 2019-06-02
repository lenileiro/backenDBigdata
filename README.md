# backenDBigdata

## Project Overview
backenDBigdata is a backend application that helps users analyse and store big data. A user loads their csv data into the cluster, then performs analysis using graphical interface.

## Project Use Case

    - Detecting bank fraud

## Technical details

```
JAVA - A programing language
Hadoop - A framework for distributed storage and processing of big data using the MapReduce programming model.
Zookeeper - A centralized service for distributed systems to a hierarchical key-value store, which is used to provide a distributed configuration service, synchronization service, and naming registry for large distributed systems.
Accumulo - A highly scalable sorted, distributed key-value store based on Google's Bigtable.
Docker - A computer program that performs operating-system-level virtualization also known as containerization.
GRPC - An open source remote procedure call system initially developed at Google.
SPARK - An open-source cluster-computing framework.
MAVEN - A build automation tool used primarily for Java projects.
```

## Features to be implemented

- [ ] Users can sign up.
- [ ] Users can login.
- [ ] User can set single vertex
- [ ] User can set single edge relationship
- [ ] User can set Multiple edge relationship
- [ ] User can search vertex
- [ ] User can search edge (Single)
- [ ] User can search edges (Multiple)
- [ ] User can search hop vertex
- [ ] User can search hop edges
- [ ] User can define relationship
- [ ] User can load csv file


## START CLUSTER

Ensure that hadoop, zookeeper and accumulo instance is running

### Build

Clean and install project dependency

``` bash
    mvn clean && mvn install
```

### Run
The maven build will provide two jar files (target folder)

    - Bundle-app.jar
        - It is bundled with dependencies

    - slim-app.jar
        - Dependencies are in slim-app.lib folder

``` bash
    java -jar target/slim-app.jar
```

``` bash
    java -jar target/bundle-app.jar
```

## Developer/Owner

[Anthony Leiro](https://github.com/lenileiro)

