//package agribazar.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import agribazar.serviceimpl.OtpService;
//
//@org.springframework.web.bind.annotation.RequestMapping("/otp")
//@org.springframework.web.bind.annotation.RestController
//public class OtpController {
//	@org.springframework.beans.factory.annotation.Autowired
//	private OtpService otpService;
//
//	@PostMapping("/send")
//	public ResponseEntity<String> sendOtp(@RequestParam String email) {
//		otpService.sendOtp(email);
//		return ResponseEntity.ok("otp sent successfuly");
//	}
//
//	@PostMapping("/validate")
//	public ResponseEntity<String> validateotp(@RequestParam String email, @RequestParam String otp) {
//		boolean isvalid = otpService.validateOtp(email, otp);
//		return isvalid ? ResponseEntity.ok("otp verified") : ResponseEntity.badRequest().body("invalid otp");
//	}
//}
