package banalytics;

import banalytics.advertisement.Advertisement;
import banalytics.log.MediaLog;
import banalytics.media.Media;
import banalytics.state.BanalyserState;

/**
 * Classe qui sert � journaliser les �v�nements qui surviennent durant
 * l'utilisation d'un contenu multim�dia par un utilisateur. Une fois les
 * �v�nements correctement journalis�s, il deviendra possible d'analyser le
 * comportement de l'utilisateur et d'en tirer des statistiques d'utilisation.
 * 
 */
public class Banalyser extends Sujet
{
    private Media media;
    private MediaLog log;
    private BanalyserState state;

    public Banalyser(Media m, Advertisement ad)
    {
        this.init(m, m.createLog(), BanalyserState.INITIAL, ad);
    }

    public Banalyser(Media med, MediaLog l, BanalyserState st, Advertisement ad)
    {
        this.init(med, l, st, ad);
    }

    private final void init(Media med, MediaLog l, BanalyserState st, Advertisement ad)
    {
        media = med;
        log = l;
        state = st;

        attach(ad);
    }

    /**
     * Retourne le cumul des revenues (de chaque annonce)
     * 
     * @return double
     */
    public double getAdRevenue()
    {
        double result = 0.0;

        for (Advertisement ad : list)
        {
            result += ad.monetize();
        }
        return result;
    }

    /**
     * Retourne le revenue d'une seul annonce
     * 
     * @param ad
     * @return
     */
    public double getAdRevenueOneAd(Advertisement ad)
    {
        return ad.monetize();
    }

    public String getTextLog()
    {

        String res = "" + media;
        res += "\n" + log + "\n";
        return res;

    }

    public void start(long position)
    {

        state = state.start(log, position);
    }

    public void stop(long position)
    {

        state = state.stop(log, position);

        // Notifie l'ensemble des annonces due au nouveau segment de lecture
        notifyObs(log.getLastSegment());
    }

    public void pause(long position)
    {

        state = state.pause(log, position);

        // Notifie l'ensemble des annonces due au nouveau segment de lecture
        notifyObs(log.getLastSegment());
    }

    public void resume(long time)
    {

        state = state.resume(log, time);
    }

    public void move(long source, long destination)
    {

        state = state.move(log, source, destination);
    }

    public void buffer(long position)
    {

        state = state.buffer(log, position);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Banalyser))
            return false;
        Banalyser other = (Banalyser) obj;
        if (state != other.state)
            return false;
        if (!log.equals(other.log))
            return false;
        if (!media.equals(other.media))
            return false;

        return true;
    }
}