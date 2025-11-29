# RestoManager

A RESTful API for managing restaurant operations, built with Spring Boot. This application provides endpoints for managing products (menu items) and orders, enabling restaurants to handle their day-to-day operations efficiently.

## Features

- **Product Management**
  - Create, read, update products (menu items)
  - Track product availability
  - Organize products by category
  - Manage product pricing

- **Order Management**
  - Create orders with multiple items
  - Track order status (CREATED, IN_PROGRESS, DONE, PAID, CANCELLED)
  - Support for dine-in and takeout orders
  - Automatic order total calculation
  - Price locking (order items preserve product price at time of order)

- **Database Migrations**
  - Flyway integration for version-controlled database schema
  - Automatic migration on application startup

## Tech Stack

- **Java 17**
- **Spring Boot 4.0.0**
- **Spring Data JPA** - Database persistence
- **PostgreSQL** - Relational database
- **Flyway** - Database migration tool
- **Maven** - Dependency management
- **Jakarta Validation** - Input validation

## Prerequisites

Before running this application, ensure you have the following installed:

- Java 17 or higher
- Maven 3.6+ 
- PostgreSQL 12+ (or compatible version)
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd restoManager
```

### 2. Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE resto;
```

### 3. Configure Database Connection

Update the database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/resto
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Run Database Migrations

Flyway will automatically run migrations on application startup. The migration files are located in `src/main/resources/db/migration/`:

- `v1__create_product_table.sql` - Creates the product table
- `V2__create_order_table.sql` - Creates the order table
- `V3__create_product_order_table.sql` - Creates the product-order junction table

### 5. Build and Run

Using Maven:

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

Or using the Maven wrapper:

```bash
# On Unix/Mac
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080` by default.

## API Endpoints

### Product Endpoints

#### Create Product
```http
POST /products/post/
Content-Type: application/json

{
  "prodName": "Margherita Pizza",
  "prodDescription": "Classic pizza with tomato and mozzarella",
  "prodCategory": "Pizza",
  "prodPrice": 12.99,
  "isProdAvailable": true
}
```

#### Get All Products
```http
GET /products/get-all-prods
```

#### Get Product by ID
```http
GET /products/get/{id}
```

#### Update Product (Partial)
```http
PATCH /products/edit/{id}
Content-Type: application/json

{
  "prodPrice": 14.99,
  "isProdAvailable": false
}
```

### Order Endpoints

#### Create Order
```http
POST /order/post/
Content-Type: application/json

{
  "customerName": "John Doe",
  "tableNumber": 5,
  "isTakeout": false,
  "productOrders": [
    {
      "prodId": "uuid-of-product-1",
      "quantity": 2
    },
    {
      "prodId": "uuid-of-product-2",
      "quantity": 1
    }
  ]
}
```

**Response:**
```json
{
  "orderId": "uuid",
  "customerName": "John Doe",
  "tableNumber": 5,
  "orderDate": "2024-01-15T12:30:00",
  "isTakeout": false,
  "orderStatus": "CREATED",
  "orderTotal": 40.97,
  "items": [
    {
      "prodId": "uuid",
      "prodName": "Margherita Pizza",
      "quantity": 2,
      "unitPrice": 12.99
    }
  ]
}
```

## Project Structure

```
restoManager/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── controllers/          # REST controllers
│   │   │   │   ├── OrderController.java
│   │   │   │   └── ProductController.java
│   │   │   ├── DTOs/                 # Data Transfer Objects
│   │   │   │   ├── order/
│   │   │   │   ├── product/
│   │   │   │   └── productOrder/
│   │   │   ├── models/               # JPA entities
│   │   │   │   ├── enums/
│   │   │   │   │   └── OrderStatusEnum.java
│   │   │   │   ├── OrderModel.java
│   │   │   │   ├── ProductModel.java
│   │   │   │   └── ProductOrderModel.java
│   │   │   ├── repositories/         # JPA repositories
│   │   │   │   ├── OrderRepository.java
│   │   │   │   ├── ProductOrderRepository.java
│   │   │   │   └── ProductRepository.java
│   │   │   ├── services/             # Business logic
│   │   │   │   ├── OrderService.java
│   │   │   │   └── ProductServices.java
│   │   │   ├── exceptions/           # Exception handlers
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── RestoManagerApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/         # Flyway migrations
│   └── test/                         # Test files
└── pom.xml
```

## Data Models

### Product
- `prodId` (UUID) - Primary key
- `prodName` (String) - Product name
- `prodDescription` (String) - Product description
- `prodCategory` (String) - Product category
- `prodPrice` (BigDecimal) - Product price
- `isProdAvailable` (Boolean) - Availability status

### Order
- `orderId` (UUID) - Primary key
- `customerName` (String) - Customer name
- `tableNumber` (Integer) - Table number (for dine-in)
- `orderDate` (LocalDateTime) - Order timestamp
- `isTakeout` (Boolean) - Takeout flag
- `orderStatus` (OrderStatusEnum) - Order status
- `orderTotal` (BigDecimal) - Total order amount
- `productOrders` (List<ProductOrderModel>) - Order items

### Order Status Enum
- `CREATED` - Order has been created
- `IN_PROGRESS` - Order is being prepared
- `DONE` - Order is ready
- `PAID` - Order has been paid
- `CANCELLED` - Order has been cancelled

## Usage Examples

### Creating a Product

```bash
curl -X POST http://localhost:8080/products/post/ \
  -H "Content-Type: application/json" \
  -d '{
    "prodName": "Caesar Salad",
    "prodDescription": "Fresh romaine lettuce with caesar dressing",
    "prodCategory": "Salad",
    "prodPrice": 8.99,
    "isProdAvailable": true
  }'
```

### Creating an Order

```bash
curl -X POST http://localhost:8080/order/post/ \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "Jane Smith",
    "tableNumber": 3,
    "isTakeout": false,
    "productOrders": [
      {
        "prodId": "your-product-uuid-here",
        "quantity": 1
      }
    ]
  }'
```

## Development

### Running Tests

```bash
mvn test
```

### Building for Production

```bash
mvn clean package
```

The JAR file will be created in the `target/` directory.

## Configuration

Key configuration options in `application.properties`:

- `spring.datasource.*` - Database connection settings
- `spring.jpa.show-sql=true` - Enable SQL logging (useful for debugging)
- `spring.flyway.enabled=true` - Enable Flyway migrations
- `spring.jpa.hibernate.ddl-auto=none` - Disable Hibernate auto-DDL (Flyway handles schema)

## License

This project is licensed under the MIT License.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Author

Created as a demo project for Spring Boot.

