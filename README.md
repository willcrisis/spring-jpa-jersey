# Proposed exercise
The candidate must:
- Create Entity classes for the tables, including relationships
- Create the Data manipulation layer. Feel free to use structure or framework you like (JPA, JDBC, Spring Data, etc).
- Create a GET REST service to retrieve the list of cities in the database, and return them as a JSON object.
- The service may receive the query param "country" as a String, to restrict the search. The parameter may be part of the Country name
   http://server:port/rest/cities[?country=name]
- Create an operation to load data into the database (Here you're free to be creative. You can load data from a simple CSV, a spreadsheet, a rest service, etc...)

Feel free to modify the files included, upgrade frameworks, add or remove packages, in every aspect you want. Just check the note regarding JUnit tests below.

# Before running

Before run the application using `mvn spring-boot:run` or building the war file with `mvn packege`, run the following to install the QueryDSL's needed classes:

    mvn install

# Expected results

After the implementation, the application should run after the following command line:

	java -jar target/spring-jpa-jersey.jar
    
or 

    mvn spring-boot:run
    
or deploy on Tomcat, or Jetty or an Application Server, as long as you include instructions for the deploy.


Then, open a browser and type :

    http://localhost:8090/rest/cities?country=Uni


It must return, at least the following (ids may vary) :

    [
        {
            "id":86,
            "name":"New York",
            "country":{
                "id":2,
                "name":"United States"
            }
        },
        {
            "id":87,
            "name":"Los Angeles",
            "country":{
                "id":2,
                "name":"United States"
            }
        },
        {
            "id":88,
            "name":"Atlanta",
            "country":{
                "id":2,
                "name":"United States"
            }
        }
    ]


# Implementation details:

- Dependencies version was updated. This made the code of EndpointTest broke. That test was updated to a working version, but that expects the same result as the previous version.
- Some files was moved to another packages to keep configurations as simple as possible.
- Database creation scripts were moved to resources folder to keep it according to Spring Boot's database initialization guide. https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-initialize-a-database-using-spring-jdbc
- The `testFindOneCountry` method of class `CountryRepositoryTest` was updated to accomplish the Spring Data's naming pattern for repository methods.
