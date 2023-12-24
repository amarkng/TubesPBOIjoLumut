
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ImNotplying
 */
public class controllerItemPerishable implements InterfaceControllerItem{
        
    @Override
    public void deleteSelected(GUI gui, int UID) {
            String sql = "DELETE FROM itemperishable WHERE uniqueID = '" + UID + "'";
            gui.db.connect();
            gui.db.query(sql);
            gui.db.disconnect();
            GUI.conTable.fetchData(gui);
            GUI.conGudang.setupGudang(gui);
    }

    @Override
    public void addItem(TambahItemGUI tambahItemGUI) {
        GUI.latestPID++;
        GUI.latestUID++;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String perishableText = tambahItemGUI.fieldPerishable.getText();
        LocalDate perishableDate = null;
        try {
            perishableDate = LocalDate.parse(perishableText, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date format", perishableText, 0);
        }
        tambahItemGUI.db.connect();
        String sql = "INSERT INTO itemperishable (uniqueID, produkID, nama, kategori, lokasi, quantity, expire) VALUES ('" + GUI.latestUID + "', '" + GUI.latestPID + "', '" + tambahItemGUI.fieldNama.getText() + "', '" + tambahItemGUI.comboKategori.getSelectedItem().toString() + "', '" + tambahItemGUI.comboGudang.getSelectedItem().toString() + "', '" + Integer.parseInt(tambahItemGUI.fieldQuantity.getText()) + "', '"+perishableDate+"')";
        tambahItemGUI.db.query(sql);
        tambahItemGUI.db.disconnect();
    }
    
    public void editItem(EditItemGUI editItemGUI) {
        GUI.latestPID++;
        GUI.latestUID++;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        String perishableText = editItemGUI.fieldPerishable.getText();
        LocalDate perishableDate = null;
        try {
            perishableDate = LocalDate.parse(perishableText, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date format", perishableText, 0);
        }
        editItemGUI.db.connect();
        String sql = "UPDATE itemperishable SET nama = '" + editItemGUI.fieldNama.getText() + "', kategori = '" + editItemGUI.comboKategori.getSelectedItem().toString() + "', quantity = '" + Integer.parseInt(editItemGUI.fieldQuantity.getText()) + "', expire = '"+perishableDate+"',  lokasi = '" + editItemGUI.comboGudang.getSelectedItem().toString() + "' WHERE uniqueID = '" + editItemGUI.uid + "'";
        editItemGUI.db.query(sql);
        editItemGUI.db.disconnect();

    }
}
