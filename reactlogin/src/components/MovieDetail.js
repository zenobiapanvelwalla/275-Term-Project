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
import Video from "../components/Video";

class MovieDetail extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movie_details:''
        }
    }

    componentDidMount(){
        let self = this;
        let path = "/movies/" + '1';
        axios.get(config.API_URL+path)
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

    render(){
        return (
            <div className="moviedetail_body">
            <NavBar></NavBar>
            <div>
            <div className="card">
                <div className="container-fliud">
                    <div className="wrapper row">
                        <div className="preview col-md-6">
                            <div className="preview-pic tab-content">
                              <div className="tab-pane active" id="pic-1"><img src={this.state.movie_details.imageUrl} /></div>
                            </div>
                        </div>  
                        <div className="details col-md-6">
                            <h3 className="product-title">{this.state.movie_details.title}</h3>
                            <div className="rating">
                                <div className="stars">
                                    <span className="fa fa-star checked"></span>
                                    <span className="fa fa-star checked"></span>
                                    <span className="fa fa-star checked"></span>
                                    <span className="fa fa-star"></span>
                                    <span className="fa fa-star"></span>
                                </div>
                                <span className="review-no">41 reviews</span>
                            </div>
                            <p className="product-description">{this.state.movie_details.synopsis}</p>
                            <h4 className="price">current price: <span>$180</span></h4>
                            <p className="vote"><strong>91%</strong> of buyers enjoyed this product! <strong>(87 votes)</strong></p>
                            <h5 className="sizes">sizes:
                                <span className="size" data-toggle="tooltip" title="small">s</span>
                                <span className="size" data-toggle="tooltip" title="medium">m</span>
                                <span className="size" data-toggle="tooltip" title="large">l</span>
                                <span className="size" data-toggle="tooltip" title="xtra large">xl</span>
                            </h5>
                            <h5 className="colors">colors:
                                <span className="color orange not-available" data-toggle="tooltip" title="Not In store"></span>
                                <span className="color green"></span>
                                <span className="color blue"></span>
                            </h5>
                            <div className="action">
                                <Video videoDetails={this.handleVideoDetails}></Video>
                                <button className="add-to-cart btn btn-default" type="button" onClick={(event) => {
                                    this.handlePlay(event)
                                }}><span className="fa fa-play">Play</span></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        )
    }

}

export default withRouter(MovieDetail);