
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ImNotplying
 */
public class controllerTable {

    public final void fetchData(GUI gui) {
        gui.db.connect();
        String cari = "SELECT * FROM item ";
        database.rs = gui.db.view(cari);
        try {
            DefaultTableModel model = (DefaultTableModel) gui.jTable2.getModel();
            model.setRowCount(0);
            while (database.rs.next()) {
                int uniqueID = database.rs.getInt(1);
                int produkID = database.rs.getInt(2);
                String itemName = database.rs.getString(3);
                String kategori = database.rs.getString(4);
                String lokasi = database.rs.getString(5);
                int quantity = database.rs.getInt(6);
                Object[] row = {uniqueID, produkID, itemName, kategori, lokasi, quantity, "-"};
                model.addRow(row);
            }
            cari = "SELECT * FROM itemperishable";
            database.rs = gui.db.view(cari);
            while (database.rs.next()) {
                int uniqueID = database.rs.getInt(1);
                int produkID = database.rs.getInt(2);
                String itemName = database.rs.getString(3);
                String kategori = database.rs.getString(4);
                String lokasi = database.rs.getString(5);
                int quantity = database.rs.getInt(6);
                String expire = database.rs.getString(7);
                Object[] row = {uniqueID, produkID, itemName, kategori, lokasi, quantity, expire};
                model.addRow(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        gui.db.disconnect();
    }
    
    void fetchFiltered(GUI gui) {
        // TODO add your handling code here:
        String lokasi = (String) gui.comboUnit.getSelectedItem();
        String kategori = (String) gui.comboKategori.getSelectedItem();
        String perishable = (String) gui.comboPerishable.getSelectedItem();
        String nama = gui.fieldNama.getText();
        String pid = gui.fieldPID.getText();
        String id = gui.fieldID.getText();
        String sql;
        if (lokasi.equals("Any")) {
            lokasi = "";
        }
        if (kategori.equals("Any")) {
            kategori = "";
        }
        if (perishable.equals("Any")) {
            perishable = "";
        }
        if (perishable.equals("Non-Perishable")) {
            sql = "SELECT * FROM item WHERE lokasi LIKE '%" + lokasi + "%' AND kategori LIKE '%" + kategori + "%' AND nama LIKE '%" + nama + "%' AND produkID LIKE '%" + pid + "%' AND uniqueID LIKE '%" + id + "%'";
            gui.db.connect();
            database.rs = gui.db.view(sql);
            try {
                DefaultTableModel model = (DefaultTableModel) gui.jTable2.getModel();
                model.setRowCount(0);
                while (database.rs.next()) {
                    int uniqueID = database.rs.getInt(1);
                    int produkID = database.rs.getInt(2);
                    String itemName = database.rs.getString(3);
                    String kategori2 = database.rs.getString(4);
                    String lokasi2 = database.rs.getString(5);
                    int quantity = database.rs.getInt(6);
                    Object[] row = {uniqueID, produkID, itemName, kategori2, lokasi2, quantity, "-"};
                    model.addRow(row);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            gui.db.disconnect();
        } else if (perishable.equals("Perishable")) {
            sql = "SELECT * FROM itemperishable WHERE lokasi LIKE '%" + lokasi + "%' AND kategori LIKE '%" + kategori + "%' AND nama LIKE '%" + nama + "%' AND produkID LIKE '%" + pid + "%' AND uniqueID LIKE '%" + id + "%'";
            gui.db.connect();
            database.rs = gui.db.view(sql);
            try {
                DefaultTableModel model = (DefaultTableModel) gui.jTable2.getModel();
                model.setRowCount(0);
                while (database.rs.next()) {
                    int uniqueID = database.rs.getInt(1);
                    int produkID = database.rs.getInt(2);
                    String itemName = database.rs.getString(3);
                    String kategori2 = database.rs.getString(4);
                    String lokasi2 = database.rs.getString(5);
                    int quantity = database.rs.getInt(6);
                    String expire = database.rs.getString(7);
                    Object[] row = {uniqueID, produkID, itemName, kategori2, lokasi2, quantity, expire};
                    model.addRow(row);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            gui.db.disconnect();
        } else {
            sql = "SELECT * FROM item WHERE lokasi LIKE '%" + lokasi + "%' AND kategori LIKE '%" + kategori + "%' AND nama LIKE '%" + nama + "%' AND produkID LIKE '%" + pid + "%' AND uniqueID LIKE '%" + id + "%'";
            gui.db.connect();
            database.rs = gui.db.view(sql);
            try {
                DefaultTableModel model = (DefaultTableModel) gui.jTable2.getModel();
                model.setRowCount(0);
                while (database.rs.next()) {
                    int uniqueID = database.rs.getInt(1);
                    int produkID = database.rs.getInt(2);
                    String itemName = database.rs.getString(3);
                    String kategori2 = database.rs.getString(4);
                    String lokasi2 = database.rs.getString(5);
                    int quantity = database.rs.getInt(6);
                    Object[] row = {uniqueID, produkID, itemName, kategori2, lokasi2, quantity, "-"};
                    model.addRow(row);
                }
                sql = "SELECT * FROM itemperishable WHERE lokasi LIKE '%" + lokasi + "%' AND kategori LIKE '%" + kategori + "%' AND nama LIKE '%" + nama + "%' AND produkID LIKE '%" + pid + "%' AND uniqueID LIKE '%" + id + "%'";
                database.rs = gui.db.view(sql);
                while (database.rs.next()) {
                    int uniqueID = database.rs.getInt(1);
                    int produkID = database.rs.getInt(2);
                    String itemName = database.rs.getString(3);
                    String kategori2 = database.rs.getString(4);
                    String lokasi2 = database.rs.getString(5);
                    int quantity = database.rs.getInt(6);
                    Date expireDate = database.rs.getDate(7);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String strExpireDate = formatter.format(expireDate);
                    Object[] row = {uniqueID, produkID, itemName, kategori2, lokasi2, quantity, strExpireDate};
                    model.addRow(row);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void UpdateItemIDs(GUI gui){
        try {
            gui.db.connect();
            String sql = "SELECT uniqueID, produkID FROM item";
            database.rs = gui.db.view(sql);
            while (database.rs.next()) {
                int uniqueID = database.rs.getInt(1);
                int produkID = database.rs.getInt(2);
                if (uniqueID > gui.latestUID) {
                    gui.latestUID = uniqueID;
                }
                if (produkID > gui.latestPID) {
                    gui.latestPID = produkID;
                }
            }
            sql = "SELECT uniqueID, produkID FROM itemperishable";
            database.rs = gui.db.view(sql);
            while (database.rs.next()) {
                int uniqueID = database.rs.getInt(1);
                int produkID = database.rs.getInt(2);
                if (uniqueID > gui.latestUID) {
                    gui.latestUID = uniqueID;
                }
                if (produkID > gui.latestPID) {
                    gui.latestPID = produkID;
                }
            }
            gui.db.disconnect();

        } catch (SQLException sQLException) {
        }
    }

    public void setupEdit(String nama, String kategori, String gudang, int quantity, String perishable, EditItemGUI editItemGUI) {
        editItemGUI.fieldNama.setText(nama);
        editItemGUI.comboKategori.setSelectedItem(kategori);
        editItemGUI.comboGudang.setSelectedItem(gudang);
        editItemGUI.fieldQuantity.setText(String.valueOf(quantity));
        if (!perishable.equals("-")) {
            editItemGUI.checkPerishable.setSelected(true);
            editItemGUI.fieldPerishable.setText(perishable);
            editItemGUI.fieldPerishable.setEnabled(true);
        } else {
            editItemGUI.checkPerishable.setEnabled(false);
        }
    }
    
}
