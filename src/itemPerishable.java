
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ImNotplying
 */
public class itemPerishable extends item{
    private LocalDate expireDate;
    
    public itemPerishable(int uniqueID, int produkID, String itemName, String kategori, int lokasi, int quantity, LocalDate expireDate){
        super(uniqueID,produkID,itemName,kategori,lokasi,quantity);
        this.expireDate=expireDate;
    }
    
    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

}