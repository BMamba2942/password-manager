/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.view;
import passwordmanager.model.Model;
import passwordmanager.controller.Controller;

public interface View {
    Controller getController();
    void setController(Controller controller);
    Model getModel();
    void setModel(Model model);
}
