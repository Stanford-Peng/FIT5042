ALTER TABLE PROPERTY DROP CONSTRAINT CNTCTPRSNcntctprsn
ALTER TABLE Tag DROP CONSTRAINT TgPrprtypropertyid
DROP TABLE CONTACT_PERSON
DROP TABLE PROPERTY
DROP TABLE Tag
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
