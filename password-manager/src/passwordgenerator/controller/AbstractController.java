/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.controller;
import passwordgenerator.model.Model;
import passwordgenerator.view.View;
/**
 *
 * @author BMamba2942
 */
public class AbstractController implements Controller{
    private View view;
    private Model model;
    
    public void setModel(Model model){this.model = model;}
    public Model getModel(){return model;}
    public View getView(){return view;}
    public void setView(View view){this.view = view;}
}
