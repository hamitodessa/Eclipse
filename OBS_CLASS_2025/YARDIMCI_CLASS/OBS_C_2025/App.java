package OBS_C_2025;

import com.sms.api.Messenger;
import com.sms.api.model.RequestGetSmsReportDetailList;
import com.sms.api.model.RequestGetSmsReportList;
import com.sms.api.model.RequestSendDynamicSms;
import com.sms.api.model.RequestSendMultiSms;
import com.sms.api.model.RequestSendSingleSms;
import com.sms.api.model.ResponseCancelSms;
import com.sms.api.model.ResponseGetCredit;
import com.sms.api.model.ResponseGetSenders;
import com.sms.api.model.ResponseGetSmsReportDetailList;
import com.sms.api.model.ResponseGetSmsReportList;
import com.sms.api.model.ResponseSendDynamicSms;
import com.sms.api.model.ResponseSendMultiSms;
import com.sms.api.model.ResponseSendSingleSms;
import com.sms.api.model.Sender;
import com.sms.api.model.SmsItem;
import com.sms.api.model.SmsReportItem;
import com.sms.api.model.SmsReportList;

public class App {
	public static void main(String[] args) {
		Messenger messenger = new Messenger("hostAdress", "username", "password");
		
		//Default port 9587 istekler http olarak gitmektedir
		//https istek atmak i�in Messenger("hostAdress", "username", "password", "9588")
		//yap�lmal�d�r
	
		sendSingleSms(messenger);
		
		sendMultiSms(messenger);
		
		sendDynamicSms(messenger);
		
		getCredit(messenger);

		getSenders(messenger);

		cancelSms(messenger);
		
		getSmsReportList(messenger);
		
		getSmsReportDetailList(messenger);
	
	}
	
	private static void sendSingleSms(Messenger messenger) {
		System.out.println("****SendSingleSms****");

		RequestSendSingleSms request = new RequestSendSingleSms();
		request.setContent("Test Mesaj� 1 - 1 g�nderimi");
		request.setNumber("905001112233");
		request.setSender("G�nderen Ba�l���");

		request.setTitle("Api G�nderimi");
		
		//Kendi sistemindeki id �ler ile e�le�tirme yapabilmek i�in kullan�lan parametre
		//request.customID = "mesajId100";
		 
		//Ticari g�nderimlerde �YS sorgusu i�in Commercial alan� true g�nderilmelidir
		//request.setCommercial(true);
		
		//Mesajlar�n AHS sorgusuna sokulmas� istenmiyorsa true de�eri girilmelidir.
		//request.setSkipAhsQuery(true);
		
		//�leri tarihli g�nderimlerde kullan�l�r
		//request.setSendingDate("2022-01-05 12:35");

		//Rapor push olarak al�nmak isteniyorsa ilgili url girilir
		//request.setPushSettings(new PushSettings("https://webhook.site/8d7ed0f7"));

		ResponseSendSingleSms response = messenger.sendSingleSms(request);
		
		if(response.getErr() == null) {
			System.out.println("PaketID : " + response.getPkgID());
		}else {
			System.out.println("Status : " + response.getErr().getStatus());
			System.out.println("Message : " + response.getErr().getMessage());
			System.out.println("Code : " + response.getErr().getCode());
		}
	}
	
	private static void sendMultiSms(Messenger messenger) {
		System.out.println("****SendMultiSms****");
		
		RequestSendMultiSms request = new RequestSendMultiSms();
		request.setContent("Test Mesaj� 1 - N g�nderimi");
		request.setSender("G�nderen Ba�l���");
		request.setTitle("Api G�nderimi");
		
		request.getNumbers().add("905001112233");
		request.getNumbers().add("905002223344");
		
		//Kendi sistemindeki id �ler ile e�le�tirme yapabilmek i�in kullan�lan parametre
		//request.customID = "mesajId100";
		 
		//Ticari g�nderimlerde �YS sorgusu i�in Commercial alan� true g�nderilmelidir
		//request.setCommercial(true);
		
		//Mesajlar�n AHS sorgusuna sokulmas� istenmiyorsa true de�eri girilmelidir.
		//request.setSkipAhsQuery(true);
		
		//�leri tarihli g�nderimlerde kullan�l�r
		//request.setSendingDate("2022-01-05 12:35");

		//Rapor push olarak al�nmak isteniyorsa ilgili url girilir
		//request.setPushSettings(new PushSettings("https://webhook.site/8d7ed0f7"));

		ResponseSendMultiSms response = messenger.sendMultiSms(request);
		
		if(response.getErr() == null) {
			System.out.println("PaketID : " + response.getPkgID());
		}else {
			System.out.println("Status : " + response.getErr().getStatus());
			System.out.println("Message : " + response.getErr().getMessage());
			System.out.println("Code : " + response.getErr().getCode());
		}
	}
	
	private static void sendDynamicSms(Messenger messenger) {
		System.out.println("****SendDynamicSms****");
		
		RequestSendDynamicSms request = new RequestSendDynamicSms();
		request.setSender("G�nderen Ba�l���");
		request.setTitle("Api G�nderimi");
		
		request.getNumbers().add(new SmsItem("905001234567", "Say�n Ali Efe bu bir deneme mesaj�"));
		request.getNumbers().add(new SmsItem("905009992211", "Say�n Ay�e Ak bu bir deneme mesaj�"));
		 
		//Kendi sistemindeki id �ler ile e�le�tirme yapabilmek i�in kullan�lan parametre
		//request.customID = "mesajId100";
		
		//Ticari g�nderimlerde �YS sorgusu i�in Commercial alan� true g�nderilmelidir
		//request.setCommercial(true);
		
		//Mesajlar�n AHS sorgusuna sokulmas� istenmiyorsa true de�eri girilmelidir.
		//request.setSkipAhsQuery(true);
		
		//�leri tarihli g�nderimlerde kullan�l�r
		//request.setSendingDate("2022-01-05 12:35");

		//Rapor push olarak al�nmak isteniyorsa ilgili url girilir
		//request.setPushSettings(new PushSettings("https://webhook.site/8d7ed0f7"));

		ResponseSendDynamicSms response = messenger.sendDynamicSms(request);
		
		if(response.getErr() == null) {
			System.out.println("PaketID : " + response.getPkgID());
		}else {
			System.out.println("Status : " + response.getErr().getStatus());
			System.out.println("Message : " + response.getErr().getMessage());
			System.out.println("Code : " + response.getErr().getCode());
		}
	}
	
	private static void getCredit(Messenger messenger) {
		System.out.println("****GetCredit****");
		ResponseGetCredit response = messenger.getCredit();
		
		if(response.getErr() == null) {
			System.out.println("Kredi : " + response.getCredit());
		}else {
			System.out.println("Status : " + response.getErr().getStatus());
			System.out.println("Message : " + response.getErr().getMessage());
			System.out.println("Code : " + response.getErr().getCode());
		}
	}
	
	private static void getSenders(Messenger messenger) {
		System.out.println("****GetSenders****");
		ResponseGetSenders response = messenger.getSenders();
		
		if(response.getErr() == null) {
			System.out.println("TotalRecord : " + response.getTotalRecord());
			for (Sender item : response.getList()) {
				System.out.println("****Item****");
				System.out.println("Statu : " + item.getStatus());
				System.out.println("Uuid : " + item.getUuid());
				System.out.println("Title : " + item.getTitle());
			}
		}else {
			System.out.println("Status : " + response.getErr().getStatus());
			System.out.println("Message : " + response.getErr().getMessage());
			System.out.println("Code : " + response.getErr().getCode());
		}
	}
	
	private static void cancelSms(Messenger messenger) {
		System.out.println("****CancelSms****");
		int packageID = 30584625;
		
		ResponseCancelSms response = messenger.cancelSms(packageID);
		
		//CustomID ye g�re paket iptal edilmek istenirse
		//ResponseCancelSms response = messenger.cancelSms("mesajId100");
		
		if(response.getErr() == null) {
			System.out.println("Status : " + response.getStatus());
		}else {
			System.out.println("Status : " + response.getErr().getStatus());
			System.out.println("Message : " + response.getErr().getMessage());
			System.out.println("Code : " + response.getErr().getCode());
		}
	}
	
	private static void getSmsReportList(Messenger messenger) {
		System.out.println("****GetSmsReportList****");
		
		RequestGetSmsReportList request = new RequestGetSmsReportList();
		request.setStartDate("2022-01-01 00:00");
		request.setFinishDate("2022-01-05 12:35");
		request.setPageIndex(0);
		request.setPageSize(100);
		
		//Tarih yerine paket id ile sorgulanmak istenirse
		//request.getIds().add(30584624);
		//request.getIds().add(30584625);
		
		//E�er ki�isel id nize g�re sorgulamak yapmak istenirse
		//customIDs g�nderiminde ids g�ndermeye gerek yoktur
		//request.getCustomIds().add("mesajId100");
		//request.getCustomIds().add("mesajId101");
		
		ResponseGetSmsReportList response = messenger.getSmsReportList(request);
		
		if(response.getErr() == null) {
			System.out.println("TotalRecord : " + response.getTotalRecord());
			for (SmsReportList item : response.getList()) {
				System.out.println("****Item****");
				System.out.println("Id: " + item.getId());
				System.out.println("Type: " + item.getType());
				System.out.println("Uuid: " + item.getUuid());
				System.out.println("State: " + item.getState());
				System.out.println("Title: " + item.getTitle());
				System.out.println("CustomId: " + item.getCustomID());
				System.out.println("Content: " + item.getContent());
				System.out.println("Sender: " + item.getSenders());
				System.out.println("Encoding: " + item.getEncoding());
				System.out.println("Validity: " + item.getValidity());
				System.out.println("SendingDate" + item.getSendingDate());
				System.out.println("ProcessingDate: " + item.getProcessingDate());
				
				System.out.println("Statictics Credit: " + item.getStatistics().getCredit());
				System.out.println("Statictics Total: " + item.getStatistics().getTotal());
				System.out.println("Statictics Delivered: " + item.getStatistics().getDelivered());
				System.out.println("Statictics Undelivered: " + item.getStatistics().getUndelivered());
				System.out.println("Statictics RCount: " + item.getStatistics().getrCount());
			}
		}else {
			System.out.println("Status : " + response.getErr().getStatus());
			System.out.println("Message : " + response.getErr().getMessage());
			System.out.println("Code : " + response.getErr().getCode());
		}
	}
	
	private static void getSmsReportDetailList(Messenger messenger) {
		System.out.println("****GetSmsReportDetailList****");
		int packageID = 30584624;
		
		RequestGetSmsReportDetailList request = new RequestGetSmsReportDetailList();
		request.setId(packageID);
		request.setPageIndex(0);
		request.setPageSize(100);
		
		//E�er ki�isel id nize g�re sorgulamak yapmak istenirse
		//customID g�nderiminde pkgID g�ndermeye gerek yoktur
		//request.setCustomID("mesajId102");
		
		ResponseGetSmsReportDetailList response = messenger.getSmsReportDetailList(request);
		
		if(response.getErr() == null) {
			System.out.println("TotalRecord : " + response.getTotalRecord());
			for (SmsReportItem item : response.getList()) {
				System.out.println("Id: " + item.getId());
				System.out.println("State: " + item.getState());
				System.out.println("Credit: " + item.getCredit());
				System.out.println("Sender: " + item.getSender());
				System.out.println("Target: " + item.getTarget());
				System.out.println("SetState: " + item.getSetState());
				System.out.println("SendingDate: " + item.getSendingDate());
				System.out.println("DeliveryDate: " + item.getDeliveryDate());
				System.out.println("ProcecingDate: " + item.getProcessingDate());
				System.out.println("SendingDate" + item.getSendingDate());
			}
		}else {
			System.out.println("Status : " + response.getErr().getStatus());
			System.out.println("Message : " + response.getErr().getMessage());
			System.out.println("Code : " + response.getErr().getCode());
		}
	}
}
