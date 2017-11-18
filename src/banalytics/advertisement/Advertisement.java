package banalytics.advertisement;

import banalytics.log.PlaySegment;

/**
 * Classe repr�sentant le contenu publicitaire en g�n�ral.
 */
public abstract class Advertisement
{

    protected String product;
    protected int viewCount;

    public Advertisement(String p)
    {
        product = p;
        viewCount = 0;
    }

    public int getViewCount()
    {
        return viewCount;
    }

    /*
     * Les types d'annonces concrets d�terminent ce qui constitue une vue et les
     * conditions de lecture du m�dia qui causent une vue de l'annonce. Chaque
     * segment de lecture contigue est v�rifiable, pour savoir si l'annonce a
     * �t� vue durant cette lecture.
     */

    public abstract void verifySegment(PlaySegment segment);

    public abstract void update(PlaySegment playSegment);

    /*
     * Calcule le montant de revenu pour le propri�taire de contenu multim�dia
     * g�n�r� par cette publicit�. Le calcul demeure la responsabilit� de la
     * compagnie publicitaire (generator).
     */
    public double monetize()
    {
        return AdGenerator.getInstance().viewsToDollars(viewCount);
    }

    @Override
    public String toString()
    {
        return "Product: " + product + "\n+Views: " + viewCount;
    }
}