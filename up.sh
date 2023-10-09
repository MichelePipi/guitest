#!/bin/sh
./gradlew shadowJar
mv build/libs/guitest-1.0-SNAPSHOT-all.jar serv/plugins/
