import React, { useState, useEffect } from 'react';
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)
  const [loanApplications, setLoanApplications] = useState([]);

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

  return (
    <>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
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
    </>
  )
}

export default App
