import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.print.DocFlavor.STRING;
import javax.swing.*;

public class App extends JFrame {
    
    private static JFrame window;
    private static JFrame loginWindow;
    private static Chart chart;
    private static Transaction transaction;
    private static FormField nameField;
    private static FormField passwordField;
    private static JButton signInButton;
    private static JButton registerButton;

    public static void main(String[] args) throws Exception {
        
        Database.setCredentials();
        login();
    }
    public static void mainWindow() throws Exception {

        final int WINDOW_WIDTH = 550;
        final int WINDOW_HEIGHT = 450;

        window = new JFrame("Income-Expense Tracker");
        JMenuBar menuBar = getMenu();
        
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chart = new Chart();      
        transaction = new Transaction();
        
        JSplitPane content = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT, chart, transaction
            );
        content.setResizeWeight(0.50);        

        window.add(content);
        window.add(menuBar, BorderLayout.NORTH);
        window.setVisible(true);
    }
    public static void login() {

        final int WINDOW_WIDTH = 350;
        final int WINDOW_HEIGHT = 350;

        loginWindow = new JFrame("Login");
        loginWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel content = new JPanel();
        JPanel buttonPanel = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JLabel helpText = new JLabel("Enter Credentials");

        nameField = new FormField(
            "Name:", "Label", new String[] {""}
            );        
        passwordField = new FormField(
            "Password:", "Passwd", new String[] {""}
            );

        signInButton = new JButton("Sign In");
        registerButton = new JButton("Register");

        signInButton.addActionListener(new ButtonActionListener());
        registerButton.addActionListener(new ButtonActionListener());

        buttonPanel.add(signInButton, BorderLayout.EAST);
        buttonPanel.add(registerButton, BorderLayout.WEST);

        content.add(helpText, BorderLayout.CENTER);
        content.add(nameField, BorderLayout.CENTER);
        content.add(passwordField, BorderLayout.CENTER);
        content.add(buttonPanel);

        loginWindow.add(content);
        loginWindow.setLocationRelativeTo(null);
        loginWindow.setVisible(true);
    }
    public static void signIn() {

        String name = nameField.getValue()[0].toString();
        String pass = new String((char[]) passwordField.getValue()[0]);

        try {
            Vector<Vector> results = Database.readDataBase(
                "SELECT ?=password FROM project.users WHERE username=?;",
                new String[] {pass, name}
                );
            if ((int)results.get(0).get(0) == 1) {

                Vector<Vector> userId = Database.readDataBase(
                    "SELECT user_id FROM project.users WHERE username=? AND password=?;", 
                    new String[] {name, pass}
                    );
                Database.setUserId(userId.get(0).get(0));

                mainWindow();
                loginWindow.setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(loginWindow, "Incorrect credentials");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public static void register() {

        String name = nameField.getValue()[0].toString();
        String pass = new String((char[]) passwordField.getValue()[0]);

        try {
            Vector<Vector> results = Database.readDataBase(
                "INSERT INTO project.users(username, password) VALUES(?, ?);",
                new String[] {name, pass}
                );
            if ((int)results.get(0).get(0) == 1) {

                Vector<Vector> userId = Database.readDataBase(
                    "SELECT user_id FROM project.users WHERE username=? AND password=?;", 
                    new String[] {name, pass}
                    );
                Database.setUserId(userId.get(0).get(0));

                mainWindow();
                loginWindow.setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(loginWindow, "Incorrect credentials");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public static JMenuBar getMenu() {

        JMenuBar menuBar = new JMenuBar();

        // create a menu
        JMenu menu = new JMenu("File");
 
        // create menuitems
        JMenuItem print = new JMenuItem("Print table");
        JMenuItem close = new JMenuItem("Exit");
        
        // add ActionListener to menuItems
        print.addActionListener(new MenuActionListener());
        close.addActionListener(new MenuActionListener());
        
        // add menu items to menu
        menu.add(print);
        menu.addSeparator();
        menu.add(close);
        
        // add menu to menu bar
        menuBar.add(menu);

        return menuBar;
    }
}
class FormField extends JPanel {

    private JLabel name;
    private JComponent widget;
    private String type;
    private Object[] value = new Object[1];

    public FormField(String string, String type_, Object[] args) {
        
        this.setLayout(new GridBagLayout());
        
        name = new JLabel(string);
        type = type_;

        switch(type) {
            case "Label":
                JTextField textField = new JTextField(16);
                if (args[0] == "Editable") {
                    textField.setEditable(false);
                }
                widget = textField;
                break;
            case "Combo":
                widget = new JComboBox<>(args);
                break;
            case "Passwd":
                widget = new JPasswordField(16);
                break;
        }
        this.add(name);
        this.add(widget);
    }
    public String getName() {
        return name.getText();
    }
    public Component getWidget() {
        return widget;
    }
    public <E> Object[] getValue() {
        if (type == "Label") {
            value[0] = ((JTextField)widget).getText();
        }
        else if (type == "Combo") {
            value[0] = ((JComboBox<String>)widget).getSelectedItem();
        }
        else if (type == "Passwd") {
            value[0] = ((JPasswordField)widget).getPassword();
        }
        return value;
    }
    public void setValue(String value) {

        Component component = this.getComponent(1);

        if (type == "Label") {
            JTextField textField = (JTextField)component;
            textField.setText(value);
        }
        else if (type == "Combo") {
            JComboBox<String> comboBox = (JComboBox<String>)component;
            comboBox.setSelectedItem(value);
        }
        else if (type == "Passwd") {
            JPasswordField password = (JPasswordField)component;
            password.setText(value);
        }
        
    }
}
class ButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {

        switch(event.getActionCommand()) {
            case "Sign In":
                App.signIn();
                break;                
            case "Register":
                App.register();
                break;
            case "Insert":
                Properties.insertRecord();
                break;
            case "Update":
                Properties.updateRecord();
                break;
        }
    }
}
class MenuActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {

        switch(event.getActionCommand()) {
            case "Print table":
                Transaction.exportToCSV();
                break;                
            case "Exit":
                System.exit(0);
                break;                    
        }
    }

}