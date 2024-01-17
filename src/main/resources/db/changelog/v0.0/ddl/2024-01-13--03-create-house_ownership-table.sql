--changeset LyeshaKorotkevich:3
DROP TABLE IF EXISTS house_ownership;
CREATE TABLE house_ownership
(
    house_id  BIGINT NOT NULL,
    person_id BIGINT NOT NULL
);

ALTER TABLE house_ownership
    ADD CONSTRAINT fk_hou_on_house FOREIGN KEY (house_id) REFERENCES houses (id);

ALTER TABLE house_ownership
    ADD CONSTRAINT fk_hou_on_person FOREIGN KEY (person_id) REFERENCES people (id);