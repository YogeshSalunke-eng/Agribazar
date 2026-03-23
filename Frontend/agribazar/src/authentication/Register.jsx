import API from '../services/API';
import React, { useState, useEffect } from 'react';
import './Register.css';
import { useNavigate } from 'react-router-dom';

const Register = () => {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [otp, setOtp] = useState('');
  const [showOtpField, setShowOtpField] = useState(false);
  const [message, setMessage] = useState('');
  const [timer, setTimer] = useState(0);
  const [error, setError] = useState('');
  const navigate = useNavigate();

const handleSubmit = async(e) => {
  e.preventDefault();
   if (!otp) {
    setError("Please enter OTP");
    return;
  }

  try {
    const response = await API.post("/auth/register", {
      email,
      password,
      otp,
    });

    setMessage("Registration Successful 🎉");
 navigate("/login");
    
  } catch (err) {
    
  if (err.response && err.response.data) {
    setError(err.response.data.message);
  } else {
    setError("Something went wrong");
  }
}
};
  useEffect(() => {
    let interval = null;

    if (timer > 0) {
      interval = setInterval(() => {
        setTimer(prev => prev - 1);
      }, 1000);
    }

    return () => clearInterval(interval);
  }, [timer]);

  const handleSendOtp = () => {
    if (!email) {
  setError("Please enter your email first!!!");
      return;
    }
setError('');
    setMessage("otp sent successfully ✅");
    setShowOtpField(true);
    setTimer(60); 
  };

  const handleResendOtp = () => {
    setMessage("OTP resent successfully ✅");
    setTimer(60);
  };

  return (
    <div className="register-container">
      <form className="register-form" onSubmit={handleSubmit}>
        <h1>Register</h1>

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
            >
              OTP
            </button>
          </div>
        </div>
{error && <p className='error-msg'>{error}</p>}

        {showOtpField && (
          <>
            <div className="input-group">
              <label>Enter OTP</label>
              <input
                type="text"
                placeholder="Enter OTP"
                value={otp}
                onChange={(e) => setOtp(e.target.value)}
              />
            </div>

            <div className="otp-timer-row">
              <span>Time left: {timer}s</span>

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
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="register-btn"
         >Register</button>

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
export default Register;