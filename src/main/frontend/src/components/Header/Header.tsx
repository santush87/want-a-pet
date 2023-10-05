import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
// import NavDropdown from 'react-bootstrap/NavDropdown';


function Header() {
  return (
    <Navbar bg="light" data-bs-theme="light">
      <Container>
        <Navbar.Brand href="/">Want a pet?</Navbar.Brand>
        <Nav className="me-right">
          <Nav.Link href="/"><b>Home</b></Nav.Link>
          <Nav.Link href="/catalog"><b>Catalog</b></Nav.Link>
          <Nav.Link href="/add-pet"><b>Add Pet</b></Nav.Link>
          <Nav.Link href="/contact"><b>Contact</b></Nav.Link>
          <Nav.Link href="/login"><b>Login</b></Nav.Link>
          <Nav.Link href="/register"><b>Register</b></Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}

export default Header;