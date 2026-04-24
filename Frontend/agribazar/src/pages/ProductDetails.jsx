import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./Productdetails.css";
import { FaShoppingCart } from "react-icons/fa";
import { FaStore } from "react-icons/fa";
const Productdetails = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const productData = location.state?.product;

  if (!productData) {
    return <h2>No product data found</h2>;
  }

  const product = productData.product;
console.log(productData);
console.log(product);

  return (
    <div className="product-details-container">

      <button className="back-btn" onClick={() => navigate(-1)}>
        ← Back
      </button>
      <div className="shopname">
     <h1> Product of  {productData.shops?.name}  <FaStore/></h1>
     </div>
      <div className="product-details-card">
        
        <img
          src={`https://agribazar-uploads.s3.ap-south-1.amazonaws.com/${product?.imageUrl}`}
          alt={product?.name}
          className="product-details-image"
        />

        <div className="product-info">
          <h1>{product?.name}</h1>

          <p className="price">₹{productData?.price}</p>

          <p className="category"><strong>Category:</strong> {product?.category}</p>
          <p className="crop"><strong>Crop:</strong> {product?.cropname}</p>

          <p className="description">
            {product?.description || "No description available"}
          </p>
        <div className="btn-section">
          <button className="add-cart-btn">
            Add to Cart<FaShoppingCart/>
          </button>
          <button className=" buy-now-btn">buy now</button>
        </div>
</div>
      </div>
    </div>
  );
};

export default Productdetails;