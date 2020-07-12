package entity;


public class AramaAlgos {
    private int id;
    private String kul_ad;
    private String algo_adi;
    private String dugum_s;
    private String core_s;
    private String p_zaman;
    private String s_zaman;
    private String daha_hizli;
    private String fark;

    public AramaAlgos() {
    }

    public AramaAlgos(String kul_ad, String algo_adi, String dugum_s, String core_s, String p_zaman, String s_zaman, String daha_hizli, String fark) {
        this.kul_ad = kul_ad;
        this.algo_adi = algo_adi;
        this.dugum_s = dugum_s;
        this.core_s = core_s;
        this.p_zaman = p_zaman;
        this.s_zaman = s_zaman;
        this.daha_hizli = daha_hizli;
        this.fark = fark;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKul_ad() {
        return kul_ad;
    }

    public void setKul_ad(String kul_ad) {
        this.kul_ad = kul_ad;
    }

    public String getAlgo_adi() {
        return algo_adi;
    }

    public void setAlgo_adi(String algo_adi) {
        this.algo_adi = algo_adi;
    }

    public String getDugum_s() {
        return dugum_s;
    }

    public void setDugum_s(String dugum_s) {
        this.dugum_s = dugum_s;
    }

    public String getCore_s() {
        return core_s;
    }

    public void setCore_s(String core_s) {
        this.core_s = core_s;
    }

    public String getP_zaman() {
        return p_zaman;
    }

    public void setP_zaman(String p_zaman) {
        this.p_zaman = p_zaman;
    }

    public String getS_zaman() {
        return s_zaman;
    }

    public void setS_zaman(String s_zaman) {
        this.s_zaman = s_zaman;
    }

    public String getDaha_hizli() {
        return daha_hizli;
    }

    public void setDaha_hizli(String daha_hizli) {
        this.daha_hizli = daha_hizli;
    }

    public String getFark() {
        return fark;
    }

    public void setFark(String fark) {
        this.fark = fark;
    }

   

    
    
}
