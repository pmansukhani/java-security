The project includes code for 2 types of security authentication for REST WebServices. 

1. Simple token authentication using UUID
2. JWT (json web token) authentication.

Most of the code for the 2 types of authentication is shared. The Service `UUIDAuthenticationService` is used for UUID based authentication and the `@Service` annotation needs to be uncommented. Similarly, the `JWTTokenService` is used for JWT based authentication.

REST requests:

**Register**
`curl -XPOST -d 'username=john&password=smith' http://localhost:8080/public/users/register`
b856850e-1ad4-456d-b5ca-1c2bfc355e5

**Login**
`curl -XPOST -d 'username=john&password=smith' http://localhost:8080/public/users/login`

**Get request**
`curl -H 'Authorization: Bearer b856850e-1ad4-456d-b5ca-1c2bfc355e5e' http://localhost:8080/users/current`

**Logout**
`curl -H 'Authorization: Bearer b856850e-1ad4-456d-b5ca-1c2bfc355e5e' http://localhost:8081/users/logout`