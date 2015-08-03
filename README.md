# SHOPPER CHALLENGE #

This project implements the public-facing site that a prospective Shopper would see when hearing about the opportunities,
 that Instacart offers. The second is writing analytics to monitor the progress of shoppers through the hiring funnel.



## Command to run the application ##

1) Run : 
```java
mvn spring-boot:run
```
The application can also be bundled as a war/jar to be a deployed as a tomcat/jetty application.

2) Test: 
```java
mvn clean test
```

This application using Spring Boot Framework, Spring Data.
It has been configured to run with h2 as in-memory datastore, but can be easily updated to run
with other sql/no-sql stores by including corresponding dependencies in the pom file.

## APIs ##
1) http://localhost:8080/funnels.json?start_date=2015-07-25&end_date=2015-09-05
Returns the report starting with buckets from Monday <= start_date and Sunday >=end_date

Sample output Json:

```js
 - {
     "2015-08-03-2015-08-09": {},
     "2015-07-27-2015-08-02": {
       "applied": 2,
       "quiz_started": 50,
       "quiz_completed": 20,
       "onboarding_requested": 10,
       "onboarding_completed": 5,
       "hired": 1
     },
     "2015-07-20-2015-07-26": {},
     "2015-08-24-2015-08-30": {},
     "2015-08-17-2015-08-23": {},
     "2015-08-31-2015-09-06": {},
     "2015-08-10-2015-08-16": {}
   }
```   
   
2) http://localhost:8080/status (GET)
 - API to return all application statuses

3) http://localhost:8080/update_status(POST)
 - API to update the status of an application using shopper_id
 - Input
    - shopper_id=1
    - new_status=applied

4) http://localhost:8080/ takes the applicant to 
    http://localhost:8080/shopper to fill up the application form.


## To-do ##

- Add session support
- Add security
- Better error handling
- Add profiles for different envs


## Notes ##

Tested with Java7