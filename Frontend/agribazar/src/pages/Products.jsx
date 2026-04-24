import React, { useEffect, useState } from "react";
import "./Pages.css";
import { useTranslation } from "react-i18next";
import API from "../services/API";
import { FaShoppingCart, FaHeart, FaRegHeart } from "react-icons/fa";
import { Navigate, useNavigate } from "react-router-dom";

function Products({ selectedshop,searchTerm,category, crop, brand, pricing }) {
  const { t } = useTranslation();
  const [products, setproducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
const [wishlistItems, setWishlistItems] = useState([]);
const [cartItems, setCartItems] = useState([]);
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
  const fetchCart = async () => {
    try {
      const res = await API.get("/api/cart/getcart");
      
      const data = Array.isArray(res.data)
        ? res.data
        : res.data.data || res.data.items || [];
      setCartItems(data);

    } catch (err) {
      console.log(err);
      setCartItems([]);
    }
  };

  fetchCart();
}, []);
const isInCart = (productId) => {
  return cartItems.some(item => item.shopProductId === productId);
};

useEffect(() => {
  const fetchWishlist = async () => {
    try {
      const res = await API.get("/api/wishlist/get");

      const data = Array.isArray(res.data)
        ? res.data
        : res.data.data || res.data.items || [];

      setWishlistItems(data);
    } catch (err) {
      console.log(err);
      setWishlistItems([]);
    }
  };

  fetchWishlist();
}, []);

const isInWishlist = (productId) => {
  return wishlistItems.some(item => item.shopProductId === productId);
};

const handleAddWishlist = async (product) => {
  try {
    const response = await API.post("/api/wishlist/add", {
      shopProductId: product.id
    });

    if (response.status === 200) {
      setWishlistItems(prev => [
        ...prev,
        {
          shopProductId:product.id  
        }
      ]);
    }
  } catch (error) {
    console.log(error);
  }
};
const handleRemoveWishlist = async (product) => {
  try {
    await API.delete("/api/wishlist/remove", {
      params: {
        wishlistId: product.wishlistId,
        shopProductId: product.shopProductId
      },
    });

    setWishlistItems(prev =>
      prev.filter(item => item.shopProduct?.id !== product.id)
    );
  } catch (error) {
    console.log(error);
  }
};


  useEffect(() => {
    if(category || selectedshop){
          fetchproducts();

    }
  }, [category,selectedshop
  ]);
const formattedCategory = category?.toUpperCase();
  const fetchproducts = async () => {
  try {
    let response;
    if (selectedshop && category && category !== "all") {
  response = await API.get(
    `/api/shops/${selectedshop}/category/${formattedCategory}/products`
  );
} else if (selectedshop) {
  response = await API.get(`/api/shops/${selectedshop}/products`);
} 
    const shuffled = shuffleArray(response.data);

    setproducts(shuffled);

  } catch (error) {
    console.log("error in fetching product", error);
  }
};
const handleClickAdd=async(product)=>{
  
  try{
  const response=await API.post("/api/cart/add",{
    shopProductId:product.id,
    quantity:1
  })

if (response.status === 200) {
setCartItems(prev => [
  ...prev,
  {
    shopProductId: product.id
  }
]);
console.log("everything is okay");
}}
catch(error){
console.log(error);
}
}


  useEffect(() => {
    applyFilters();
  }, [products, crop,brand,category, pricing,searchTerm]);

  const applyFilters = () => {
    let filtered = [...products];

   if (searchTerm && searchTerm.trim() !== "") {
  filtered = filtered.filter(p =>
    p.product?.name?.toLowerCase().includes(searchTerm.toLowerCase())
  );
}
if (crop !== "all") {
  filtered = filtered.filter(p =>
    p.product?.cropname?.toLowerCase().includes(crop.toLowerCase())
  );
}

if (brand !== "all") {
  filtered = filtered.filter(p =>
    p.product?.name?.toLowerCase().includes(brand.toLowerCase())
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


return (
    <div className="shops-container">
      <div className="products-grid">
  {filteredProducts.length === 0 ? (
    <h1 className="no-products">Products Not Found</h1>
  ) : (
    filteredProducts.map((product) => (
      <div key={product.product?.id} className="product-card">
        <span
  className="wishlist-icon"
  onClick={() =>
    isInWishlist(product.id)
      ? handleRemoveWishlist(product)
      : handleAddWishlist(product)
  }
>
  {isInWishlist(product.id) ? (
    <FaHeart color="red" size={35} />
  ) : (
    <FaRegHeart color="gray" size={35} />
  )}
</span>
        <img
          src={`https://agribazar-uploads.s3.ap-south-1.amazonaws.com/${product.product?.imageUrl}`}
          alt={product.product?.name}
          className="product-image"
        />
        <h4>{product.product?.name}</h4>
        <p className="price">{product.price}</p>
        <p className="category">{product.product?.cropname}</p>

        <div className="buttons">
          <button className="view-btn"
           onClick={() =>
  navigate("/productdetails", { state: { product } })}>
            View
          </button>
          {isInCart(product.id) ? (
  <button
    className="gocart-btn"
    onClick={()=>navigate("/cart")}
  >
    Go to Cart<FaShoppingCart/>
  </button>
) : (
  <button
    className="addcart-btn"
    onClick={() => handleClickAdd(product)}
  >
    Add to Cart<FaShoppingCart/>
  </button>
)}
        </div>
      </div>
    ))
  )}
</div>
      </div>
  );
}

export default Products;