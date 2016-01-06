--liquibase formatted sql

--changeset vaa25:14
INSERT INTO event (id, title, event_date, create_date, reg_link, address, point, contact, price, currency_id, city_id)
VALUES
  (-10, 'Operation openshift', '10.07.2115', NULL, 'http://www.java.com.ua', 'Beresteyska', 'POINT(30.742017 50.458585)',
   'java@gmail.com', NULL, NULL, -1)
;

--rollback DELETE FROM event;