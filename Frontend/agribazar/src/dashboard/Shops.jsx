import React from 'react'
import "./Dashboard.css";
import { useTranslation } from "react-i18next";
import shopImg1 from "../assets/dhokeshwar.png";
import shopImg2 from "../assets/bhumiputra.png";
import shopImg3 from "../assets/balaji.png";
import shopImg4 from "../assets/natha.png";
import shopImg5 from "../assets/jadhav.png";
import shopImg6 from "../assets/ujjwal.png";

const Shops = ({setSelectedShop}) => {
  const {t}=useTranslation();
const shopsData = [
    {shopId:1, name: "shreeDhokeshwar", img: shopImg1 },
    {shopId:2, name: "bhumiputra", img: shopImg2 },
    {shopId:3, name: "balaji", img: shopImg3 },
    {shopId:4, name: "natha", img:shopImg4},
    {shopId:5, name: "jadhav", img:shopImg5  },
    {shopId:6, name: "ujjwal", img: shopImg6},
  ];

  return (
<div className="shops-wrapper">
<div className="shop-info">
<div className="shop-right">Choose you shop</div>
<button className="shop-left">see more</button>
</div>

      <div className="shops-container">
        {shopsData.map((item) => (
          <div className="shops-card" key={item.shopId}
          onClick={()=>setSelectedShop(item.shopId)}
          >
            <div className="shops-image-box">
              <img src={item.img} alt={t(item.name)} />
            </div>
         <p className="shop-title">{t(item.name)}</p>
          </div>
        ))}
      </div>

     
    </div>  )
}

export default Shops;