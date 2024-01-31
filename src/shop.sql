-- Efface la base de donée si elle existe et la créer
DROP database IF EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;
--créer la table T_Articles
CREATE TABLE T_Articles (
	IdArticle		int(4) 		PRIMARY KEY AUTO_INCREMENT,
	Description 	varchar(40) NOT NULL,
	Brand 			varchar(30) NOT NULL,
	UnitaryPrice 	float(8) NOT NULL
) ENGINE = InnoDB;
--Insérer les données
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Souris', 'Logitech', 39.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Souris ergonomique', 'infoMalin', 51.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Clavier', 'Thrust', 19.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Système d''exploitation', 'Windows', 79.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Tapis de souris', 'Chapeau bleu', 9.99);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Clé USB 2Tb', 'Verbasin', 29.99);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Ordinateur', 'Dell', 499.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('CD-R ×50', 'Verbasin', 19.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('DVD+R ×50', 'Verbasin', 22.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Blue ray ×10', 'Verbasin', 39.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Batterie ordi portable', 'Accu pro', 179.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Casque audio filaire', 'Asus', 39.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Casque bluetooth', 'Olytech', 49.90);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Webcam', 'redeyes', 29.90);
--Ajouter des articles
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES
	('Souris gaucher', 'Razer', 169.90),
	('Clavier rétroéclairé RVB', 'logitech', 49.90),
	('Ordinateur portable', 'Alienware', 2499.90);

--ajouter un article
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Ordinateur Apple X500000', 'Apple', 44999.90);
/*
-- afficher tout
SELECT * FROM T_Articles;
**
--modifier un article
SELECT * FROM T_Articles WHERE IdArticle = 13 ;
UPDATE T_Articles SET UnitaryPrice = 54.90 WHERE IdArticle = 13;
SELECT * FROM T_Articles WHERE IdArticle = 13;
**
--supprimmer un article
SELECT * FROM T_Articles WHERE IdArticle BETWEEN 14 AND 16;
DELETE FROM T_Articles WHERE IdArticle = 15;
SELECT * FROM T_Articles WHERE IdArticle BETWEEN 14 AND 16;
**
--selectionner tous les artciles où le prix est > à 100.
SELECT * FROM T_Articles WHERE UnitaryPrice > 100;
--selectionner les articles qui ont un prix entre 50 et 150
SELECT * FROM T_Articles WHERE UnitaryPrice BETWEEN 50 AND 150;
**
--afficher les articles par prix croissant
SELECT * FROM T_Articles ORDER BY UnitaryPrice ASC;

--Afficher uniquement la description des articles
SELECT Description FROM T_Articles;
**
-- Choisissez une requête particulièrement intéressante à présenter
--cetterequête va chercher un article commençant par cla et ayant un prix inférieur à 30
SELECT * FROM T_Articles WHERE Description LIKE 'Cla%' AND UnitaryPrice < 30;
**
*
--1.12 Ajouter la table des catégories à votre base de données et insérez-en
CREATE TABLE categories (
	idCaterory int(4) PRIMARY KEY AUTO_INCREMENT,
	category varchar(30),
	description varchar(60)
);
INSERT INTO categories (category, description) VALUES
	('Matériel', 'Accéssoires informatique'),
	('Stockage', 'Périphériques de stockage'),
	('ordinateur', 'Les ordinateurs tour et portables'),
	('dépannage', 'Le matériel de dépannage');
	
SELECT * FROM categories;
**
--1.13 Afficher les articles de l'id 11 à 16 par ordre de prix croissant
SELECT * FROM T_Articles WHERE (IdArticle BETWEEN 11 AND 16) ORDER BY UnitaryPrice ASC;
*/