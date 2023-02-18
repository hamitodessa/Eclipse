package OBS_C_2025;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;



public interface ISTOK {

	public void baglan() throws SQLException, ClassNotFoundException;
	public void fAT_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins,String kull,String sifre,String port) throws ClassNotFoundException, SQLException;
	public void fAT_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public String fat_firma_adi() throws ClassNotFoundException, SQLException;
	public ResultSet stk_kod_degisken_oku(String fieldd,String sno,String nerden) throws ClassNotFoundException, SQLException;
	public ResultSet urun_kod_degisken_ara(String fieldd,String sno,String nerden,String arama) throws ClassNotFoundException, SQLException;
	public ResultSet stk_kod_alt_grup_degisken_oku (int sno) throws ClassNotFoundException, SQLException;
	public void stk_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
	public void urun_degisken_eski(String fieldd ,String degisken_adi ,String nerden ,String sno ,int ID ) throws ClassNotFoundException, SQLException;
	public void urun_degisken_alt_grup_eski(String alt_grup ,int ana_grup ,int  ID ) throws ClassNotFoundException, SQLException;
	public void urun_degisken_kayit(String fieldd  ,String nerden,String degisken_adi,String sira) throws ClassNotFoundException, SQLException;
	public void  urun_degisken_alt_grup_kayit (String alt_grup , int ana_grup ) throws ClassNotFoundException, SQLException;
	public ResultSet stk_urun(String sira,String arama) throws ClassNotFoundException, SQLException;
	public void stk_ur_sil(String kodu) throws ClassNotFoundException, SQLException;
	public void stk_ur_kayit(String kodu ,String adi ,String birim ,double kusurat ,String sinif,
            int anagrup, int altgrup, String acik1 ,String acik2 , int ozkod1 , int ozkod2,
            String  barkod ,int mensei,double  agirlik , InputStream resim ,double fiat ,double fiat2 , String recete ,String usr ,double fiat3 ) throws ClassNotFoundException, SQLException, IOException;
	public ResultSet ur_kod_bak(String kodu) throws ClassNotFoundException, SQLException;
	public ResultSet stk_barkod_kod_oku(String sira) throws ClassNotFoundException, SQLException;
	public ResultSet urun_adi_oku (String kodu,String kodbarcode) throws ClassNotFoundException, SQLException;
	public ResultSet fat_oz_kod (String cins) throws ClassNotFoundException, SQLException;
	public ResultSet son_satis_fiati_oku(String kodu,String muskodu,String gircik) throws ClassNotFoundException, SQLException;
	public void fat_giris_sil(String fno,String cins) throws ClassNotFoundException, SQLException;
	public void fat_kaydet(String fatno ,String kodu ,int depo ,double  fiat ,double tevkifat  
            , double miktar ,String gircik ,double tutar,double iskonto ,double kdv  
            , String tarih, String izah,String doviz,String  adrfirma ,String carfirma  
            , String ozkod ,double kur ,String cins,int  anagrp,int  altgrp ,String usr) throws ClassNotFoundException, SQLException;
	public void dipnot_sil(String ino,String cins,String gircik) throws ClassNotFoundException, SQLException;
	public void dipnot_yaz(String eno,String bir,String iki,String uc,String tip,String gircik,String usr) throws ClassNotFoundException, SQLException;
	public void  fat_no_yaz(String irsno,String fatno) throws ClassNotFoundException, SQLException;
	public void aciklama_sil(String evrcins,String evrno,String cins) throws ClassNotFoundException, SQLException;
	public void aciklama_yaz(String evrcins,int satir,String evrno,String aciklama,String gircik) throws ClassNotFoundException, SQLException;
	public void stok_sil(String eno,String ecins,String cins) throws ClassNotFoundException, SQLException;
	public void stk_kaydet(String evrno ,String evrcins , String tarih,int depo,String urnkodu,double miktar,double fiat ,double tutar
            , double kdvlitut,String hareket ,String izah , int anagrp ,int altgrp ,double kur ,String b1,String doviz 
            , String hspkodu ,String usr) throws ClassNotFoundException, SQLException;
	public ResultSet fatura_oku(String fno,String cins) throws ClassNotFoundException, SQLException;
	public String aciklama_oku(String evrcins,int satir,String evrno,String gircik) throws ClassNotFoundException, SQLException;
	public ResultSet irsaliye_no_doldur(String fno,String hareket) throws ClassNotFoundException, SQLException;
	public ResultSet dipnot_oku(String ino,String cins ,String gircik) throws ClassNotFoundException, SQLException;
	public int fatura_no_al(String cins) throws ClassNotFoundException, SQLException;
	public boolean stok_bak_kontrol(String kodu) throws ClassNotFoundException, SQLException;
	public boolean stok_bak_kontrol_barcode(String barkodu) throws ClassNotFoundException, SQLException;
	public void rec_sil(String rno) throws ClassNotFoundException, SQLException;
	public void recete_kayit(String recno,boolean durum,String tur,String kodu ,double miktar ,int anagrp,int altgrup ,String usr) throws ClassNotFoundException, SQLException;
	public void kod_recete_yaz(String ukodu,String rec) throws ClassNotFoundException, SQLException;
	public int recete_no_al() throws ClassNotFoundException, SQLException;
	public String recete_son_bordro_no_al () throws ClassNotFoundException, SQLException;
	public ResultSet recete_oku (String rno) throws ClassNotFoundException, SQLException;
	public ResultSet urun_arama() throws ClassNotFoundException, SQLException;
	public ResultSet imalat_urun_ara (String kodu) throws ClassNotFoundException, SQLException;
	public ResultSet resim_oku(String ukodu) throws ClassNotFoundException, SQLException;
	public ResultSet recete_arama() throws ClassNotFoundException, SQLException;
	public ResultSet stok_oku(String eno,String cins) throws ClassNotFoundException, SQLException;
	public String uret_son_bordro_no_al () throws ClassNotFoundException, SQLException ;
	public int uretim_fisno_al() throws ClassNotFoundException, SQLException;
	public double son_imalat_fiati_oku(String kodu) throws ClassNotFoundException, SQLException;
	public ResultSet uret_ilk_tarih(String baslangic,String tar,String ukodu) throws ClassNotFoundException, SQLException;
	public double gir_ort_fiati_oku(String kodu,String ilkt,String tarih) throws ClassNotFoundException, SQLException;
	public int irsaliye_no_al(String cins) throws ClassNotFoundException, SQLException;
	public ResultSet irsaliye_oku (String ino,String hareket) throws ClassNotFoundException, SQLException;
	public void irs_giris_sil(String ino,String hareket) throws ClassNotFoundException, SQLException;
	public void irs_kayit(String irsno ,String kodu ,int depo ,double fiat ,double  iskon ,double miktar,double tutar ,
            double  kdv ,String  tar ,String dvz ,double kur,String firma ,String crhsp ,String sevktar ,String ozkod ,
            int  anagrp ,int altgrp,String fatno,String harek,String cins ,String usr ,String izahat) throws ClassNotFoundException, SQLException;
	public ResultSet irs_oz_kod (String cins) throws ClassNotFoundException, SQLException;
	public ResultSet irs_son_satis_fiati_oku(String kodu,String muskodu,String gircik) throws ClassNotFoundException, SQLException;
	public ResultSet ggdy_oku() throws ClassNotFoundException, SQLException;
	public void gdy_sil(Integer gid) throws ClassNotFoundException, SQLException;
	public void gdy_kayit(String isim ,String adres,String semt,String sehir,String usr) throws ClassNotFoundException, SQLException;
	public ResultSet parametre_oku(String nerden , String satsut) throws ClassNotFoundException, SQLException ;
	public void evr_format_irs(String satsut, double Tarih, double Sevk_Tarih , double FIRMA_KODU ,double FIRMA_UNVANI ,
			double VERGI_DAIRESI,double VERGI_NO ,double GIDECEGI_YER, double NOT_1 ,double NOT_2 ,double NOT_3 ,double BASLIK_BOLUM ,
			double BARKOD,double URUN_KODU ,double URUN_ADI ,double DEPO ,double SIMGE , double BIRIM_FIAT  ,
            double ISKONTO  , double MIKTAR  , double K_D_V  , double TUTAR  , double TUTAR_TOPLAM  ,
            double ISKONTO_TOPLAMI  , double BAKIYE  , double K_D_V_TOPLAMI  , double BELGE_TOPLAMI  , double YAZI_ILE  , double ALT_BOLUM,String usr  ) throws ClassNotFoundException, SQLException;
	public void evr_format_fat(String satsut, double Tarih,  double FIRMA_KODU ,double FIRMA_UNVANI ,
			double VERGI_DAIRESI,double VERGI_NO ,double GIDECEGI_YER, double NOT_1 ,double NOT_2 ,double NOT_3 ,double BASLIK_BOLUM ,
			double BARKOD,double URUN_KODU ,double URUN_ADI ,double DEPO , double IZAHAT,double SIMGE , double BIRIM_FIAT  ,
            double ISKONTO  , double MIKTAR  , double K_D_V  , double TUTAR  , double TUTAR_TOPLAM  ,
            double ISKONTO_TOPLAMI  , double BAKIYE  , double K_D_V_TOPLAMI  , double BELGE_TOPLAMI  ,
            double TEVKIFAT_ORANI  , double AL_TAR_TEV_ED_KDV  , double TEV_DAH_TOP_TUTAR  ,
            double BEYAN_ED_KDV  , double TEV_HAR_TOP_TUT  , double YAZI_ILE  , double TEV_KASESI  , double ALT_BOLUM  ,
            double N1  , double  N2  , double  N3  , double  N4  , double  N5  , double  N6  , double  N7  , double  N8  , double  N9  , double  N10  ,
            String usr  ) throws ClassNotFoundException, SQLException;
	public ResultSet fat_rapor(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String tv1 ,String tv2,String anagrup,String altgrup , String depo,String turu ,String ozel1 ,String ozel2,
			String a1 ,String a2,String dvz ,String dvz2) throws ClassNotFoundException, SQLException;
	public ResultSet fat_rapor_fat_tar(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String tv1 ,String tv2,String anagrup,String altgrup , String depo,String turu ,String ozel1 ,String ozel2,
			String a1 ,String a2,String bir ,String dvz ,String dvz2,String iki , String grp) throws ClassNotFoundException, SQLException;
	public ResultSet fat_rapor_cari_kod(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String tv1 ,String tv2,String anagrup,String altgrup , String depo,String turu ,String ozel1 ,String ozel2,
			String a1 ,String a2,String cari_yer ,String dvz ,String dvz2, String grp) throws ClassNotFoundException, SQLException;
	public ResultSet imalat_rapor(String f1 , String f2 , String t1 ,String t2 , String k1 ,String k2,
			String r1 ,String r2 ,String depo,String anagrp,String altgrp , String b1 ,String b2,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException;
	public ResultSet envanter_rapor_u_kodu(String t1 , String t2 , String k1 ,String k2 , String f1 ,String f2,
			String m1 ,String m2 ,String anagrup,String altgrup ,String depo, String depohar ,String ure,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException;
	public ResultSet envanter_rapor_u_kodu_oncekitarih(String t1 , String t2 , String k1 ,String k2 , String f1 ,String f2,
			String m1 ,String m2 ,String anagrup,String altgrup ,String depo, String depohar ,String ure,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException;
	public ResultSet envanter_rapor_ana_grup_alt_grup(String t1 , String t2 , String k1 ,String k2 , String f1 ,String f2,
			String m1 ,String m2 ,String anagrup,String altgrup ,String depo, String depohar ,String ure,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException;
	public ResultSet stok_rapor(String t1 , String t2 , String t3 , String t4,String k1 ,String k2 , String f1 ,String f2,
			String f3 ,String turu, String depohar ,String ure,String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException;
	public ResultSet baslik_bak(String baslik,String ordr,String jkj,String ch1,String k1,String k2,String f1,String f2,String t1,String t2) throws ClassNotFoundException, SQLException;
	public ResultSet grp_urn_kodlu(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException;
	public ResultSet grp_urn_kodlu_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException;
	public ResultSet grp_mus_kodlu(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException;
	public ResultSet grp_mus_kodlu_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException;
	public ResultSet grp_yil_ay(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException;
	public ResultSet grp_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException;
	public ResultSet grp_ana_grup(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException;
	public ResultSet grp_ana_grup_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException;
	public ResultSet ima_baslik_bak(String bas ,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String qwq9,String k1,String k2,String t1,String t2,String ordrr) throws ClassNotFoundException, SQLException;
	public ResultSet ima_alt_kod(String slct,String sstr_5,String sstr_2,String sstr_4,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String qwq9,String s1 ,String s2,String k1,String k2,String t1,String t2,
			String sstr_1,String ordrr,String sstr_55) throws ClassNotFoundException, SQLException;
	public ResultSet irs_rapor(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String turu,String anagrup,String altgrup , String ozkod,
			String fat1,String fat2,String a1 ,String a2) throws ClassNotFoundException, SQLException;
	public ResultSet rec_rapor(String f1,String f2,String k1,String k2,String anagrp,String altgrp,String durum,String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException;
	public ResultSet rec_detay_rapor(String f1,String f2) throws ClassNotFoundException, SQLException;
	public ResultSet irs_detay_rapor(String ino , String turu) throws ClassNotFoundException, SQLException;
	public ResultSet ort_hes_kodu(String fdf,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc) throws ClassNotFoundException, SQLException;
	public ResultSet ort_hes_ana_kodu(String fdf,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc) throws ClassNotFoundException, SQLException;
	public ResultSet ort_diger_kodu(String yu,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc,String iu) throws ClassNotFoundException, SQLException;
	public ResultSet bos_kur(String kur , String cins) throws ClassNotFoundException, SQLException;
	public ResultSet urun_liste(String k1 , String k2,String b1 , String b2,String bi1 , String bi2,String s1 , String s2,
			String anagrp , String altgrp,String mensei,String o1 , String o2,String  depo) throws ClassNotFoundException, SQLException;
	public ResultSet uret_doldur(String t1,String t2) throws ClassNotFoundException, SQLException;
	public void uret_no_degis(String e_no,String y_no) throws ClassNotFoundException, SQLException;
	public void uret_b1_degis(String y_no) throws ClassNotFoundException, SQLException;
	public void uret_b1_sifir(String y_no) throws ClassNotFoundException, SQLException;
	public void uret_izahat_duzelt(String y_no) throws ClassNotFoundException, SQLException;
	public void uret_izahat_cikis_duzelt(String y_no,String eski_no) throws ClassNotFoundException, SQLException;
	public void uret_aciklama_duzelt(String y_no,int asid) throws ClassNotFoundException, SQLException;
	public void uretim_fisno_yapilanma_kayit(int nom) throws ClassNotFoundException, SQLException;
	public ResultSet sql_sorgu(String sql)  throws ClassNotFoundException, SQLException;
	public ResultSet zayi_oku(String eno,String cins) throws ClassNotFoundException, SQLException;
	public String zayi_son_bordro_no_al () throws ClassNotFoundException, SQLException ;
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException;
	public ResultSet envanter_rapor(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String calisanpara) throws ClassNotFoundException, SQLException;
	public ResultSet zayi_rapor(String t1 , String t2 , String k1 , String  k2 , String f1 ,String f2,
			String anagrp,String altgrp, String depo,String uanagrp,String ualtgrp ) throws ClassNotFoundException, SQLException;
	public ResultSet irsaliye_faturasiz(String hareket ,String arama) throws ClassNotFoundException, SQLException;
	public String son_irsno_al(String cins) throws ClassNotFoundException, SQLException;
	public ResultSet envanter_rapor_fifo(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String calisanpara) throws ClassNotFoundException, SQLException;
	public ResultSet envanter_rapor_fifo_2(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String ukodu,String calisanpara) throws ClassNotFoundException, SQLException;
	public BigDecimal envanter_rapor_lifo(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String calisanpara) throws ClassNotFoundException, SQLException;
	public void ana_yaz(String ydosya,String kull) throws ClassNotFoundException, SQLException;
	public void alt_yaz(String ydosya,String kull) throws ClassNotFoundException, SQLException;
	public void gidecegi_yer(String ydosya,String kull) throws ClassNotFoundException, SQLException;
	public void evr_for(String ydosya,String kull) throws ClassNotFoundException, SQLException;
	public void mal_yaz(String ydosya,String kull,String eski_ukodu,String yen_ukodu) throws ClassNotFoundException, SQLException;
	public void ysonu_fat_kaydet(String fatno ,String kodu ,int depo ,double  fiat ,double tevkifat  
            , double miktar ,String gircik ,double tutar,double iskonto ,double kdv  
            , String tarih, String izah,String doviz,String  adrfirma ,String carfirma  
            , String ozkod ,double kur ,String cins,int  anagrp,int  altgrp ,String usr,String ydosya) throws ClassNotFoundException, SQLException;
	public void ysonu_stk_kaydet(String evrno ,String evrcins , String tarih,int depo,String urnkodu,double miktar,double fiat ,double tutar
            , double kdvlitut,String hareket ,String izah , int anagrp ,int altgrp ,double kur ,String b1,String doviz 
            , String hspkodu ,String usr,String ydosya) throws ClassNotFoundException, SQLException;
	public void degisken_degistir(int anagrp,int altgrp, int anaygrp,int altygrp) throws ClassNotFoundException, SQLException;
	public void urun_degisken_alt_grup_sil(int  ID ) throws ClassNotFoundException, SQLException;
	public void urun_kod_degisken_sil(  String nerden  ,   String hangi_Y, int sira) throws ClassNotFoundException, SQLException;
	public  boolean alt_grup_kontrol(int anagrp,int altgrp) throws ClassNotFoundException, SQLException;
	public void create_table_log() throws SQLException ;
	public ResultSet fatura_oku_printer(String fno,String cins) throws ClassNotFoundException, SQLException;
	public int zayi_fisno_al() throws ClassNotFoundException, SQLException;
	public ResultSet fat_detay_rapor(String fno , String turu) throws ClassNotFoundException, SQLException;

}
