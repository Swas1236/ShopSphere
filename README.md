# ShopSphere 🛒

A production-oriented e-commerce backend built with **Java, Spring Boot, MySQL, Redis, JWT Authentication, and OpenAPI/Swagger**.

ShopSphere demonstrates how a modern backend application can handle product management, user authentication, role-based authorization, shopping carts, order processing, stock management, caching, validation, and API documentation.

---

## 🚀 Features

### 🔐 Authentication & Authorization

* User registration and login
* BCrypt password hashing
* JWT-based authentication
* Role-based authorization
* `USER` and `ADMIN` roles
* Protected REST APIs

### 📦 Product Management

* Create products
* Update products
* Delete products
* Get product by ID
* Get all products
* Search products by name
* Filter products by category
* Filter products by price range
* Product validation

### 🛒 Shopping Cart

* Add products to cart
* Update cart quantity
* Remove products from cart
* View cart
* Automatically merge duplicate cart items

### 📋 Order Management

* Place orders from cart
* Order history
* Automatic stock reduction
* Insufficient-stock validation
* Transactional order processing
* Order status management

### ⚡ Redis Caching

* Product list caching
* Redis-based cache storage
* Cache invalidation after product changes
* Configurable cache expiration

### 📖 API Documentation

* OpenAPI documentation
* Swagger UI
* JWT Bearer authentication support
* Interactive API testing

### 🛡️ Error Handling & Validation

* Global exception handling
* Request validation
* Custom insufficient-stock exception
* Meaningful API error responses

---

## 🛠️ Tech Stack

| Technology        | Purpose                        |
| ----------------- | ------------------------------ |
| Java 17           | Programming language           |
| Spring Boot 3.3.5 | Backend framework              |
| Spring Web        | REST APIs                      |
| Spring Data JPA   | Database access                |
| Spring Security   | Authentication & authorization |
| JWT               | Stateless authentication       |
| BCrypt            | Password hashing               |
| MySQL             | Relational database            |
| Redis             | Caching                        |
| Maven             | Dependency management          |
| Swagger / OpenAPI | API documentation              |
| Git & GitHub      | Version control                |

---

## 🏗️ Project Architecture

```text
ShopSphere
│
├── controller
│   ├── ProductController
│   ├── UserController
│   ├── CartController
│   └── OrderController
│
├── service
│   ├── ProductService
│   ├── UserService
│   ├── CartService
│   └── OrderService
│
├── repository
│   ├── ProductRepository
│   ├── UserRepository
│   ├── CartRepository
│   ├── CartItemRepository
│   ├── OrderRepository
│   └── OrderItemRepository
│
├── model
│   ├── Product
│   ├── Cart
│   ├── CartItem
│   ├── Order
│   ├── OrderItem
│   └── user
│
├── security
│   ├── JwtService
│   ├── JwtFilter
│   └── SecurityConfig
│
├── exception
│   ├── GlobalExceptionHandler
│   └── InsufficientStockException
│
└── config
    ├── RedisConfig
    └── OpenApiConfig
```

---

## 🔑 Authentication Flow

```text
Client
   │
   ▼
Login API
   │
   ▼
UserService
   │
   ▼
BCrypt Password Verification
   │
   ▼
JWT Token Generated
   │
   ▼
Client sends JWT
   │
   ▼
JwtFilter
   │
   ▼
SecurityContext
   │
   ▼
Protected API
```

---

## 🗄️ Database

ShopSphere uses **MySQL** for persistent application data.

Main entities include:

* Users
* Products
* Carts
* Cart Items
* Orders
* Order Items

---

## ⚡ Caching Strategy

Product data is cached using **Redis**.

```text
Client
   │
   ▼
GET /api/products
   │
   ▼
Redis Cache
   │
   ├── Cache Hit ─────► Return cached data
   │
   └── Cache Miss
           │
           ▼
        MySQL
           │
           ▼
      Store in Redis
           │
           ▼
       Return data
```

Product cache is invalidated when products are created, updated, or deleted.

---

## 📚 API Endpoints

### Authentication

```text
POST /api/users/register
POST /api/users/login
```

### Products

```text
GET    /api/products
GET    /api/products/{id}
GET    /api/products/search?name=
GET    /api/products/category?category=
GET    /api/products/price?minPrice=&maxPrice=

POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

### Cart

```text
POST   /api/cart/add
GET    /api/cart
PUT    /api/cart/update
DELETE /api/cart/remove
```

### Orders

```text
POST /api/orders/place
GET  /api/orders
```

---

## 📖 Swagger UI

After starting the application, open:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger provides an interactive interface for exploring and testing the REST APIs.

---

## ⚙️ Requirements

Before running ShopSphere, install:

* Java 17
* Maven
* MySQL 8+
* Redis / Memurai
* IntelliJ IDEA or another Java IDE

---

## 🚀 Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/Swas1236/ShopSphere.git
cd ShopSphere
```

### 2. Configure MySQL

Create the database:

```sql
CREATE DATABASE shopsphere;
```

Configure your local database credentials in:

```text
src/main/resources/application-local.properties
```

> This file contains local secrets and is intentionally excluded from Git.

### 3. Start Redis

Make sure your Redis server is running on:

```text
localhost:6379
```

### 4. Run the application

Using Maven:

```bash
mvn spring-boot:run
```

Or run `ShopSphereApplication` directly from IntelliJ IDEA.

---

## 🔒 Security Notes

Sensitive configuration such as:

* Database passwords
* JWT secrets
* Local environment configuration

is kept outside the Git repository using:

```text
application-local.properties
```

This file is excluded through `.gitignore`.

---

## 🧪 Testing

The backend has been tested for:

* User registration
* User login
* JWT authentication
* Role-based authorization
* Product CRUD operations
* Product search and filtering
* Cart operations
* Order placement
* Stock reduction
* Insufficient-stock handling
* Redis caching
* Swagger API testing

---

## 🎯 Project Goals

ShopSphere was built to demonstrate practical backend development concepts including:

* REST API design
* Layered architecture
* Dependency injection
* JPA and Hibernate
* Authentication and authorization
* JWT security
* Database design
* Redis caching
* Transaction management
* Exception handling
* API documentation
* Git and GitHub workflow

---

## 👨‍💻 Author

**Swas1236**

GitHub:
https://github.com/Swas1236
