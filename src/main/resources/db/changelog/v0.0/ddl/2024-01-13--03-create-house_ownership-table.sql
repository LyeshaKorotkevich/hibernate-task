CREATE TABLE houseOwnership
(
    house_id  BIGINT NOT NULL,
    person_id BIGINT NOT NULL
);

ALTER TABLE houseOwnership
    ADD CONSTRAINT fk_hou_on_house FOREIGN KEY (house_id) REFERENCES houses (id);

ALTER TABLE houseOwnership
    ADD CONSTRAINT fk_hou_on_person FOREIGN KEY (person_id) REFERENCES people (id);