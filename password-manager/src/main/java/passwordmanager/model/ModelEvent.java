/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.model;
import java.awt.event.ActionEvent;
/**
 *
 * @author BMamba2942
 */
public class ModelEvent extends ActionEvent {
    public ModelEvent(Object obj, int id, String message)
    {
        super(obj, id, message);
    }
    
}
