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
import MovieFilter from './MovieFilter'
import css from '../custom_css/movieList.css';

class CustomerDashboard extends Component {

    constructor(props) {
        super(props);
        this.state = {
            temp:0,
            movies:[]
        };
    
    }
    componentDidMount(){
        let self = this;
        axios.get(config.API_URL+'/admin/movies')
        .then(function (response) {
          console.log("Message " + JSON.stringify(response.data.message));
        //   this.setState({movies:response.data.message[0]});
          self.setState({movies:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    handleMovieChange(e){
        e.preventDefault();
        this.setState({ movies: e.target.value });
    }

  
    display_movies()
    {
        const item = this.state.movies.map((movie,index) =>{

            return(
                
                    <div className="col-sm-3">
                        <img src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"} height="200" className="d-block"  />
                        <div className="card-body">
                            <h5 className="card-title" >{movie.title}</h5>
                            </div> 
                    </div>
            )
        });
        return(
            <div className="row">
                <div className="col-sm-2"><MovieFilter filterMovie = {this.handleMovieChange}/></div>
                <div className="col-sm-9">
                    {item}
                </div>
            </div>
        )
    }

    render(){
        return(
            <div className="adminvmBackgound">
                <AdminNavBar></AdminNavBar>
                <div>
                    {this.display_movies()}
                </div>
            </div>
        )
    }
}

export default withRouter(CustomerDashboard);
