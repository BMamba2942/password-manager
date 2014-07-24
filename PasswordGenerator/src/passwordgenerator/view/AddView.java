/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.view;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import passwordgenerator.model.ModelEvent;
import passwordgenerator.model.Model;
import passwordgenerator.controller.Controller;
import passwordgenerator.controller.AddPController;
import passwordgenerator.model.PasswordModel;

/**
 *
 * @author Joseph
 */
public class AddView extends AbstractView {
    public static final String GEN = "Generate";
    private JTextField passSize;
    private JTextField passName;
    
    public AddView(Model model, Controller controller)
    {
        super(model, controller);
        Handler handle = new Handler();
        passName = new JTextField();
        JLabel passNameLabel = new JLabel("Password Name");
        JLabel passSizeLabel = new JLabel("Password Size");
        passSize = new JTextField("8");
        JButton addPass = new JButton(GEN);
        addPass.addActionListener(handle);
        passName.addActionListener(handle);
        passSize.addActionListener(handle);
        JPanel thePanel = new JPanel();
        thePanel.setLayout(new GridLayout(5, 25, 2, 1));
        thePanel.add(passNameLabel);
        thePanel.add(passName);
        thePanel.add(passSizeLabel);
        thePanel.add(passSize);
        thePanel.add(addPass);
        this.getContentPane().add(thePanel);
        pack();
    }
    
    public void modelChanged(ModelEvent e)
    {
        
    }
    
    class Handler implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            ((AddPController)getController()).operation(e.getActionCommand(),
                    passName.getText(), Integer.parseInt(passSize.getText()), null);
        }
    }
    
}
