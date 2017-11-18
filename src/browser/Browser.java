package browser;


/*
 * Interface pour les objets bidon servant � tester le d�tecteur d'Adblock
 */
public interface Browser {

	public boolean isAdBlocked();
	public boolean isLooking();
}
