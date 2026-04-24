package agribazar.service;
import java.util.Map; 
public interface PaymentService {


    boolean verifyPayment(Map<String, String> data) throws Exception;

	String createOrder(Long amount) throws Exception;
}