CREATE EXTENSION pgcrypto;
CREATE SEQUENCE Aktualizacja_danych_id_seq;

CREATE TABLE Aktualizacja_danych
(
  "id_aktual_data" INTEGER DEFAULT NEXTVAL('Aktualizacja_danych_id_seq')  NOT NULL,
  "nr_dzialki" integer NOT NULL,
  "nr_aktualizacji" integer NOT NULL,
  "data_aktualizacji" character varying(255),
  "sposob_przekazania_danych_do_aktualizacji" character varying(255),
  "zaktualizowane_dane" character varying(255),
PRIMARY KEY ("id_aktual_data")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Aktualizacja_danych
  OWNER TO postgres;

CREATE TABLE Dostep
(
  "nr_dzialkowicza" integer NOT NULL,
  "login" character varying(40) NOT NULL ,
  "password" character varying(20) NOT NULL DEFAULT crypt('MyNewPassword', gen_salt('des')),
  "enabled" boolean NOT NULL DEFAULT TRUE,
  "role" character varying(15) NOT NULL DEFAULT 'ROLE_USER',
  UNIQUE (login,role),
PRIMARY KEY ("nr_dzialkowicza")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Dostep
  OWNER TO postgres;


CREATE TABLE Dzialkowicz
(
  "nr_dzialkowicza" integer NOT NULL,
  "imie" character varying(255),
  "nazwisko" character varying(255),
 "ulica" character varying(255),
  "nr_domu" character varying(255),
  "nr_lokalu" character varying(255),
  "kod_pocztowy" character varying(255),
  "miasto" character varying(255),
  "adres_do_korespondencji" character varying(255),
  "telefon" integer,
  "email" character varying(40),
PRIMARY KEY ("nr_dzialkowicza")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Dzialkowicz
  OWNER TO postgres;


CREATE TABLE Dzialki
(
  "nr_dzialki" integer NOT NULL,
  "powierzchnia" integer,
  "nr_czlonkowski" integer,
 PRIMARY KEY ("nr_dzialki")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Dzialki
  OWNER TO postgres;


CREATE TABLE IBAN
(
  "nr_dzialki" integer NOT NULL,
  "kod_iban" character varying(100),
  "nr_konta" character varying(100),
 PRIMARY KEY ("nr_dzialki")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE IBAN
  OWNER TO postgres;

CREATE SEQUENCE Informacja_id_seq;

CREATE TABLE Informacja
(
  "id_informacja" INTEGER DEFAULT NEXTVAL('Informacja_id_seq')  NOT NULL,
  "nadawca" character varying(5) NOT NULL,
  "nr_dzialki" integer,
  "nr_informacji" integer,
  "rok_rozliczeniowy" integer,
  "stan_rozliczenia" numeric(17,2),
  "informacja" character varying(252),
   PRIMARY KEY ("id_informacja")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Informacja
  OWNER TO postgres;

CREATE SEQUENCE Odczyt_licznika_id_seq;


CREATE TABLE Odczyt_licznika
(
  "id_odczyt_licznika" INTEGER DEFAULT NEXTVAL('Odczyt_licznika_id_seq')  NOT NULL,
  "nr_dzialki" integer NOT NULL,
  "nr_pomiaru" integer NOT NULL,
  "data" character varying(255),
  "stan_licznika" integer,
  "naleznosc" numeric(17,2),
PRIMARY KEY (id_odczyt_licznika)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Odczyt_licznika
  OWNER TO postgres;

CREATE SEQUENCE Wyciagi_JS_id_seq;

CREATE TABLE Wyciagi_JS
(
  "id_wyciagu" INTEGER DEFAULT NEXTVAL('Wyciagi_JS_id_seq')  NOT NULL,
  "nr_wyciagu" integer NOT NULL,
  "nr_dzialki" integer NOT NULL,
  "kwota" numeric(17,2),
  "data" character varying(255),
  "opis" character(255),
  "skladka" numeric(17,2),
  "cynsz" numeric(17,2),
  "awrbp" numeric(17,2),
  "wpisowe" numeric(17,2),
  "energia_rozpoczecie_sezonu" numeric(17,2),
  "energia_zakonczenie_sezonu" numeric(17,2),
  "dyzur_z_roku_poprzedniego_na_biezacy" numeric(17,2),
  "dyzur_z_roku_biezacego_na_nastepny" numeric(17,2),
  "zadluzenie_z_roku_poprzedniego" numeric(17,2),
  "licznik" numeric(17,2),
   PRIMARY KEY ("id_wyciagu")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Wyciagi_JS
  OWNER TO postgres;

CREATE SEQUENCE Zobowiazania_id_seq;

CREATE TABLE Zobowiazania
(
  "nr_zobowiazania" INTEGER DEFAULT NEXTVAL('Zobowiazania_id_seq')  NOT NULL,
  "rok_rozliczeniowy" integer NOT NULL,
  "nr_dzialki" integer NOT NULL,
  "bilans_otwarcia" numeric(17,2),
  "skladka" numeric(17,2),
  "czynsz" numeric(17,2),
  "anr" numeric(17,2),
  "wpisowe" numeric(17,2),
  "energia_rozpocecie_sezonu" numeric(17,2),
  "energia_zakonczenia_seoznu" numeric(17,2),
  "dyzur_z_roku_poprzedniego_na_biezacy" numeric(17,2),
  "dyzur_z_roku_biezacego_na_nastepny" numeric(17,2),
  "zadluzenie_z_roku_poprzedniego" numeric(17,2),
  "zobowiazania_razem_z_bo" numeric(17,2),
   PRIMARY KEY ("nr_zobowiazania")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Zobowiazania
  OWNER TO postgres;

ALTER TABLE Dostep
ADD FOREIGN KEY ("nr_dzialkowicza")
REFERENCES Dzialkowicz("nr_dzialkowicza");


ALTER TABLE Odczyt_licznika
ADD FOREIGN KEY ("nr_dzialki")
REFERENCES Dzialki("nr_dzialki");

ALTER TABLE Informacja
ADD FOREIGN KEY ("nr_dzialki")
REFERENCES Dzialki("nr_dzialki");

ALTER TABLE Wyciagi_JS
ADD FOREIGN KEY ("nr_dzialki")
REFERENCES Dzialki("nr_dzialki");

ALTER TABLE Zobowiazania
ADD FOREIGN KEY ("nr_dzialki")
REFERENCES Dzialki("nr_dzialki");

ALTER TABLE IBAN
ADD FOREIGN KEY ("nr_dzialki")
REFERENCES Dzialki("nr_dzialki");

ALTER TABLE Aktualizacja_danych
ADD FOREIGN KEY ("nr_dzialki")
REFERENCES Dzialki("nr_dzialki");

ALTER TABLE Dzialki
ADD FOREIGN KEY ("nr_czlonkowski")
REFERENCES Dzialkowicz("nr_dzialkowicza");