import React, {Component} from 'react';
import {Link, withRouter} from "react-router-dom";
import ReactDOM from 'react-dom';
import {Typeahead} from 'react-bootstrap-typeahead';
import admin_dshbd from "../custom_js/admin_dshbd.js";
import 'react-bootstrap-typeahead/css/Typeahead.css';
import AdminDashboard from './AdminDashboard.js';
import AdminNavBar from './AdminNavBar.js';
import config from '../config.js';
import axios from 'axios';
import {DataSearch} from '@appbaseio/reactivesearch';

class SearchMovies extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies:[],
            allmovies:[]
        };
        this.display_movies = this.display_movies.bind(this);
    }
    componentDidMount(){
        let self = this;
        axios.get(config.API_URL+'/admin/movies')
        .then(function (response) {
        //   console.log("Message " + JSON.stringify(response.data));
        //   this.setState({movies:response.data.message[0]});
          self.setState({movies:response.data.message, allmovies:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    display_movies()
    {
        // console.log("Inside display movie");
        // console.log(this.state.movies);
        const item = this.state.movies.map((movie,index) =>{

            return(
                <div className="row">
                    <div className="col-sm-2 text-white border">{movie.title}</div>
                    <div className="col-sm-6 text-white border">{movie.synopsis}</div>
                    <div className="col-sm-2 text-white border">{movie.actors}</div>
                    <div className="col-sm-2 text-white border">{movie.director}</div>
                </div>   
            )
        });
        return(
            <div>
                {item}
            </div>
        )
    }

    render(){
        return(
            <div className="adminvmBackgound">
                <AdminNavBar></AdminNavBar>
                <DataSearch
                    componentId="SearchSensor"
                    dataField={this.state.allmovies.title}
                    />
                <div className="row">
                        <div className="col-sm-8 offset-2">
                            <div className="input-group">
                                <input type="text" className="form-control rounded"
                                       label="search"
                                       // value={this.state.bid_period}
                                       onChange={(event) => {
                                        var searchVals = event.target.value.trim().split(" ");
                                        console.log("Last val : " + searchVals.length-1);
                                        console.log("Search Vals : " + searchVals);
                                        var movieS = [];
                                        this.state.allmovies.forEach(m => {
                                            searchVals.forEach(sv => {
                                                if(m.actors.indexOf(sv) > -1 || m.director.indexOf(sv) > -1 || m.synopsis.indexOf(sv) > -1 || m.title.indexOf(sv) > -1)
                                                {
                                                    movieS.push(m);
                                                }
                                            })
                                        });
                                        var new_arr = [];
                                        movieS.forEach((m) => {
                                            console.log("movie id : " + m.movieId);
                                            if(!new_arr.includes(m))
                                            {
                                                new_arr.push(m);
                                            }
                                        })
                                        console.log("Result Data:" + JSON.stringify(movieS));
                                        console.log("New Array : " + new_arr);
                                        this.setState({
                                                movies: new_arr
                                               })

                                        console.log("New array Length : " + new_arr.length);
                                        
                                       }}
                                    >
                                    </input>
                                </div>
                            </div>
                        </div>
                <div className = "row">
                    {this.display_movies()}
                </div>
            </div>
        )
    }
}

export default withRouter(SearchMovies);
