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
    private controller controller;
    private controllerPerishable controllerPerishable;
    private String tempatStorage;
    private boolean[] listLokasiTerpakai;
    private int lowestEmpty;
    
    public gudang(int StorageID, int besarStorage, String tempatStorage){
        this.storageID = storageID;
        this.besarStorage = besarStorage;
        this.tempatStorage = tempatStorage;
        this.listLokasiTerpakai = new boolean[besarStorage];
        this.lowestEmpty = 0;
        this.controller = new controller();
        this.controllerPerishable = new controllerPerishable(); 
    }
    
    public boolean isFull() {
        return lowestEmpty == besarStorage;
    }
    
    public int getLowestEmpty() {
        return lowestEmpty;
    }
    
    public void setLowestEmpty(int lowestEmpty) {
        this.lowestEmpty = lowestEmpty;
    }
    
    public void updateStorage(int besarStorage, String tempat) {
        this.besarStorage = besarStorage;
        this.tempatStorage = tempat;
        this.listLokasiTerpakai = new boolean[besarStorage];
        this.lowestEmpty = 0;
    }
    
    public void clearStorage() {
        listLokasiTerpakai = new boolean[besarStorage];
        lowestEmpty = 0;
    }
    
    public boolean[] getLokasiTerpakai() {
        return listLokasiTerpakai;
    }
    
    
}
