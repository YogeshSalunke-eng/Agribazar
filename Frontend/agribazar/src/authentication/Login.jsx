import API from '../services/API';
import React, { useState } from 'react';
import './Login.css';
import { useNavigate } from 'react-router-dom';
const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, seterror]=useState("");
  const navigate=useNavigate();
  const  handleSubmit=async(e)=>{
    e.preventDefault();


try{
  const response=await API.post("/auth/login",{
email,
password

 } );
   const { token, role } = response.data;
  localStorage.setItem("token", token);
 if(role==="ROLE_USER"){
navigate("/userDashboard");
 }
 else if(role==="ROLE_SELLER"){
  navigate("/sellerDashboard");
 }
 else{
  navigate("/login");
 }
}
catch(err){
if(err.response && err.response.data){
  seterror(err.response.data.message);
  console.log(err.response.data);
}
else{
  seterror("Incorrect email or password");
}
}

}
  
  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h1>Login</h1>
        
        <div className="input-group">
          <label style= {{color:"#2e7d32"}}> Email</label>
          <input 
            type="email" 
            placeholder="Enter your email" 
            value={email} 
            required 
            onChange={(e)=>setEmail(e.target.value)}
          />
        </div>

        <div className="input-group">
          <label style={{color:"#2e7d32"}}>Password</label>
          <input 
            type="password" 
            placeholder="Enter your password" 
            value={password} 
            required 
            onChange={(e)=> setPassword(e.target.value)}
          />
        </div>
{error && <p className='error-msg'> {error}</p>}
        <button type="submit" className="login-btn">Login</button>

        <div className="login-footer">
          <div className="forgot-pass">
                        <span className='lastname'> Forget Passowrd </span>

            <p className='givelink' onClick={()=>navigate("/forget")}>click here</p>
          </div>
          <div className="register-link">
            <span className='lastname'> New user? </span>
           <p className='givelink' onClick={()=>navigate("/")}>register</p>
          </div>
        </div>
      </form>
    </div>
  );
};

export default Login;