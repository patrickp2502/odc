import React from 'react';
import { QueryClient, QueryClientProvider } from 'react-query';
import './App.css';


function App() {

  const client = new QueryClient();


  return (
    <>
      <QueryClientProvider client={client}>
        Welcome to ODC
      </QueryClientProvider>
    </>
  );
}

export default App;
