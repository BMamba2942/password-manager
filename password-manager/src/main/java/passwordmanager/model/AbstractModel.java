/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.model;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author BMamba2942
 */
public abstract class AbstractModel implements Model {
    private ArrayList listeners = new ArrayList(5);
    
    public void notifyChanged(ModelEvent event)
    {
        ArrayList list = (ArrayList)listeners.clone();
        Iterator it = list.iterator();
        while(it.hasNext())
        {
            ModelListener ml = (ModelListener)it.next();
            ml.modelChanged(event);
        }
    }
    public void addModelListener(ModelListener l)
    {
        listeners.add(l);
    }
    public void removeModelListener(ModelListener l)
    {
        listeners.remove(l);
    }
}
