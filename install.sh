#!/bin/bash

# update packages
sudo apt-get update

# Install git
sudo apt-get install git-core

# Install jdk
sudo apt-get install openjdk-8-jdk

#install
sudo apt-get install maven

echo 'JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64' >> /etc/environment
source /etc/environment

mvn clean install