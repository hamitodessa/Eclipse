package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KERESTE_MYSQL implements IKERESTE {
	static Connection con = null;
	static Statement stmt = null;

	@Override
	public void baglan() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String cumle = "jdbc:mysql://" +  BAGLAN.kerDizin.cONN_STR ;
		con = DriverManager.getConnection(cumle, BAGLAN.kerDizin.kULLANICI, BAGLAN.kerDizin.sIFRESI);
		
	}

	@Override
	public void kER_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kER_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void create_table_log() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void kER_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String ker_firma_adi() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet kod_pln() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void kod_kayit(String kodu, String aciklama) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kod_sil(String kod) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet kons_pln() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void kons_kayit(String kons, String aciklama) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kons_sil(String kons) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String kod_adi(String kod) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ker_kod_degisken_oku(String fieldd, String sno, String nerden)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ker_kod_degisken_ara(String fieldd, String sno, String nerden, String arama)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ker_kod_alt_grup_degisken_oku(int sno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean alt_grup_kontrol(int anagrp, int altgrp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ker_degisken_alt_grup_sil(int ID) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_kod_degisken_sil(String nerden, String hangi_Y, int sira)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_degisken_eski(String fieldd, String degisken_adi, String nerden, String sno, int ID)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_degisken_kayit(String fieldd, String nerden, String degisken_adi, String sira)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_degisken_alt_grup_kayit(String alt_grup, int ana_grup) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_degisken_alt_grup_eski(String alt_grup, int ana_grup, int ID)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet ker_oz_kod(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ker_giris_sil(String eno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_kaydet(KER_BILGI kBILGI, String user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet ker_oku(String eno, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dipnot_sil(String ino, String cins, String gircik) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dipnot_yaz(String eno, String bir, String iki, String uc, String tip, String gircik, String usr)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet dipnot_oku(String ino, String cins, String gircik) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aciklama_sil(String evrcins, String evrno, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aciklama_yaz(String evrcins, int satir, String evrno, String aciklama, String gircik)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String aciklama_oku(String evrcins, int satir, String evrno, String gircik)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
