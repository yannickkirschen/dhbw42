import React from 'react'
import pic from '../../Sponsor.jpg'
import { NavDropdown, Nav, Navbar, Container } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const NavigationBar = () => {
    return (
        <Navbar bg='red' expand="lg" color='red' style={{backgroundColor: '#e2001a'}}>
        <Container>
            <Navbar.Brand href='/'>DHBW 42</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto" >
                <Nav.Link style={{color: '#6e7c84'}} href='/questionList'>Question List</Nav.Link>
                <Nav.Link style={{color: '#6e7c84'}} href='/createPost'>Ask Question</Nav.Link>
                <Nav.Link style={{color: '#6e7c84'}} href='/faq'>FAQ</Nav.Link>
                <Nav.Link style={{color: '#6e7c84'}} href='/about'>About</Nav.Link>
            </Nav>
            </Navbar.Collapse>
            <div>Sponsored by: <img alt='' src={pic} width={45} height={40}></img></div>
        </Container>
        </Navbar>
    )
}

export default NavigationBar;