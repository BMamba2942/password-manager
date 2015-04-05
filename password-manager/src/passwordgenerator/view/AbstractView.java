/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.view;
import javax.swing.*;
import passwordgenerator.model.ModelListener;
import passwordgenerator.model.AbstractModel;
import passwordgenerator.model.Model;
import passwordgenerator.controller.Controller;
/**
 *
 * @author Joseph
 */
abstract public class AbstractView extends JFrame implements View, ModelListener {
    private Model model;
    private Controller controller;
    public AbstractView(Model model, Controller controller)
    {
        setModel(model);
        setController(controller);
    }
    
    public void registerWithModel()
    {
        ((AbstractModel)model).addModelListener(this);
    }
    
    public Controller getController(){return controller;}
    public void setController(Controller controller){this.controller = controller;}
    public Model getModel(){return model;}
    public void setModel(Model model)
    {
        this.model = model;
        registerWithModel();
    }
}
