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
import ReactStars  from 'react-stars';
import Video from "../components/Video";

class MovieDetail extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movie_details:'',
            giveReview: false,
            rating:''
        }
        this.watchMovie = this.watchMovie.bind(this);
    }

    componentWillMount(){
        console.log("Inside movie detail fetching")
        let self = this;
        let path = "/movies/" + this.props.match.params.movieId;
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

    handleVideoDetails = (details) => {
        this.setState({videoDetails:details})            
    }

    watchMovie(event){
        let watchingDetails = {
            userId : localStorage.getItem('user_id'),
            movieId : this.props.match.params.movieId,
            url : this.state.movie_details.movieUrl,
            checkpoint : 0
        }
        localStorage.setItem('watchingDetails',watchingDetails);
        this.props.history.push('/video');
    }

    render(){
        return (
            <div className="moviedetail_body">
                <NavBar></NavBar>
                <div className = "row">
                    <div className = "col-md-6 sectionM" id="pic-1"><img className="imageSection" src={this.state.movie_details.imageUrl } /></div>
                    <div className = "col-md-6 sectionM">

                        {/* title */}
                        <div className="row">
                            <h3 className="text-left">{this.state.movie_details.title + "(" +  this.state.movie_details.year + ")"}</h3>
                        </div>

                        <div className="row">
                            <ReactStars
                                    value = {this.state.movie_details.avgStarRating}
                                    count={5}
                                    size={20}
                                    color2={'#ffd700'}
                                    edit = {false} 
                            />
                        </div>

                        <div className="row">
                            {/* Add Review */}
                            {localStorage.getItem('role') == 'ADMIN' ? null : 
                            <div>
                            <button type="button" onClick={(event) => {this.setState({giveReview:true})}} className="btn btn-link btn-sm ml-0 pl-0 "><p className = "btnLink"><u>Add Review</u></p></button>
                            <button type="button" onClick={(event) => {this.setState({giveReview:true})}} className="btn btn-link btn-sm ml-0 pl-0 ml-0 pl-0 "><p className = "btnLink"><u>See Reviews</u></p></button>
                            </div>}
                        </div>

                        <div className="row">
                            <div className="col-md-6">
                            {/* if customer and started watching reviews */}
                            { !this.state.giveReview ? null : 
                            <div className="border reviewSection text-left pt-2 pl-2 pr-2 pb-2">   
                                <b>Add Rating</b>
                                <ReactStars
                                value = {this.state.rating}
                                count={5}
                                onChange={(event)=>{
                                    this.setState({rating : event})
                                    console.log("Rating : " + event);
                                }}
                                size={24}
                                color2={'#ffd700'} />
                                <textarea placeholder="Add Review" ref="reviewText" className="form-control"  rows="3"></textarea>  
                                <button className="btn btn-outline-primary btn-sm mt-1" onClick={(event) => {
                                    if(this.state.rating <= 0)
                                    {

                                    }
                                    else
                                    {
                                        var payload = {
                                            userId : localStorage.getItem('user_id'),
                                            userName : localStorage.getItem('user_details').displayName,
                                            movieId : this.props.match.params.movieId ,
                                            starRating : this.state.rating,
                                            reviewText : this.refs.reviewText.value
                                        }
                                        console.log("Review : " + JSON.stringify(payload));
                                        let self = this;
                                        axios.post(config.API_URL+'/movies/add-review',payload)
                                        .then(function (response) {
                                        console.log("Attributes " + JSON.stringify(response.data));
                                        self.setState({actor:response.data});
                                        })
                                        .catch(function (error) {
                                        console.log(error);
                                        });
                                    }
                                }}>Submit</button>
                                <button className="btn btn-outline-primary btn-sm mt-1" onClick={(event) => {this.setState({giveReview:false})}}>Cancel</button>
                            </div>}
                        </div>
                    </div>

                    <div className="row">
                        <p className="small text-left detailText">{this.state.movie_details.synopsis}</p>
                    </div>
                    <div className="row detailText">
                                <b>Director: </b><p>{this.state.movie_details.director}</p>
                    </div>
                    <div className="row detailText">
                                <b>Cast: </b><p>{this.state.movie_details.actors}</p>
                    </div>
                    <div className="row">
                        <button type="button" onClick={this.watchMovie} className="btn btn-danger">Watch Now</button>     
                    </div>

                    <div className="row">
                        <div className="line"></div>
                    </div>

                    </div>
                </div>

                {/* //reviews section */}
                <div className="row"></div>
            </div>

        )
    }

}

export default withRouter(MovieDetail);