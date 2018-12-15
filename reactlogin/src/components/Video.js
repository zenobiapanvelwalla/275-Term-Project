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
import css from '../custom_css/movieList.css';
import style from '../custom_css/admin_dashboard.css';
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
            watchingDetails : '',
            directors:[],
            actors:[],
            genre:[],
          };
        
        //   this.ref = this.ref.bind(this);
          this.endVideo = this.endVideo.bind(this); 
          this.handlePause = this.handlePause.bind(this); 
          this.onPlay = this.onPlay.bind(this); 

          console.log("Inside Video" + this.state.watchingDetails)
    }

    componentDidMount(){


      ///movies/recommend-movies/{movieId}
      

        console.log("Inside" + JSON.stringify(localStorage.getItem('watchingDetails')))

        let abc = JSON.parse(localStorage.getItem('watchingDetails'));

        this.setState({watchingDetails:abc},() => {
               
          let self = this;
          axios.get(config.API_URL+'/movies/recommend-movies/' + this.state.watchingDetails.movieId)
          .then(function (response) {
            console.log("Attributes " + JSON.stringify(response.data.basedOnDirector));
          //   this.setState({movies:response.data.message[0]});
          self.setState({directors:response.data.basedOnDirector, actors:response.data.basedOnActor, genre:response.data.basedOnGenre});
          })
          .catch(function (error) {
            console.log(error);
          });
          }
       
          )

        console.log("Watching details" + JSON.stringify(abc));
    }


    onPlay(e){
        let self = this;
        console.log(JSON.stringify(this.state.watchingDetails))
        if(localStorage.getItem('role') != 'ADMIN'){
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
  }

    handlePause(e){
        let self = this;
        console.log(JSON.stringify(this.state.watchingDetails))
        if(localStorage.getItem('role') != 'ADMIN'){
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
    }

    endVideo(e){
        let self = this;
        console.log(JSON.stringify(this.state.watchingDetails))
        if(localStorage.getItem('role') != 'ADMIN'){
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
    }

      render() {

        var imagecard = {
          margin: "50px !important" 
        }

        const display_movies_d = this.state.directors.map((movie,index) =>{

          return(
              
                  <div className = "col-sm-4 ml-0 mr-0 card-deck">
                  <div className="pr-3 pt-2 view overlay zoom">
                  <img onClick={() =>{
                      let path = "/moviedetail/" +  movie.movieId;
                      localStorage.setItem("movie_id",movie.movieId)
                      localStorage.setItem("paymentType",movie.availability)
                      this.props.history.push(path);
                  }} className=" card-img-top center mt-2 img-fluid" style={imagecard} src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"} alt="Card image cap"></img>
                      {/* <img src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"}/> */}
                      <div className="">
                          <h5 className="" >{movie.title}</h5>
                          </div> 
                          </div>
                          </div>

          )
      });
      const display_movies_a = this.state.actors.map((movie,index) =>{

        return(
            
                <div className = "col-sm-4 ml-0 mr-0 card-deck">
                <div className="pr-3 pt-2 view overlay zoom">
                <img onClick={() =>{
                    let path = "/moviedetail/" +  movie.movieId;
                    localStorage.setItem("movie_id",movie.movieId)
                    localStorage.setItem("paymentType",movie.availability)
                    this.props.history.push(path);
                }} className="card-img-top center mt-2 img-fluid" style={imagecard} src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"} alt="Card image cap"></img>
                    {/* <img src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"}/> */}
                    <div className="">
                        <h5 className="" >{movie.title}</h5>
                        </div> 
                        </div>
                        </div>

        )
    });
    const display_movies_g = this.state.genre.map((movie,index) =>{

      return(
          
              <div className = "col-sm-4 ml-0 mr-0 card-deck">
              <div className="pr-3 pt-2 view overlay zoom">
              <img onClick={() =>{
                  let path = "/moviedetail/" +  movie.movieId;
                  localStorage.setItem("movie_id",movie.movieId)
                  localStorage.setItem("paymentType",movie.availability)
                  this.props.history.push(path);
              }} className="card-img-top center mt-2 img-fluid" style={imagecard} src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"} alt="Card image cap"></img>
                  {/* <img src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"}/> */}
                  <div className="">
                      <h5 className="" >{movie.title}</h5>
                      </div> 
                      </div>
                      </div>

      )
  });


        return (
        
          <div>
            <div className="row">
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

                <br />
                <br />
                <br />
                <div className="row badge badge-primary"><h3>Based on the Directors You like</h3></div>
                <div className="row">{display_movies_d}</div>
                <div className="row badge badge-primary"><h3>Based on the Actors You like</h3></div>
                <div className="row ml-2">  No movies available. Please give ratings for more effective recommendation</div>
                <div className="row badge badge-primary"><h3>Based on the Genres You like</h3></div>
                <div className="row">{display_movies_g}</div>
          </div>




        );
      }

    

}

export default withRouter(Video);