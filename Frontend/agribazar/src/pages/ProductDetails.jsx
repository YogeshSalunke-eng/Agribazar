import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./ProductDetails.css";

const ProductDetails = () => {
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
     <h1>{productData.shops?.name}</h1>
     </div>
      <div className="product-details-card">
        
        <img
          src={`http://localhost:8080/uploads/${product?.imageUrl}`}
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
            Add to Cart
          </button>
          <button className=" buy-now-btn">buy now</button>
        </div>
</div>
      </div>
    </div>
  );
};

export default ProductDetails;