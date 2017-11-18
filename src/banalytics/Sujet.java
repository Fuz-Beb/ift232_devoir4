package banalytics;

import java.util.ArrayList;

import banalytics.advertisement.Advertisement;
import banalytics.log.PlaySegment;
import browser.Browser;

/**
 * Classe source du patern observer
 */
public abstract class Sujet
{
    private ArrayList<Advertisement> list;
    private ArrayList<Browser> browserList;

    /**
     * Constructeur par défaut
     */
    public Sujet()
    {
        list = new ArrayList<Advertisement>();
    }

    /**
     * Ajoute un observeur à la liste
     * 
     * @param o
     */
    public void attach(Advertisement o)
    {
        list.add(o);
    }

    /**
     * Ajoute un veto observeur à la liste
     * 
     * @param b
     */
    public void attachBrowser(Browser b)
    {
        browserList.add(b);
    }

    /**
     * Notifie l'ensemble des observateurs qu'un nouveau segment de lecture a
     * été joué
     * 
     * @param playSegment
     */
    public void notifyObs(PlaySegment playSegment)
    {
        for (Advertisement o : list)
        {
            if(browserList.isEmpty())
                try
                {
                    throw new Exception("Erreur - Pas de browser !");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            
            for(Browser b : browserList)
            {
                if(!b.isAdBlocked() & b.isLooking())
                    o.update(playSegment);
            }               
        }
    }

    /**
     * @return the list
     */
    protected ArrayList<Advertisement> getList()
    {
        return list;
    }
}