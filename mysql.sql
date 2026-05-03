CREATE DATABASE QuickBite;
USE QuickBite;

#user table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    
    role ENUM('USER','OWNER') DEFAULT 'USER',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

#restaurant table
CREATE TABLE restaurants (
    restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
    owner_id INT,
    restaurant_name VARCHAR(100) NOT NULL,
    address TEXT,
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (owner_id)
    REFERENCES users(user_id)
);

#food item
CREATE TABLE food_items (
    food_id INT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id INT,
    food_name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(50),
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (restaurant_id)
    REFERENCES restaurants(restaurant_id)
);


# cart table
CREATE TABLE cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    food_id INT,
    quantity INT DEFAULT 1,
    FOREIGN KEY (user_id)
    REFERENCES users(user_id),
    
    FOREIGN KEY (food_id)
    REFERENCES food_items(food_id)
);

#orders table
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    total_amount DECIMAL(10,2),
    order_status ENUM(
        'PENDING',
        'PREPARING',
        'DELIVERED',
        'CANCELLED'
    ) DEFAULT 'PENDING',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
);

# order item table
CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    food_id INT,
    quantity INT,
    price DECIMAL(10,2),

    FOREIGN KEY (order_id)
    REFERENCES orders(order_id),

    FOREIGN KEY (food_id)
    REFERENCES food_items(food_id)
);

