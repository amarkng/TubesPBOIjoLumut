
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ImNotplying
 */
public class controllerPerishable implements interfaceController {
    private ArrayList<itemPerishable> listItem;
    
    public controllerPerishable() 
    {
        this.listItem = new ArrayList<>();
    }
    
    public boolean isOccupied(int index, gudang Gudang) 
    {
        return index >= 0 && index < Gudang.getBesarStorage() && Gudang.getLokasiTerpakai()[index];
    }
    
    public void tambahItem(itemPerishable Item, gudang gudang) 
    {
        if (gudang.isFull()) {
            System.out.println("Gudang penuh");
            return;
        } else {
            if (!isOccupied(gudang.getLowestEmpty(), gudang)) {
                listItem.add(Item);
                gudang.getLokasiTerpakai()[gudang.getLowestEmpty()] = true;
                gudang.setLowestEmpty(gudang.getLowestEmpty() + 1);
            } else {
            //Mengecek ulang lowestEmpty
                for (int i = 0; i < gudang.getBesarStorage(); i++) {
                    if (!isOccupied(i, gudang)) {
                        gudang.setLowestEmpty(i);
                        break;
                    }
                }
                listItem.add(Item);
                gudang.getLokasiTerpakai()[gudang.getLowestEmpty()] = true;
                gudang.setLowestEmpty(gudang.getLowestEmpty() + 1);
            }
        }
        
    }

    public void deleteByID(int uniqueID, gudang gudang){
        int index = searchByID(uniqueID);
        if (index != -1) {
            listItem.remove(index);
            gudang.getLokasiTerpakai()[index] = false;
        }
    } 

    public void deleteAllProdukID(int produkID, gudang gudang) 
    {
        listItem.removeIf(itemPerishable -> itemPerishable.getProdukID() == produkID);
        for (int i = 0; i < gudang.getBesarStorage(); i++) {
            if (listItem.get(i) == null) {
                gudang.getLokasiTerpakai()[i] = false;
            }
        }
    }

    public void deleteAllName(String name, gudang gudang) 
    {
        listItem.removeIf(itemPerishable -> itemPerishable.getItemName().equals(name));
        for (int i = 0; i < gudang.getBesarStorage(); i++) {
            if (listItem.get(i) == null) {
                gudang.getLokasiTerpakai()[i] = false;
            }
        }
    }

    public int searchByID(int uniqueID) 
    {
        return listItem.indexOf(listItem.stream().filter(itemPerishable -> itemPerishable.getUniqueID() == uniqueID).findFirst().orElse(null));
    }

    public int searchAllProdukID(int produkID) 
    {
        return listItem.indexOf(listItem.stream().filter(itemPerishable -> itemPerishable.getProdukID() == produkID).findFirst().orElse(null));
    }

    public int searchAllName(String name) 
    {
        return listItem.indexOf(listItem.stream().filter(itemPerishable -> itemPerishable.getItemName().equals(name)).findFirst().orElse(null));
    }

    public void pindahLokasi(int lokasiAwal, int lokasiAkhir, gudang gudangAwal, gudang gudangAkhir) 
    {
        if (isOccupied(lokasiAwal, gudangAwal) && lokasiAkhir >= 0 && lokasiAkhir < gudangAkhir.getBesarStorage() && !isOccupied(lokasiAkhir, gudangAkhir)) {
            itemPerishable Item = listItem.get(lokasiAwal);
            listItem.remove(lokasiAwal);
            listItem.add(lokasiAkhir, Item);
            gudangAwal.getLokasiTerpakai()[lokasiAwal] = false;
            gudangAkhir.getLokasiTerpakai()[lokasiAkhir] = true;
        }
    }
    
    public void buangExpired(gudang gudang){
        LocalDate now = LocalDate.now();
        listItem.removeIf(itemPerishable -> itemPerishable.getExpireDate().isAfter(now));
        for (int i = 0; i < gudang.getBesarStorage(); i++) {
            if (listItem.get(i) == null) {
                gudang.getLokasiTerpakai()[i] = false;
            }
        }
    }

}
