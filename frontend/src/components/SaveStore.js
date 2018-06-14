import React, { Component } from 'react';
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.css';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

class SaveStores extends Component {

    // Initializing important variables
    constructor(props) {
       super(props);
       this.saveStore = this.saveStore.bind(this);
       this.handleNameChange = this.handleNameChange.bind(this);
       this.handleAddressChange = this.handleAddressChange.bind(this);
       this.state = {
           name: "",
           address: ""
       };
     }

     handleNameChange(e) {
        this.setState({name: e.target.value});
     }
     handleAddressChange(e) {
        this.setState({address: e.target.value});
     }

    saveStore(event) {
        event.preventDefault();
        console.log("Saving store");
        const authStr = localStorage.getItem('id_token');
        const headers = {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": authStr,
            "Access-Control-Request-Origin" : "*",
            "Access-Control-Allow-Credentials":"true",
            "withCredentials" : true,
        }

        const store = {
                       "address": this.state.address,
                       "coordinate": {
                         "latitude": 0,
                         "longitude": 0
                       },
                       "name": this.state.name,
                       "rating": 0,
                       "storeType": "SWEET"
                     }


        console.log(name);
        axios({
            method: "post",
            url: "http://localhost:8080/v1/stores",
            withCredentials: true,
            data: store,
            headers
          }).then(function (response) {
             return Promise.resolve(response);
          }).catch(function (error) {
             console.log(error);
          });
    }

    render() {
            return (
              <Form>
                <FormGroup>
                  <Label for="name">Name</Label>
                  <Input type="text" name="storeName" id="storeName" onChange={this.handleNameChange} placeholder="Store name" />
                </FormGroup>
                <FormGroup>
                  <Label for="address">Address</Label>
                  <Input type="text" name="storeAddress" id="storeAddress" onChange={this.handleAddressChange} placeholder="Address" />
                </FormGroup>
                <Button onClick={this.saveStore }>Save</Button>
              </Form>
            );
        }
}

export default SaveStores;
