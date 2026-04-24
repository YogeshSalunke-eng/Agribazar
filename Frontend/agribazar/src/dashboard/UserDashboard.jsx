import React from 'react'
import Navbar from './Navbar'
import {useState } from 'react';
import Footer from './Footer';
import Shops from './Shops';
import Categories from './Categories';
import Pages from '../pages/Pages';
import ProductDetails from '../pages/Productdetails';
import Elements from '../elements/Elements';
const UserDashboard = () => {
const[shopId,setShopId]=useState(null);
const[selectedCategory,setSelectedCategory]=useState(null);

  return (
    <div>
<Navbar/>
<Shops setShopId={setShopId} selectedCategory={selectedCategory}/>
  <Elements setSelectedCategory={setSelectedCategory} shopId={shopId}/>



<Footer/>

    </div>
  )
}

export default UserDashboard;