CREATE TABLE `cambio`
(
    `id`                bigint         NOT NULL AUTO_INCREMENT,
    `from_currency`     varchar(3)     NOT NULL,
    `to_currency`       varchar(3)     NOT NULL,
    `conversion_factor` decimal(65, 2) NOT NULL,
    PRIMARY KEY (`id`)
);
