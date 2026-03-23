import React, { useEffect, useState } from "react";
import "./Pages.css";
import { useTranslation } from "react-i18next";
import API from "../services/API";
import { FaShoppingCart } from "react-icons/fa";
import { Navigate, useNavigate } from "react-router-dom";

function Products({ selectedshop,selectedCategory, crop, brand, pricing }) {
  const { t } = useTranslation();

  const [products, setproducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
const navigate=useNavigate();
  const shuffleArray = (array) => {
    const arr = [...array];
    for (let i = arr.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [arr[i], arr[j]] = [arr[j], arr[i]];
    }
    return arr;
  };

  useEffect(() => {
    if(selectedshop){
          fetchproducts();

    }
  }, [selectedshop]);

  const fetchproducts = async () => {
    try {
      const response = await API.get(`/shops/${selectedshop}/products`);
      const shuffled = shuffleArray(response.data);
      setproducts(shuffled);
    } catch (error) {
      console.log("error in fetching product", error);
    }
  };

  useEffect(() => {
    applyFilters();
  }, [products, crop,brand,selectedCategory, pricing]);

  const applyFilters = () => {
    let filtered = [...products];
    if (selectedCategory && selectedCategory !== "all") {
    filtered = filtered.filter(p =>
      p.category?.toLowerCase() === selectedCategory.toLowerCase()
    );
  }

    if (crop !== "all") {
      filtered = filtered.filter(p =>
        p.cropname.toLowerCase().includes(crop.toLowerCase())
      );
    }

    if (brand !== "all") {
      filtered = filtered.filter(p =>
        p.name.toLowerCase().includes(brand.toLowerCase())
      );
    }

    if (pricing !== "all") {
      filtered = filtered.filter(p => {
        const price = p.price;

        switch (pricing) {
          case "0-200":
            return price <= 200;
          case "200-500":
            return price > 200 && price <= 500;
          case "500-1000":
            return price > 500 && price <= 1000;
          case "1000-2000":
            return price > 1000 && price <= 2000;
          case "2000+":
            return price > 2000;
          default:
            return true;
        }
      });
    }

    setFilteredProducts(filtered);
  };
console.log("Selected Category:", selectedCategory);
console.log("Product Category:", products[0]?.category);


return (
    <div className="shops-container">
      <div className="products-grid">
      {filteredProducts.map((product) => (
        <div key={product.productId} className="product-card">
          <img
            src={`http://localhost:8080/uploads/${product.imageUrl}`}
            alt={product.name}
            className="product-image"
          />

          <h3>{product.name}</h3>
          <p className="price">₹{product.price}</p>
          <p className="category">{product.cropname}</p>

          <div className="buttons">
            <button className="view-btn" onClick={()=>navigate("/productdetails")}>View</button>
            <button className="addcart-btn" onClick={()=>navigate("/addcart")}>
              add to cart <FaShoppingCart />
            </button>
          </div>
        </div>
      ))}
      </div>
    </div>
  );
}

export default Products;