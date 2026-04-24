import { useState } from "react";
import Filters from './Filters';
import Products from './Products';
import { useLocation } from "react-router-dom";
const Pages = ({ selectedshop,category,searchTerm}) => {
  const [crop, setCrop] = useState("all");
  const [brand, setBrand] = useState("all");
  const [pricing, setPricing] = useState("all");

  console.log("selected category is",category);
  return (
  
    <div className="pages-container">
      <div className="sidebar">
        <Filters
          crop={crop}
          brand={brand}
          pricing={pricing}
          onCropChange={setCrop}
          onBrandChange={setBrand}
          onPriceChange={setPricing}
        />
      </div>

      <div className="content">
        <Products
        category={category}
          selectedshop={selectedshop}
          crop={crop}
          brand={brand}
          searchTerm={searchTerm}
          pricing={pricing}
        />
      </div>
    </div>
  );
};
export default Pages;