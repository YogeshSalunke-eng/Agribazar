import React from 'react'
import Navbar from './Navbar'
import {useState } from 'react';
import Footer from './Footer';
import Shops from './Shops';
import Categories from './Categories';
import Pages from '../pages/Pages';
import ProductDetails from '../pages/Productdetails';
import Elements from '../elements/Elements';
import { useLocation } from 'react-router-dom';
const ProductDashboard = () => {
const[selectedshop,setSelectedShop]=useState(null);
const[category,setCategory]=useState(null);
const [searchTerm, setSearchTerm] = useState("");
const location=useLocation();
const {shopId } = location.state || {};
  return (
    <div>
<Navbar setSearchTerm={setSearchTerm} />
<Categories setCategory={setCategory}/> 

<Pages 
  selectedshop={shopId} 
  category={category}
  searchTerm={searchTerm}
/> 
<Footer/>

    </div>
  )
}

export default ProductDashboard;