-- Question:
-- Find orders that have no successful payment (status = 'SUCCESS') using:
-- - Subquery
-- - JOIN
-- Also explain performance considerations.

CREATE TABLE payments
(
    payment_id int primary key,
    order_id   int     not null references orders (order_id),
    status     varchar not null check ( status in ('SUCCESS', 'FAIL') )
);

CREATE INDEX idx_payments_status on payments (order_id, status);

INSERT INTO payments (payment_id, order_id, status)
VALUES (1, 1, 'SUCCESS'),
       (2, 2, 'SUCCESS'),
       (3, 3, 'FAIL'),
       (4, 3, 'SUCCESS'),
       (5, 4, 'FAIL'),
       (6, 4, 'FAIL')
;


SELECT order_id
from orders
where not exists (SELECT 1
                  FROM payments
                  where orders.order_id = payments.order_id
                    and payments.status = 'SUCCESS');

SELECT orders.order_id
from orders
         left join payments
                   on orders.order_id = payments.order_id and payments.status = 'SUCCESS'
where payments.order_id IS NULL;
