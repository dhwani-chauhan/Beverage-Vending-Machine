# BeverageVendingMachine

### Back End Challenge
Create a basic rest API that can perform CRUD operations on Beverage Vending Machine

### Functionalities:

1. Display all beverages
2. Dispensing correct beverage
    * 4 Types of coffee:
        1. Black Coffee:
            + Water -> 3 unit
            + Coffee -> 1 unit
            + Sugar -> 1 unit
        2. Coffee with Milk:
            + Water -> 1 unit
            + Coffee -> 1 unit
            + Milk -> 2 unit
            + Sugar -> 1 unit
        3. Sugarfree Blak Coffee:
            + Water -> 3 unit
            + Coffee -> 1 unit
        4. Sugarfree Coffee with Milk:
             + Water -> 1 unit
            + Coffee -> 1 unit
            + Milk -> 2 unit
3. Notify when it ran out of inventory

## Built With(Technology):

  - Maven - Dependency Management
  - JDK - Java™ Platform, Standard Edition Development Kit
  - Spring Boot - Framework to ease the bootstrapping and development of new Spring Applications
  - Lombok - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
  - PostgresSQL

## External Tool Used:
 - Postman - API Development Environment (Testing Docmentation) 

## Running the application Locally:
There are several ways to run a Spring Boot application on your local machine. 
- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open PostgreSQL and create Database with name beveragevendingmachine
- Import Queries from Queries.sql to your PostgreSQL
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open any Editor(like Eclipse, Visual Studio Code,IntelliJ)
    - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
    - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the Spring Boot Maven plugin like so:
>mvn spring-boot:run

## Database Design
- beverage
    * b_id - Beverage Id (Primary Key)
    * b_name - Beverage Name
    * status - Beverage Available Status
- inventory
    * i_id - Inventory Id (Primary Key)
    * i_name - Inventory Name
    * total_quantity - Quantity of Inventory Available
- ingredients
    * in_id - Ingredient Id (Primary Key)
    * b_id - Beverage Id (Foreign Key)
    * i_id - Inventory Id (Foreign Key)
    * quantity_required - Ingredient Quantity Required for a Beverage

## APIs include following functionalities

### Beverage
| Name | URL | Method | Purpose | Parameter | Result |
|---|---|---|---|---|---|
| Add Beverage | http://localhost/beverage/add | POST | Add beverage in Machine | NONE | Beverage Object |
| Update Beverage | http://localhost/beverage/update | PUT | Update existing beverage from Machine | NONE | Beverage Object |
| Delete Beverage | http://localhost/beverage/delete/{beverageID} | DELETE | Delete existing beverage from Machine | BeverageID | Boolean |
| Show Single Beverage | http://localhost/beverage/find/{BeverageId} | GET | Details of a Requested Bevarage from the System | BeverageID | Beverage Object |
| Show All Beverages | http://localhost/beverage/ | GET | Show all beverages  | NONE | List of Beverages  |
| Check Availablity of a beverage | http://localhost/beverage/available/{BeverageId} | GET | Retrive all beverages available in the machine | BeverageId | Boolean |
| Check All Beverages | http://localhost/available | GET | Check all beverages available in the machine | NONE | List of Beverages |
| Order Beverage | http://localhost/beverage/order/{beverageId} | GET | Orders a beverage with valid beverageId | beverageId | Ingredients Object |

### Inventory
| Name | URL | Method | Purpose | Parameter | Result |
|---|---|---|---|---|---|
| Add Inventory | http://localhost/inventory/add | POST | Add inventory in Machine | NONE | Inventory Object |
| Update Inventory | http://localhost/inventory/update | PUT | Update existing inventory from the system | NONE | Inventory Object |
| Delete Inventory | http://localhost/inventory/delete/{inventoryID} | DELETE | Delete existing inventory from Machine | inventoryID | Boolean |
| Show Single Inventory | http://localhost/inventory/find/{inventoryId} | GET | Details of a Requested Inventory from the System | inventoryID | Inventory Object |
| Show All Inventories | http://localhost/inventory/ | GET | Show all Inventories  | NONE | List of Inventories |
| Increase Inventory Quantity | http://localhost:8080/inventory/increaseQuantity/{inventoryId}/{quantity} | GET | Increase the quantity of an existing inventory from the system | inventoryID, quantity | Inventory Object |
| Check for Empty Inventory | http://localhost:8080/inventory/checkEmpty/{inventoryId} | GET | Check if requested inventories is empty or not  | inventoryId | Boolean |
| Check Inventory with given quantity | http://localhost:8080/inventory/checkQuantity/{inventoryId}/{quantity} | GET | check whether inventory possess given quantity | inventoryID, quantity | Boolean |
| Empty Inventories | http://localhost:8080/inventory/emptyInventories | GET | Retrive all empty inventories | NONE | List of Inventories Object |

### Ingredients
| Name | URL | Method | Purpose | Parameter | Result |
|---|---|---|---|---|---|
| Add Ingredient | http://localhost/ingredients/add | POST | Add Ingredient for particular beverage | NONE | Ingredient Object |
| Update Ingredient | http://localhost/ingredients/update | PUT | Update Ingredient of particular beverage | NONE | Ingredient Object |
| Delete Ingredient | http://localhost/ingredients/delete/{ingredientID} | DELETE | Delete existing Ingredient | ingredientID | Boolean |
| Show Single Ingredient | http://localhost/ingredients/find/{ingredientId} | GET | Details of a Requested Ingredient from the System | ingredientID | Ingredient Object |
| Show All Ingredients | http://localhost/ingredients/findall | GET | Show all Ingredients used by all beverages  | NONE | List of Ingredients Object |

## Files and Directories
* API
* src
    +  main
    + java
        + com.Nestaway.BeverageVendingMachine
        + com.Nestaway.BeverageVendingMachine.controller
        + com.Nestaway.BeverageVendingMachine.model
        + com.Nestaway.BeverageVendingMachine.repository
        + com.Nestaway.BeverageVendingMachine.service
* mvnw
* mvnw.cmd
* pom.xml
* HELP.md
* README.md

## Packages
- models — to hold our entities
- repositories — to communicate with the database
- services — to hold our business logic
- controllers — to listen to the client
- pom.xml - contains all the project dependencies
