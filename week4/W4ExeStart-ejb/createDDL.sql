CREATE TABLE CONTACT_PERSON (contact_person_id INTEGER NOT NULL, NAME VARCHAR(255), phone_number VARCHAR(255), PRIMARY KEY (contact_person_id))
CREATE TABLE PROPERTY (property_id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, number_of_bedrooms INTEGER, PRICE FLOAT, SIZE FLOAT, postcode VARCHAR(255), STATE VARCHAR(255), street_address VARCHAR(255), street_number VARCHAR(255), SUBURB VARCHAR(255), CONTACTPERSON_contact_person_id INTEGER, PRIMARY KEY (property_id))
CREATE TABLE Tag (Property_property_id INTEGER, value VARCHAR(255))
ALTER TABLE PROPERTY ADD CONSTRAINT CNTCTPRSNcntctprsn FOREIGN KEY (CONTACTPERSON_contact_person_id) REFERENCES CONTACT_PERSON (contact_person_id)
ALTER TABLE Tag ADD CONSTRAINT TgPrprtypropertyid FOREIGN KEY (Property_property_id) REFERENCES PROPERTY (property_id)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(15), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
