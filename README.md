# Backend Practical Test
## Task:
*	Create a new microservice using Java >= 17 with spring boot framework 
*	The microservice should include 3 Restful APIs to manage employees database. 
*	The database should be file(s) within the project (e.g. txt, json )
* The submission should be pushed to your Github and link to be shared and the code should be working.
* APIs
  * Create Employee API
    * Insert a new employee in the database. 
  * Retrieve Employee by Id API
    * Retrieve employees’ details by Id
  * Search Employees API
    * The query parameters are optional (name & fromSalary & toSalary)
    * If the name parameter is passed, the api should search for all employees that their first name or last name contains the search text.
    * If fromSalary or toSalary or both passed, consider the following. 
      * fromSalary only  Retrieve all employees who have salary >= fromSalary
      * toSalary only  Retrieve all employees who have salary <= toSalary
      * Both  Retrieve all employees who have salary between fromSalary and toSalary inclusive. 
    * The query result should be cached in-mem for 1 hour only. 

## Tech stack:
* Spring boot 3
* Java 21
* Lombok
* Jackson
* Map Struct

## Start up:
1. Build the project using gradle
2. Start the project by entering: java -jar build/libs/employee-1.0.0-plain.jar

## Other:
* You can find the postman collection in postman folder