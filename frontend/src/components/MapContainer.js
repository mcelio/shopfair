import React, { Component } from 'react';
import ReactDOM from "react-dom";
import {Map, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';
import Stores from './Stores';

const stores = new Stores();

export class MapContainer extends Component {

  // Initializing important variables
    constructor(props) {
       super(props);
       this.setCenter = this.setCenter.bind(this);
       this.getMarkers = this.getMarkers.bind(this);
       this.loadMap = this.loadMap.bind(this);
       this.getPointByAddress = this.getPointByAddress.bind(this);
       this.loadStore = this.loadStore.bind(this);
       this.state = {
          center: {
             lat: -25.363882,
             lng: 131.044922,
          },
          markers: {
             lat: -25.363882,
             lng: 131.044922
          }
       };
     }

  getPointByAddress(address, loadStore) {
     let geocoder = new window.google.maps.Geocoder();
     return geocoder.geocode( { 'address': address}, function(results, status) {
         if (status == 'OK') {
             console.log(results[0].geometry.location);
             loadStore(results[0].geometry.location)
         } else {
             console.log('Geocode was not successful for the following reason: ' + status);
         }
      });
  }

  loadStore(location) {
       console.log("Starting loadStores");
       if(location) {
           console.log("markers -> " + this.state.markers.lat);
           const marker =  this.state.markers + "," + JSON.stringify(location);
           //console.log("markers -> " + marker);
           this.setState({
              markers: marker
           });
       }
  }

  setCenter(map) {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                map.setCenter(new google.maps.LatLng(position.coords.latitude,position.coords.longitude));
                this.setState({
                        center: {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        }
                    }
                );
            });
        }
    }

  getMarkers() {
         let markers;
         stores.getStores().then((response) => {
            response.data.map((store, index) => {
               let geocoder = new window.google.maps.Geocoder();
               geocoder.geocode( { 'address': store.address}, (results, status) => {
                   if (status == 'OK') {
                        const addrLocation = JSON.parse(results[0].geometry.location);
                        const location = JSON.parse(this.state.markers) + "," + addrLocation;
                        console.log("markers -> " + location);
                        this.setState({
                            markers: location
                        });
                   }
               });
            });
         }).catch(error => console.log(error));
    }

  componentDidMount() {
     //this.props.history.push('/stores');
     this.loadMap();
  }

  loadMap() {
      if (this.props && this.props.google) { // checks to make sure that props have been passed
        const {google} = this.props; // sets props equal to google
        const maps = google.maps; // sets maps to google maps props

        const mapRef = this.refs.map; // looks for HTML div ref 'map'. Returned in render below.
        const node = ReactDOM.findDOMNode(mapRef); // finds the 'map' div in the React DOM, names it node

        const mapConfig = Object.assign({}, {
          zoom: 18, // sets zoom. Lower numbers are zoomed further out.
          mapTypeId: 'roadmap' // optional main map layer. Terrain, satellite, hybrid or roadmap--if unspecified, defaults to roadmap.
        })

        this.map = new maps.Map(node, mapConfig); // creates a new Google map on the specified node (ref='map') with the specified configuration set above.
        // set center
        this.setCenter(this.map);

        stores.getStores().then((response) => {
                    response.data.map((store, index) => {
                       let geocoder = new window.google.maps.Geocoder();
                       geocoder.geocode( { 'address': store.address}, (results, status) => {
                           if (status == 'OK') {
                                const marker = new google.maps.Marker({ // creates a new Google maps Marker object.
                                      position: results[0].geometry.location, // sets position of marker to specified location
                                      map: this.map, // sets markers to appear on the map we just created on line 35
                                      title: store.name // the title of the marker is set to the name of the location
                                });
                           }
                       });
                    });
                 }).catch(error => console.log(error));
      }
    }

    render() {
      const style = { // MUST specify dimensions of the Google map or it will not work. Also works best when style is specified inside the render function and created as an object
        width: '90vw', // 90vw basically means take up 90% of the width screen. px also works.
        height: '75vh' // 75vh similarly will take up roughly 75% of the height of the screen. px also works.
      }

      return ( // in our return function you must return a div with ref='map' and style.
        <div ref="map" style={style}>
          loading map...
        </div>
      )
    }
}

export default GoogleApiWrapper({
  apiKey: ("AIzaSyADFQTplHncQsGKz2NT9sBHrI55OeQxWl4")
})(MapContainer)
