package agribazar.controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import agribazar.service.PaymentService;
@CrossOrigin(origins ="http://localhost:5173")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestParam Long amount) {
        try {
            String order = paymentService.createOrder(amount);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Order creation failed");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody Map<String, String> data) {
        try {
            boolean isValid = paymentService.verifyPayment(data);

            if (isValid) {
                return ResponseEntity.ok("Payment Successful");
            } else {
                return ResponseEntity.badRequest().body("Invalid Signature");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Verification error");
        }
    }
}
