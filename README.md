# Asalkar Healthy Hub - Cold Pressed Oil E-commerce Platform

A complete e-commerce solution for selling premium cold-pressed oils with Spring Boot backend and Angular frontend.

## Features

### Backend (Spring Boot)
- **Product Management**: CRUD operations for cold-pressed oil products
- **User Authentication**: Registration and login system
- **Shopping Cart**: Add/remove items from cart
- **Order Management**: Place orders and track delivery status
- **Category Filtering**: Filter products by oil type
- **Stock Management**: Track inventory levels

### Frontend (Angular)
- **Responsive Design**: Mobile-friendly Bootstrap UI
- **Product Catalog**: Browse and filter products
- **Product Details**: Detailed view with benefits and extraction methods
- **Shopping Cart**: Manage cart items
- **User Authentication**: Login and registration forms
- **Modern UI**: Clean, professional design

## Product Categories
- Coconut Oil
- Sesame Oil
- Groundnut Oil
- Sunflower Oil
- Mustard Oil

## Tech Stack

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database (development)
- PostgreSQL (production ready)
- Lombok
- Maven

### Frontend
- Angular 17
- TypeScript
- Bootstrap 5
- RxJS
- Standalone Components

## Setup Instructions

### Backend Setup
1. Navigate to project root
2. Run: `mvn spring-boot:run`
3. Backend will start on `http://localhost:8080`
4. H2 Console: `http://localhost:8080/h2-console`

### Frontend Setup
1. Navigate to `frontend` directory
2. Run: `npm install`
3. Run: `ng serve`
4. Frontend will start on `http://localhost:4200`

## API Endpoints

### Products
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/category/{category}` - Get products by category
- `POST /api/products` - Add new product
- `PUT /api/products/{id}` - Update product

### Authentication
- `POST /api/auth/register` - Register user
- `POST /api/auth/login` - Login user

### Cart
- `POST /api/cart` - Add to cart
- `GET /api/cart/user/{userId}` - Get user cart
- `DELETE /api/cart/user/{userId}/product/{productId}` - Remove from cart

### Orders
- `POST /api/orders` - Place order
- `GET /api/orders/user/{userId}` - Get user orders
- `PUT /api/orders/deliver/{orderId}` - Mark as delivered

## Sample Products
The application comes with pre-loaded sample data including:
- Pure Coconut Oil (500ml) - ₹299.99
- Organic Sesame Oil (500ml) - ₹249.99
- Pure Groundnut Oil (1L) - ₹199.99
- Virgin Sunflower Oil (1L) - ₹179.99
- Premium Mustard Oil (500ml) - ₹229.99

## Testing
Use the provided Postman collection (`AsalkarHealthyHub_Postman_Collection.json`) to test all API endpoints.

## Production Deployment
- Update `application.properties` for PostgreSQL
- Build frontend: `ng build --prod`
- Deploy backend JAR file
- Serve frontend from web server