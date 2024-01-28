import React, { useState, useEffect } from 'react';
import reactLogo from './assets/react.svg'
import moneyBinLogo from './assets/Logo.png'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)
  const [loanApplications, setLoanApplications] = useState([]);
  const [monthlyPayment, setMonthlyPayment] = useState('');
  const currentURL = window.location.href;

  useEffect(() => {
    fetch('http://localhost:8080/api/loan-applications')
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

    const response = await fetch('http://localhost:8080/api/calculate-mortgage', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loanDetails)
    });

    if (!response.ok) {
      const message = `An error has occurred: ${response.statusText}`;
      console.error(message);
      return;
    }

    const result = await response.json();
    setMonthlyPayment(result);
  };

  return (
    <>
      <div>
        <a href={currentURL} target="_blank">
          <img src={moneyBinLogo} className="logo" alt="Money Bin logo" />
        </a>
      </div>
      <h1>Money Bin Inc</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
      <div className="App">
      <header className="App-header">
        <h1>Loan Applications</h1>
        <ul>
          {loanApplications.map((application, index) => (
            <li key={index}>{application}</li>
          ))}
        </ul>
      </header>
    </div>
    <div className="App">
      <header className="App-header">
        <h1>Mortgage Calculator</h1>
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
        {monthlyPayment && <p>{monthlyPayment}</p>}
      </header>
    </div>
    </>
  )
}

export default App
