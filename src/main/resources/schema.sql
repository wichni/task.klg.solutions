create table if not exists lessor (
    id BIGINT NOT NULL,
    name VARCHAR(100),
    PRIMARY KEY (id)
);

create table if not exists object_for_rent (
    id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    unit_price DECIMAL,
    surface DECIMAL,
    description VARCHAR(500),
    lessor_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (lessor_id) REFERENCES lessor(id)
);

create table if not exists tenant (
    id BIGINT NOT NULL,
    name VARCHAR(100),
    PRIMARY KEY (id)
);

create table if not exists reservation (
    id BIGINT NOT NULL,
    date_from DATE NOT NULL,
    date_to DATE NOT NULL,
    lessor_id BIGINT,
    tenant_id BIGINT,
    cost DECIMAL,
    PRIMARY KEY (id),
    FOREIGN KEY (lessor_id) REFERENCES lessor(id),
    FOREIGN KEY (tenant_id) REFERENCES tenant(id)
);