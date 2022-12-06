package OBS_C_2025;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class STOK_ACCESS {
	private static  ISTOK _IStok;
	private static ILOGGER[] _Logger;

	public STOK_ACCESS(ISTOK _IStok, ILOGGER[] _Logger)
	{
		this._IStok = _IStok;
		this._Logger = _Logger;;
	}
	
	public static void baglan() throws SQLException, ClassNotFoundException
	{
	
		_IStok.baglan();
	}
	public void fAT_SIFIR_L (String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre, String mesaj,String evrak, DIZIN_BILGILERI dBILGI,String port) throws ClassNotFoundException, SQLException 
	{
		 _IStok.fAT_SIFIR_L( kod,dizin_yeri, dizin,  fir_adi, ins, kull, sifre,port);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla("Firma Adi:" + fir_adi,evrak, dBILGI);

	}
	public void fAT_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IStok.fAT_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla("Firma Adi:" + fir_adi,evrak, dBILGI);
	}
	public void stk_firma_adi_kayit(String fadi, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IStok.stk_firma_adi_kayit(fadi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public ResultSet envanter_rapor_u_kodu(String t1 , String t2 , String k1 ,String k2 , String f1 ,String f2,
			String m1 ,String m2 ,String anagrup,String altgrup ,String depo, String depohar ,String ure,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		return _IStok.envanter_rapor_u_kodu(t1, t2, k1, k2, f1, f2, m1, m2, anagrup, altgrup, depo, depohar, ure, uanagrp, ualtgrp);
	}
	public ResultSet fatura_oku_printer(String fno,String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.fatura_oku_printer(fno, cins);
	}
	public ResultSet parametre_oku(String nerden , String satsut) throws ClassNotFoundException, SQLException
	{
		return _IStok.parametre_oku(nerden, satsut);
	}
	public ResultSet urun_adi_oku (String kodu,String kodbarcode) throws ClassNotFoundException, SQLException
	{
		return _IStok.urun_adi_oku(kodu, kodbarcode);
	}
	public ResultSet stk_barkod_kod_oku(String sira) throws ClassNotFoundException, SQLException
	{
		return _IStok.stk_barkod_kod_oku(sira);
	}
	public ResultSet stk_kod_degisken_oku(String fieldd,String sno,String nerden) throws ClassNotFoundException, SQLException
	{
		return _IStok.stk_kod_degisken_oku(fieldd, sno, nerden);
	}
	public ResultSet urun_kod_degisken_ara(String fieldd,String sno,String nerden,String arama) throws ClassNotFoundException, SQLException 
	{
		return _IStok.urun_kod_degisken_ara(fieldd, sno, nerden, arama);
	}
	public ResultSet stk_kod_alt_grup_degisken_oku (int sno) throws ClassNotFoundException, SQLException 
	{
		return _IStok.stk_kod_alt_grup_degisken_oku(sno);
	}
	public void degisken_degistir(int anagrp,int altgrp, int anaygrp,int altygrp
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IStok.degisken_degistir(anagrp, altgrp, anaygrp, altygrp);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public  boolean alt_grup_kontrol(int anagrp,int altgrp) throws ClassNotFoundException, SQLException
	{
		return _IStok.alt_grup_kontrol(anagrp, altgrp);
	}
	public void urun_degisken_alt_grup_sil(int  ID , String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IStok.urun_degisken_alt_grup_sil(ID);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void urun_kod_degisken_sil(   String hangi_Y,String nerden  ,   int sira) throws ClassNotFoundException, SQLException
	{
		_IStok.urun_kod_degisken_sil(nerden, hangi_Y, sira);
		
	}
	public void urun_degisken_eski(String fieldd ,String degisken_adi ,String nerden ,String sno ,int ID ) throws ClassNotFoundException, SQLException 
	{
		_IStok.urun_degisken_eski(fieldd, degisken_adi, nerden, sno, ID);
	}
	public void urun_degisken_alt_grup_eski(String alt_grup ,int ana_grup ,int  ID ) throws ClassNotFoundException, SQLException
	{
		_IStok.urun_degisken_alt_grup_eski(alt_grup, ana_grup, ID);
	}
	public void urun_degisken_kayit(String fieldd  ,String nerden,String degisken_adi,String sira) throws ClassNotFoundException, SQLException 
	{
		_IStok.urun_degisken_kayit(fieldd, nerden, degisken_adi, sira);
	}
	public void  urun_degisken_alt_grup_kayit (String alt_grup , int ana_grup ) throws ClassNotFoundException, SQLException
	{
		_IStok.urun_degisken_alt_grup_kayit(alt_grup, ana_grup);
	}
	public void evr_format_irs(String satsut, double Tarih, double Sevk_Tarih , double FIRMA_KODU ,double FIRMA_UNVANI ,
			double VERGI_DAIRESI,double VERGI_NO ,double GIDECEGI_YER, double NOT_1 ,double NOT_2 ,double NOT_3 ,double BASLIK_BOLUM ,
			double BARKOD,double URUN_KODU ,double URUN_ADI ,double DEPO ,double SIMGE , double BIRIM_FIAT  ,
                 double ISKONTO  , double MIKTAR  , double K_D_V  , double TUTAR  , double TUTAR_TOPLAM  ,
                 double ISKONTO_TOPLAMI  , double BAKIYE  , double K_D_V_TOPLAMI  , double BELGE_TOPLAMI  , double YAZI_ILE  , double ALT_BOLUM,String usr  ) throws ClassNotFoundException, SQLException
	{
		_IStok.evr_format_irs(satsut, Tarih, Sevk_Tarih, FIRMA_KODU, FIRMA_UNVANI, VERGI_DAIRESI, VERGI_NO, GIDECEGI_YER, NOT_1, NOT_2, NOT_3, BASLIK_BOLUM, BARKOD, URUN_KODU, URUN_ADI, DEPO, SIMGE, BIRIM_FIAT, ISKONTO, MIKTAR, K_D_V, TUTAR, TUTAR_TOPLAM, ISKONTO_TOPLAMI, BAKIYE, K_D_V_TOPLAMI, BELGE_TOPLAMI, YAZI_ILE, ALT_BOLUM, usr);
	}
	public void evr_format_fat(String satsut, double Tarih,  double FIRMA_KODU ,double FIRMA_UNVANI ,
			double VERGI_DAIRESI,double VERGI_NO ,double GIDECEGI_YER, double NOT_1 ,double NOT_2 ,double NOT_3 ,double BASLIK_BOLUM ,
			double BARKOD,double URUN_KODU ,double URUN_ADI ,double DEPO , double IZAHAT,double SIMGE , double BIRIM_FIAT  ,
                 double ISKONTO  , double MIKTAR  , double K_D_V  , double TUTAR  , double TUTAR_TOPLAM  ,
                 double ISKONTO_TOPLAMI  , double BAKIYE  , double K_D_V_TOPLAMI  , double BELGE_TOPLAMI  ,
                 double TEVKIFAT_ORANI  , double AL_TAR_TEV_ED_KDV  , double TEV_DAH_TOP_TUTAR  ,
                 double BEYAN_ED_KDV  , double TEV_HAR_TOP_TUT  , double YAZI_ILE  , double TEV_KASESI  , double ALT_BOLUM  ,
                 double N1  , double  N2  , double  N3  , double  N4  , double  N5  , double  N6  , double  N7  , double  N8  , double  N9  , double  N10  ,
                 String usr  ) throws ClassNotFoundException, SQLException 
	{
		_IStok.evr_format_fat(satsut, Tarih, FIRMA_KODU, FIRMA_UNVANI, VERGI_DAIRESI, VERGI_NO, GIDECEGI_YER, NOT_1, NOT_2, NOT_3, BASLIK_BOLUM, BARKOD, URUN_KODU, URUN_ADI, DEPO, IZAHAT, SIMGE, BIRIM_FIAT, ISKONTO, MIKTAR, K_D_V, TUTAR, TUTAR_TOPLAM, ISKONTO_TOPLAMI, BAKIYE, K_D_V_TOPLAMI, BELGE_TOPLAMI, TEVKIFAT_ORANI, AL_TAR_TEV_ED_KDV, TEV_DAH_TOP_TUTAR, BEYAN_ED_KDV, TEV_HAR_TOP_TUT, YAZI_ILE, TEV_KASESI, ALT_BOLUM, N1, N2, N3, N4, N5, N6, N7, N8, N9, N10, usr);
	}
	public ResultSet bos_kur(String kur , String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.bos_kur(kur, cins);
	}
	public int fatura_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.fatura_no_al(cins);
	}
	public boolean stok_bak_kontrol(String kodu) throws ClassNotFoundException, SQLException
	{
		return _IStok.stok_bak_kontrol(kodu);
	}
	public boolean stok_bak_kontrol_barcode(String barkodu) throws ClassNotFoundException, SQLException 
	{
		return _IStok.stok_bak_kontrol_barcode(barkodu);
	}
	public ResultSet fatura_oku(String fno,String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.fatura_oku(fno, cins);
	}
	public String aciklama_oku(String evrcins,int satir,String evrno,String gircik) throws ClassNotFoundException, SQLException
	{
		return _IStok.aciklama_oku(evrcins, satir, evrno, gircik);
	}
	public ResultSet irsaliye_no_doldur(String fno,String hareket) throws ClassNotFoundException, SQLException 
	{
		return _IStok.irsaliye_no_doldur(fno, hareket);
	}
	public ResultSet sql_sorgu(String sql) throws ClassNotFoundException, SQLException
	{
		return _IStok.sql_sorgu(sql);
	}
	public void fat_giris_sil(String fno,String cins , String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		_IStok.fat_giris_sil(fno, cins);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void fat_kaydet(String fatno ,String kodu ,int depo ,double  fiat ,double tevkifat  
            , double miktar ,String gircik ,double tutar,double iskonto ,double kdv  
            , String tarih, String izah,String doviz,String  adrfirma ,String carfirma  
            , String ozkod ,double kur ,String cins,int  anagrp,int  altgrp ,String usr
            , String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IStok.fat_kaydet(fatno, kodu, depo, fiat, tevkifat, miktar, gircik, tutar, iskonto, kdv, tarih, izah, doviz, adrfirma, carfirma, ozkod, kur, cins, anagrp, altgrp, usr);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void stok_sil(String eno,String ecins,String cins) throws ClassNotFoundException, SQLException
	{
		_IStok.stok_sil(eno, ecins, cins);
	}
	public void stk_kaydet(String evrno ,String evrcins , String tarih,int depo,String urnkodu,double miktar,double fiat ,double tutar
            , double kdvlitut,String hareket ,String izah , int anagrp ,int altgrp ,double kur ,String b1,String doviz 
            , String hspkodu ,String usr) throws ClassNotFoundException, SQLException
	{
		_IStok.stk_kaydet(evrno, evrcins, tarih, depo, urnkodu, miktar, fiat, tutar, kdvlitut, hareket, izah, anagrp, altgrp, kur, b1, doviz, hspkodu, usr);
	}
	public void dipnot_yaz(String eno,String bir,String iki,String uc,String tip,String gircik,String usr) throws ClassNotFoundException, SQLException
	{
		_IStok.dipnot_yaz(eno, bir, iki, uc, tip, gircik, usr);
	}
	public void dipnot_sil(String ino,String cins,String gircik) throws ClassNotFoundException, SQLException
	{
		_IStok.dipnot_sil(ino, cins, gircik);
	}
	public void aciklama_sil(String evrcins,String evrno,String cins) throws ClassNotFoundException, SQLException
	{
		_IStok.aciklama_sil(evrcins, evrno, cins);
	}
	public void aciklama_yaz(String evrcins,int satir,String evrno,String aciklama,String gircik) throws ClassNotFoundException, SQLException 
	{
		_IStok.aciklama_yaz(evrcins, satir, evrno, aciklama, gircik);
	}
	public void  fat_no_yaz(String irsno,String fatno) throws ClassNotFoundException, SQLException
	{
		_IStok.fat_no_yaz(irsno, fatno);
	}
	public ResultSet irs_son_satis_fiati_oku(String kodu,String muskodu,String gircik) throws ClassNotFoundException, SQLException
	{
		return _IStok.irs_son_satis_fiati_oku(kodu, muskodu, gircik);
	}
	public ResultSet son_satis_fiati_oku(String kodu,String muskodu,String gircik) throws ClassNotFoundException, SQLException 
	{
		return _IStok.son_satis_fiati_oku(kodu, muskodu, gircik);
	}
	public ResultSet fat_oz_kod (String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.fat_oz_kod(cins);
	}
	public ResultSet dipnot_oku(String ino,String cins ,String gircik) throws ClassNotFoundException, SQLException
	{
		return _IStok.dipnot_oku(ino, cins, gircik);
	}
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.son_no_al(cins);
	}
	public ResultSet irsaliye_oku (String ino,String hareket) throws ClassNotFoundException, SQLException
	{
		return _IStok.irsaliye_oku(ino, hareket);
	}
	public ResultSet ggdy_oku() throws ClassNotFoundException, SQLException 
	{
		return _IStok.ggdy_oku();
	}
	public void gdy_sil(Integer gid) throws ClassNotFoundException, SQLException
	{
		_IStok.gdy_sil(gid);
	}public void gdy_kayit(String isim ,String adres,String semt,String sehir,String usr) throws ClassNotFoundException, SQLException
	{
		_IStok.gdy_kayit(isim, adres, semt, sehir, usr);
	}
	public int uretim_fisno_al() throws ClassNotFoundException, SQLException
	{
		return _IStok.uretim_fisno_al();
	}
	public String uret_son_bordro_no_al () throws ClassNotFoundException, SQLException 
	{
		return _IStok.uret_son_bordro_no_al();
	}
	public double son_imalat_fiati_oku(String kodu) throws ClassNotFoundException, SQLException 
	{
		return _IStok.son_imalat_fiati_oku(kodu);
	}
	public double gir_ort_fiati_oku(String kodu,String ilkt,String tarih) throws ClassNotFoundException, SQLException
	{
		return _IStok.gir_ort_fiati_oku(kodu, ilkt, tarih);
	}
	public ResultSet uret_ilk_tarih(String baslangic,String tar,String ukodu) throws ClassNotFoundException, SQLException
	{
		return _IStok.uret_ilk_tarih(baslangic, tar, ukodu);
	}
	public ResultSet imalat_urun_ara (String kodu) throws ClassNotFoundException, SQLException
	{
		return _IStok.imalat_urun_ara(kodu);
	}
	public ResultSet resim_oku(String ukodu) throws ClassNotFoundException, SQLException
	{
		return _IStok.resim_oku(ukodu);
	}
	public ResultSet recete_oku (String rno) throws ClassNotFoundException, SQLException 
	{
		return _IStok.recete_oku(rno);
	}
	public ResultSet stok_oku(String eno,String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.stok_oku(eno, cins);
	}
	public ResultSet irsaliye_faturasiz(String hareket ,String arama) throws ClassNotFoundException, SQLException
	{
		return _IStok.irsaliye_faturasiz(hareket, arama);
	}
	public int irsaliye_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.irsaliye_no_al(cins);
	}
	public void irs_giris_sil(String ino,String hareket) throws ClassNotFoundException, SQLException
	{
		_IStok.irs_giris_sil(ino, hareket);
	}
	public void irs_kayit(String irsno ,String kodu ,int depo ,double fiat ,double  iskon ,double miktar,double tutar ,
            double  kdv ,String  tar ,String dvz ,double kur,String firma ,String crhsp ,String sevktar ,String ozkod ,
            int  anagrp ,int altgrp,String fatno,String harek,String cins ,String usr ,String izahat) throws ClassNotFoundException, SQLException
	{
		_IStok.irs_kayit(irsno, kodu, depo, fiat, iskon, miktar, tutar, kdv, tar, dvz, kur, firma, crhsp, sevktar, ozkod, anagrp, altgrp, fatno, harek, cins, usr, izahat);
	}
	public ResultSet irs_oz_kod (String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.irs_oz_kod(cins);
	}
	public String son_irsno_al(String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.son_irsno_al(cins);
	}
	public ResultSet recete_arama() throws ClassNotFoundException, SQLException
	{
		return _IStok.recete_arama();
	}
	public int recete_no_al() throws ClassNotFoundException, SQLException
	{
		return _IStok.recete_no_al();
	}
	public String recete_son_bordro_no_al () throws ClassNotFoundException, SQLException
	{
		return _IStok.recete_son_bordro_no_al();
	}
	public void kod_recete_yaz(String ukodu,String rec) throws ClassNotFoundException, SQLException
	{
		_IStok.kod_recete_yaz(ukodu, rec);
	}
	public void rec_sil(String rno) throws ClassNotFoundException, SQLException
	{
		_IStok.rec_sil(rno);
	}
	public void recete_kayit(String recno,boolean durum,String tur,String kodu ,double miktar ,int anagrp,int altgrup ,String usr) throws ClassNotFoundException, SQLException
	{
		_IStok.recete_kayit(recno, durum, tur, kodu, miktar, anagrp, altgrup, usr);
	}
	public void ana_yaz(String ydosya,String kull) throws ClassNotFoundException, SQLException
	{
		_IStok.ana_yaz(ydosya, kull);
	}
	public void alt_yaz(String ydosya,String kull) throws ClassNotFoundException, SQLException 
	{
		_IStok.alt_yaz(ydosya, kull);
	}
	public void gidecegi_yer(String ydosya,String kull) throws ClassNotFoundException, SQLException
	{
		_IStok.gidecegi_yer(ydosya, kull);
	}
	public void evr_for(String ydosya,String kull) throws ClassNotFoundException, SQLException
	{
		_IStok.evr_for(ydosya, kull);
	}
	public void mal_yaz(String ydosya,String kull,String eski_ukodu,String yen_ukodu) throws ClassNotFoundException, SQLException
	{
		_IStok.mal_yaz(ydosya, kull, eski_ukodu, yen_ukodu);
	}
	public void ysonu_fat_kaydet(String fatno ,String kodu ,int depo ,double  fiat ,double tevkifat  
            , double miktar ,String gircik ,double tutar,double iskonto ,double kdv  
            , String tarih, String izah,String doviz,String  adrfirma ,String carfirma  
            , String ozkod ,double kur ,String cins,int  anagrp,int  altgrp ,String usr,String ydosya) throws ClassNotFoundException, SQLException
	{
		_IStok.ysonu_fat_kaydet(fatno, kodu, depo, fiat, tevkifat, miktar, gircik, tutar, iskonto, kdv, tarih, izah, doviz, adrfirma, carfirma, ozkod, kur, cins, anagrp, altgrp, usr, ydosya);
	}
	public void ysonu_stk_kaydet(String evrno ,String evrcins , String tarih,int depo,String urnkodu,double miktar,double fiat ,double tutar
            , double kdvlitut,String hareket ,String izah , int anagrp ,int altgrp ,double kur ,String b1,String doviz 
            , String hspkodu ,String usr,String ydosya) throws ClassNotFoundException, SQLException
	{
		_IStok.ysonu_stk_kaydet(evrno, evrcins, tarih, depo, urnkodu, miktar, fiat, tutar, kdvlitut, hareket, izah, anagrp, altgrp, kur, b1, doviz, hspkodu, usr, ydosya);
	}
	public ResultSet uret_doldur(String t1,String t2) throws ClassNotFoundException, SQLException
	{
		return _IStok.uret_doldur(t1, t2);
	}
	public void uret_no_degis(String e_no,String y_no) throws ClassNotFoundException, SQLException
	{
		_IStok.uret_no_degis(e_no, y_no);
	}
	public void uret_b1_degis(String y_no) throws ClassNotFoundException, SQLException
	{
		_IStok.uret_b1_degis(y_no);
	}
	public void uret_b1_sifir(String y_no) throws ClassNotFoundException, SQLException 
	{
		_IStok.uret_b1_sifir(y_no);
	}
	public void uret_izahat_duzelt(String y_no) throws ClassNotFoundException, SQLException
	{
		_IStok.uret_izahat_duzelt(y_no);
	}
	public void uret_izahat_cikis_duzelt(String y_no,String eski_no) throws ClassNotFoundException, SQLException 
	{
		_IStok.uret_izahat_cikis_duzelt(y_no, eski_no);
	}
	public void uret_aciklama_duzelt(String y_no,int asid) throws ClassNotFoundException, SQLException 
	{
		_IStok.uret_aciklama_duzelt(y_no, asid);
	}
	public void uretim_fisno_yapilanma_kayit(int nom) throws ClassNotFoundException, SQLException
	{
		_IStok.uretim_fisno_yapilanma_kayit(nom);
	}
	public ResultSet urun_arama() throws ClassNotFoundException, SQLException 
	{
		return _IStok.urun_arama();
	}
	public int zayi_fisno_al() throws ClassNotFoundException, SQLException
	{
		return _IStok.zayi_fisno_al();
	}
	public ResultSet zayi_oku(String eno,String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.zayi_oku(eno, cins);
	}
	public String zayi_son_bordro_no_al () throws ClassNotFoundException, SQLException
	{
		return _IStok.zayi_son_bordro_no_al();
	}
	public ResultSet ur_kod_bak(String kodu) throws ClassNotFoundException, SQLException
	{
		return _IStok.ur_kod_bak(kodu);
	}
	public ResultSet stk_urun(String sira,String arama) throws ClassNotFoundException, SQLException
	{
		return _IStok.stk_urun(sira, arama);
	}
	public void stk_ur_sil(String kodu) throws ClassNotFoundException, SQLException 
	{
		_IStok.stk_ur_sil(kodu);
	}
	public void stk_ur_kayit(String kodu ,String adi ,String birim ,double kusurat ,String sinif,
            int anagrup, int altgrup, String acik1 ,String acik2 , int ozkod1 , int ozkod2,
            String  barkod ,int mensei,double  agirlik , InputStream resim ,double fiat ,double fiat2 , String recete ,String usr ,double fiat3 ) throws ClassNotFoundException, SQLException, IOException 
	{
		_IStok.stk_ur_kayit(kodu, adi, birim, kusurat, sinif, anagrup, altgrup, acik1, acik2, ozkod1, ozkod2, barkod, mensei, agirlik, resim, fiat, fiat2, recete, usr, fiat3);
	}
	public ResultSet envanter_rapor(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String calisanpara) throws ClassNotFoundException, SQLException
	{
		return _IStok.envanter_rapor(t1, t2, k1, k2, f1, f2, m1, m2, anagrup, altgrup, depo, depohar, ure, uanagrp, ualtgrp, calisanpara);
	}
	public ResultSet envanter_rapor_fifo(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String calisanpara) throws ClassNotFoundException, SQLException
	{
		return _IStok.envanter_rapor_fifo(t1, t2, k1, k2, f1, f2, m1, m2, anagrup, altgrup, depo, depohar, ure, uanagrp, ualtgrp, calisanpara);
	}
	public ResultSet envanter_rapor_fifo_2(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String ukodu,String calisanpara) throws ClassNotFoundException, SQLException 
	{
		return _IStok.envanter_rapor_fifo_2(t1, t2, k1, k2, f1, f2, m1, m2, anagrup, altgrup, depo, depohar, ure, uanagrp, ualtgrp, ukodu, calisanpara);
	}
	public BigDecimal envanter_rapor_lifo(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String calisanpara) throws ClassNotFoundException, SQLException
	{
		return _IStok.envanter_rapor_lifo(t1, t2, k1, k2, f1, f2, m1, m2, anagrup, altgrup, depo, depohar, ure, uanagrp, ualtgrp, calisanpara);
	}
	public ResultSet fat_rapor(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String tv1 ,String tv2,String anagrup,String altgrup , String depo,String turu ,String ozel1 ,String ozel2,
			String a1 ,String a2,String dvz ,String dvz2) throws ClassNotFoundException, SQLException
	{
		return _IStok.fat_rapor(ino1, ino2, t1, t2, m1, m2, k1, k2, tv1, tv2, anagrup, altgrup, depo, turu, ozel1, ozel2, a1, a2, dvz, dvz2);
	}
	public ResultSet fat_rapor_cari_kod(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String tv1 ,String tv2,String anagrup,String altgrup , String depo,String turu ,String ozel1 ,String ozel2,
			String a1 ,String a2,String cari_yer ,String dvz ,String dvz2, String grp) throws ClassNotFoundException, SQLException 
	{
		return _IStok.fat_rapor_cari_kod(ino1, ino2, t1, t2, m1, m2, k1, k2, tv1, tv2, anagrup, altgrup, depo, turu, ozel1, ozel2, a1, a2, cari_yer, dvz, dvz2, grp);
	}
	public ResultSet fat_rapor_fat_tar(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String tv1 ,String tv2,String anagrup,String altgrup , String depo,String turu ,String ozel1 ,String ozel2,
			String a1 ,String a2,String bir ,String dvz ,String dvz2,String iki , String grp) throws ClassNotFoundException, SQLException
	{
		return _IStok.fat_rapor_fat_tar(ino1, ino2, t1, t2, m1, m2, k1, k2, tv1, tv2, anagrup, altgrup, depo, turu, ozel1, ozel2, a1, a2, bir, dvz, dvz2, iki, grp);
	}
	public ResultSet fat_detay_rapor(String fno , String turu) throws ClassNotFoundException, SQLException
	{
		return _IStok.fat_detay_rapor(fno, turu);
	}
	public ResultSet grp_urn_kodlu(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		return _IStok.grp_urn_kodlu(sstr_2, sstr_4, kur_dos, jkj, ch1, qwq6, qwq7, qwq8, s1, s2, k1, k2, jkj1, deg1, deg2, t1, t2, sstr_5, sstr_1);
	}
	public ResultSet grp_urn_kodlu_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		return _IStok.grp_urn_kodlu_yil(sstr_2, sstr_4, kur_dos, jkj, ch1, qwq6, qwq7, qwq8, s1, s2, k1, k2, jkj1, deg1, deg2, t1, t2, sstr_5, sstr_1);
	}
	public ResultSet grp_mus_kodlu(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		return _IStok.grp_mus_kodlu(sstr_2, sstr_4, kur_dos, jkj, ch1, qwq6, qwq7, qwq8, s1, s2, k1, k2, jkj1, deg1, deg2, t1, t2, sstr_5, sstr_1);
	}
	public ResultSet grp_mus_kodlu_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		return _IStok.grp_mus_kodlu_yil(sstr_2, sstr_4, kur_dos, jkj, ch1, qwq6, qwq7, qwq8, s1, s2, k1, k2, jkj1, deg1, deg2, t1, t2, sstr_5, sstr_1);
	}
	public ResultSet grp_yil_ay(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		return _IStok.grp_yil_ay(sstr_2, sstr_4, kur_dos, jkj, ch1, qwq6, qwq7, qwq8, s1, s2, k1, k2, jkj1, deg1, deg2, t1, t2, sstr_5, sstr_1);
	}
	public ResultSet grp_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		return _IStok.grp_yil(sstr_2, sstr_4, kur_dos, jkj, ch1, qwq6, qwq7, qwq8, s1, s2, k1, k2, jkj1, deg1, deg2, t1, t2, sstr_5, sstr_1);
	}
	public ResultSet grp_ana_grup(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		return _IStok.grp_ana_grup(sstr_2, sstr_4, kur_dos, jkj, ch1, qwq6, qwq7, qwq8, s1, s2, k1, k2, jkj1, deg1, deg2, t1, t2, sstr_5, sstr_1);
	}
	public ResultSet grp_ana_grup_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		return _IStok.grp_ana_grup_yil(sstr_2, sstr_4, kur_dos, jkj, ch1, qwq6, qwq7, qwq8, s1, s2, k1, k2, jkj1, deg1, deg2, t1, t2, sstr_5, sstr_1);
	}
	public ResultSet baslik_bak(String baslik,String ordr,String jkj,String ch1,String k1,String k2,String f1,String f2,String t1,String t2) throws ClassNotFoundException, SQLException 
	{
		return _IStok.baslik_bak(baslik, ordr, jkj, ch1, k1, k2, f1, f2, t1, t2);
	}
	public ResultSet ima_alt_kod(String slct,String sstr_5,String sstr_2,String sstr_4,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String qwq9,String s1 ,String s2,String k1,String k2,String t1,String t2,
			String sstr_1,String ordrr,String sstr_55) throws ClassNotFoundException, SQLException 
	{
		return _IStok.ima_alt_kod(slct, sstr_5, sstr_2, sstr_4, jkj, ch1, qwq6, qwq7, qwq8, qwq9, s1, s2, k1, k2, t1, t2, sstr_1, ordrr, sstr_55);
	}
	public ResultSet ima_baslik_bak(String bas ,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String qwq9,String k1,String k2,String t1,String t2,String ordrr) throws ClassNotFoundException, SQLException 
	{
		return _IStok.ima_baslik_bak(bas, jkj, ch1, qwq6, qwq7, qwq8, qwq9, k1, k2, t1, t2, ordrr);
	}
	public ResultSet imalat_rapor(String f1 , String f2 , String t1 ,String t2 , String k1 ,String k2,
			String r1 ,String r2 ,String depo,String anagrp,String altgrp , String b1 ,String b2,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		return _IStok.imalat_rapor(f1, f2, t1, t2, k1, k2, r1, r2, depo, anagrp, altgrp, b1, b2, uanagrp, ualtgrp);
	}
	public ResultSet irs_rapor(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String turu,String anagrup,String altgrup , String ozkod,
			String fat1,String fat2,String a1 ,String a2) throws ClassNotFoundException, SQLException 
	{
		return _IStok.irs_rapor(ino1, ino2, t1, t2, m1, m2, k1, k2, turu, anagrup, altgrup, ozkod, fat1, fat2, a1, a2);
	}
	public ResultSet irs_detay_rapor(String ino , String turu) throws ClassNotFoundException, SQLException
	{
		return _IStok.irs_detay_rapor(ino, turu);
	}
	public ResultSet ort_hes_kodu(String fdf,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc) throws ClassNotFoundException, SQLException
	{
		return _IStok.ort_hes_kodu(fdf, qwq6, qwq7, m1, m2, s1, s2, k1, k2, t1, t2, kurkod, kurc);
	}
	public ResultSet ort_hes_ana_kodu(String fdf,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc) throws ClassNotFoundException, SQLException 
	{
		return _IStok.ort_hes_ana_kodu(fdf, qwq6, qwq7, m1, m2, s1, s2, k1, k2, t1, t2, kurkod, kurc);
	}
	public ResultSet ort_diger_kodu(String yu,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc,String iu) throws ClassNotFoundException, SQLException
	{
		return _IStok.ort_diger_kodu(yu, qwq6, qwq7, m1, m2, s1, s2, k1, k2, t1, t2, kurkod, kurc, iu);
	}
	public ResultSet rec_rapor(String f1,String f2,String k1,String k2,String anagrp,String altgrp,String durum,String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		return _IStok.rec_rapor(f1, f2, k1, k2, anagrp, altgrp, durum, uanagrp, ualtgrp);
	}
	public ResultSet rec_detay_rapor(String f1,String f2) throws ClassNotFoundException, SQLException
	{
		return _IStok.rec_detay_rapor(f1, f2);
	}
	public ResultSet stok_rapor(String t1 , String t2 , String t3 , String t4,String k1 ,String k2 , String f1 ,String f2,
			String f3 ,String turu, String depohar ,String ure,String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		return _IStok.stok_rapor(t1, t2, t3, t4, k1, k2, f1, f2, f3, turu, depohar, ure, uanagrp, ualtgrp);
	}
	public ResultSet envanter_rapor_ana_grup_alt_grup(String t1 , String t2 , String k1 ,String k2 , String f1 ,String f2,
			String m1 ,String m2 ,String anagrup,String altgrup ,String depo, String depohar ,String ure,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException 
	{
		return _IStok.envanter_rapor_ana_grup_alt_grup(t1, t2, k1, k2, f1, f2, m1, m2, anagrup, altgrup, depo, depohar, ure, uanagrp, ualtgrp);
	}
	public ResultSet urun_liste(String k1 , String k2,String b1 , String b2,String bi1 , String bi2,String s1 , String s2,
			String anagrp , String altgrp,String mensei,String o1 , String o2,String  depo) throws ClassNotFoundException, SQLException
	{
		return _IStok.urun_liste(k1, k2, b1, b2, bi1, bi2, s1, s2, anagrp, altgrp, mensei, o1, o2, depo);
	}
	public ResultSet zayi_rapor(String t1 , String t2 , String k1 , String  k2 , String f1 ,String f2,
			String anagrp,String altgrp, String depo,String uanagrp,String ualtgrp ) throws ClassNotFoundException, SQLException
	{
		return _IStok.zayi_rapor(t1, t2, k1, k2, f1, f2, anagrp, altgrp, depo, uanagrp, ualtgrp);
	}
	public String fat_firma_adi() throws ClassNotFoundException, SQLException
	{
		return _IStok.fat_firma_adi();
	}
}
