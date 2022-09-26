import './App.css';
import OperationView from './Components/OperationView';
// import { useQuery } from 'react-query';

function App() {

  useQuery()

  return (
    <div className="App">
     <h1>Welcome to ODC</h1>
    <div className='Container'>
     <Menu/>
     <OperationView/>
     </div>
    </div>
  );
}

export default App;
