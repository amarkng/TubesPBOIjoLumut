/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ImNotplying
 */
public interface interfaceController {

    public boolean isOccupied(int index, gudang Gudang);

    public void deleteByID(int uniqueID, gudang gudang);

    public void deleteAllProdukID(int produkID, gudang gudang);

    public void deleteAllName(String name, gudang gudang);

    public int searchByID(int uniqueID);

    public int searchAllProdukID(int produkID);

    public int searchAllName(String name);

    public void pindahLokasi(int lokasiAwal, int lokasiAkhir, gudang gudangAwal, gudang gudangAkhir);
}
