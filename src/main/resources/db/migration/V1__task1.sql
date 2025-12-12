CREATE TABLE customers
(
    customer_id int primary key,
    name        varchar not null,
    status      varchar not null check (status in ('ACTIVE', 'DISABLED') )
);

CREATE TABLE orders
(
    order_id     int primary key,
    customer_id  int not null,
    created_at   timestamp not null,
    total_amount decimal,
    constraint fk_orders_customer
        foreign key (customer_id)
            references customers (customer_id)
            on delete cascade
            on update cascade
);

INSERT INTO customers
VALUES (1, 'Ivan', 'ACTIVE'),
       (2, 'Petr', 'ACTIVE'),
       (3, 'Milan', 'ACTIVE'),
       (4, 'Vladimir', 'ACTIVE'),
       (5, 'John', 'DISABLED');

CREATE INDEX idx_orders_created_at
    ON orders (customer_id, created_at);

CREATE INDEX idx_customers_status
    ON customers (customer_id, status);

INSERT INTO orders (order_id, customer_id, created_at, total_amount)
VALUES (1, 1, NOW() - INTERVAL '1 day', 1000.50),
       (2, 1, NOW() - INTERVAL '5 days', 2000.70),
       (3, 2, NOW() - INTERVAL '1 month', 1500.40),
       (4, 2, NOW() - INTERVAL '2 months', 700.80),
       (5, 3, NOW() - INTERVAL '3 month', 0),
       (6, 3, NOW() - INTERVAL '1 year', 100.0);

-- Get, for each active customer, the total amount they spent
-- in the last 6 months and the count of their orders, excluding
-- those with zero orders.
SELECT
    orders.customer_id,
    customers.name,
    sum(total_amount),
    count(order_id)
FROM orders LEFT JOIN customers
ON orders.customer_id=customers.customer_id
WHERE status='ACTIVE' AND created_at>=(NOW()-INTERVAL '6 months')
GROUP BY orders.customer_id, customers.name
HAVING sum(total_amount)>0.0;
