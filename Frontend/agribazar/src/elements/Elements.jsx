import React, { useEffect, useState } from "react";
import API from "../services/API";
import "./Elements.css";
import { useNavigate } from "react-router-dom";
const Elements = ({setSelectedCategory,shopId}) => {
const navigate=useNavigate();
  const categories = [
    "fungicides",
    "herbicides",
    "insecticides",
    "nutrients",
    "seeds",
    "fertilizers",
  ];

  const [productsByCategory, setProductsByCategory] = useState({});
const handleOnClick=(product)=>{
  if(!shopId){
      setSelectedCategory(product.category);
    alert("enter your shop first");
    return;
  }
      setSelectedCategory(product.category);
  navigate("/productdashboard", {
  state: {
    category: product.category,
    shopId: shopId
  }
});
}
  useEffect(() => {
    fetchAllCategories();
  }, []);

  const fetchAllCategories = async () => {
    try {
      let data = {};

      for (let cat of categories) {
        const response = await API.get(`/api/products/category/${cat.toUpperCase()}?page=0&size=6`);
        data[cat] = response.data; 
      }

      setProductsByCategory(data);
    } catch (error) {
      console.log("Error fetching categories", error);
    }
  };

  return (
    <div>
      {categories.map((cat) => (
        <div className="categories-wrapper" key={cat}>

          <div className="category-info">
            <div className="category-right">{cat}</div>
            <button className="category-left">See more ➤</button>
          </div>

          <div className="userproduct-grid">
            {productsByCategory[cat]?.length > 0 ? (
              productsByCategory[cat].map((product) => (
                <div key={product?.id} className="userproduct-card"onClick={() => handleOnClick(product)}>
                  <img
                    src={`https://agribazar-uploads.s3.ap-south-1.amazonaws.com/${product?.imageUrl}`}
                    alt={product?.name}
                    className="userproduct-image"
                  />
                  <h3>{product?.name}</h3>
                </div>
              ))
            ) : (
              <p>No products found</p>
            )}
          </div>

        </div>
      ))}
    </div>
  );
};

export default Elements;