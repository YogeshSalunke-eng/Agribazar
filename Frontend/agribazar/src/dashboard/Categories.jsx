import React from "react";
import "./Dashboard.css";
import { useState } from "react";
import nutrientsImg from "../assets/nutrients.png"
import insceticidesImg from "../assets/insecticides.png"
import fungicidesImg from "../assets/fungicides.png"
import seedsImg  from "../assets/seeds.png"
import herbicidesImg from "../assets/herbicides.png"
import fertilizersImg from "../assets/fertilizers.png" 
import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router-dom";
const Categories = () => {
  const {t}=useTranslation();
  const categoryData = [
    { name: "nutrients", img: nutrientsImg },
    { name: "fungicides", img: fungicidesImg },
    { name: "insecticides", img: insceticidesImg },
    { name: "seeds", img:seedsImg},
    { name: "herbicides", img:herbicidesImg  },
    { name: "fertilizers", img: fertilizersImg},
  ];
  const [activeCategory, setActiveCategory] = useState(null);
const navigate= useNavigate();
const handlecategory = (categoryName) => {
  setActiveCategory(categoryName);
  navigate("/pages", { state: { selectedCategory: categoryName } });
};

  return (
    <div className="categories-wrapper">
      <div className="categories-container">
        {categoryData.map((item, index) => (
<div
  className={`category-card ${
    activeCategory === item.name ? "active-category" : ""
  }`}
  key={index}
  onClick={() => handlecategory(item.name)}
>            <div className="image-box">
              <img src={item.img} alt={t(item.name)} />
            </div>
            <p className="category-title">{t(item.name)}</p>
          </div>
        ))}
      </div>

     
    </div>
  );
};

export default Categories;