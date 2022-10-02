import React from 'react';
import { QueryClient, QueryClientProvider } from 'react-query';
import './App.css';
import Index from './components/pages/Index';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Steps from './components/pages/Steps';
import Operations from './components/pages/Operations';
import Overview from './components/pages/Overview';

const App: React.FC = () => {

  const client = new QueryClient();
  return (
    <>
      <QueryClientProvider client={client}>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<Steps />} />
            <Route path='/steps' element={<Steps />} />
            <Route path='/operations' element={<Operations />} />
            <Route path='/overview' element={<Overview />} />
          </Routes>
        </BrowserRouter>
      </QueryClientProvider>
    </>);
}

export default App;
