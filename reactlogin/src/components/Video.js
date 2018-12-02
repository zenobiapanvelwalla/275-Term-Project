// import React, {Component} from 'react';
// import {Link, withRouter} from "react-router-dom";
// import ReactDOM from 'react-dom';
// import {Typeahead} from 'react-bootstrap-typeahead';
// import admin_dshbd from "../custom_js/admin_dshbd.js";
// import 'react-bootstrap-typeahead/css/Typeahead.css';
// import NavBar from './NavBar';
// import AdminDashboard from './AdminDashboard.js';
// import AdminNavBar from './AdminNavBar.js';
// import config from '../config.js';
// import axios from 'axios';
// import details from '../custom_css/movie_detail.css';
// // import YouTube from 'react-youtube';
// import Modal from 'react-modal';

// const customStyles = {
//     content : {
//       top                   : '0%',
//       left                  : '0%',
//       right                 : '0%',
//       bottom                : '0%',
//       margin_right: '-50%',
//       transform             : 'translate(-50%, -50%)'
//     }
//   };

// class Video extends Component {

//     constructor(props) {
//         super(props);
//         this.state = {
//             modalIsOpen: false
//           };
          
//     }

//       render() {
//         const opts = {
//             height: '390',
//             width: '640',
//             playerVars: { // https://developers.google.com/youtube/player_parameters
//               autoplay: 1
//             },
//           };
//         return (
//           <div>
//               <YouTube
//                 videoId="NV6s5McYB6g"                  // defaults -> null
//                 // id={string}                       // defaults -> null
//                 // className={string}                // defaults -> null
//                 // containerClassName={string}       // defaults -> ''
//                 opts={opts}                        // defaults -> {}
//                 // onReady={func}                    // defaults -> noop
//                 // onPlay={func}                     // defaults -> noop
//                 onPause={(event) => {
//                     console.log("Pause at: " + event.target.getCurrentTime())
//                 }}                    // defaults -> noop
//                 onEnd={(event) => {
//                     console.log("End at: " + event.target.getCurrentTime())
//                 }} 
//                                    // defaults -> noop
//                 // onError={func}                    // defaults -> noop
//                 // onStateChange={func}              // defaults -> noop
//                 // onPlaybackRateChange={func}       // defaults -> noop
//                 // onPlaybackQualityChange={func}    // defaults -> noop
//                 />
//           </div>
//         );
//       }

    

// }

// export default withRouter(Video);