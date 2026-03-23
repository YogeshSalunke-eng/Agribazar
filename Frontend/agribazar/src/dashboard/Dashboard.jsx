import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './Navbar'
import {useState } from 'react';
import Footer from './Footer';
import Shops from './Shops';
import Categories from './Categories';
import Pages from '../pages/Pages';
import ProductDetails from '../pages/ProductDetails';
const Dashboard = () => {
const[selectedshop,setSelectedShop]=useState(null);
  return (
    <div>
<Navbar/>
<Categories/>
<Shops setSelectedShop={setSelectedShop}/>
{selectedshop && <Pages selectedshop={selectedshop}/>}
<ProductDetails/>
<Footer/>

    </div>
  )
}

export default Dashboard;