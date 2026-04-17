import React, { useState } from "react";
import API from "../services/API";
import "./CompletePofile.css";
import { useNavigate, useLocation } from "react-router-dom";

const CompleteProfile = () => {
  const [name, setName] = useState("");
  const [role, setRole] = useState("ROLE_USER"); 
  const [villageName, setVillageName] = useState("");
  const [selectedShops, setSelectedShops] = useState([]); 
  const [error, setError] = useState("");

  const navigate = useNavigate();
  const location = useLocation();
  const email = location.state?.email;

  const villages = [
    "Takli dhokeshwar",
    "Wasunde",
    "Wadagaon sawtal",
    "Vankute",
    "Dhotre",
    "kanhur pathar",
    "karjule"
  ];

  const availableShops = [
    "Shree Dhokeshwar Krushi Udyog",
    "Bhumiputra Krushi Seva",
    "Balaji Krushi Seva",
    "Natha Farming",
    "JADHAV Krushi Kendra",
    "Ujjwal Krushi Udyog"
  ];

  const handleShopClick = (shop) => {
  setSelectedShops((prev) =>
    prev.includes(shop)
      ? prev.filter((s) => s !== shop)
      : [...prev, shop]
  );
};

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await API.put("/auth/complete-profile", {
        email,
        name,
        role, 
        villageName,
      });

      if (response.status === 200) {
        navigate("/login");
      }
    } catch (err) {
      setError(err.response?.data || "Error saving profile");
    }
  };

  return (
    <div className="profile-container">
      <form className="profile-form" onSubmit={handleSubmit}>
        <h2>Complete Your Profile 🌾</h2>

        {error && <p className="error">{error}</p>}

        <div className="input-group">
          <label>Name</label>
          <input
            type="text"
            placeholder="Enter your name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>

        <div className="input-group">
          <label>Role</label>
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="ROLE_USER">User</option>
            <option value="ROLE_SELLER">Shop Owner</option>
          </select>
        </div>

        <div className="input-group">
          <label>Select Village</label>
          <select
            value={villageName}
            onChange={(e) => setVillageName(e.target.value)}
            required
          >
            <option value="">-- Select Village --</option>
            {villages.map((v, index) => (
              <option key={index} value={v}>
                {v}
              </option>
            ))}
          </select>
        </div>

        <div className="input-group">
          <label>Select Favourite Shops</label>
          <div className="shop-grid">
            {availableShops.map((shop) => (
              <div
                key={shop}
                className={`shop-card ${
                  selectedShops.includes(shop) ? "shop-card-selected" : ""
                }`}
                onClick={() => handleShopClick(shop)} 
              >
                {shop}
              </div>
            ))}
          </div>
        </div>

        <button type="submit" className="submit-btn">
          Save Profile
        </button>
      </form>
    </div>
  );
};

export default CompleteProfile;