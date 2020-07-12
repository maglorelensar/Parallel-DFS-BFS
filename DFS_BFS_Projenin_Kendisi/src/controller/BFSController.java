package controller;

import bfs.Diyagram;
import bfs.Cekirdek;
import bfs.SeriBFS;
import dao.AramaAlgosDao;
import entity.AramaAlgos;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import util.DBConnection;

public class BFSController implements Initializable {

    @FXML
    private TextField txtCoreSayisiBelirle;
    @FXML
    private TextField txtDugumSayisiBelirle;
    @FXML
    private Label lblParalelZaman;
    @FXML
    private Label lblSeriZaman;
    @FXML
    private Label lblSonuc;
    @FXML
    private TableView<?> tblview;
    @FXML
    private TextField txtKullaniciAdi;
    
    private AramaAlgosDao adao;
    private AramaAlgos aramaalgos;
    private DBConnection db;

    public DBConnection getDb() {
        if (this.db == null) {
            this.db = new DBConnection();
        }
        return db;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCalistir(ActionEvent event) {            
		long start, end;
		final int  dugumSayisi =Integer.parseInt(txtDugumSayisiBelirle.getText());
		final int islemciSayisi = Integer.parseInt(txtCoreSayisiBelirle.getText());
		
                //Sadece Dugum sayısı kadar ziyaret olabilir
		boolean[] ziyaretDurum = new boolean[dugumSayisi];
               //İlk başta tüm düğümlerin hiç uğranmamış şekilde false yapıoruz /Tam sebebi? 
		for(int i = 0; i<dugumSayisi; i++){
			ziyaretDurum[i] = false;
		}
                
		Diyagram grph = new Diyagram(dugumSayisi,ziyaretDurum,islemciSayisi);
		
                System.out.println("---------------PARALEL_BFS---------------");
                
		start = Calendar.getInstance().getTimeInMillis();
		Thread[] cores = new Cekirdek[islemciSayisi];
		for(int i = 0; i<islemciSayisi; i++){
                     
			cores[i] = new Cekirdek(grph,i);
			cores[i].start();
		}
		for(int i = 0; i<islemciSayisi; i++){
			try {
				cores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end = Calendar.getInstance().getTimeInMillis();
                long prlzmn=end-start;
		System.out.println("Paralel Zaman "+(end-start)+" ms"); 
                lblParalelZaman.setText(String.valueOf(end-start)+" ms");
		boolean basariliCalistimi = true;
		for(int i = 0;i<dugumSayisi;i++){
			if(!ziyaretDurum[i]){
				basariliCalistimi = false;
				System.out.println("Paralel BFS Başarısız");
				break;
			}
		}
		if(basariliCalistimi)
			System.out.println("Paralel BFS Başarılı ");
		
                System.out.println("----------------SERİ_BFS-----------------");
                
		for(int i = 0;i<dugumSayisi;i++){
			ziyaretDurum[i] = false;
		}
		start = Calendar.getInstance().getTimeInMillis();
		SeriBFS serialDFS = new SeriBFS(dugumSayisi, ziyaretDurum, dugumSayisi-1);
		Thread serial = new Thread(serialDFS);
		serial.start();
		try {
			serial.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		end = Calendar.getInstance().getTimeInMillis();
                long srzmn=end-start;
		System.out.println("Seri Zaman "+(end-start)+" ms"); 
                lblSeriZaman.setText(String.valueOf(end-start)+" ms");
		basariliCalistimi = true;
		for(int i = 0;i<dugumSayisi;i++){
			if(!ziyaretDurum[i]){
				basariliCalistimi = false;
				System.out.println("Seri BFS Başarısız");
				break;
			}
		}
		if(basariliCalistimi)
			System.out.println("Seri BFS Başarılı ");
                    
                System.out.println("-----------------SONUÇ-------------------");
               
                 String x="";
                 long fark=0;
                
                if((prlzmn<srzmn)){
                    
                    System.out.println("Paralel BFS seri DFS'den daha hızlı Fark: "+(-1*(prlzmn-srzmn))+" ms");
                    lblSonuc.setText("Paralel BFS daha hızlı Fark: "+String.valueOf(-(prlzmn-srzmn))+" ms");
                    x="Paralel";
                    fark=-(prlzmn-srzmn);
                }
                else{
                    System.out.println("Seri BFS paralel DFS'den daha hızlı Fark: "+(prlzmn-srzmn)+" ms");
                    lblSonuc.setText("Seri BFS daha hızlı Fark: "+String.valueOf(prlzmn-srzmn)+" ms");
                    x="Seri";
                    fark=(prlzmn-srzmn);
                }
               // this.aramaalgos=new AramaAlgos(txtKullaniciAdi.getText(), "BFS", String.valueOf(prlzmn)+" ms", String.valueOf(srzmn)+" ms",x,String.valueOf(fark)+" ms" );
               this.aramaalgos=new AramaAlgos(txtKullaniciAdi.getText(), "BFS", txtDugumSayisiBelirle.getText(), 
                txtCoreSayisiBelirle.getText(),String.valueOf(prlzmn)+" ms", String.valueOf(srzmn)+" ms", x, String.valueOf(fark)+" ms");
                getAdao().addAUrun(aramaalgos);
                dataTransfer(tblview);
                
                txtDugumSayisiBelirle.setText("");
                txtCoreSayisiBelirle.setText("");
                txtKullaniciAdi.setText("");
                
	}

      private ObservableList<ObservableList> aa;

    private void dataTransfer(TableView t) {
        t.getColumns().clear();
        Connection c;
        aa = FXCollections.observableArrayList();
        try {
            c = getDb().getConnection();
            //sql string ifademiz. 
            String SQL = "SELECT * from  testler";//tablomuzun adı bilgi. id ve adi alanları var. 
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            // TABLO SÜTUNLARINI DİNAMİK OLARAK EKLEYECEĞİMİZ YAPI 
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                t.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }

            //ObservableList e verileri ekleyen döngü
            while (rs.next()) {
                //Satırları yinele
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //sütunları yinele
                    row.add(rs.getString(i));
                }
                System.out.println("Satır eklendi " + row);
                aa.add(row);
            }

            //Sonucu tabloya ekleme
            t.setItems(aa);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hata oluştu");
        }
    }
    @FXML
    private void btnGeri(ActionEvent e) throws IOException {
         Parent x = FXMLLoader.load(getClass().getResource("Gecit.fxml"));

            Scene z = new Scene(x);

            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

            double width = 430;
            double height = 300;

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            window.setX((screenBounds.getWidth() - width) / 2);
            window.setY((screenBounds.getHeight() - height) / 2);
       
            window.setScene(z);
            window.setTitle("");
            window.show();
        
    }

    public AramaAlgosDao getAdao() {
        if(this.adao==null)
            this.adao=new AramaAlgosDao();
        return adao;
    }

    public AramaAlgos getAramaalgos() {
        if(this.aramaalgos==null)
            this.aramaalgos=new AramaAlgos();
        return aramaalgos;
    }

    
    @FXML
    private void btnGoruntule(ActionEvent event) {
        dataTransfer(tblview);
    }

    @FXML
    private void btnKapat(ActionEvent event) {
         tblview.getItems().removeAll(aa);
    }

    
    
    
    }
    

