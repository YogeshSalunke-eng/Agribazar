import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react'
import './App.css'
//import Login from './authentication/Login'
//import Register from './authentication/Register'
//import ForgetPassword from './authentication/ForgetPassword'
import Dashboard from './dashboard/Dashboard';
import { Route, Routes } from 'react-router-dom'
import Pages from './pages/Pages';
import WishList from './elements/WishList';
import Cart from './elements/Cart';
import ProductDetails from './pages/ProductDetails';
function App() {
  return (
    <Routes>

 {/* <Route path='/' element={<Register/>}/>
<Route path='/login' element={<Login/>}/>
 <Route path='/forget' element={<ForgetPassword/>}/>
  <Route path='/dashboard' element={<Dashboard/>}/> */}
  <Route path='/pages' element={<Pages/>}/>
  <Route path='/'element={<Dashboard/>}/>
  <Route path='/wishlist' element={<WishList/>}/>
  <Route path='/cart' element={<Cart/>}/>
  <Route path='/productdetails' element={<ProductDetails/>}/>
  </Routes>

 
  )
}

export default App;






