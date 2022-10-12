import React from 'react';
import { QueryClient, QueryClientProvider } from 'react-query';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Steps from './components/pages/Steps';
import Operations from './components/pages/Operations';
import Overview from './components/pages/Overview';
import OperationDetails from './components/pages/OperationDetails';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';


const App: React.FC = () => {

  const client = new QueryClient();
  return (
    <>
      <LocalizationProvider dateAdapter={AdapterDateFns}>
        <QueryClientProvider client={client}>
          <BrowserRouter>
            <Routes>
              <Route path='/' element={<Steps />} />
              <Route path='/steps' element={<Steps />} />
              <Route path='/operations' element={<Operations />} />
              <Route path='/overview' element={<Overview />} />
              <Route path='/operations/:operationId' element={<OperationDetails />} />
            </Routes>
          </BrowserRouter>
        </QueryClientProvider>
      </LocalizationProvider>
    </>);
}

export default App;
