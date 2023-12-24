
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ImNotplying
 */
public class controllerGudang {

    public final void setupUnitCombo(GUI gui) {
        DefaultComboBoxModel<String> newmodel = new DefaultComboBoxModel<>();
        newmodel.addElement("Any");
        for (int i = 0; i < GUI.Gudang.size(); i++) {
            newmodel.addElement(GUI.Gudang.get(i).getTempatStorage());
        }
        gui.comboUnit.setModel(newmodel);
    }

    public final void setupGudang(GUI gui) {
        try {
            GUI.Gudang = new ArrayList<>();
            gui.db.connect();
            String sql = "SELECT * FROM gudang";
            database.rs = gui.db.view(sql);
            while (database.rs.next()) {
                int storageID = database.rs.getInt(1);
                int besarStorage = database.rs.getInt(2);
                String tempatStorage = database.rs.getString(3);
                if (storageID > GUI.latestGID) {
                    GUI.latestGID = storageID;
                }
                GUI.Gudang.add(new gudang(storageID, besarStorage, tempatStorage));
            }
            gui.db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void tambahGudang(TambahGudangGUI tambahGudangGUI) {
        GUI.latestGID++;
        String tempatStorage = tambahGudangGUI.fieldGudang.getText();
        int besarStorage = Integer.parseInt(tambahGudangGUI.fieldBesar.getText());
        String sql = "INSERT INTO gudang VALUES (" + GUI.latestGID + ", " + besarStorage + ", '" + tempatStorage + "')";
        tambahGudangGUI.db.connect();
        tambahGudangGUI.db.query(sql);
        tambahGudangGUI.db.disconnect();
    }

    void gudangComboTambah(TambahItemGUI tambahItemGUI) {
        DefaultComboBoxModel<String> newmodel = new DefaultComboBoxModel<>();
        for (int i = 0; i < GUI.Gudang.size(); i++) {
            newmodel.addElement(GUI.Gudang.get(i).getTempatStorage());
        }
        tambahItemGUI.comboGudang.setModel(newmodel);
    }
    
}
