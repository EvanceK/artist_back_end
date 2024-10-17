package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.artist.entity.OrderDetails;
import com.artist.entity.Orders;
import com.artist.repository.OrderDetailsRepository;
import com.artist.repository.OrdersRepository;
import com.artist.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Autowired
	private OrdersRepository or;

	@Autowired
	private OrderDetailsRepository odr;

	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	// 發送郵件的邏輯
	public void sendPasswordResetEmail(String email, String resetLink) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Artist重置密碼請求");
		message.setText("我們收到您更改密碼的請求。如果您希望重置密碼，請點擊下方按鈕。如果這不是您的操作，請忽略此郵件。\n" + resetLink + "\n請注意，此鏈接將在 15分鐘內過期。");

		mailSender.send(message);
	}

	// 發送得標信
	public void sendAuctionWinningEmail(String painting) throws MessagingException {
		Optional<OrderDetails> orderNumberWithPaintings = odr.findOrderNumberWithPaintings(painting);
		if (orderNumberWithPaintings.isEmpty()) {
		    System.out.println("信件寄發異常：訂單不存在");
		    return;
		}
		OrderDetails orderDetails = orderNumberWithPaintings.get();
		String orderNumber = orderDetails.getOrderNumber();
		Optional<Orders> ordersOptional = or.findByOrderNumber(orderNumber);
		

		if (ordersOptional.isEmpty()) {
		    System.out.println("信件寄發異常：訂單不存在");
		    return; 
		}

		Orders order = ordersOptional.get();
		String email = order.getCustomer().getEmail();	
		LocalDateTime endTime = order.getOrderDate();
		String paintingName = orderDetails.getPainting().getPaintingName();
		String paintingId = orderDetails.getPainting().getPaintingId();
		Double winningPrice = orderDetails.getPrice();
		String paymentLink ="http://localhost:5173/home/cusdashboard/winningRecords";

		// 創建 MIME 消息
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		// 設置郵件標題
		helper.setSubject("[Your App Name] 恭喜您！您已贏得拍賣品");

		// 設置收件人
		helper.setTo(email);

		// 設置發件人
		helper.setFrom("your-email@gmail.com");

		// 純文本內容（非 HTML 支持的郵件客戶端會顯示此內容）
		String plainText = "親愛的用戶，恭喜您贏得了拍賣品 '" + paintingName + "'。" + "請在拍賣結束後 24 小時內確認付款資訊。" + "付款資訊連結："
				+ paymentLink;

		// HTML 格式的郵件內容
		String htmlText = "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; background-color: #f9f9f9;\">"
				+ "<h2 style=\"text-align: center; color: #4CAF50;\">恭喜！您已成功贏得拍賣品</h2>" + "<p>親愛的用戶：</p>"
				+ "<p>您已成功贏得拍品 <strong>" + paintingName + "</strong>。以下是相關信息：</p>" + "<ul>"
				+ "<li><strong>拍賣編號：</strong>" + paintingId + "</li>" + "<li><strong>得標價格：</strong>" + winningPrice
				+ " 元</li>" + "<li><strong>拍賣結束時間：</strong>" + endTime + "</li>" + "</ul>"
				+ "<p>請在 24 小時內確認付款資訊，以確保交易有效。您可以點擊下方按鈕來確認：</p>" + "<p style=\"text-align: center;\">" + "<a href=\""
				+ paymentLink
				+ "\" style=\"padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px;\">立即付款</a>"
				+ "</p>" + "<p>如果按鈕無法點擊，請複製以下連結並於瀏覽器中開啟：</p>" + "<p style=\"word-break: break-all;\"><a href=\""
				+ paymentLink + "\">" + paymentLink + "</a></p>" + "<p>謝謝您參與我們的拍賣活動！</p>"
				+ "<p>[Your App Name] 團隊</p>" + "<hr>"
				+ "<p style=\"font-size: 0.9em; color: #555;\">此郵件為系統自動發送，請勿回覆。</p>" + "</div>";

		// 使用帶兩個參數的 setText 方法設置純文本和 HTML 內容
		helper.setText(plainText, htmlText);

		// 發送郵件
		mailSender.send(mimeMessage);
	
	}

}
