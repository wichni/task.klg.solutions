insert into object_for_rent (id, name, unit_price, surface, description) values
    (1, 'Pokój dla studenta', 123.90, 9.1, 'Pokój jednoosobowy dla osoby studiującej. W mieszkaniu jest kuchnia, łazienka i balkon.'),
    (2, 'Biuro nad bałtykiem', 250.99, 20.0, 'Biuro znajduje się na 5 piętrze z widokiem na bałtyk'),
    (3, 'Dom jednorodzinny', 1500.00, 79.0, 'Wynajmę dom z ogrodem dla rodziny, możliwość wynajmu długoterminowego'),
    (4, 'Mieszkanie dwupokojowe', 2500.00, 120.0, 'Mieszkanie dwupokojowe w centrum miasta, chętnie wynajmę dla rodziny z dziećmi'),
    (5, 'Biuro w centrum Warszawy', 150.0, 10.0, 'Wynajmę biuro w centrum Warszawy z widokiem na Pałac Kultury');

insert into lessor (id, name, object_for_rent_id) values
    (1, 'Wynajem biur corporation', 2),
    (2, 'Wynajem biur corporation', 5),
    (3, 'Tomasz Xyz', 3),
    (4, 'Wynajem mieszkań', 4),
    (5, 'Weronika Abc', 1);

insert into tenant (id, name) values
    (1, 'Firma programistyczna'),
    (2, 'Student'),
    (3, 'Firma transportowa');

insert into reservation (id, date_from, date_to, lessor_id, tenant_id, cost) values
    (1, '2022-08-20', '2022-08-21', 1, 1, 99.9),
    (2, '2022-08-30', '2022-09-30', 5, 2, 50.0),
    (3, '2022-08-22', '2022-08-30', 1, 1, 399.9);