Shopfair Application

#Cloning

git clone https://github.com/mcelio/shopfair.git

# Task Description
You should be able to start the example application by executing com.shopfare.ShopfareServerApplicantTestApplication,
which starts a webserver on port 8081 (http://localhost:8081) and serves SwaggerUI where can inspect and try existing
endpoints.

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring MVC with Spring Boot
* Database H2 (In-Memory)
* Maven

#Running the application:

mvn spring-boot:run -Dserver.port=8081


#Frontend
Build from sources

cd shopfair/frontend
npm install --build-from-source

Running frontend

npm run start


Notes:

1) Use credentials below to log in the application and be able to create stores and products:

Username: shopfair
Password: shopfair

2) We are using temporary Google Maps API key, for production purpose we need to acquire API license.

GoogleMaps api key

AIzaSyAwVwunTGm9xs4zxhsYkcjSuQ8AStnuNRU

