import API from '../services/API';
import React, { useState, useEffect } from 'react';
import './Register.css';
import { useNavigate } from 'react-router-dom';

const ForgetPassword = () => {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [otp, setOtp] = useState('');
  const [showOtpField, setShowOtpField] = useState(false);
  const [message, setMessage] = useState('');
  const [timer, setTimer] = useState(0);
  const [error, setError] = useState('');
  const[isVerified, setIsVerified]=useState(false);
  const[loading,setLoading]=useState(false);
  const navigate = useNavigate();

const handleSubmit = async(e) => {
  e.preventDefault();
   if (!isVerified) {
    setError("Please verify the otp first");
    return;
  }

  try {
    const response = await API.post("/auth/forgot-password", {
      email:email.trim(),
      password,
      
    });
if(response.status===200){
setMessage("password change successfully, please login");
 navigate("/login");
}
    else{
      setError("registration fail");
    }
  } catch (err) {
    
  if (err.response && err.response.data) {
    setError(err.response.data);
  } else {
    setError("Something went wrong");
  }
}
};
  useEffect(() => {
    if(timer<=0){
      return;
    }

      const interval = setInterval(() => {
        setTimer(prev => prev - 1);
      }, 1000);
    

    return () => clearInterval(interval);
  }, [timer]);

  const handleSendOtp = async() => {
    if (!email) {
  setError("Please enter your email first!!!");
      return;
    }
    try{
      setLoading(true);
      const response=await API.post("email/send-otp",{
        email
      });
      if(response.status===200){
setMessage("otp sent successfully ✅");
setLoading(false);
    setShowOtpField(true);
    setTimer(60); 
      }
    }
    catch(e){
if (err.response && err.response.data) {
    setError(err.response.data);}
  else{
    setError("error in sending otp");
  }}
  };

  const handleResendOtp = async() => {
    if (!email) {
  setError("Please enter your email first!!!");
      return;
    }
    try{
      setLoading(true);
      const response=await API.post("email/send-otp",{
        email
      });
      if(response.ok){
setMessage("otp sent successfully ✅");
setLoading(false);
    setShowOtpField(true);
    setTimer(60); 
      }
    }
    catch(e){
if (err.response && err.response.data) {
    setError(err.response.data);}    }
  };
const handleOtpChange=async(value)=>{
  if(value.length==6){
    console.log("otp is ",value);
    console.log("email is",email);
    try{
      const response=await API.post("/email/verify-otp",{
        email:email.trim(),
        otp:value
      }
      );
      if(response.status===200){
        setIsVerified(true);
        setMessage("otp verified ✅");
      }
      else {setMessage("Invalid otp ❌");}
    }
    catch(err){
if (err.response && err.response.data) {
    setError(err.response.data);}   
  else{
    setError("error in verifying otp")
  } }
  }
}
  return (
    <div className="register-container">
      <form className="register-form" onSubmit={handleSubmit}>
        <h1>change password</h1>

        <div className="input-group email-group">
          <label>Email</label>
          <div className="email-row">
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            <button
              type="button"
              className="otp-btn"
              onClick={handleSendOtp}
              disabled={showOtpField || loading}
            >
            {loading?"sending":"send otp"}
            </button>
          </div>
        </div>
{error && <p className='error-msg'>{error}</p>}
 {message && <p className="success-msg">{message}</p>}

        {showOtpField && (
          <>
            <div className="input-group">
              <label>Enter OTP</label>
              <input
                type="text"
                placeholder="Enter OTP"
                value={otp}
                onChange={(e) => {
                  setOtp(e.target.value);
                  handleOtpChange(e.target.value);}}
              />
            </div>

            <div className="otp-timer-row">
              <span>⏳{timer}s</span>

              <button
                type="button"
                className="resend-btn"
                disabled={timer > 0}
                onClick={handleResendOtp}

              >
                Resend OTP
              </button>
            </div>
          </>
        )}

        <div className="input-group">
          <label>Password</label>
          <input
            type="password"
            placeholder="Enter new password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        <button type="button" className="next-btn"
                 disabled={!password || !isVerified}

         >save
         </button>

        <div className="register-footer">
          <div className="register-link">
  <span className='lastname'>Already have account </span>
  <span 
    className='givelink'
    onClick={() => navigate("/login")}
  >
    login
  </span>
</div>
        </div>
      </form>
    </div>
  );
};
export default ForgetPassword;