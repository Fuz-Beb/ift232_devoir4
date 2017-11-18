/**
 * 
 */
package browser;

/**
 * @author Bebo
 *
 */
public class Filtre implements Browser
{
    private boolean adblock;
    private boolean looking;
    
    /**
     * @param b
     * @param c
     */
    public Filtre(boolean adblock, boolean looking)
    {
        this.adblock = adblock;
        this.looking = looking;
    }

    @Override
    public boolean isAdBlocked()
    {
        if(adblock)
            return true;
        
        return false;
    }

    @Override
    public boolean isLooking()
    {
        if(looking)
            return true;
        
        return false;
    }

}
