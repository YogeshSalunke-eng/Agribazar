import React, { useState } from "react";
import axios from "axios";

function Payment() {
  const [amount, setAmount] = useState(0);

  const handleSetAmount = (value) => {
    setAmount(value);
  };

  const handlePayment = async () => {
    if (!amount) {
      alert("Please select amount");
      return;
    }

    try {
      const res = await axios.post(
        `http://localhost:8080/api/payment/create-order?amount=${amount}`
      );

      const order = res.data;

      const options = {
        key: "rzp_test_SeZKyJX8LY2luE",
        amount: order.amount,
        currency: "INR",
        name: "AgriBazar",
        description: "Test Payment",
        order_id: order.id,

        handler: async function (response) {
          await axios.post("http://localhost:8080/api/payment/verify", response);
          alert("Payment Successful");
        },

        prefill: {
          name: "Yogesh",
          email: "test@gmail.com",
          contact: "9999999999",
        },

        theme: {
          color: "#3399cc",
        },
      };

      const rzp = new window.Razorpay(options);
      rzp.open();

    } catch (err) {
      console.error(err);
      alert("Payment failed");
    }
  };

  return (
    <div>
      <h2>Select Amount</h2>

      <button onClick={() => handleSetAmount(100)}>₹100</button>
      <button onClick={() => handleSetAmount(500)}>₹500</button>
      <button onClick={() => handleSetAmount(1000)}>₹1000</button>

      <h3>Selected Amount: ₹{amount}</h3>

      <button onClick={handlePayment}>Pay Now</button>
    </div>
  );
}

export default Payment;