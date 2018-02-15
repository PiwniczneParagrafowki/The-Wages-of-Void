FROM ubuntu

# Download and install packeges
RUN apt-get -y update
RUN apt-get -y install apt-utils
RUN apt-get -y install dos2unix
RUN apt-get -y install openjdk-8-jdk
RUN apt-get -y install net-tools
RUN apt-get -y install mysql-server
RUN mysql_secure_installation

EXPOSE 8080
