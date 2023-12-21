
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ImNotplying
 */
public class item {
    private int uniqueID;
    private int produkID;
    private String itemName;
    private String kategori;
    private String lokasi;
    private int quantity;
    
    public item(int uniqueID, int produkID, String itemName, String kategori, String lokasi, int quantity){
        this.uniqueID=uniqueID;
        this.produkID=produkID;
        this.itemName=itemName;
        this.kategori=kategori;
        this.lokasi=lokasi;
        this.quantity=quantity;
        
    }

    public String getKategori() {
        return kategori;
    }

    public void setkategori(String kategori) {
        this.kategori = kategori;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public int getProdukID() {
        return produkID;
    }

    public void setProdukID(int produkID) {
        this.produkID = produkID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
