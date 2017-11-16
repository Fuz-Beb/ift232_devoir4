package banalytics.advertisement;

import java.util.ArrayList;
import java.util.Random;

import banalytics.media.Media;

/*
 * Générateur d'annonces. Représente la compagnie qui achète de l'espace publicitaire. 
 * Choisit présentement un produit au hasard dans une simple liste.
 * Ce générateur d'annonces produit toujours le même montant d'argent par vue.
 */

public class AdGenerator {

	private ArrayList<String> products;

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
	 * Génère une annonce qui est une bannière superposée au contenu. La
	 * bannière apparaîtra au milieu de la lecture du contenu.
	 */
	public Advertisement generateBannerAd(Media media) {

		String product = products.get(rgen.nextInt(products.size()));
		return new BannerAd(product, this, media.getDuration() / 2);

	}

	/*
	 * Formule de conversion de vues de la publicité en revenus pour le
	 * propriétaire du contenu. Applicable à toutes les publicités générées par
	 * ce générateur.
	 */
	public double viewsToDollars(int views) {

		return views * VIEWVALUE;

	}

}
