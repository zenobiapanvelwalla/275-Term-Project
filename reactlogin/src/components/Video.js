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

class Video extends Component {

    constructor(props) {
        super(props);
        this.state = {
           
        }
    }

    componentDidMount(){
        
    }

    render(){
        return (
                            <div className="action">
                            <YouTube
                            videoId="ujQ-fCUH9TY"                  // defaults -> null
                            //   id={}                       // defaults -> null
                            //   className={}                // defaults -> null
                            //   containerClassName={}       // defaults -> ''
                            //   opts={}                        // defaults -> {}
                            //   onReady={}                    // defaults -> noop
                            //   onPlay={}                     // defaults -> noop  
                            onPause={(event) => {
                                    console.log(event.target.getCurrentTime());
                                }
                                }                    // defaults -> noop
                            //   onEnd={}                      // defaults -> noop
                            //   onError={}                    // defaults -> noop
                            //   onStateChange={}              // defaults -> noop
                            //   onPlaybackRateChange={}       // defaults -> noop
                            //   onPlaybackQualityChange={}    // defaults -> noop
                            />
                            </div>
              
        )
    }

}

export default withRouter(Video);