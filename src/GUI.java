
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ImNotplying
 */
public class GUI extends javax.swing.JFrame {
    private database db;
    private ArrayList<item> Item;
    private item _item;
    public static ArrayList<gudang> Gudang;
    public static int latestUID = 0;
    public static int latestPID = 0;
    
    public GUI() {
        initComponents();
        db = new database();
        db.connect();
        fetchData();
        setupGudang();
        setupController();
        setupUnitCombo();
        setupKategoriCombo();
        //System.out.println(Gudang.get(0).controller.getListItem().get(0).getItemName());
    }
    
    public final void setupKategoriCombo(){
        db.connect();
        String sql = "SELECT kategori FROM kategori";
        database.rs = db.view(sql);
        try {
            DefaultComboBoxModel<String> newmodel = new DefaultComboBoxModel<>();
            newmodel.addElement("Any");
            while (database.rs.next()) {
                newmodel.addElement(database.rs.getString("kategori"));
            }
            comboKategori.setModel(newmodel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnect();
    }
    
    public final void setupUnitCombo(){
        DefaultComboBoxModel<String> newmodel = new DefaultComboBoxModel<>();
        newmodel.addElement("Any");
        for (int i = 0; i < GUI.Gudang.size(); i++) {
            newmodel.addElement(GUI.Gudang.get(i).getTempatStorage());
        }
        comboUnit.setModel(newmodel);
    }
    
    public final void setupController() {
        try {
            db.connect();
            String sql = "SELECT * FROM item";
            database.rs = db.view(sql);
            while (database.rs.next()) {
                
                int uniqueID = database.rs.getInt(1);
                int produkID = database.rs.getInt(2);
                String itemName = database.rs.getString(3);
                String kategori = database.rs.getString(4);
                String lokasi = database.rs.getString(5);
                int quantity = database.rs.getInt(6);
                
                if (uniqueID > latestUID) {
                    latestUID = uniqueID;
                }
                if (produkID > latestPID) {
                    latestPID = produkID;
                }
                
                item tempitem = new item(uniqueID, produkID, itemName, kategori, lokasi, quantity);
                for (int i = 0; i < Gudang.size(); i++) {
                    if (Gudang.get(i).getTempatStorage().equals(lokasi)) {
                        Gudang.get(i).controller.tambahItem(tempitem, Gudang.get(i));
                        break;
                    }
                }
            }
            db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public final void setupGudang(){
        try {
            Gudang = new ArrayList<>();
            db.connect();
            String sql = "SELECT * FROM gudang";
            database.rs = db.view(sql);
            while (database.rs.next()) {
                int storageID = database.rs.getInt(1);
                int besarStorage = database.rs.getInt(2);
                String tempatStorage = database.rs.getString(3);
                Gudang.add(new gudang(storageID, besarStorage, tempatStorage));
            }
            db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public final void fetchData(){
        
        db.connect();
        String cari = "SELECT * FROM item ";
        database.rs = db.view(cari);
        Item = new ArrayList<>();
        try {
            while (database.rs.next()) {
                
                int uniqueID = database.rs.getInt(1);
                int produkID = database.rs.getInt(2);
                String itemName = database.rs.getString(3);
                String kategori = database.rs.getString(4);
                String lokasi = database.rs.getString(5);
                int quantity = database.rs.getInt(6);

                Item.add(new item(uniqueID, produkID, itemName, kategori, lokasi, quantity));
            }

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0); 
            for (int i = 0; i < Item.size(); i++) {
                item ITEM = Item.get(i);
                Object[] row = { ITEM.getUniqueID(), ITEM.getProdukID(), ITEM.getItemName(), ITEM.getKategori(), ITEM.getLokasi(), ITEM.getQuantity(), "-"};
                model.addRow(row);
            }
            
            cari = "SELECT * FROM itemperishable";
            database.rs = db.view(cari);
            while (database.rs.next()) {
                
                int uniqueID = database.rs.getInt(1);
                int produkID = database.rs.getInt(2);
                String itemName = database.rs.getString(3);
                String kategori = database.rs.getString(4);
                String lokasi = database.rs.getString(5);
                int quantity = database.rs.getInt(6);
                String expire = database.rs.getString(7);

                Object[] row = { uniqueID, produkID, itemName, kategori, lokasi, quantity, expire};
                model.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        db.disconnect();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboPerishable = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        comboKategori = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        fieldID = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        fieldPID = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        fieldNama = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        comboUnit = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        EditItemButton = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        AddItemButton = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        AddKategoriButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        AddGudangButton = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        buttonDelete = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Control Unit");
        setResizable(false);
        setSize(new java.awt.Dimension(820, 500));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setPreferredSize(new java.awt.Dimension(830, 472));

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ProdukID", "Nama", "Kategori", "Lokasi", "Jumlah", "Expire"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setColumnSelectionAllowed(true);
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable2.setPreferredSize(new java.awt.Dimension(400, 620));
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMinWidth(70);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable2.getColumnModel().getColumn(1).setMinWidth(70);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(70);
            jTable2.getColumnModel().getColumn(2).setMinWidth(200);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(3).setMinWidth(150);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable2.getColumnModel().getColumn(4).setMinWidth(70);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(70);
            jTable2.getColumnModel().getColumn(5).setMinWidth(50);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(150);
        }

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setText("Filter");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("Perishable");

        comboPerishable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        comboPerishable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "Perishable", "Non-Perishable" }));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setText("Kategori");

        comboKategori.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        comboKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "Soda", "Helm", "Sendal" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("ID");

        fieldID.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        SearchButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        SearchButton.setText("Search");
        SearchButton.setAlignmentX(0.5F);
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        fieldPID.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Produk ID");

        fieldNama.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Nama Produk");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(comboPerishable, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(comboKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(fieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(fieldPID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(fieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPerishable)
                    .addComponent(comboKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldPID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel10.setText("Unit");
        jLabel10.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel17.setText("Pilih Unit Gudang");

        comboUnit.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        comboUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BDG-1", "JKT-1", "BDG-2" }));
        comboUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUnitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboUnit)
                .addContainerGap())
        );

        jLabel21.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel21.setText("Control");

        jButton9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton9.setText("Edit");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        EditItemButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        EditItemButton.setText("Edit");
        EditItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditItemButtonActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel22.setText("Edit Item");

        jLabel23.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel23.setText("Tambah Item");

        AddItemButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        AddItemButton.setText("Add");
        AddItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddItemButtonActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel24.setText("Tambah Kategori");

        AddKategoriButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        AddKategoriButton.setText("Add");
        AddKategoriButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddKategoriButtonActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel25.setText("Pindah Gudang");

        AddGudangButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        AddGudangButton.setText("Add");
        AddGudangButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGudangButtonActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel26.setText("Tambah Gudang");

        buttonDelete.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel27.setText("Delete Selected");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(EditItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel21)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(AddGudangButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddKategoriButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddItemButton))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel27))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EditItemButton)
                        .addComponent(buttonDelete)))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(AddGudangButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(AddKategoriButton)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addGap(22, 22, 22))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUnitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboUnitActionPerformed

    private void AddGudangButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGudangButtonActionPerformed
        TambahGudangGUI edit = new TambahGudangGUI(this,true);
        edit.setVisible(true);
    }//GEN-LAST:event_AddGudangButtonActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        PindahGudangGUI edit = new PindahGudangGUI(this,true);
        edit.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void EditItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditItemButtonActionPerformed
        // TODO add your handling code here:
        EditItemGUI edit = new EditItemGUI(this,true);
        edit.setVisible(true);
    }//GEN-LAST:event_EditItemButtonActionPerformed

    private void AddItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemButtonActionPerformed
        // TODO add your handling code here:
        TambahItemGUI add = new TambahItemGUI(this,true,null);
        add.setVisible(true);
    }//GEN-LAST:event_AddItemButtonActionPerformed

    private void AddKategoriButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddKategoriButtonActionPerformed
        TambahKategoriGUI add = new TambahKategoriGUI(this,true);
        add.setVisible(true);
    }//GEN-LAST:event_AddKategoriButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        fetchData();
        setupGudang();
        setupController();
    }//GEN-LAST:event_formWindowGainedFocus

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        // TODO add your handling code here:
        int rowindex = jTable2.getSelectedRow();
        if (rowindex != -1) {
            int uniqueID = (int) jTable2.getValueAt(rowindex, 0);
            String sql;
            String expire = (String) jTable2.getValueAt(rowindex, 6);
            if ("-".equals(expire)){
                sql = "DELETE FROM item WHERE uniqueID = '"+uniqueID+"'";
            } else {
                sql = "DELETE FROM itemperishable WHERE uniqueID = '"+uniqueID+"'";
            }
            
            db.connect();
            db.query(sql);
            db.disconnect();
            fetchData();
            setupGudang();
            setupController();
        }
        
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // TODO add your handling code here:
        String lokasi = (String) comboUnit.getSelectedItem();
        String kategori = (String) comboKategori.getSelectedItem();
        String perishable = (String) comboPerishable.getSelectedItem();
        String nama = fieldNama.getText();
        String pid = fieldPID.getText();
        String id = fieldID.getText();
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
        if(perishable.equals("Non-Perishable")){
            sql = "SELECT * FROM item WHERE lokasi LIKE '%"+lokasi+"%' AND kategori LIKE '%"+kategori+"%' AND nama LIKE '%"+nama+"%' AND produkID LIKE '%"+pid+"%' AND uniqueID LIKE '%"+id+"%'";
            db.connect();
            database.rs = db.view(sql);
            try {
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0); 
                while (database.rs.next()) {

                    int uniqueID = database.rs.getInt(1);
                    int produkID = database.rs.getInt(2);
                    String itemName = database.rs.getString(3);
                    String kategori2 = database.rs.getString(4);
                    String lokasi2 = database.rs.getString(5);
                    int quantity = database.rs.getInt(6);

                    Object[] row = { uniqueID, produkID, itemName, kategori2, lokasi2, quantity, "-"};
                    model.addRow(row);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            db.disconnect();
        } else if (perishable.equals("Perishable")) {
            sql = "SELECT * FROM itemperishable WHERE lokasi LIKE '%"+lokasi+"%' AND kategori LIKE '%"+kategori+"%' AND nama LIKE '%"+nama+"%' AND produkID LIKE '%"+pid+"%' AND uniqueID LIKE '%"+id+"%'";
            db.connect();
            database.rs = db.view(sql);
            try {
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0); 
                while (database.rs.next()) {

                    int uniqueID = database.rs.getInt(1);
                    int produkID = database.rs.getInt(2);
                    String itemName = database.rs.getString(3);
                    String kategori2 = database.rs.getString(4);
                    String lokasi2 = database.rs.getString(5);
                    int quantity = database.rs.getInt(6);
                    String expire = database.rs.getString(7);

                    Object[] row = { uniqueID, produkID, itemName, kategori2, lokasi2, quantity, expire};
                    model.addRow(row);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            db.disconnect();

        } else {
            sql = "SELECT * FROM item WHERE lokasi LIKE '%"+lokasi+"%' AND kategori LIKE '%"+kategori+"%' AND nama LIKE '%"+nama+"%' AND produkID LIKE '%"+pid+"%' AND uniqueID LIKE '%"+id+"%'";
            db.connect();
            database.rs = db.view(sql);
            try {
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0); 
                while (database.rs.next()) {

                    int uniqueID = database.rs.getInt(1);
                    int produkID = database.rs.getInt(2);
                    String itemName = database.rs.getString(3);
                    String kategori2 = database.rs.getString(4);
                    String lokasi2 = database.rs.getString(5);
                    int quantity = database.rs.getInt(6);

                    Object[] row = { uniqueID, produkID, itemName, kategori2, lokasi2, quantity, "-"};
                    model.addRow(row);
                }
                sql = "SELECT * FROM itemperishable WHERE lokasi LIKE '%"+lokasi+"%' AND kategori LIKE '%"+kategori+"%' AND nama LIKE '%"+nama+"%' AND produkID LIKE '%"+pid+"%' AND uniqueID LIKE '%"+id+"%'";
                database.rs = db.view(sql);
                while (database.rs.next()) {

                    int uniqueID = database.rs.getInt(1);
                    int produkID = database.rs.getInt(2);
                    String itemName = database.rs.getString(3);
                    String kategori2 = database.rs.getString(4);
                    String lokasi2 = database.rs.getString(5);
                    int quantity = database.rs.getInt(6);
                    String expire = database.rs.getString(7);

                    Object[] row = { uniqueID, produkID, itemName, kategori2, lokasi2, quantity, expire};
                    model.addRow(row);
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        

    }//GEN-LAST:event_SearchButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddGudangButton;
    private javax.swing.JButton AddItemButton;
    private javax.swing.JButton AddKategoriButton;
    private javax.swing.JButton EditItemButton;
    private javax.swing.JButton SearchButton;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JComboBox<String> comboKategori;
    private javax.swing.JComboBox<String> comboPerishable;
    private javax.swing.JComboBox<String> comboUnit;
    private javax.swing.JTextField fieldID;
    private javax.swing.JTextField fieldNama;
    private javax.swing.JTextField fieldPID;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
