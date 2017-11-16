package banalytics.advertisement;

import java.util.ArrayList;
import java.util.Random;

import banalytics.media.Media;

/*
 * G�n�rateur d'annonces. Repr�sente la compagnie qui ach�te de l'espace publicitaire. 
 * Choisit pr�sentement un produit au hasard dans une simple liste.
 * Ce g�n�rateur d'annonces produit toujours le m�me montant d'argent par vue.
 */

public class AdGenerator {

	private ArrayList<String> products;
	private static AdGenerator INSTANCE = null;


	private static final double VIEWVALUE = 0.00003;
	private Random rgen;

	public AdGenerator() {
		products = new ArrayList<String>();
		products.add("Homeopathic Colon Cleansing");
		products.add("One Simple Trick Pony");
		products.add("Shocking Weight Loss Non-Diuretic");
		products.add("Abs of Steal");
		products.add("Holistic Kale Beverages");
		products.add("Better Below the Belt Selfies");
		products.add("Get Rich Yesterday Scheme");
		products.add("Soulmate From Somewheristan");
		rgen = new Random();
	}

	/*
	 * G�n�re une annonce qui est une banni�re superpos�e au contenu. La
	 * banni�re appara�tra au milieu de la lecture du contenu.
	 */
	public Advertisement generateBannerAd(Media media) {

		String product = products.get(rgen.nextInt(products.size()));
		return new BannerAd(product, media.getDuration() / 2);

	}
	
    /*
     * G�n�re une annonce qui est une banni�re superpos�e au contenu. La
     * banni�re appara�tra au milieu de la lecture du contenu.
     */
    public Advertisement generateVideoAd(Media media) {

        String product = products.get(rgen.nextInt(products.size()));
        return new VideoAd(product, media.getDuration() / 2, media.getDuration() / 4);

    }

	/*
	 * Formule de conversion de vues de la publicit� en revenus pour le
	 * propri�taire du contenu. Applicable � toutes les publicit�s g�n�r�es par
	 * ce g�n�rateur.
	 */
	public double viewsToDollars(int views) {

		return views * VIEWVALUE;

	}

	public static synchronized AdGenerator getInstance() {
        if (INSTANCE== null) {
            INSTANCE= new AdGenerator();
        }
        return INSTANCE;
    }
}
