package banalytics.advertisement;

import banalytics.log.PlaySegment;

/*
 * Classe représentant le contenu publicitaire en général.
 */

public abstract class Advertisement {
	
	private AdGenerator generator;
	protected String product;
	protected int viewCount;
	
	
	public Advertisement(String p,AdGenerator g){
		product=p;
		viewCount=0;
		generator=g;
	}	
	
	public int getViewCount(){
		return viewCount;
	}
	
	/*
	 * Les types d'annonces concrets déterminent ce qui constitue une vue et les conditions de
	 * lecture du média qui causent une vue de l'annonce.
	 * Chaque segment de lecture contigue est vérifiable, pour savoir si l'annonce a été vue durant
	 * cette lecture. 
	 */
	
	public abstract void verifySegment(PlaySegment segment);
		
	/*
	 * Calcule le montant de revenu pour le propriétaire de contenu multimédia généré par cette publicité.
	 * Le calcul demeure la responsabilité de la compagnie publicitaire (generator).	
	 */	
	public double monetize(){
		
		return generator.viewsToDollars(viewCount);
	}
	
	@Override
	public String toString(){
		
		return "Product: "+product+ "\n+Views: "+viewCount;
		
	}
	
	
}
