/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.model;
import java.awt.event.ActionEvent;
/**
 *
 * @author Joseph
 */
public class ModelEvent extends ActionEvent {
    public ModelEvent(Object obj, int id, String message, float amount)
    {
        super(obj, id, message);
    }
}
