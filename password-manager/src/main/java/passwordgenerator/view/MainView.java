/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.view;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import passwordgenerator.controller.PasswordController;
import passwordgenerator.model.ModelEvent;
import passwordgenerator.model.PasswordModel;
import passwordgenerator.util.SimpleFileProcessor;

/**
 *
 * @author BMamba2942
 */
public class MainView extends AbstractView {
    public static final String ADD = "Generate new Password";
    public static final String COPY = "Copy selected Password to clipboard";
    public static final String ADD_OWN = "Add my own Password";
    public static final String REMOVE = "Remove selected Password";
    public static final String RENAME = "Rename selected Password";
    public static final String EXIT = "Exit";
    private JComboBox passwords;
    private static File file;
    private static PasswordController pController;
    private static SimpleFileProcessor processor;
    
    public MainView(PasswordModel model, PasswordController controller)
    {
        super(model, controller);
        super.setTitle("Password Manager");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Handler handle = new Handler();
        WHandler wHandle = new WHandler();
        this.addWindowListener(wHandle);
        passwords = new JComboBox(model.getNames());
        passwords.addActionListener(handle);
        JButton addPass = new JButton(ADD);
        addPass.addActionListener(handle);
        JButton copPass = new JButton(COPY);
        copPass.addActionListener(handle);
        JButton addOwnPass = new JButton(ADD_OWN);
        addOwnPass.addActionListener(handle);
        JButton removePass = new JButton(REMOVE);
        removePass.addActionListener(handle);
        JButton renamePass = new JButton(RENAME);
        renamePass.addActionListener(handle);
        JButton exitButton = new JButton(EXIT);
        exitButton.addActionListener(handle);
        JPanel thePanel = new JPanel();
        thePanel.setLayout(new FlowLayout());
        thePanel.add(passwords);
        thePanel.add(addOwnPass);
        thePanel.add(addPass);
        thePanel.add(copPass);
        thePanel.add(removePass);
        thePanel.add(renamePass);
        thePanel.add(exitButton);
        this.getContentPane().add(thePanel);
        pack();
    }
    
    public void modelChanged(ModelEvent e)
    {
        switch(e.getActionCommand())
        {
            case "add":
                passwords.removeAllItems();
                for(Object p : (((PasswordModel)getModel()).getNames()))
                {
                  passwords.addItem(p);
                }
            break;

            case "remove":
                passwords.removeItemAt(e.getID()); 
        }
        
        this.repaint();
        pack();
    }
    
    class Handler implements ActionListener 
    {
    	@Override
        public void actionPerformed(ActionEvent e)
        {
            ((PasswordController)getController()).operation(e.getActionCommand()
                    , passwords.getSelectedIndex(), file);
        }
    }
    
    class WHandler extends WindowAdapter
    {
    	@Override
    	public void windowClosing(WindowEvent we)
    	{
    		((PasswordModel)pController.getModel()).savePasswords(processor.getFile());
    	}
    }
    
    public static void main(String args[])
    {
        processor = new SimpleFileProcessor();
        pController = new PasswordController(processor.process());
        file = processor.getFile();
    }
    
    
}

