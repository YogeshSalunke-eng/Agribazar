import React, { useState, useEffect } from "react";
import "./Element.css";
import { FaTrash, FaShoppingCart } from "react-icons/fa";
import API from "../services/API";
import { FaHeart } from "react-icons/fa"; 

const WishList = () => {
  const [wishListItems, setWishListItems] = useState([]);

  useEffect(() => {
    const fetchWishList = async () => {
      try {
        const res = await API.get("/api/wishlist/get");

        const items = res.data.items || [];
        const formattedItems = items.map((item) => ({
          id: item.id,
          name: item.productName,
          price: item.price, 
          image: `https://agribazar-uploads.s3.ap-south-1.amazonaws.com/${item.imageUrl}`, 
          category: "Product", 
          shopname:item.shopName,
          wishlistId:item.wishListId,
          shopProductId:item.shopProductId
        }));

        setWishListItems(formattedItems);

      } catch (err) {
        console.log(err);
        setWishListItems([]);
      }
    };

    fetchWishList();
  }, []);

  
const removeItem = async (item) => {
  try {
    await API.delete("/api/wishlist/remove", {
      params: {
        wishlistId: item.wishlistId,
        shopProductId: item.shopProductId
      },
    });

    setWishListItems((prev) =>
      prev.filter((i) => i.id !== item.id)
    );

    console.log("Item removed successfully");
  } catch (error) {
    console.log("Error removing item:", error);
  }
};

  return (
    <div className="cart-page">
      <h2 className="cart-title">
         My wishlist <FaHeart/>
      </h2>

      {wishListItems.length === 0 ? (
        <div className="empty-cart">
          <h3>Your wishlist is empty 🛒</h3>
          <p>Add products to see them here.</p>
        </div>
      ) : (
        <>
          <div className="cart-container">
            {wishListItems.map((item) => (
              <div className="cart-card" key={item.id}>
                <img src={item.image} alt={item.name} />

                <div className="cart-info">
                  <div className="shopandname">
                  <h3>{item.name}</h3>
                  <p>from {item.shopname}</p>
                  </div>
                  <p className="category">{item.category}</p>
                  <p className="price">₹{item.price}</p>

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
        </>
      )}
    </div>
  );
};

export default WishList;