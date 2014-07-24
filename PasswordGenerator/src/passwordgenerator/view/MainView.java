/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.view;
import javax.swing.*;
import passwordgenerator.controller.PasswordController;
import passwordgenerator.model.PasswordModel;
import passwordgenerator.model.ModelEvent;
import java.awt.*;
import java.awt.event.*;
import passwordgenerator.util.SimpleFileProcessor;

/**
 *
 * @author Joseph
 */
public class MainView extends AbstractView {
    public static final String ADD = "Add new Password";
    public static final String COPY = "Copy to clipboard";
    private JComboBox passwords;
    public MainView(PasswordModel model, PasswordController controller)
    {
        super(model, controller);
        super.setTitle("Password Generator");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Handler handle = new Handler();
        passwords = new JComboBox(model.getNames());
        passwords.addActionListener(handle);
        JButton addPass = new JButton(ADD);
        addPass.addActionListener(handle);
        JButton copPass = new JButton(COPY);
        copPass.addActionListener(handle);
        JPanel thePanel = new JPanel();
        thePanel.setLayout(new FlowLayout());
        thePanel.add(passwords);
        thePanel.add(addPass);
        thePanel.add(copPass);
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
            ((PasswordController)getController()).operation(e.getActionCommand(), passwords.getSelectedIndex());
        }
    }
    
    public static void main(String args[])
    {
        SimpleFileProcessor processor = new SimpleFileProcessor();
        //new PasswordController(processor.process(args));
        new PasswordController(processor.process());
    }
    
    
}

