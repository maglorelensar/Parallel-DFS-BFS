package dao;

import entity.AramaAlgos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import util.DBConnection;


public class AramaAlgosDao {
       private Connection c;
    
       public void addAUrun(AramaAlgos a) {
        try {
            c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("insert into testler  values(default,?,?,?,?,?,?,?,?,default)");
            ps.setString(1, a.getKul_ad());
            ps.setString(2, a.getAlgo_adi());
            ps.setString(3, a.getDugum_s()); 
            ps.setString(4, a.getCore_s()); 
            ps.setString(5, a.getP_zaman());
            ps.setString(6, a.getS_zaman());   
            ps.setString(7, a.getDaha_hizli());
            ps.setString(8, a.getFark());
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("KAYDETMEDE SORUN !");
            System.out.println(e.getLocalizedMessage());
        } finally {
            DBConnection.closeConnection(c);
        }
    } 
}
