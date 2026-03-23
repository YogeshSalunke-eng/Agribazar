//package agribazar.serviceimpl;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//@org.springframework.stereotype.Service
//public class EmailService {
//
//	@org.springframework.beans.factory.annotation.Autowired
//	private JavaMailSender mailSender;
//
//	public void sendOtpEmail(String toEmail, String otp) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(toEmail);
//		message.setSubject("Your OTP Verification Mail");
//		message.setText("Your OTP is " + otp + "\nValid for 5 minutes");
//		mailSender.send(message);
//	}
//}