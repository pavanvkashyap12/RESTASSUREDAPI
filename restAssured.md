# REST ASSURED API
End point: Address where API is hosted on the Server.



HTTP methods which are commonly used to communicate with Rest API’s are

GET, POST, PUT, and DELETE

GET- The GET method is used to extract information from the given server using a given URI. While using GET request, it should only extract data and should have no other effect on the data. No Payload/Body required

How to send input data in GET?
Ans: Using Query Parameters


POST- A POST request is used to send data to the server, for example, customer information, file upload, etc. using HTML forms.

How to send input data in POST?
Ans: Using Form Parameters /Body Payload




PUT- Replaces all current representations of the target resource with the uploaded content.

DELETE- Removes all current representations of the target resource given by a URI.



Resources:
Resources represent API/Collection which can be accessed from the Server

Google.com/maps
google.com/search
google.com/images


Path Parameters:
Path parameters are variable parts of a URL path. They are typically used to point to a specific resource within a collection, such as a user identified by ID

https://www.google.com/Images/1123343
https://www.google.com/docs/1123343
https://amazon.com/orders/112

https://www.google.com/search?q=newyork&oq=newyork&aqs=chrome..69i57j0l7.2501j0j7&sourceid=chrome&ie=UTF-8



Query Parameters:
Query Parameter is used to sort/filter the resources.

Query Parameters are identified with?””

https://amazon.com/orders?sort_by=2/20/2020



Headers/Cookies:

Headers represent the meta-data associated with the API request and response. In layman terms, we were sending Additional details to API to process our request.
Example : Authorization details
Also mention content-type




End Point Request URL can be constructed as below
Base URL/resource/(Query/Path)Parameters

## What is Rest Assured ?
- Rest-Assured is a Java based library that is used to test RESTFUL Web Services/API's
- Install Java and set in System Variables
- Install Eclipse and create a maven project
- configure Rest Assured jars into project
- Java vs Maven Project -> Java Project is simple project and Java is attached to it
- Maven project is also a java project with  lot of advanatages

## What is maven ?
- Maven is a build managment tool
- Advantage is automatic standard project skeleton creation
- Ease of assing project dependencies -> maven repository
- Seamless CI/CD Integration
- Java project is not ready for API testing -> we need to add those jars here
- But in maven we can use pom.xml file to add dependenices via mvn repository
- artifactID -> projectName
- groupdID -> package that has many projects
- javaproject -> rightclick -> configure -> convert to maven project
- pom.xml is the heart of the project
- add dependencies tag and add rest assured -> save -> it will start downloading jars -> now we can see these is maven respository folder
- similarly testng and Hamcrest -> to match Json response to actuall response
- go to src create a package and class

## Rest Assured principles
- Rest Assured works on 3 principles
- given -> all input details to submit to API
- when ->  submit the API -> resource and HTTP Method go in when rest all in given
- then -> validate the response


- install testng plugin