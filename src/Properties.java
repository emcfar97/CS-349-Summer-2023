import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

public class Properties extends JPanel {

    private static JTable table;
    private static String rowid;
    private static FormField amountField;
    private static FormField dateField;
    private static FormField descriptionField;
    private static FormField categoryField;
    private static JButton updateButton;
    private static JButton insertButton;

    private String[] Category = {
        "", "Auto & Transport", "Bills & Utilities", "Business Services", "Education", "Entertainment", "Fees & Charges", "Financial", "Food & Dining", "Gifts & Donations", "Health & Fitness", "Home", "Income", "Investments", "Loans", "Shopping", "Taxes", "Transfer", "Uncategorized"
        };

    public Properties(JTable table_) {

        table = table_;
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        constraints.gridx = 0;

        amountField = new FormField(
            "Amount:", "Label", new Object[] {"editable"}
            );   
        dateField = new FormField(
            "Date:", "Label", new Object[] {""}
            );
        descriptionField = new FormField(
            "Description:", "Label", new Object[] {""}
            );
        categoryField = new FormField(
            "Category:", "Combo", Category
            );

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ButtonActionListener());
        insertButton = new JButton("Insert");
        insertButton.addActionListener(new ButtonActionListener());

        this.add(amountField, constraints);
        this.add(dateField, constraints);
        this.add(descriptionField, constraints);
        this.add(categoryField, constraints);

        this.add(updateButton, constraints);
        this.add(insertButton, constraints);
    }
    public void populate(String[] args) {

        rowid = args[0];
        
        amountField.setValue(args[1]);
        dateField.setValue(args[2]);
        if (args[3] != "null") {
            descriptionField.setValue(args[3]);
            }
        descriptionField.setValue(args[3]);
        categoryField.setValue(args[4]);
    }
    public static void insertRecord() {

        String[] args = new String[5];

        args[0] = amountField.getValue()[0].toString();
        args[1] = dateField.getValue()[0].toString();
        args[2] = descriptionField.getValue()[0].toString();
        args[3] = categoryField.getValue()[0].toString();
        args[4] = Database.getUserId();

        try {
            Vector<Vector> data = Database.readDataBase(
                "INSERT INTO transactions(amount, date, description, category, user_id) VALUES(?, ?, ?, ?, ?);",
                args
                );

            int result = (int)data.get(0).get(0);
            if (result == 1) {
                table.setModel(Transaction.populate());
                table.repaint();
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    public static void updateRecord() {

        String[] args = new String[6];

        args[0] = amountField.getValue()[0].toString();
        args[1] = dateField.getValue()[0].toString();
        args[2] = descriptionField.getValue()[0].toString();
        args[3] = categoryField.getValue()[0].toString();
        args[4] = rowid;
        args[5] = Database.getUserId();

        try {
            Vector<Vector> data = Database.readDataBase(
                "UPDATE transactions SET amount=?, date=?, description=?, category=? WHERE rowid=? AND user_id=?;",
                args
                );

            int result = (int)data.get(0).get(0);
            if (result == 1) {
                table.setModel(Transaction.populate());
                table.repaint();
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
class ButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        Properties.updateRecord();
    }
}