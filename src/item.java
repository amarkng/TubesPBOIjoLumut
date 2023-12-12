
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
    private int lokasi;
    private int quantity;
    
    public item(int uniqueID, int produkID, String itemName, int lokasi, int quantity){
        this.uniqueID=uniqueID;
        this.produkID=produkID;
        this.itemName=itemName;
        this.lokasi=lokasi;
        this.quantity=quantity;
        
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

    public int getLokasi() {
        return lokasi;
    }

    public void setLokasi(int lokasi) {
        this.lokasi = lokasi;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
