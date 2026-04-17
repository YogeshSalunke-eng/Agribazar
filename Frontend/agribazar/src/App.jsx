import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react'
import './App.css'
import Login from './authentication/Login'
import Register from './authentication/Register'
import ForgetPassword from './authentication/ForgetPassword'
import { Route, Routes } from 'react-router-dom'
import Pages from './pages/Pages';
import WishList from './elements/WishList';
import Cart from './elements/Cart';
import ProductDetails from './pages/ProductDetails';
import CompleteProfile from './authentication/CompleteProfile';
import UserDashboard from './dashboard/UserDashboard';
import ProductDashboard from './dashboard/ProductDashboard';
function App() {
  return (
    <Routes>

 <Route path='/' element={<Register/>}/>
<Route path='/login' element={<Login/>}/>
 <Route path='/forget' element={<ForgetPassword/>}/>
  <Route path='/userDashboard' element={<UserDashboard/>}/>
  <Route path='/pages' element={<Pages/>}/>
  <Route path='/wishlist' element={<WishList/>}/>
  <Route path='/cart' element={<Cart/>}/>
  <Route path='/productdetails' element={<ProductDetails/>}/>
  <Route path='/completeprofile' element={<CompleteProfile/>}/>
  <Route path='productdashboard' element={<ProductDashboard/>}/>
  </Routes>

  )
}

export default App;






