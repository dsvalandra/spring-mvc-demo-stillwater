spring-mvc-demo-stillwater
This project is a RESTful API for a simulated insurance company's Customer Relationship Management (CRM) system. It is built with Spring Boot and showcases a layered architecture with complex relational data modeling. This project serves as a portfolio piece to demonstrate my skills in backend development, database design, and API implementation.

Features
The API provides full CRUD (Create, Read, Update, Delete) functionality for a multi-entity data model:

Agents: Insurance agents with unique license numbers. An Agent can be associated with multiple Customers and Quotes.

Customers: The central entity, with detailed personal information. A Customer can have many Policies and Quotes, and is assigned to one Agent.

Policies: Insurance policies linked to a specific Customer.

Quotes: Insurance quotes linked to both a Customer and an Agent.

The project demonstrates a solid understanding of one-to-many relationships and uses Hibernate and Spring Data JPA for data persistence. It also correctly handles common challenges like circular dependencies in REST responses using @JsonIgnore.

Technologies Used
Java: The core programming language.

Spring Boot: The application framework, including an embedded Tomcat server for easy deployment.

Spring Data JPA: Simplifies database interactions with repositories for each entity.

Maven: Manages project dependencies and the build lifecycle.

SQLite: A lightweight, file-based relational database for local development.

RESTful API: The architectural style for all API endpoints.

Project Structure
The project is organized using a layered architecture to maintain a clear separation of concerns.

src/main/java/com/example/customercrudmvc2/:

model/: Contains the data entities (Customer, Policy, Agent, Quote), which map directly to database tables.

repository/: Interfaces that extend JpaRepository for data access.

service/: Contains the business logic and orchestrates data flow.

controller/: Handles incoming HTTP requests and routes them to the appropriate service.

src/main/resources/: Contains application configurations, such as application.properties.

pom.xml: Defines project dependencies and build settings.

Getting Started
Prerequisites
Java 17 or newer

Maven

An IDE like Spring Tool Suite (STS) or IntelliJ IDEA

Running the Application
Clone the repository: git clone [repository-url]

Navigate to the project directory: cd [repository-name]

Run the application: You can run the CustomerCrudMvc2Application.java file directly from your IDE. The embedded server will start the application on port 8080.

API Endpoints
You can use a tool like Postman or curl to test the API endpoints. All endpoints are available under the http://localhost:8080/api/ base URL.

Endpoint

HTTP Method

Description

/agents

POST

Create a new agent.

/agents

GET

Retrieve all agents.

/agents/{id}

GET

Retrieve a single agent by ID.

/customers

POST

Create a new customer (with a linked agent).

/customers

GET

Retrieve all customers.

/customers/{id}

GET

Retrieve a single customer by ID.

/customers/{id}

PUT

Update an existing customer.

/customers/{id}

DELETE

Delete a customer.

/customers/{id}/details

GET

Gets a customer and all their associated policies, quotes, and agent info in one request.

/policies/customer/{customerId}

POST

Create a new policy for a specific customer.

/policies

GET

Retrieve all policies.

/policies/{id}

GET

Retrieve a single policy by ID.

/quotes/customer/{customerId}/agent/{agentId}

POST

Create a new quote for a specific customer and agent.

/quotes

GET

Retrieve all quotes.

/quotes/{id}

GET

Retrieve a single quote by ID.

API Examples
Here are some examples of how to interact with the API endpoints using JSON payloads.

1. Create a New Agent (POST /api/agents)
Request Body

{
  "name": "Alex Johnson",
  "licenseNumber": "AJ12345"
}

Success Response (200 OK)

{
  "id": 1,
  "name": "Alex Johnson",
  "licenseNumber": "AJ12345"
}

2. Create a New Customer (POST /api/customers)
Request Body

{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "address": "123 Main St",
  "phoneNumber": "555-123-4567",
  "dateOfBirth": "1990-05-15",
  "agent": {
    "id": 1
  }
}

Success Response (200 OK)

{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "address": "123 Main St",
  "phoneNumber": "555-123-4567",
  "dateOfBirth": "1990-05-15",
  "agent": {
    "id": 1,
    "name": "Alex Johnson",
    "licenseNumber": "AJ12345"
  }
}

3. Get Customer with All Details (GET /api/customers/{id}/details)
This is the most powerful endpoint, as it returns a single JSON object containing all the related data for a specific customer. For example, if you make a request for customer with ID 3, who has one policy and one quote tied to them, the response will look like this:

Example Request
GET http://localhost:8080/api/customers/3/details

Success Response (200 OK)

{
  "id": 3,
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "address": "123 Main St",
  "phoneNumber": "555-123-4567",
  "dateOfBirth": "1990-05-15",
  "policies": [
    {
      "id": 1,
      "policyNumber": "PC-001",
      "policyType": "Auto Insurance",
      "coverageAmount": 50000,
      "startDate": "2025-01-01",
      "endDate": "2026-01-01"
    }
  ],
  "agent": {
    "id": 1,
    "name": "Alex Johnson",
    "licenseNumber": "AJ12345"
  },
  "quotes": [
    {
      "id": 1,
      "quoteNumber": "QT-789",
      "quotedAmount": 750,
      "expirationDate": "2025-10-24"
    }
  ]
}

4. Create a New Policy (POST /api/policies/customer/{customerId})
Example Request
POST http://localhost:8080/api/policies/customer/3

Request Body

{
  "policyNumber": "PC-001",
  "policyType": "Auto Insurance",
  "coverageAmount": 50000.00,
  "startDate": "2025-01-01",
  "endDate": "2026-01-01"
}

Success Response (200 OK)

{
  "id": 1,
  "policyNumber": "PC-001",
  "policyType": "Auto Insurance",
  "coverageAmount": 50000,
  "startDate": "2025-01-01",
  "endDate": "2026-01-01"
}

5. Create a New Quote (POST /api/quotes/customer/{customerId}/agent/{agentId})
Example Request
POST http://localhost:8080/api/quotes/customer/3/agent/1

Request Body

{
  "quoteNumber": "QT-789",
  "quotedAmount": 750.00,
  "expirationDate": "2025-10-24"
}

Success Response (200 OK)

{
  "id": 1,
  "quoteNumber": "QT-789",
  "quotedAmount": 750,
  "expirationDate": "2025-10-24"
}
