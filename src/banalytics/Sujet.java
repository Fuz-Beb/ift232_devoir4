package banalytics;

import java.util.ArrayList;

import banalytics.advertisement.Advertisement;
import banalytics.log.PlaySegment;

/**
 * Classe source du patern observer
 */
public abstract class Sujet
{
    protected ArrayList<Advertisement> list;

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
     * Notifie l'ensemble des observateurs qu'un nouveau segment de lecture a
     * été joué
     * 
     * @param playSegment
     */
    public void notifyObs(PlaySegment playSegment)
    {
        for (Advertisement o : list)
            o.update(playSegment);
    }
}