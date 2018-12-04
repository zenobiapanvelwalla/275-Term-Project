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

        let watchingDetails = {
            userId : 1,
            movieId : 1,
            url : "EXeTwQWrcwY?start=20s",
            checkpoint : '0.0',
            player : ''
        }

        this.state = {
            modalIsOpen: false,
            watchingDetails : watchingDetails
          };
        
        //   this.ref = this.ref.bind(this);
          this.endVideo = this.endVideo.bind(this);  
          
    }

    endVideo(){
        // console.log("Player " + JSON.stringify(this.state.player));
        // console.log("TTTTTIME : " + this.state.player.getCurrentTime())
        this.props.history.push('/customerdashboard');
    }

    ref = player => {
        this.player=player 
        }

      render() {
        const opts = {
            height: '390',
            width: '640',
            playerVars: { // https://developers.google.com/youtube/player_parameters
              autoplay: 1
            },
          };


        return (
        
          <div>
              <button className="btn btn-warning" onClick={this.endVideo}>Stop</button>
              <ReactPlayer
              ref={this.ref}
                playing
                controls
                url='https://www.youtube.com/watch?v=Mh5LY4Mz15o?start=20s'
                youtubeConfig={{ playerVars: { end: 40 }}}
                onPause = {(event) => {
                    // console.log("Current Time " + this.state.player.getCurrentTime());
                    console.log("Current Time " + this.player.getCurrentTime());
                    // console.log("Current Time " + ref.getCurrentTime());
                    // this.player.seekTo(parseFloat(event.target.value))
                    console.log(event);
                }}
                />
              <YouTube
                ref={this.ref}
                youtubeConfig={{
                    playerVars: {
                      start: 33
                    }
                  }}
                videoId={this.state.watchingDetails.url}  // defaults -> null
                // id={string}                       // defaults -> null
                // className={string}                // defaults -> null
                // containerClassName={string}       // defaults -> ''
                opts={opts}                        // defaults -> {}
                onReady={(event) => {
                    event.target.start = 10.0;
                }}                    // defaults -> noop
                onPlay={(event) => {
                    event.target.start = 10.0;
                }}                     // defaults -> noop
                onPause={(event) => {
                    
                    console.log("Pause at: " + event.target.getCurrentTime())
                }}                    // defaults -> noop
                onEnd={(event) => {
                    console.log("End at: " + event.target.getCurrentTime())
                }} 
                                   // defaults -> noop
                // onError={}                    // defaults -> noop
                // onStateChange={func}              // defaults -> noop
                // onPlaybackRateChange={func}       // defaults -> noop
                // onPlaybackQualityChange={func}    // defaults -> noop
                />
          </div>
        );
      }

    

}

export default withRouter(Video);