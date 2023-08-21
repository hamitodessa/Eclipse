package OBS_C_2025;

public class sayiyiYaziyaCevir {


	//sayiyiYaziyaCevir cevir = new sayiyiYaziyaCevir();
	//double qa = 150001.25 ;
	//String qwe = Double.toString(qa);
	// String yaziyla= cevir.sayiyiYaziyaCevir(qwe, 2, "TL", "KURUŞ", "#", null, null, null);
	// System.out.println(yaziyla);



	// Sayıyı yazıya çevirme (Kuruşlu ve sınırsız basamaklı, Örneğin: binyüzseksen TL onbeş Kuruş,
	// ayrıca istediğiniz yabancı dile entegre edebilirsiniz.)
	// Örnek kullanım şekli : sayiyiYaziyaCevir cevir = new sayiyiYaziyaCevir();
	// String yaziyla= cevir.sayiyiYaziyaCevir(125478.254, 2, "TL", "Kuruş", "#", null, null, null);
	public  String sayiyiYaziyaCevir(String sayi, int kurusbasamak,	String parabirimi, String parakurus, String diyez, String[] bb1, String[] bb2, String[] bb3) 
	{
		//public String sayiyiYaziyaCevir(String sayi, int kurusbasamak,	String parabirimi, String parakurus, String diyez, String[] bb1, String[] bb2, String[] bb3) {

		// kurusbasamak virgülden sonra gösterilecek basamak sayısı
		// bb1, bb2, bb3 ise sayıların değişik dillerde yazılması için list
		// parabirimi = TL gibi , parakurus = Kuruş gibi
		// diyez başa ve sona kapatma işareti atar # gibi
		String[] b1 = {"", "BİR", "İKİ", "ÜÇ",  "DÖRT", "BEŞ", "ALTI", "YEDİ", "SEKİZ", "DOKUZ"};
		String[] b2 = {"", "ON", "YİRMİ", "OTUZ", "KIRK", "ELLİ", "ALTMIŞ", "YETMİŞ", "SEKSEN", "DOKSAN"};
		String[] b3 = {"", "YÜZ", "BİN", "MİLYON", "MİLYAR", "TRİLYON", "TRİLYAR"};
		// ek(6) = "KATRİLYON"
		// ek(7) = "KATRİLYAR"

		//
		if (bb1 != null) { // farklı dil kullanımı yada farklı yazım biçimi için
			b1 = bb1;
		}
		if (bb2 != null) { // farklı dil kullanımı
			b2 = bb2;
		}
		if (bb3 != null) { // farklı dil kullanımı
			b3 = bb3;
		}

		String say1, say2 = ""; // say1 virgül öncesi, say2 kuruş bölümü
		String sonuc = "";

		sayi = sayi.replace(",", "."); //virgül noktaya çevrilir

		if (sayi.indexOf(".") > 0) { // nokta varsa (kuruş)

			say1 = sayi.substring(0, sayi.indexOf(".")); // virgül öncesi
			say2 = sayi.substring(sayi.indexOf("."), sayi.length()); // virgül sonrası, kuruş

		} else {
			say1 = sayi; // kuruş yok
		}

		char[] rk = say1.toCharArray(); // rakamlara ayırma

		String son;
		int w = 1; // işlenen basamak
		int sonaekle = 0; // binler on binler yüzbinler vs. için sona bin (milyon,trilyon...) eklenecek mi?
		int kac = rk.length; // kaç rakam var?
		int sonint; // işlenen basamağın rakamsal değeri
		int uclubasamak = 0; // hangi basamakta (birler onlar yüzler gibi)
		int artan = 0; // binler milyonlar milyarlar gibi artışları yapar
		String gecici;

		if (kac > 0) { // virgül öncesinde rakam var mı?

			for (int i = 0; i < kac; i++) {

				son = String.valueOf(rk[kac - 1 - i]); // son karakterden başlayarak çözümleme yapılır.
				sonint = Integer.parseInt(son); // işlenen rakam

				if (w == 1) { // birinci basamak bulunuyor

					sonuc = b1[sonint] + sonuc;

				} else if (w == 2) { // ikinci basamak

					sonuc = b2[sonint] + sonuc;

				} else if (w == 3) { // 3. basamak

					if (sonint == 1) {
						sonuc = b3[1] + sonuc;
					} else if (sonint > 1) {
						sonuc = b1[sonint] + b3[1] + sonuc;
					}
					uclubasamak++;
				}

				if (w > 3) { // 3. basamaktan sonraki işlemler

					if (uclubasamak == 1) {

						if (sonint > 0) {
							sonuc = b1[sonint] + b3[2 + artan] + sonuc;
							if (artan == 0) { // birbin yazmasını engelle
								if (kac - 1 == i) { // 11000 yazılışını düzeltme
									sonuc = sonuc.replace(b1[1] + b3[2], b3[2]);
								}
							}
							sonaekle = 1; // sona bin eklendi
						} else {
							sonaekle = 0;
						}
						uclubasamak++;

					} else if (uclubasamak == 2) {

						if (sonint > 0) {
							if (sonaekle > 0) {
								sonuc = b2[sonint] + sonuc;
								sonaekle++;
							} else {
								sonuc = b2[sonint] + b3[2 + artan] + sonuc;
								sonaekle++;
							}
						}
						uclubasamak++;

					} else if (uclubasamak == 3) {

						if (sonint > 0) {
							if (sonint == 1) {
								gecici = b3[1];
							} else {
								gecici = b1[sonint] + b3[1];
							}
							if (sonaekle == 0) {
								gecici = gecici + b3[2 + artan];
							}
							sonuc = gecici + sonuc;
						}
						uclubasamak = 1;
						artan++;
					}

				}

				w++; // işlenen basamak

			}
		} // if(kac>0)

		if ("".equals(sonuc)) { // virgül öncesi sayı yoksa para birimi yazma
			parabirimi = "";
		}

		say2 = say2.replace(".", "");
		String kurus = "";

		if (!"".equals(say2)) { // kuruş hanesi varsa

			if (kurusbasamak > 3) { // 3 basamakla sınırlı
				kurusbasamak = 3;
			}
			if (say2.length() > kurusbasamak) { // belirlenen basamak kadar rakam yazılır
				say2 = say2.substring(0, kurusbasamak);
			}
			char[] kurusrk = say2.toCharArray(); // rakamlara ayırma
			kac = kurusrk.length; // kaç rakam var?
			w = 1;

			for (int i = 0; i < kac; i++) { // kuruş hesabı

				son = String.valueOf(kurusrk[kac - 1 - i]); // son karakterden başlayarak çözümleme yapılır.
				sonint = Integer.parseInt(son); // işlenen rakam

				if (w == 1) { // birinci basamak

					if (kurusbasamak > 0) {
						kurus = b1[sonint] + kurus;
					}

				} else if (w == 2) { // ikinci basamak
					if (kurusbasamak > 1) {
						kurus = b2[sonint] + kurus;
					}

				} else if (w == 3) { // 3. basamak
					if (kurusbasamak > 2) {
						if (sonint == 1) { // 'biryüz' ü engeller
							kurus = b3[1] + kurus;
						} else if (sonint > 1) {
							kurus = b1[sonint] + b3[1] + kurus;
						}
					}
				}
				w++;
			}
			if ("".equals(kurus)) { // virgül öncesi sayı yoksa para birimi yazma
				parakurus = "";
			} else 
			{
				kurus = kurus + " "; // + "'DİR.";
			}
			kurus = kurus + parakurus + "'DİR."; // kuruş hanesine 'kuruş' kelimesi ekler
		}

		sonuc = diyez + sonuc + " " + parabirimi + " " + kurus + diyez;

		return sonuc;

	}

}
