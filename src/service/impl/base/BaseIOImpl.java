package service.impl.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.tools.FtpClient;

public class BaseIOImpl {	
	
	/**
	 * 檔案格式(副檔名)判斷
	 * 將檔案bytes轉換成hex(十六進制)格式，判斷檔案編碼
	 * 應付無副檔名或副檔名錯誤投機取巧的檔案上傳
	 * @param is
	 * @return
	 */
	public String getTypeByStream(FileInputStream is) {
		byte[] b = new byte[4];
		try {
			is.read(b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type = bytesToHexString(b).toUpperCase();
		if (type.contains("FFD8FF")) {
			return "jpg";
		} else if (type.contains("89504E47")) {
			return "png";
		} else if (type.contains("47494638")) {
			return "gif";
		} else if (type.contains("49492A00")) {
			return "tif";
		} else if (type.contains("424D")) {
			return "bmp";
		} else if (type.contains("494433")) {
			return "mp3";
		}
		return type;
	}

	/**
	 * byte陣列轉換成16進制字串 
	 * @param src 
	 * @return
	 **/
	private String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	
	/**
	 * 下載ftp檔案
	 * @param ServerDir
	 * @param fileName
	 * @param baseDir
	 * @return
	 */
	public boolean getFTPFile(String host, String username, 
			String password, String serverDir, String localDir, String fileName){				
		
		try{
			FtpClient ftp=new FtpClient(host, username, password, "", "");
			ftp.connect();
			ftp.setBinaryTransfer(true);
			ftp.setLocalDir(localDir);
			ftp.setServerDir(serverDir);
			ftp.get(fileName, false);
			return true;
		}catch(Exception e){
			e.printStackTrace();			
			return false;
		}		
	}
	
	/**
	 * 上傳Ftp
	 * @param FTPHost
	 * @param username
	 * @param password
	 * @param ServerDir
	 * @param LocalDir
	 * @param fileName
	 * @return
	 */
	public boolean putFTPFile(String host, String username, String password, String server_dir, String ap_dir, String file_name){		
		try{				
			FtpClient ftp=new FtpClient(host, username, password, null, null);
			ftp=new FtpClient(host, username, password, null, null);				
			ftp.connect();				
			ftp.setLocalDir(server_dir);
			ftp.setServerDir(ap_dir);				
			ftp.setBinaryTransfer(true);
			ftp.put(file_name, true);//傳輸完成刪除檔案
			return true;
		}catch(Exception e){
			e.printStackTrace();	
			return false;
		}
	}
	
	public InputStream getFtpInputStream(String host, String username, String password, String serverDir, String fileName) throws IOException{
		FtpClient ftp=new FtpClient(host, username, password, "", "");
		ftp.connect();
		ftp.setBinaryTransfer(true);
		//ftp.setLocalDir("");
		ftp.setServerDir(serverDir);		
		return ftp.getInputStream(fileName);
	}
	
	/**
	 * 儲存客戶端到伺服器
	 * @param src
	 * @param dst
	 */
	public void copyFile(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), 16384);
				out = new BufferedOutputStream(new FileOutputStream(dst), 16384);
				byte[] buffer = new byte[16384];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取得伺服器context
	 * @param session
	 * @return
	 */
	public String getCnitextPath(HttpSession session){
		return session.getServletContext().getRealPath("/");
	}
	
	/**
	 * 建立伺服器資料夾
	 * @param path
	 */
	public void createLocalFolder(String path){		
		//path = getCnitextPath(session)+path;
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 取副檔名
	 * @param fileName
	 * @return
	 */
	public String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos).toLowerCase();
	}
	
	/**
	 * 取檔名
	 * @param fileName
	 * @return
	 */
	public String getFileName(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(0,pos);
	}
	
	/**
	 * 寄送單一郵件
	 * @param host
	 * @param receiver
	 * @param sender
	 * @param subject
	 * @param mess
	 * @param username
	 * @param password
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void sendMail(String host, String receiver, String sender, String subject, String mess, String username, String password) throws AddressException, MessagingException, UnsupportedEncodingException{		
		

		boolean sessionDebug = false;

		//Get system properties
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host",host); //指定SMTP server
		prop.put("mail.transport.protocol","smtp"); //設定傳送協定
		prop.put("mail.smtp.auth","true"); //設定是否須smtp驗證

		// 產生新的Session
		javax.mail.Session mailsess = Session.getDefaultInstance(prop);
		mailsess.setDebug(sessionDebug); //是否在控制台顯示debug訊息
		
		
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(mess, "text/html; charset=UTF-8");
		Multipart email = new MimeMultipart();
		email.addBodyPart(textPart);
		//email.addBodyPart(picturePart);
		Message msg = new MimeMessage(mailsess);
		msg.setFrom(new InternetAddress(sender)); // 設定傳送郵件的發信人
		InternetAddress[] address= {new InternetAddress(receiver)}; // 設定傳送郵件的收件者
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setHeader("Subject", MimeUtility.encodeText(subject, "big5", null)); //設定主題
		msg.setContent(email);		

		//發送郵件
		Transport transport = mailsess.getTransport("smtp"); //只支持IMAP、 SMTP和 POP3
		transport.connect(host, username, password); //以smtp的方式登入mail server
		transport.sendMessage(msg,msg.getAllRecipients());
		transport.close();
			  
			  
		//return true;	
	}
	
}
