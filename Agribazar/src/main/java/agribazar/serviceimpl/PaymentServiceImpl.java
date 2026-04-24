package agribazar.serviceimpl;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.springframework.beans.factory.annotation.Value;
import agribazar.model.RazorpayUtil;
import agribazar.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService {
	
	
	    @Value("${razorpay.key.id}")
	    private String KEY_ID;

	    @Value("${razorpay.key.secret}")
	    private String KEY_SECRET;

	
    @Override
    public String createOrder(Long amount) throws Exception {
        RazorpayClient client = new RazorpayClient(KEY_ID, KEY_SECRET);
        JSONObject options = new JSONObject();
        options.put("amount", amount * 100); 
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());

        Order order = client.orders.create(options);

        return order.toString();
    }

    @Override
    public boolean verifyPayment(Map<String, String> data) throws Exception {
        String orderId = data.get("razorpay_order_id");
        String paymentId = data.get("razorpay_payment_id");
        String signature = data.get("razorpay_signature");

        String payload = orderId + "|" + paymentId;

        String generatedSignature = RazorpayUtil.generateSignature(payload, KEY_SECRET);
        System.out.println("the paayment id is"+ paymentId);
        System.out.println("the order id is"+ orderId);
        System.out.println("the signature is"+ signature);
        System.out.println("the generatedSignature is"+ generatedSignature);

        return generatedSignature.equals(signature);
    }
}