# Instructions

## Pre-requisites
- JDK 1.8
- Tomcat 8.5
- Gradle 3.4

## Assumptions made
- Tomcat is running on port 8080 in localhost

## Steps

- Clone this repo in to your machine and run "gradle build" command from the base directoy of project. This will generate war file in build/libs folder of gradle
- Deploy the war file into tomcat server and restart tomcat.
- Now you can access the json file at [swagger.json]
  - [sample json]


# Integrating with swagger UI

- Clone [swagger UI master] repository into local machine
- Open dist/index.html file in browser
- Give the location of json file ( [swagger.json] ) in search bar at the top of the page and click on explore.

You can see the documentation of API in the broswser now. You can take this standalone swagger UI app and deploy into application/web server to make this avilable via server and can be used to view the documentation of API's using swagger.json.

# Further usage
- This swagger.json can be used to build API client using swagger code gen tool.



 [swagger.json]: <http://localhost:8080/swagger-for-api-doc/api/swagger.json>
 [sample json]: <https://api.myjson.com/bins/h0nlz>
 [swagger UI master]: <https://github.com/swagger-api/swagger-ui>
