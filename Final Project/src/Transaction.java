import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class Transaction extends JPanel {

    private static JTable table;
    private static Properties properties;
    private static String[] args = {Database.getUserId()};
    private static Vector<Vector> data;
    private static Vector<String> columnNames = new Vector<> (Arrays.asList(
        "ID",
        "Amount",
        "Date",
        "Description",
        "Category"
    ));

    public Transaction() throws Exception {
        
        table = new JTable();   
        properties = new Properties(table);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setModel(populate());
        
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
        table.setRowSelectionAllowed(true);        
        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(
            new SelectionListener() 
            );

        JSplitPane content = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT, scrollPane, properties
            );
            
        content.setResizeWeight(0.25);

        this.add(content);
    }
    public static void populateProperties() {
        
        String[] selectedData = new String[table.getColumnCount()];
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow != -1) {
            for (int i = 0; i < table.getColumnCount(); i++) {
                try {
                    selectedData[i] = table.getValueAt(selectedRow, i).toString();
                } catch (Exception e) {
                    selectedData[i] = String.valueOf(table.getValueAt(selectedRow, i));
                }
            }
            properties.populate(selectedData);
        }
    }
    public static DefaultTableModel populate() throws Exception{

        data = Database.readDataBase(
            "SELECT rowid, amount, date, description, category FROM transactions WHERE user_id=? ORDER BY date;",
            args
            );
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        return model; 
    }
    public static boolean exportToCSV() {

        String row = "";
                    
        try {

            FileWriter csv = new FileWriter(new File("Report.csv"));
            
            for (int i = 1; i < table.getColumnCount(); i++) {
                System.out.println(table.getColumnName(i));
                row += table.getColumnName(i) + ",";
            }

            csv.write(row + "\n");

            for (int i = 0; i < table.getRowCount(); i++) {
                row = "";
                for (int j = 1; j < table.getColumnCount(); j++) {
                    row += table.getValueAt(i, j) + ",";
                }
                csv.write(row + "\n");
            }

            csv.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
}
}
class SelectionListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent event) {
        try {
            Transaction.populateProperties();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}