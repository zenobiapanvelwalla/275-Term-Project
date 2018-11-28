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

class AdminViewMovies extends Component {

    constructor(props) {
        super(props);
        this.state = {
            temp:0,
            movies:[]
        };
        this.handleDelete = this.handleDelete.bind(this);
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

    handleDelete(id){
        console.log("Inside handle delete");
        console.log(id);
        let self = this;
        let path = '/movies/delete/' + id.toString();
        axios.delete(config.API_URL+path)
        .then(function (response) {
          console.log("Message " + JSON.stringify(response.data.message));
        //   this.setState({movies:response.data.message[0]});
        self.setState({
            movies: self.state.movies.filter((item) => id != item.movieId)
          });
        })
        .catch(function (error) {
          console.log(error);
        });

    }

    display_movies()
    {
        const item = this.state.movies.map((movie,index) =>{

            return(
                <div className="col-md-4 ">
                    <div className="card mt-2 cardMoive">
                        <img src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"} height="200" className="d-block"  />
                        <div className="card-body mb">
                            <h5 className="card-title">{movie.title}</h5>
                            <button type="button" className="btn btn-secondary mb">Update</button>
                            <button type="button" className="btn btn-danger mb" onClick={() => {this.handleDelete(movie.movieId)}}>Delete</button>
                        </div> 
                    </div>
                </div>
            )
        });
        return(
            <div className="row">
                {item}
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

export default withRouter(AdminViewMovies);
