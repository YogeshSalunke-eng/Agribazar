//package agribazar.serviceimpl;
//
//import java.time.LocalDateTime;
//import java.util.Random;
//
//import agribazar.model.EmailOtp;
//import agribazar.repository.EmailOtpRepository;
//
//@org.springframework.stereotype.Service
//public class OtpService {
//	@org.springframework.beans.factory.annotation.Autowired
//	private EmailOtpRepository emailOtpRepository;
//	@org.springframework.beans.factory.annotation.Autowired
//	private EmailService emailService;
//
//	public void sendOtp(String email) {
//		String otp = String.valueOf(new Random().nextInt(900000) + 100000);
//		EmailOtp emailOtp = new EmailOtp();
//
//		emailOtp.setEmail(email);
//		emailOtp.setExpiryTime(java.time.LocalDateTime.now().plusMinutes(5));
//		emailOtp.setOtp(otp);
//		emailOtp.setVerified(false);
//		emailOtpRepository.save(emailOtp);
//		System.out.println("Email received: " + email);
//		emailService.sendOtpEmail(email, otp);
//	}
//
//	public boolean validateOtp(String email, String otp) {
//
//		EmailOtp emailOtp = emailOtpRepository.findTopByEmailOrderByExpiryTimeDesc(email)
//				.orElseThrow(() -> new RuntimeException("OTP not found"));
//
//		if (emailOtp.isVerified()) {
//			throw new RuntimeException("Already verified");
//		}
//
//		if (!emailOtp.getOtp().equals(otp)) {
//			throw new RuntimeException("Invalid OTP");
//		}
//
//		if (emailOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
//			throw new RuntimeException("OTP expired");
//		}
//
//		emailOtp.setVerified(true);
//		emailOtpRepository.save(emailOtp);
//
//		return true;
//	}
//
//}
