import React from "react";
import ReactDOM from "react-dom";
import Login from './Login';
import Stores from './Stores';
import SaveStore from './SaveStore';
import App from './App';
import MapContainer from './MapContainer';
import 'bootstrap/dist/css/bootstrap.css';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem } from 'reactstrap';
import { BrowserRouter as Router, Route, Redirect, Link } from "react-router-dom";
import { NavLink as RRNavLink } from 'react-router-dom';
import AuthService from './AuthService';

const Auth = new AuthService();

class Shopfair extends React.Component {

  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {
      isOpen: false
    };
  }
  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  componentDidMount() {
     //this.props.history.push('/map');
  }

  render() {
    return (
      <Router>
          <div>
            <Navbar color="faded" light expand="md">
              <NavbarBrand href="/">Shopfair</NavbarBrand>
              <NavbarToggler onClick={this.toggle} />
              <Collapse isOpen={this.state.isOpen} navbar>
                <Nav className="ml-auto" navbar>
                  <NavItem>
                    <NavLink to="/stores" activeClassName="active" tag={RRNavLink}>Stores</NavLink>
                  </NavItem>
                  <NavItem>
                    <NavLink to="/saveStores" activeClassName="active" tag={RRNavLink}>Save Store</NavLink>
                  </NavItem>
                  <NavItem>
                    <NavLink to="/login" activeClassName="active" tag={RRNavLink}>Login</NavLink>
                  </NavItem>              
                  <NavItem>
                    <NavLink to="/map" activeClassName="active" tag={RRNavLink}>Map</NavLink>
                  </NavItem> 
                </Nav>
              </Collapse>
            </Navbar>
            <Route exact path="/stores" render={(props) => (
                                  Auth.loggedIn() ? (
                                    <Stores {...props} />
                                  ) : (
                                    <Redirect to="/login"/>
                                  )
                                )}/>
            <Route exact path="/saveStores" component={SaveStore} />
            <Route path="/login" component={Login} />
            <Route path="/map" component={MapContainer} />
         </div>   
      </Router>
    );
  }
}

export default Shopfair;




