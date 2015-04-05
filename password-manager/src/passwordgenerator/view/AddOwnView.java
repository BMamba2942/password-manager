/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.view;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import passwordgenerator.model.ModelEvent;
import passwordgenerator.model.Model;
import passwordgenerator.controller.Controller;
import passwordgenerator.controller.AddPController;
import java.awt.event.ActionListener;


/**
 *
 * @author Joseph
 */
public class AddOwnView extends AbstractView {
    private JTextField passName;
    private JTextField passSize;
    public static final String ADD = "Add Password"; 
    
    
    public AddOwnView(Model model, Controller controller)
    {
        super(model, controller);
        Handler handle = new Handler();
        passName = new JTextField();
        JLabel passNameLabel = new JLabel("Password Name");
        JLabel passSizeLabel = new JLabel("Password");
        passSize = new JTextField();
        JButton addPass = new JButton(ADD);
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
    
    class Handler implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            try
            {
                ((AddPController)getController()).operation(e.getActionCommand(),
                    passName.getText(), Integer.parseInt(passSize.getText()),
                    passSize.getText());
            }
            catch(NumberFormatException f)
            {
                ((AddPController)getController()).operation(e.getActionCommand(),
                    passName.getText(), 1, //1 is a placeholder int
                    passSize.getText());
            }
        }
        
    }
    
}
