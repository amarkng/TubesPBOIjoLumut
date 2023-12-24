
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ImNotplying
 */
public class controllerKategori {

    public final void setupKategoriCombo(GUI gui) {
        gui.db.connect();
        String sql = "SELECT * FROM kategori";
        database.rs = gui.db.view(sql);
        try {
            DefaultComboBoxModel<String> newmodel = new DefaultComboBoxModel<>();
            newmodel.addElement("Any");
            while (database.rs.next()) {
                int id = database.rs.getInt(1);
                newmodel.addElement(database.rs.getString("kategori"));
                if (id > GUI.latestKID) {
                    GUI.latestKID = id;
                }
            }
            gui.db.disconnect();
            gui.comboKategori.setModel(newmodel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void kategoriComboTambah(TambahItemGUI tambahItemGUI) {
        tambahItemGUI.db.connect();
        String sql = "SELECT kategori FROM kategori";
        database.rs = tambahItemGUI.db.view(sql);
        try {
            DefaultComboBoxModel<String> newmodel = new DefaultComboBoxModel<>();
            while (database.rs.next()) {
                newmodel.addElement(database.rs.getString("kategori"));
            }
            tambahItemGUI.comboKategori.setModel(newmodel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tambahItemGUI.db.disconnect();
    }

    void addKategori(TambahKategoriGUI tambahKategoriGUI) {
        GUI.latestKID++;
        String kategori = tambahKategoriGUI.fieldKategori.getText();
        String sql = "INSERT INTO kategori VALUES (" + GUI.latestKID + ", '" + kategori + "')";
        tambahKategoriGUI.db.connect();
        tambahKategoriGUI.db.query(sql);
        tambahKategoriGUI.db.disconnect();
    }

    void kategoriComboEdit(EditItemGUI editItemGUI) {
        editItemGUI.db.connect();
        String sql = "SELECT kategori FROM kategori";
        database.rs = editItemGUI.db.view(sql);
        try {
            DefaultComboBoxModel<String> newmodel = new DefaultComboBoxModel<>();
            while (database.rs.next()) {
                newmodel.addElement(database.rs.getString("kategori"));
            }
            editItemGUI.comboKategori.setModel(newmodel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        editItemGUI.db.disconnect();
    }
    
}
