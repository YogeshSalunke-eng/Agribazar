import React from "react";
import "./Dashboard.css";
import { useTranslation } from "react-i18next";
import i18n from "../i18n";
import 'bootstrap/dist/css/bootstrap.min.css';
import agribazarlogo from "../assets/agri.png";

import {
  FaSearch,
  FaTruck,
  FaHeart,
  FaUser,
  FaShoppingCart,
} from "react-icons/fa";
import { IoLanguage } from "react-icons/io5";
import { useNavigate } from "react-router-dom";const Navbar = () => {
const{t}=useTranslation();
const navigate=useNavigate();

  return (
      <div className="navbar-1 d-flex w-100 justify-content-between align-items-center">
        <div className="navbar-left">
          <img src={agribazarlogo} alt="agribazar" className="agrilogo"/>
          
        </div>

        <div className="navbar-center">
          <input
            type="text"
            placeholder="Search products..."
            className="search-input"
          />
          <button className="search-btn">
            <FaSearch />
          </button>
        </div>

        <div className="navbar-right d-flex gap-3">
          <div className="language-box">
            <IoLanguage />
            <select onChange={(e)=> i18n.changeLanguage(e.target.value)}>
              <option value="en">English</option>
              <option value="hi">हिन्दी</option>
              <option value="mr">मराठी</option>
            </select>
          </div>

          <div className="nav-item">
            <FaTruck />
            <span>{t("track_order")}</span>
          </div>

          <div className="nav-item" onClick={()=>navigate("/wishlist")}>
            <FaHeart />
            <span>{t("wishlist")}</span>
          </div>

          <div className="nav-item">
            <FaUser />
            <span>{t("login")}</span>
          </div>

          <div className="nav-item" onClick={()=>navigate("/cart")}>
            <FaShoppingCart />
            <span>{t("cart")}</span>
          </div>
        </div>
      </div>


      
      
  );
};

export default Navbar;