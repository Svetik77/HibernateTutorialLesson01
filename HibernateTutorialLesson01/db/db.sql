--CREATE DATABASE config_lesson ENCODING 'UTF-8';
-- CREATE DATABASE many_to_one_lesson ENCODING 'UTF-8';
-- Database: "hibernateLesson02"




CREATE TABLE IF NOT EXISTS  public.engine1
(
  id integer NOT NULL DEFAULT nextval('engine1_id_seq'::regclass),
  model character varying(25) NOT NULL,
  power integer NOT NULL,
  CONSTRAINT engine1_pkey PRIMARY KEY (id)
)
 

INSERT INTO engine (model, power) VALUES ('engine_model_00', 1250);
INSERT INTO engine (model, power) VALUES ('engine_model_02', 2820);

--CREATE TABLE IF NOT EXISTS engine1 (
--  id    SERIAL PRIMARY KEY,
--  model VARCHAR(25) NOT NULL,
--  power INTEGER     NOT NULL
--);