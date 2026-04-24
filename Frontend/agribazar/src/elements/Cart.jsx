import React, { useState, useEffect } from "react";
import "./Element.css";
import { FaTrash, FaShoppingCart } from "react-icons/fa";
import API from "../services/API";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
    const [amount, setAmount] = useState(0);
  
  useEffect(() => {
    const fetchCart = async () => {
      try {
        const res = await API.get("/api/cart/getcart");
        const items = res.data.items || [];
        console.log("the originl ata is", items)
        const formattedItems = items.map((item) => ({
          id: item.id,
          name: item.productName,
          price: parseFloat(item.price.replace(/[^\d.]/g, "")) || 0, 
          quantity: item.quantity || 1,
          image: `https://agribazar-uploads.s3.ap-south-1.amazonaws.com/${item.imageUrl}`, 
          category: "Product", 
          shopname:item.shopname,
          shopProductId:item.shopProductId,
          cartId:item.cartId
        }));

        setCartItems(formattedItems);

      } catch (err) {
        console.log(err);
        setCartItems([]);
      }
    };

    fetchCart();
  }, []);

  const increaseQty = (id) => {
    setCartItems((prev) =>
      prev.map((item) =>
        item.id === id
          ? { ...item, quantity: item.quantity + 1 }
          : item
      )
    );
  };

  const decreaseQty = (id) => {
    setCartItems((prev) =>
      prev.map((item) =>
        item.id === id && item.quantity > 1
          ? { ...item, quantity: item.quantity - 1 }
          : item
      )
    );
  };

  

  const totalAmount = cartItems.reduce(
    (total, item) => total + item.price * item.quantity,
    0
  );

const removeItem = async (item) => {
  try {
    await API.delete("/api/cart/remove", {
      params: {
        cartId: item.cartId,
        shopProductId: item.shopProductId
      },
    });

    setCartItems((prev) =>
      prev.filter((i) => i.id !== item.id)
    );

    console.log("Item removed successfully");
  } catch (error) {
    console.log("Error removing item:", error);
  }
};


const handlePayment = async () => {
  if (!totalAmount || totalAmount <= 0) {
    alert("Cart is empty");
    return;
  }
const amountInPaise = Math.round(totalAmount * 100);
  try {
    const res = await API.post(`/api/payment/create-order`, null, {
  params: {
    amount: amountInPaise
  }
});

    const order = res.data;
    const options = {
  key: import.meta.env.VITE_RAZORPAY_KEY_ID,
      amount: order.amount, 
      currency: "INR",
      name: "AgriBazar",
      description: "Order Payment",
      order_id: order.id,

      handler: async function (response) {
        try {
          await API.post("/api/payment/verify", response);
          alert("Payment Successful ✅");
        } catch (err) {
          console.error(err);
          alert("Payment verification failed");
        }
      },

      prefill: {
        name: "Yogesh",
        email: "test@gmail.com",
        contact: "9999999999",
      },

      theme: {
        color: "#2e7d32",
      },
    };

    if (!window.Razorpay) {
      alert("Razorpay SDK not loaded");
      return;
    }

    const rzp = new window.Razorpay(options);
    rzp.open();

  } catch (err) {
    console.error(err);
    alert("Payment failed");
  }
};
  return (
    <div className="cart-page">
      <h2 className="cart-title">
        <FaShoppingCart /> My Cart
      </h2>

      {cartItems.length === 0 ? (
        <div className="empty-cart">
          <h3>Your cart is empty 🛒</h3>
          <p>Add products to see them here.</p>
        </div>
      ) : (
        <>
          <div className="cart-container">
            {cartItems.map((item) => (
              <div className="cart-card" key={item.id}>
                <img src={item.image} alt={item.name} />

                <div className="cart-info">
                  <div className="shopandname">
                  <h3>{item.name}</h3>
                  <p>from {item.shopname}</p>
                  </div>
                  <p className="category">{item.category}</p>
                  <p className="price">₹{item.price}</p>

                  <div className="quantity-box">
                    <button onClick={() => decreaseQty(item.id)}>-</button>
                    <span>{item.quantity}</span>
                    <button onClick={() => increaseQty(item.id)}>+</button>
                  </div>

                  <button
  className="remove-btn"
  onClick={() => removeItem(item)}
>
  <FaTrash /> Remove
</button>
                </div>
              </div>
            ))}
          </div>

          <div className="cart-summary">
            <h3>Total: ₹{totalAmount.toFixed(2)}</h3>
            <button className="buy-btn"
            onClick={handlePayment}
            >Buy Now</button>
          </div>
        </>
      )}
    </div>
  );
};

export default Cart;