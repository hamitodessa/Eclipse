package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;

import org.apache.commons.collections.functors.IfClosure;

import com.sms.api.Messenger;
import com.sms.api.model.RequestGetSmsReportDetailList;
import com.sms.api.model.RequestGetSmsReportList;
import com.sms.api.model.ResponseGetSenders;
import com.sms.api.model.ResponseGetSmsReportDetailList;
import com.sms.api.model.ResponseGetSmsReportList;
import com.sms.api.model.Sender;
import com.sms.api.model.SmsReportItem;
import com.sms.api.model.SmsReportList;

import GUNLUK.Aylik_Gorunum;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SMS_INET extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SMS_INET frame = new SMS_INET();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SMS_INET() {
		setTitle("SMS INET");
		setBounds(0, 0,900, 600);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);


		JSplitPane splitPane = new JSplitPane();
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 100));
		panel.setMaximumSize(new Dimension(0, 100));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hisset();
			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		panel.add(btnNewButton);
		
//*************************************************************************************		
	
		
		
	}
	public void hisset()
	{
		Messenger messenger = new Messenger("app.teknomart.com.tr", "okumuslar.orman", "MLVvFaj9", "9588");
		RequestGetSmsReportList request = new RequestGetSmsReportList();
		request.setStartDate("2022-01-01 00:00");
		request.setFinishDate("2025-01-05 12:35");
		request.setPageIndex(0);
		request.setPageSize(100);
		
		//Tarih yerine paket id ile sorgulanmak istenirse
		//request.getIds().add(30584624);
		//request.getIds().add(23312871);
		
		//Eðer kiþisel id nize göre sorgulamak yapmak istenirse
		//customIDs gönderiminde ids göndermeye gerek yoktur
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
}
