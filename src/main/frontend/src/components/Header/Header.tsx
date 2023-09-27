// import { Link } from 'react-router-dom'

import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
// import NavDropdown from 'react-bootstrap/NavDropdown';


function Header() {
  return (
    // <Navbar bg="dark" data-bs-theme="dark">
    //   <Container>
    //     <Navbar.Brand href="#home">Navbar</Navbar.Brand>
    //     <Nav className="me-auto">
    //       <Nav.Link href="#home">Home</Nav.Link>
    //       <Nav.Link href="#features">Features</Nav.Link>
    //       <Nav.Link href="#pricing">Pricing</Nav.Link>
    //     </Nav>
    //   </Container>
    // </Navbar>
    // <br />
    // <Navbar bg="primary" data-bs-theme="dark">
    //   <Container>
    //     <Navbar.Brand href="#home">Navbar</Navbar.Brand>
    //     <Nav className="me-auto">
    //       <Nav.Link href="#home">Home</Nav.Link>
    //       <Nav.Link href="#login">Login</Nav.Link>
    //       <Nav.Link href="#register">Register</Nav.Link>
    //     </Nav>
    //   </Container>
    // </Navbar>

    // <br />
    <Navbar bg="light" data-bs-theme="light">
      <Container>
        <Navbar.Brand href="/">Want a pet?</Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="/">Home</Nav.Link>
          <Nav.Link href="/login">Login</Nav.Link>
          <Nav.Link href="/register">Register</Nav.Link>
          <Nav.Link href="/add-pet">Add Pet</Nav.Link>
          <Nav.Link href="/catalog">Catalog</Nav.Link>
          <Nav.Link href="/contact">Contact</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}

export default Header;