import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import GreveForm from './components/GreveForm';
import GreveFimForm from './components/GreveFimForm';
import GreveList from './components/GreveList';
import GreveDetail from './components/GreveDetail';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import  'react-bootstrap';

const App = () => (
  <Router>
    <header style={{  padding: '20px', textAlign: 'left' }}>
  <img src="https://www.dieese.org.br/imagens/logo-dieese-2021.png" alt="DIEESE" style={{ width: '300px' }} />
</header>
    <Navbar />
    <ToastContainer />
    <Routes >
      <Route path="/" element={<GreveList />} />
      <Route path="/add" element={<GreveForm />} />
      <Route path="/edit/:id" element={<GreveForm />} />
      <Route path="/details/:id" element={<GreveDetail />} />
      <Route path="/edit/fim/:id" element={<GreveFimForm />} />
    </Routes>
  </Router>
);

export default App;
