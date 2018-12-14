import React,{Component} from 'react';
import {withRouter} from 'react-router-dom';
import config from '../config.js';
import axios from 'axios';
import AdminNavBar from './AdminNavBar';
import customer from "../custom_css/browse_customers.css";

class HighlyRatedMovies extends Component{
    state = {
        list:true,
        movieHistory:false,
        topTen:false,
        highlyRatedList:[],
        topTenList:[],  //topTenList will be populated depending on what is selected from the dropdown
    }
    constructor(props){
        super(props);
    }
    componentWillMount(){
        console.log("Inside higly rated movies page")
        let self = this;
        //get top ten users in last 24 hours
        axios.get(config.API_URL+"/movies/highly-rated",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log("Higly rated movies : " + JSON.stringify(response.data.message));
          self.setState({ highlyRatedList:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });


         //get top ten users in last 24 hours
         axios.get(config.API_URL+"/movies/top-ten/30",{withCredentials: true})
         .then(function (response) {
           //console.log("Message " + JSON.stringify(response));
           console.log("Top 10 movies : " + JSON.stringify(response.data.message));
           self.setState({ topTenList:response.data.message});
         })
         .catch(function (error) {
           console.log(error);
         });
    }

    render(){
    return (
        <div className="customer-container">
            <AdminNavBar/>
            <div className="row customer-row">
            <div className="col-md-3 ">
                <div className="list-group ">
                    <a href="#"  onClick={(event) => {
                                            this.setState({list:true, topTen:false});
                                            }} className={this.state.list==true?'list-group-item list-group-item-action active':'list-group-item list-group-item-action'}>Highly Rated Movies</a>
                    <a href="#"  onClick={(event) => {
                                            this.setState({list:false, topTen:true});
                                        }} className={this.state.topTen==true?"list-group-item list-group-item-action active":"list-group-item list-group-item-action"}>Top 10</a>
                </div> 
		    </div>
            {this.state.list==true?
            <div className="card-default cardBC" id="users">
                <div className="card-header"><b>Highly Rated Movies</b></div>
                <div className="table-responsive">
                <table className="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Movie Title</th>
                            <th>Synopsis</th>
                            <th>Genre</th>
                            <th>Actors</th>
                            <th>
            
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.highlyRatedList.map(movie =>
                        <tr 
                            key={movie.id}> 
                            <td>{movie.title}</td>
                            <td>{movie.synopsis}</td>
                            <td>{movie.genre}</td>
                            <td>{movie.actors}</td>
                            <td><a href="#history" onClick={() =>{
                                    let path = "/moviedetail/" +  movie.movieId;
                                    localStorage.setItem("movie_id",movie.movieId)
                                    localStorage.setItem("paymentType",movie.availability)
                                    this.props.history.push(path);
                                }}>Details</a></td>
                            
                        </tr>
                    )}
                    </tbody>
                </table>
                </div>
            </div>
            :""}

            {this.state.topTen==true?
                <div className="card-default cardBC" id="users">
                    <div className="card-header"><b>Top 10 Movies</b></div>
                    <div className="table-responsive">
                    <table className="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Movie Title</th>
                                <th>Synopsis</th>
                                <th>Genre</th>
                                <th>Actors</th>
                                <th>
                
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.state.topTenList.map(movie =>
                            <tr 
                                key={movie.id}> 
                                <td>{movie.title}</td>
                                <td>{movie.synopsis}</td>
                                <td>{movie.genre}</td>
                                <td>{movie.actors}</td>
                                <td><a href="#history" onClick={() =>{
                                        let path = "/moviedetail/" +  movie.movieId;
                                        localStorage.setItem("movie_id",movie.movieId)
                                        localStorage.setItem("paymentType",movie.availability)
                                        this.props.history.push(path);
                                    }}>Details</a></td>
                                
                            </tr>
                        )}
                        </tbody>
                    </table>
                    </div>
                </div>
                :""}

            </div>
        </div>
    )};
}

export default withRouter(HighlyRatedMovies);