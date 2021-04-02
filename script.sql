CREATE DATABASE operateur;
\c operateur;

CREATE SEQUENCE SeqOperateur;
CREATE SEQUENCE SeqOffre;
CREATE SEQUENCE SeqPrixAppel;
CREATE SEQUENCE SeqClient;
CREATE SEQUENCE SeqTypeOffre;
CREATE SEQUENCE SeqDetailsOffre;
CREATE SEQUENCE SeqActionOffre;


CREATE TABLE Token(
	idClient smallserial NOT NULL PRIMARY KEY,
	nom VARCHAR(50) NOT NULL,
	numero VARCHAR(50) NOT NULL,
	token VARCHAR(100) NOT NULL,
	expiration DATE NOT NULL
);

create table operateur(
	id int primary key DEFAULT NEXTVAL('SeqOperateur'),
	nom varchar(10) not null
);
create table client(
	id smallserial primary key ,
	nom varchar(50) not null,
	Prenom varchar(50),
	identite varchar(50) not null,
	numero varchar(50) not null,
	motDePasse varchar(50),
	idOperateur int,
	FOREIGN KEY (idOperateur) REFERENCES operateur(id)
);
INSERT INTO operateur VALUES (DEFAULT,'Telma');
INSERT INTO operateur VALUES (DEFAULT,'Airtel');
INSERT INTO operateur VALUES (DEFAULT,'Orange');


create table typeOffre(
	id int primary key DEFAULT NEXTVAL('SeqTypeOffre'),
	nomType varchar(200),
	durer int
);
INSERT INTO typeOffre VALUES (DEFAULT,'Mora',1);

Create table actionOffre(
	id int primary key DEFAULT NEXTVAL('SeqActionOffre'),
	nomAction varchar(200),
	unite varchar (20)
);
INSERT INTO actionOffre VALUES (DEFAULT,'Internet','mega');
INSERT INTO actionOffre VALUES (DEFAULT,'Appel','ariary');
INSERT INTO actionOffre VALUES (DEFAULT,'Sms','nombre');
INSERT INTO actionOffre VALUES (DEFAULT,'Facebook','mega');


create table Offre (
	id int primary key DEFAULT NEXTVAL('SeqOffre'),
	nomOffre varchar(200) unique,
	prixOffre decimal,
	code varchar(200) unique,
	idOperateur int ,
	FOREIGN KEY (idOperateur) REFERENCES operateur(id)
);
INSERT INTO Offre VALUES (DEFAULT,'MoraOne',500,'Mora1000',1);

create table detailsOffre(
	id int primary key DEFAULT NEXTVAL('SeqDetailsOffre'),
	idOffre int not null,
	idType int not null,
	idAction int not null,
	valeur decimal(7,2),
	FOREIGN KEY (idAction) REFERENCES actionOffre(id),
	FOREIGN KEY (idType) REFERENCES typeOffre(id),
	FOREIGN KEY (idOffre) REFERENCES offre(id)
);
INSERT INTO detailsOffre VALUES (DEFAULT,1,1,1,1000);

create table PrixAppel(
	id int primary key DEFAULT NEXTVAL('SeqPrixAppel'),
	prix decimal(8,2) not null,
	temps int not null,
	idOperateur1 int not null,
	idOperateur2 int not null,
	FOREIGN KEY (idOperateur1) REFERENCES operateur(id),
	FOREIGN KEY (idOperateur2) REFERENCES operateur(id)
);
INSERT INTO PrixAppel VALUES(DEFAULT,100,1,1,1);
INSERT INTO PrixAppel VALUES(DEFAULT,125,1,1,2);
INSERT INTO PrixAppel VALUES(DEFAULT,130,1,1,3);
INSERT INTO PrixAppel VALUES(DEFAULT,125,1,2,1);
INSERT INTO PrixAppel VALUES(DEFAULT,105,1,2,2);
INSERT INTO PrixAppel VALUES(DEFAULT,128,1,2,3);
INSERT INTO PrixAppel VALUES(DEFAULT,135,1,3,1);
INSERT INTO PrixAppel VALUES(DEFAULT,130,1,3,2);
INSERT INTO PrixAppel VALUES(DEFAULT,103,1,3,3);


create or replace view v_offre as
 select detailsOffre.id,detailsOffre.idOffre,detailsOffre.idType,detailsOffre.idAction,detailsOffre.valeur, offre.nomOffre,offre.prixOffre,offre.code ,offre.idOperateur from detailsOffre join offre on detailsOffre.idOffre=offre.id;


create or replace view v_offretype as select v_offre.* , typeOffre.nomType, typeOffre.durer from v_offre join typeOffre on v_offre.idType=typeOffre.id;


create or replace view v_offredetails as select v_offretype.*,actionOffre.nomAction, actionOffre.unite from v_offretype join actionOffre on v_offretype.idAction=actionOffre.id;
/////////////////////////////////////////
CREATE SEQUENCE SeqDepot;
CREATE SEQUENCE SeqAppel;
CREATE SEQUENCE SeqAchatOffre;
CREATE SEQUENCE SeqAchatCredit;

create table Depot(
	id int primary key DEFAULT NEXTVAL('SeqDepot'),	
	valeur Decimal(20,2),
	idclient int ,
	datedepot date,
	etat varchar(20),
	FOREIGN KEY (idclient) REFERENCES client(id)
);
create table appel (
	id int primary key DEFAULT NEXTVAL('SeqAppel'),
	temps int,
	idclient1 int ,
	idclient2 int ,
	dateAppel Timestamp,
	FOREIGN KEY (idClient1) REFERENCES client(id),
	FOREIGN KEY (idClient2) REFERENCES client(id)
);

create table achatoffre(
	id int primary key DEFAULT NEXTVAL('SeqAchatOffre'),
	idclient int ,
	idoffre int not null,
	dateachatoffre timestamp,
	payement int,
	FOREIGN KEY (idclient) REFERENCES client(id),
	FOREIGN KEY (idoffre) REFERENCES offre(id)
);
create or replace view v_achatoffre as select achatoffre.id,achatoffre.idclient,achatoffre.idoffre ,achatoffre.dateachatoffre,achatoffre.payement ,v_offredetails.nomoffre,v_offredetails.prixoffre,v_offredetails.code,v_offredetails.nomType,v_offredetails.durer,v_offredetails.idType,v_offredetails.idAction from achatoffre join v_offredetails on achatoffre.idOffre=v_offredetails.idoffre;
	
	
create table achatcredit(
	id int primary key DEFAULT NEXTVAL('SeqAchatCredit'),
	idclient int ,
	prix decimal(20,2),
	dateachatcredit timestamp,
	FOREIGN KEY (idclient) REFERENCES client(id)
);


create or replace view totalDepot as select sum(valeur) as depot ,idclient from depot  where etat='ok' group by idclient;



create or replace view totalCredit as select sum(prix) as achatcredit ,idclient from achatcredit group by idclient;

create or replace view totalAchatOffre as select sum(prixoffre) as achatoffre ,idclient from v_achatoffre where payement = '1' group by idclient  ;

create or replace view v_solde as select totalDepot.depot, totalCredit.achatcredit,totalDepot.idclient from totalCredit join totalDepot on totalCredit.idClient=totalDepot.idClient ;

create or replace view solde as select v_solde.depot,v_solde.achatcredit,totalAchatOffre.achatoffre ,v_solde.idClient from totalAchatOffre join v_solde on v_solde.idclient=totalAchatOffre.idclient ;

create or replace view soldeClient as select depot-(achatcredit+achatoffre) as solde,idclient from solde;

create view pourcentage as
select count(*)*100/(select count(*) from achatoffre)as pourcentage, idoffre from achatoffre group by idoffre; 



create or replace view stat as
select v_offredetails.*,pourcentage.pourcentage from v_offredetails left join pourcentage on v_offredetails.idoffre=pourcentage.idoffre;