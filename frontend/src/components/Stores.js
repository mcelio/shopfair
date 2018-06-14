import React, { Component } from 'react';
import decode from 'jwt-decode';
import axios from "axios";
import AuthService from "./AuthService";
import 'bootstrap/dist/css/bootstrap.css';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

const Auth = new AuthService();

class Stores extends Component {

    // Initializing important variables
    constructor(props) {
       super(props);
       this.state = {
         stores: []
       };
       this.getStores = this.getStores.bind(this)
     }

     getStores(){
             console.log("Getting stores");
             const authStr = localStorage.getItem('id_token');
             const headers = {
                 "Accept": "application/json",
                 "Content-Type": "application/json",
                 "Authorization": authStr,
                 "Access-Control-Request-Origin" : "*",
                 "Access-Control-Allow-Credentials":"true",
                 "withCredentials" : true,
             }
             return axios.get("http://localhost:8080/v1/stores", headers);
          }

    componentDidMount() {
        console.log("Getting stores");
        const authStr = localStorage.getItem('id_token');
        const headers = {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": authStr,
            "Access-Control-Request-Origin" : "*",
            "Access-Control-Allow-Credentials":"true",
            "withCredentials" : true,

        }
        return axios.get("http://localhost:8080/v1/stores", headers)
            .then(response => {
              // create an array of contacts only with relevant data
              this.setState({
                      stores: response.data
                    });
         }).catch(error => console.log(error));
    }

    render() {
            return (
              <ul>
                {this.state.stores.map(function(store, index){
                  return (
                      <div key={index}>
                        <h1>{store.id}</h1>
                        <p>{store.name}</p>
                        <p>{store.address}</p>
                      </div>
                    )
                  }
                )}
              </ul>
            );
        }
}

export default Stores;
