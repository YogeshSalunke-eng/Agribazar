import { useState } from "react";
import Filters from './Filters';
import Products from './Products';
import { useLocation } from "react-router-dom";
const Pages = ({ selectedshop }) => {
  const [crop, setCrop] = useState("all");
  const [brand, setBrand] = useState("all");
  const [pricing, setPricing] = useState("all");
  const location=useLocation();
  const selectedCategory=location.state?.selectedCategory;
  console.log("selected category is",selectedCategory);
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
        selectedCategory={selectedCategory}
          selectedshop={selectedshop}
          crop={crop}
          brand={brand}
          pricing={pricing}
        />
      </div>
    </div>
  );
};
export default Pages;