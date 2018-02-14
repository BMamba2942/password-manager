/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.controller;
import passwordmanager.model.Model;
import passwordmanager.view.View;
public interface Controller {
    void setModel(Model model);
    Model getModel();
    View getView();
    void setView(View view);
}
