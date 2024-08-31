import { Link } from 'react-router-dom';
import '../App.css';
import  Navbar from'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';

const N = () => (
    <Navbar expand="lg" className="navb">
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          <Nav.Link className="navl"  href="/">Home</Nav.Link> 
  
          <Nav.Link className="navl"
   href="/add">Adicionar Greve</Nav.Link>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
  
export default N;
