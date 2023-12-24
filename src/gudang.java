/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ImNotplying
 */

/* Kalo ada salah, ubah aja :D
   -rama
*/
public class gudang {
    private int storageID;
    private int besarStorage;
    private String tempatStorage;
    
    public gudang(int StorageID, int besarStorage, String tempatStorage){
        this.storageID = storageID;
        this.besarStorage = besarStorage;
        this.tempatStorage = tempatStorage;
    }

    public int getStorageID() {
        return storageID;
    }

    public void setStorageID(int storageID) {
        this.storageID = storageID;
    }

    public int getBesarStorage() {
        return besarStorage;
    }

    public void setBesarStorage(int besarStorage) {
        this.besarStorage = besarStorage;
    }

    public String getTempatStorage() {
        return tempatStorage;
    }

    public void setTempatStorage(String tempatStorage) {
        this.tempatStorage = tempatStorage;
    }
    
    
}
