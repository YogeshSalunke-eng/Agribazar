import React, { useState, useEffect } from 'react';
import './ForgetPassword.css';
import { useNavigate } from 'react-router-dom';

const ForgetPassword = () => {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [otp, setOtp] = useState('');
  const [showOtpField, setShowOtpField] = useState(false);
  const [message, setMessage] = useState('');
  const [timer, setTimer] = useState(0);
const [error, setError] = useState('');
  const navigate = useNavigate();

const handleSubmit = (e) => {
  e.preventDefault();
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
    setMessage("Email sent successfully ✅");
    setShowOtpField(true);
    setTimer(60); 
  };

  const handleResendOtp = () => {
    setMessage("OTP resent successfully ✅");
    setTimer(60);
  };

  return (
    <div className="forget-container">
      <form className="forget-form" onSubmit={handleSubmit}>
        <h1>Reset the Password</h1>

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

        <button type="submit" className="forget-btn">Save</button>

        <div className="forget-footer">
          <div className="forget-link">
            <span className='lastname'>Already have account </span>
           <p className='givelink' onClick={()=>navigate("/login")}>login</p>
          </div>
        </div>
      </form>
    </div>
  );
};

export default ForgetPassword;