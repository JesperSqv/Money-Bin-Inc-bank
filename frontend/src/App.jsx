import React, { useState, useEffect, useLayoutEffect } from 'react';
import backgroundImage from './assets/background.jpg'
import moneyBinLogo from './assets/Logo.png'
import './App.css'

function App() {
  const [count, setCount] = useState(0)
  const [loanApplications, setLoanApplications] = useState([]);
  const [monthlyPayment, setMonthlyPayment] = useState('');

  const currentURL = window.location.href;

  const apiLoanApps = 'api/loan-applications';
  const apiLoanAppsUrl = currentURL + apiLoanApps;
  const apiMortage = 'api/calculate-mortgage';
  const apiMortageUrl = currentURL + apiMortage;

  const divStyle = {
    backgroundImage: `url(${backgroundImage})`,
    backgroundSize: 'cover', 
    backgroundRepeat: 'repeat',
    width: '100%',
    height: '100%',
    boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)',
    textShadow: '4px 4px 8px rgba(0, 0, 0, 0.9)',
  };

  const TITLE = 'Money Bin Inc';

  useEffect(() => {
    document.title = TITLE
 }, []);

  useLayoutEffect(() => {
    document.body.style.backgroundColor = "gray"
  });

  useEffect(() => {
    fetch(apiLoanAppsUrl)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => setLoanApplications(data))
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  const [loanDetails, setLoanDetails] = useState({
    totalLoan: '',
    interest: '',
    years: ''
  });
  

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setLoanDetails({
      ...loanDetails,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const response = await fetch(apiMortageUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loanDetails)
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.text(); 
    })
    .then(data => {
      setMonthlyPayment(data);
    })
    .catch(error => console.error('Error fetching data:', error));    
  };

  return (
    <>
      <div style={divStyle}>
        <div>
          <a href={currentURL} target="_blank">
            <img src={moneyBinLogo} className="logo" alt="Money Bin logo" />
          </a>
        </div>
        <h1 style={{ color: 'white' }}>Money Bin Inc</h1>
        <div className="App">
        <header className="App-header">
          <p style={{ color: 'white' }}>Introducing Money Bin Inc - Where Customers Come First!

  At Money Bin Inc, we're proud to be your trusted financial partner, committed to putting your needs at the forefront. With a relentless focus on you, we've earned our reputation as the best customer-centric bank.

  Our dedication to personalized service, cutting-edge technology, and transparent practices sets us apart. We're more than just a bank; we're your financial ally, always accessible and ready to help.

  But don't just take our word for it. See what our valued customers have to say below. Their stories are a testament to our commitment to excellence. Join us today, and experience banking the way it should be – centered around YOU.</p>
          <div style={{ textAlign: 'left' }}>
            {loanApplications.map((application, index) => (
              <p style={{ color: 'white' }} key={index}>{application}</p>
            ))}
          </div>
        </header>
      </div>
      <div className="App">
        <header className="App-header">
          <h1 style={{ color: 'white' }}>Mortgage Calculator</h1>
          <h2 style={{ color: 'white' }}>Test what your own mortage would be!</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="number"
              name="totalLoan"
              value={loanDetails.totalLoan}
              onChange={handleInputChange}
              placeholder="Total Loan"
            />
            <input
              type="number"
              step="0.01"
              name="interest"
              value={loanDetails.interest}
              onChange={handleInputChange}
              placeholder="Annual Interest Rate"
            />
            <input
              type="number"
              name="years"
              value={loanDetails.years}
              onChange={handleInputChange}
              placeholder="Loan Period in Years"
            />
            <button type="submit">Calculate</button>
          </form>
          {monthlyPayment && <h3 style={{ color: 'white' }}>{monthlyPayment}</h3>}
        </header>
      </div>
    </div>
    </>
  )
}

export default App
