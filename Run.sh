#!/bin/bash

MAVEN_OPTS=" -Xmx8g "
export MAVEN_OPTS

mvn -e -X exec:java > Result-minieAgressive.txt

#mvn exec:java -jar ${JarDir} -cp org.lambda3.graphene.cli.GrapheneCLI  -Dexec.args="--operation RE --output CMDLINE --reformat DEFAULT --input FILE ${InputDir}${InputFile}" -Dconfig.file="../conf/graphene.conf"
