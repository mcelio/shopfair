import React, { Component } from 'react';
import AuthService from './AuthService';
import './Login.css';
import 'bootstrap/dist/css/bootstrap.css';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

class Login extends Component {
    constructor(props){
        super(props);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.Auth = new AuthService();
        this.state = {
            username: "",
            password: ""
        };
    }

    handleUsernameChange(e) {
       this.setState({username: e.target.value});
    }
    handlePasswordChange(e) {
       this.setState({password: e.target.value});
    }
 
    render() {
        return (
          <Form>
            <FormGroup>
              <Label for="username">Email</Label>
              <Input type="email" name="usernameInput" id="usernameInput" onChange={this.handleUsernameChange} placeholder="Username or email" />
            </FormGroup>
            <FormGroup>
              <Label for="password">Password</Label>
              <Input type="password" name="passwordInput" id="passwordInput" onChange={this.handlePasswordChange} placeholder="Password" />
            </FormGroup>       
            <Button onClick={this.handleFormSubmit }>Go</Button>
          </Form>
        );
    }
    
    handleFormSubmit(e){
        e.preventDefault();      
        console.log("Username: " + this.state.username);
        console.log("Password: " + this.state.password);
        this.Auth.login(this.state.username,this.state.password)
            .then(res =>{
               console.log("Response: " + res);
            }).catch(err =>{
                console.log("Error Response");
                alert(err);
            })
    }

    componentWillMount(){
        if(this.Auth.loggedIn()){
//            this.props.history.replace('/');
        }
    }
}

export default Login;
