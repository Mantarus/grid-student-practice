Grid Practice - ChatService

Installation Docker and PostgreSQL
----------------------------------

First of all you need to install Docker on your computer. To be sure that you'll
get the latest version you need to download this from official Docker repository.
To do this y`ou need to add official Docker repository to APT.

Update existing package list:
$ sudo apt Update

Install packages that allows apt to use HTTPS:
$ sudo apt install apt-transport-https ca-certificates curl software-properties-common

Add a GPG-key for official Docker repository:
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

Add a Docker repository to APT:
$ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"

Update the package list:
$ sudo apt update

And finally, install Docker:
$ sudo apt install docker-ce

Here, the Docker installation is completed. To install a PostgreSQL container
you need to type this command:
$ docker container run -d --name=postgresql -p 5432:5432 -e POSTGRES_PASSWORD=password -e PGDATA=/pgdata -v /pgdata:/pgdata postgres

Where -d means run it in the background
--name=container-name needs to set the name to your container. If it's  missing, Docker will set randomly generated name
-p needs to map port from host to container
-e set environment variable
-v mount volume/directory
last word "postgres" indicates the name of installing container. Also you can write postgres:11.4 to get specific version. By default, the latest version will be installed.

Swagger documentation browsing
----------------------------------

To view JSON Swagger documentation of Chatservice paste http://localhost:8080/v2/api-docs to the search bar in Postman.

To browse human readable HTML-documentation paste http://localhost:8080/swagger-ui.html to the browser's search bar/