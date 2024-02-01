import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.naming.spi.DirStateFactory.Result;
import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import java.sql.Statement;
import java.sql.Connection;

import org.omg.PortableInterceptor.TRANSPORT_RETRY;

public class TestBdd {
	private static String table = "T_Articles";

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Exercice 2, interagis avec Eclipse dans ma base de données");
		boolean again = true;
		int choise;
		while (again) {
			String instruction = "Choisissez:\n"+
					"1: Consulter les articles - 2: Ajouter un article - 3: supprimmer un article - 4: Éditer un article";
			choise = writeNumber(instruction, scn);
			switch (choise) {
			case 1:
				lookDatabase();
				break;
			case 2:
				addArticle(scn);
				break;
			case 3:
				deleteArticle(scn);
				break;
			case 4:
				modifyArticle(scn);
				break;
			default:
				System.out.println("Cette option n'existe pas");
				break;
			}
			System.out.println("Souhaitez-vous continuer ? (Oui / Non)");
			if (scn.next().equals("Non")) {
				again = false;
			}
		}
		System.out.println("Fin du programme");
	}

	//voir les articles
	public static void lookDatabase() {
		ArrayList<Article> articles = RequestDatabase.readDatabase("SELECT * FROM "+table+";");
		for(Article a: articles) {
			System.out.println("Id: "+a.getIdArticle() +". Description: "+ a.getDescription()+". Marque: "+a.getBrand()+". Prix unitaire: "+a.getStringPrice()+".");
		}
	}

	//ajouter un article
	public static void addArticle(Scanner scn) {
		String addOk, article, marque;
		boolean addBool = false;
		double price;
		do {
			article = writeData("Quel est l'article a ajouter ?", scn);
			marque = writeData("Quel est la marque de l'article ?", scn);
			price = writePrice("Quel est le prix ?", scn);
			System.out.println("Ajouter:\n"+
					article+". marque: "+marque+". au prix unitaire de: "+price+".\n"+
					"Oui ou Non ?");
			addOk = scn.next();
			if (addOk.equals("Oui")) {
				RequestDatabase.writeDataBase("INSERT INTO "+table+" (Description, Brand, UnitaryPrice) VALUES ('"+article+"', '"+marque+"', "+price+");");
				addBool = true;
			}
		} while (!addBool);
	}

	//supprimmer un article
	public static void deleteArticle(Scanner scn) {
		String instruction = "Choississez l'id de l'article à supprimmer. 0 = afficher la liste des articles";
		int choise;
		boolean tryAgain = true;
		while (tryAgain) {
			choise = writeNumber(instruction, scn);
			if (choise != 0) {
				String articleToDelete = "";
				ArrayList<Article> article = RequestDatabase.readDatabase("SELECT * FROM "+table+" WHERE IdArticle = "+choise+";");
				for (Article a : article) {
					articleToDelete += "Id: "+a.getIdArticle() +". Description: "+ a.getDescription()+". Marque: "+a.getBrand()+". Prix unitaire: "+a.getStringPrice()+".";
				}
				System.out.println("Voulez-vous supprimmer l'article:\n"+
						articleToDelete+"\n"+
						"Cette action est irréversible ! (Oui / Non)"
						);
				if (scn.next().equals("Oui")) {
					RequestDatabase.writeDataBase("DELETE FROM "+table+" WHERE IdArticle = "+choise+";");
					tryAgain = false;
				} else {
					System.out.println("La supression est annulé");	
				}
			} else {
				lookDatabase();
			}
		}
	}
	
	//modifier un article
	public static void modifyArticle(Scanner scn) {
		String instruction = "Sélectionner l'id de l'article à modifier. (0 pour les afficher tous)";
		int choise;
		boolean tryAgain = true;
		while (tryAgain) {
			choise = writeNumber(instruction, scn);
			if (choise != 0) {
				String 	articleToChange = "",
						articleBase = "",
						marqueBase = "";
				Double 	prixBase = (double) 0;
				Double	newPrix;
				ArrayList<Article> article = RequestDatabase.readDatabase("SELECT * FROM "+table+" WHERE IdArticle = "+choise+";");
				for (Article a : article) {
					articleBase = a.getDescription();
					marqueBase = a.getBrand();
					prixBase = a.getUnitPrice();
					articleToChange += "Id: "+a.getIdArticle() +". Description: "+articleBase+". Marque: "+marqueBase+". Prix unitaire: "+prixBase+".";
				}
				String 	newArticle = modifyContentArticle(articleBase, scn),
						newMarque = modifyContentArticle(marqueBase, scn);
				System.out.println("Ancien prix: "+prixBase+". Le changer ? (Oui/Non)");
				String changePrice = scn.next();
				if (changePrice.equals("Oui")) {
					newPrix = writePrice("Quel est le nouveau prix ?", scn);
				} else {
					newPrix = prixBase;
				}
				if (articleBase != newArticle || marqueBase != newMarque || prixBase != newPrix) {
					RequestDatabase.writeDataBase("UPDATE "+table+" SET Description = '"+newArticle+"', Brand = '"+newMarque+"', UnitaryPrice = "+newPrix+" WHERE IdArticle = "+choise+";");
				} else {
					System.out.println("Les données sont inchangés");
				}
				tryAgain = false;
			} else {
				lookDatabase();
			}
		}
		
	}

	//récupére une donnée
	public static String writeData(String instruction, Scanner scn) {
		scn.nextLine();
		String x, tryAgainStr;
		boolean tryAgain = false;
		do {
			System.out.println(instruction);
			x = scn.nextLine();
			System.out.println(x+" Est-ce correct (O/N)?");
			tryAgainStr = scn.nextLine();
			if (tryAgainStr.equals("O")) {
				tryAgain = true;
			}
		} while (!tryAgain);
		return x;
	}

	//récupère un nombre entier
	public static int writeNumber(String instruction, Scanner scn) {
		boolean reset = true;
		int x = 0;
		while (reset) {
			System.out.println(instruction);
			try {
				x = scn.nextInt();
				return x;
			}catch (Exception e) {
				System.out.println("ceci n'est pas un chiffre: "+e);
				scn.next();
			}
		};
		return 0;
	}

	//récupere un double
	public static double writePrice(String instruction, Scanner scn) {
		boolean reset = true;
		double x = 0;
		while (reset) {
			System.out.println(instruction);
			try {
				x = scn.nextDouble();
				return x;
			}catch (Exception e) {
				System.out.println("ceci n'est pas un chiffre: "+e);
				scn.next();
			}
		};
		return 0;
	}
	
	//methode pour changer confirmer uen modification
	public static String modifyContentArticle(String object, Scanner scn) {
		System.out.println("Modifier "+object+" ? (Oui/Non)");
		String choise = scn.next();
		if (choise.equals("Oui")) {
			String newObjet = writeData("Par quoi remplacer "+object+" ?", scn);
			return newObjet;
		} else {
			return object;
		}
	}
}
