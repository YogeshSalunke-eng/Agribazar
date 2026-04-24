import React from "react";
import "./Dashboard.css";
import { useTranslation } from "react-i18next";
import i18n from "../i18n";
import 'bootstrap/dist/css/bootstrap.min.css';
import agribazarlogo from "../assets/agri.png";
import { useEffect,useState } from "react";
import API from "../services/API";
import {
  FaSearch,
  FaTruck,
  FaHeart,
  FaUser,
  FaShoppingCart,
} from "react-icons/fa";
import { IoLanguage } from "react-icons/io5";
import { useNavigate } from "react-router-dom";
const Navbar = ({setSearchTerm}) => {
const{t}=useTranslation();
const navigate=useNavigate();
const [showProfile, setShowProfile] = useState(false);
const [user, setUser] = useState(null);

useEffect(() => {
    API.get("/auth/me")
      .then(res => setUser(res.data))
      .catch(err => console.log(err));

}, []);
console.log(user);
useEffect(() => {
  const handleClickOutside = (e) => {
    if (!e.target.closest(".profile-dropdown") && 
    !e.target.closest(".login-section")) {
  setShowProfile(false);
}
  };

  document.addEventListener("click", handleClickOutside);
  return () => document.removeEventListener("click", handleClickOutside);
}, []);

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
  onChange={(e) => setSearchTerm(e.target.value)}
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

<div className="nav-item" onClick={()=>navigate("/cart")}>
            <FaShoppingCart />
            <span>{t("cart")}</span>
          </div>
          <div className="nav-item">
            <FaUser />
<div className="login-section">
  <span onClick={() => setShowProfile(!showProfile)}>
    {user?user.name :"user"}
  </span>
</div>
   </div>
{showProfile && user && (
  <div className="profile-dropdown">
    
    <div className="profile-header">
      <div className="user-icon">👤</div>
      <h3>{user.name}</h3>
      <p>{user.email}</p>
      <p>{user.villageName}</p>
    </div>

    <div className="profile-options">
      <button onClick={() => navigate("/orders")}>
        Previous Orders
      </button>

      <button
        className="logout-btn"
        onClick={() => {
          navigate("/login");
        }}
      >
        Logout
      </button>
    </div>

  </div>
)}
        </div>
      </div>


      
      
  );
};

export default Navbar;