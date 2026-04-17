import React from "react";
import "./Footer.css";
import agribazarlogo from "../assets/agri.png";
import { FaFacebookF, FaInstagram, FaTwitter, FaLinkedinIn } from "react-icons/fa";
import { IoSend } from "react-icons/io5";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">

        <div className="footer-col">
<img src={agribazarlogo} alt="agribazar" className="agrilogo"/>
          <p className="footer-desc">
            Explore a wide range of agricultural products and machinery at
            AgriBazar. From seeds and fertilizers to farm tools, we provide all
            you need for successful farming.
          </p>
          <button className="app-btn">▶ Download Agribazar App</button>
        </div>

        <div className="footer-col">
          <h3>Quick Links</h3>
          <ul>
            <li>› About Us</li>
            <li>› Privacy Policy</li>
            <li>› Return Policy</li>
            <li>› Shipping Policy</li>
            <li>› Terms & Conditions</li>
            <li>› Careers</li>
            <li>› Blog</li>
            <li>› Contact Us</li>
            <li>› Track Order</li>
          </ul>
        </div>

        <div className="footer-col">
          <h3>Top Categories</h3>
          <ul>
            <li>› Vegetable Seeds</li>
            <li>› Crop Protection</li>
            <li>› Crop Nutrition</li>
            <li>› Plants</li>
            <li>› Pots & Planters</li>
          </ul>

          <h3 className="follow-title">Follow Us</h3>
          <div className="social-icons">
            <FaFacebookF />
            <FaInstagram />
            <FaTwitter />
            <FaLinkedinIn />
          </div>
        </div>

        <div className="footer-col">
          <h3>Stay Updated</h3>
          <p>Get the latest offers and product updates directly in your inbox.</p>

          <div className="subscribe-box">
            <input type="email" placeholder="Enter your Email" />
            <button><IoSend /></button>
          </div>

          <h3 className="contact-title">Contact Us</h3>
          <p><strong>Agribazar Pvt. Ltd.</strong></p>
          <p>
            Takli Dhokeshwar, Near ST Stand,<br />
            tal-parner, AhilyaNagar,<br />
            Maharashtra - 414304
          </p>
          <p>📧 Email: contact@agribazar.in</p>
          <p>📞 Customer Care: 9307004288</p>
        </div>

      </div>

      <div className="footer-bottom">
        © 2026 Agribazar Pvt. Ltd. All rights reserved.
      </div>
    </footer>
  );
};

export default Footer;