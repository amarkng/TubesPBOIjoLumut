/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
/**
 *
 * @author ImNotplying
 */
public class itemPerishable extends item{
    private Date expireDate;
    
    public itemPerishable(int uniqueID, int produkID, String itemName, String kategori, int lokasi, int quantity, Date expireDate){
        super(uniqueID,produkID,itemName,kategori,lokasi,quantity);
        this.expireDate=expireDate;
    }
    
    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

}