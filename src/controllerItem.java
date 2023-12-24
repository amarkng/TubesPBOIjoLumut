
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ImNotplying
 */
public class controllerItem implements InterfaceControllerItem{

    public void deleteSelected(GUI gui, int UID) {
        String sql = "DELETE FROM item WHERE uniqueID = '" + UID + "'";
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
        tambahItemGUI.db.connect();
        String sql = "INSERT INTO item (uniqueID, produkID, nama, kategori, lokasi, quantity) VALUES ('" + GUI.latestUID + "', '" + GUI.latestPID + "', '" + tambahItemGUI.fieldNama.getText() + "', '" + tambahItemGUI.comboKategori.getSelectedItem().toString() + "', '" + tambahItemGUI.comboGudang.getSelectedItem().toString() + "', '" + Integer.parseInt(tambahItemGUI.fieldQuantity.getText()) + "')";
        tambahItemGUI.db.query(sql);
        tambahItemGUI.db.disconnect();

    }
    
    public void editItem(EditItemGUI editItemGUI) {
        GUI.latestPID++;
        GUI.latestUID++;
        editItemGUI.db.connect();
        String sql = "UPDATE item SET nama = '" + editItemGUI.fieldNama.getText() + "', kategori = '" + editItemGUI.comboKategori.getSelectedItem().toString() + "', quantity = '" + Integer.parseInt(editItemGUI.fieldQuantity.getText()) + "',  lokasi = '" + editItemGUI.comboGudang.getSelectedItem().toString() + "' WHERE uniqueID = '" + editItemGUI.uid + "'";
        editItemGUI.db.query(sql);
        editItemGUI.db.disconnect();

    }
}
