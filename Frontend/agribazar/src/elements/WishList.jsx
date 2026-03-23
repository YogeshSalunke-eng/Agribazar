import React from "react";
import "./Element.css";
import seedsImg from "../assets/seeds.png";
import { FaHeart, FaShoppingCart, FaTrash } from "react-icons/fa";

const WishList = () => {
  const products = [
    {
      id: 1,
      name: "Ujjwal Fungicide",
      category: "Fungicides",
      price: 499,
      description:
        "High quality fungicide for crop protection and better yield.",
      image: seedsImg,
    },
  ];

  return (
    <div className="wishlist-page">
      <h2 className="wishlist-title">My Wishlist ❤️</h2>

      {products.length === 0 ? (
        <div className="empty-wishlist">
          <h3>Your wishlist is empty</h3>
          <p>Add products you love and they’ll appear here.</p>
        </div>
      ) : (
        <div className="wishlist-container">
          {products.map((item) => (
            <div className="wishlist-card" key={item.id}>
              <img src={item.image} alt={item.name} />

              <div className="wishlist-info">
                <h3>{item.name}</h3>
                <p className="category">{item.category}</p>
                <p className="description">{item.description}</p>
                <p className="price">₹{item.price}</p>

                <div className="wishlist-buttons">
                  <button className="add-cart-btn">
                    <FaShoppingCart /> Add to Cart
                  </button>

                  <button className="remove-btn">
                    <FaTrash /> Remove
                  </button>
                </div>
              </div>

              <div className="wishlist-heart">
                <FaHeart />
              </div>
            </div>
          ))}
        </div>
      )}
     
    </div>
  );
};

export default WishList;