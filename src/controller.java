/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
/**
 *
 * @author ImNotplying
 */
public class controller {
    private ArrayList<item> listItem;
    
    public controller() 
    {
        this.listItem = new ArrayList<>();
    }
    
    public boolean isOccupied(int index) 
    {
        return index >= 0 && index < listItem.size() && listItem.get(index) != null;
    }

    public void tambahItem(item Item) 
    {
        listItem.add(Item);
    }

    public void deleteByID(int uniqueID) 
    {
        listItem.removeIf(item -> item.getUniqueID() == uniqueID);
    }

    public void deleteAllProdukID(int produkID) 
    {
        listItem.removeIf(item -> item.getProdukID() == produkID);
    }

    public void deleteAllName(String name) 
    {
        listItem.removeIf(item -> item.getItemName().equals(name));
    }

    public int searchByID(int uniqueID) 
    {
        return listItem.indexOf(listItem.stream().filter(item -> item.getUniqueID() == uniqueID).findFirst().orElse(null));
    }

    public int searchAllProdukID(int produkID) 
    {
        return listItem.indexOf(listItem.stream().filter(item -> item.getProdukID() == produkID).findFirst().orElse(null));
    }

    public int searchAllName(String name) 
    {
        return listItem.indexOf(listItem.stream().filter(item -> item.getItemName().equals(name)).findFirst().orElse(null));
    }

    public void pindahLokasi(int lokasiAwal, int lokasiAkhir) 
    {
        if (isOccupied(lokasiAwal) && lokasiAkhir >= 0 && lokasiAkhir < listItem.size() && !isOccupied(lokasiAkhir)) {
            item Item = listItem.get(lokasiAwal);
            listItem.remove(lokasiAwal);
            listItem.add(lokasiAkhir, Item);
        }
    }
}
