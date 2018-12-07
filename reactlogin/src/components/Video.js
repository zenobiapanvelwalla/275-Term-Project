import React, {Component} from 'react';
import {Link, withRouter} from "react-router-dom";
import ReactDOM from 'react-dom';
import {Typeahead} from 'react-bootstrap-typeahead';
import admin_dshbd from "../custom_js/admin_dshbd.js";
import 'react-bootstrap-typeahead/css/Typeahead.css';
import NavBar from './NavBar';
import AdminDashboard from './AdminDashboard.js';
import AdminNavBar from './AdminNavBar.js';
import config from '../config.js';
import axios from 'axios';
import details from '../custom_css/movie_detail.css';
import YouTube from 'react-youtube';
import Modal from 'react-modal';
import ReactPlayer from 'react-player';
// import screenfull from 'screenfull';

const customStyles = {
    content : {
      top                   : '0%',
      left                  : '0%',
      right                 : '0%',
      bottom                : '0%',
      margin_right: '-50%',
      transform             : 'translate(-50%, -50%)'
    }
  };

class Video extends Component {

    constructor(props) {
        super(props);

        
        this.state = {
            modalIsOpen: false,
            watchingDetails : ''
          };
        
        //   this.ref = this.ref.bind(this);
          this.endVideo = this.endVideo.bind(this); 
          this.handlePause = this.handlePause.bind(this); 
          this.onPlay = this.onPlay.bind(this); 

          console.log("Inside Video" + this.state.watchingDetails)
    }

    componentDidMount(){

        console.log("Inside" + JSON.stringify(localStorage.getItem('watchingDetails')))

        let abc = JSON.parse(localStorage.getItem('watchingDetails'));

        this.setState({watchingDetails:abc})

        console.log("Watching details" + JSON.stringify(abc));
    }


    onPlay(e){
        let self = this;
        console.log(JSON.stringify(this.state.watchingDetails))
        let path = "/user-activities/store/" + self.state.watchingDetails.userId + "/" + self.state.watchingDetails.movieId + "/" + 1 ;
        console.log("Path of play store" + path)
        console.log(config.API_URL+path);
        axios.get(config.API_URL+path,{withCredentials: true})
        .then(function (response) {
          console.log("Message " + JSON.stringify(response));
          self.setState({movie_details:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    handlePause(e){
        let self = this;
        console.log(JSON.stringify(this.state.watchingDetails))
        let path = "/user-activities/store/" + self.state.watchingDetails.userId + "/" + self.state.watchingDetails.movieId + "/" + 1 ;
        console.log("Path of play store" + path)
        console.log(config.API_URL+path);
        axios.get(config.API_URL+path,{withCredentials: true})
        .then(function (response) {
          console.log("Message " + JSON.stringify(response));
          self.setState({movie_details:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    endVideo(e){
        let self = this;
        console.log(JSON.stringify(this.state.watchingDetails))
        let path = "/user-activities/store/" + self.state.watchingDetails.userId + "/" + self.state.watchingDetails.movieId + "/" + 2 ;
        console.log("Path of play store" + path)
        console.log(config.API_URL+path);
        axios.get(config.API_URL+path,{withCredentials: true})
        .then(function (response) {
          console.log("Message " + JSON.stringify(response));
          self.setState({movie_details:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });
    }

      render() {


        return (
        
          <div>
              <ReactPlayer
                ref={this.ref}
                playing
                controls
                width='1048px'
                height='600px'
                // onReady={(event) => {
                //     screenfull.request(ReactDOM.findDOMNode(this.player))
                // }}
                url={this.state.watchingDetails.url}
                onPlay={this.onPlay}
                onPause = {this.handlePause}
                onEnded = {this.onEnded}
                />
          </div>
        );
      }

    

}

export default withRouter(Video);