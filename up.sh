#!/bin/sh
mvn clean install
mv target/guitest-1.0-SNAPSHOT.jar serv/plugins
