package agribazar.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agribazar.dtos.EmailRequestDTO;
import agribazar.model.OtpStorage;
import agribazar.model.OtpUtil;
import agribazar.repository.UserRepository;
import agribazar.serviceimpl.EmailServiceImpl;

@RestController
@RequestMapping("/email")
public class EmailController {
@Autowired
private UserRepository userRepository;
@Autowired
private OtpStorage otpStorage;
@Autowired
private EmailServiceImpl emailServiceImpl;
@PostMapping("/send-otp")
	public ResponseEntity<String> sendOtp(@RequestBody EmailRequestDTO emailRequest){
	String email=emailRequest.getEmail();
	if(userRepository.existsByEmail(email)){
return ResponseEntity.badRequest().body("user allready exist, please login");
	}
	String otp = OtpUtil.generateOtp();
	otpStorage.storeOtp(email, otp);
	emailServiceImpl.sendOtp(email, otp);
	return ResponseEntity.ok("otp send successfully to your register email");
	}
	
@PostMapping("/verify-otp")
public ResponseEntity<String> verifyotp(@RequestBody EmailRequestDTO emailRequest){
	String email=emailRequest.getEmail().trim();
	String otp = emailRequest.getOtp();
	String storedOtp=otpStorage.getOtp(email);
	System.out.println("Email from request: " + email);
	System.out.println("OTP from request: " + otp);
	System.out.println("Stored OTP: " + otpStorage.getOtp(email));
	if(storedOtp!=null && storedOtp.equals(otp)) {
	otpStorage.removeOtp(email);
	return ResponseEntity.ok("otp verified successfully");
}
	return ResponseEntity.badRequest().body("wrong otp, please enter correct otp");

}






}
