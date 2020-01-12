# Animal Accounting [SpringBootRestShelter]

Using this service, you can keep a record of animals in a shelter / zoo.

#### Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management

### Author

* *Anna Steshenko* 

# Use of service

* ### Adding an animal

**Request Type:** POST   
**URL:** http: // localhost: 8080 / shelter /  

**Incoming parameters:**  
  name [*String*] - Name  
  type [*String*] - Type  

**Types of allowed animals:**  

| Types | 
| ----- |
| CAT |
| PARROT |
| DOG |
| SNAKE |
| HAMSTER |
| MOUSE |
| FISH |
| MONKEY |
| PIG |
| HORSE |  

**Example:**   
{  
   "name": "Pit",    
    "type": "DOG"  
}  
**Answer:**

{  
    "id": 1,  
    "name": "Pit",  
    "type": "DOG"  
}  

* ### Editing

**Request Type:** PUT   
**URL:** http: // localhost: 8080 / shelter / [id]    

**Incoming parameters:**  
  name [*String*] - Name  
  type [*String*] - Type  

**Example:**  
http: // localhost: 8080 / shelter / **1**  
 
{  
  "name": "Kitty",  
  "type": "CAT"  
}  
**Answer:**  
{  
    "id": 1,  
    "name": "Kitty",  
    "type": "CAT"  
}  
* ### Getting

**Request Type:** GET   
**URL:** http: // localhost: 8080 / shelter / [id]    

**Example:**  
http: // localhost: 8080 / shelter / **1**  

**Answer:**  
{  
    "id": 1,  
    "name": "Kitty",  
    "type": "CAT"  
}  
* ###  Getting a list of animals

**Request Type:** GET   
**URL:** http: // localhost: 8080 / shelter /      

**Example:**  
http: // localhost: 8080 / shelter /    

**Answer:**  
*(list consisting of one element is returned)*  
{  
    "id": 1,  
    "name": "Kitty",  
    "type": "CAT"  
}  
* ###  Deleting

**Request Type:** DELETE   
**URL:** http: // localhost: 8080 / shelter / [id]       

**Example:**  
http: // localhost: 8080 / shelter / 1     

**Answer:**  
*(deleted item is returned)*  
{  
    "id": 1,  
    "name": "Kitty",  
    "type": "CAT"  
}  

# Error processing

* ###  Example 1
*(wrong animal type)*  

http: // localhost: 8080 / shelter /      
  
{   
    "name": "Joe",   
    "type": "Random word"  
}  
 
**Answer:**    
{  
   "status": "NOT_FOUND",  
   "message": "Animal type is incorrect"  
}  
* ###  Example 2
*(invalid json)*  

http: // localhost: 8080 / shelter /      

{  
  "namе": "Joe",2  
  "type": "CAT"  
}  

**Answer:**    
{  
    "status": "BAD_REQUEST",  
    "message": "JSON request is malformed",  
    "debugMessage": "JSON parse error: Unexpected character ('2' (code 50)): was expecting double-quote to start field name; nested exception is com.fasterxml.jackson.core.JsonParseException: Unexpected character ('2' (code 50)): was expecting double-quote to start field name\n at [Source: (PushbackInputStream); line: 2, column: 23]"  
}  








