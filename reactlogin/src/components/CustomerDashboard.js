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
import MovieFilterActors from './MovieFilterActors';
import MovieFilterRating from './MovieFilterRating';
import MovieFilterGenre from './MovieFilterGenre';

class CustomerDashboard extends Component {

    constructor(props) {
        super(props);
        this.state = {
            temp:0,
            searchtext:[],
            movies:[],
            allmovies:[],
            beforefilterMovies:[],
            selectedDirectors:[],
            selectedActors:[],
            selectedGenre:[]
        };
    this.handleMovieChange = this.handleMovieChange.bind(this);
    this.handleActorChange = this.handleActorChange.bind(this);
    this.handleRatingChange = this.handleRatingChange.bind(this);
    this.handleGenreChange = this.handleGenreChange.bind(this);
    this.filterMovies = this.filterMovies.bind(this);
    }
    componentDidMount(){
        let self = this;
        axios.get(config.API_URL+'/movies')
        .then(function (response) {
        //   console.log("Message " + JSON.stringify(response.data.message));
        //   this.setState({movies:response.data.message[0]});
          self.setState({movies:response.data.message, allmovies:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    filterMovies(){

        this.setState({movies : this.state.beforefilterMovies}, () => {
        //director filter start
        if(this.state.movies.length != 0){
            if(this.state.selectedDirectors.length == 0){ }
            else{
                console.log("Inside Director Filter");
                console.log("Selected Dir Values :  " + this.state.selectedDirectors);
                console.log("Movies from parent " + this.state.beforefilterMovies);
                let showMovies = [];
                this.state.movies.map((movie) => {
                    if(this.state.selectedDirectors.includes(movie.director))
                    {
                        console.log("inside if");
                        showMovies.push(movie);
                    }
                })
                this.setState({
                    movies: showMovies
                    }, () => {
                            console.log("Movies after director filter");
                            console.log("****************After dir filter " + this.state.movies)

        //actor filter start
        if(this.state.movies.length != 0){
            if(this.state.selectedActors.length == 0){ }
            else{
                console.log("Inside Actor Filter");
                console.log("Selected Dir Values :  " + this.state.selectedActors);
                console.log("Movies from parent " + this.state.movies);
                let showMovies = [];
                this.state.movies.map((movie) => {
                    this.state.selectedActors.map((actor) => {
                          if(movie.actors.toLowerCase().includes(actor.toLowerCase())){
                            console.log("inside if");
                            if(!showMovies.includes(movie))
                            {
                                showMovies.push(movie);
                            }
                        }
                    } )
                })
                this.setState({
                    movies: showMovies
                }, () => {
                console.log("Movies after actor filter");
                });
            }
        }
        //Actor End
               
        })}}
        //director End

                })}

    handleMovieChange(data){
        console.log("E : " + data);
        console.log("It is called from child");
        this.setState({ selectedDirectors: data},() => {
            this.filterMovies();
            console.log("Selected Directors " + this.state.selectedDirectors);
        });

    }
    handleActorChange(data){
        console.log("E : " + data);
        console.log("It is called from child");
        this.setState({ selectedActors: data},() => {
            this.filterMovies();
            console.log("Selected Actors " + this.state.selectedActors);
        });
    }

    handleRatingChange(data){
        console.log("E : " + data);
        console.log("It is called from Rating child");
        this.setState({ selectedRating: data},() => {
            console.log("Selected Rating " + this.state.selectedRating);
        });
    }

    handleGenreChange(data){
        console.log("E : " + data);
        console.log("It is called from Rating child");
        this.setState({ selectedGenre: data},() => {
            console.log("Selected Genre " + this.state.selectedGenre);
            this.filterMovies();
        });
    }

  
    display_movies()
    {
        const item = this.state.movies.map((movie,index) =>{

            return(
                
                    <div className = "col-sm-4   ml-0 mr-0 border card-deck">
                    <div className="card">
                    <img onClick={() =>{
                        let path = "/moviedetail/" +  movie.movieId;
                        this.props.history.push(path);
                    }} class="card-img-top center" src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"} alt="Card image cap"></img>
                        {/* <img src={movie.imageUrl || "http://www.kickoff.com/chops/images/resized/large/no-image-found.jpg"}/> */}
                        <div className="">
                            <h5 className="" >{movie.title}</h5>
                            </div> 
                            </div>
                            </div>

            )
        });
        return(
            <div className="row">
                <div className="col-sm-2 mycontent-left">
                <br/>
                <div className="text-left text-small small pl-1"><b>Directors</b></div>
                <div><MovieFilter filterMovie = {this.handleMovieChange}/></div>
                <br/>
                <div  className="text-left text-small small pl-1">Actors</div>
                <div><MovieFilterActors filterMovie = {this.handleActorChange}/></div>
                <br/>
                <div  className="text-left text-small small pl-1">Rating</div>
                <div><MovieFilterRating filterMovie = {this.handleRatingChange}/></div>
                <br/>
                <div  className="text-left text-small small pl-1">Genre</div>
                <div><MovieFilterGenre filterMovie = {this.handleGenreChange}/></div>
                
                </div>
                <div className="col-sm-10">
                <div className="row"><input type="text" className="form-control rounded"
                                       label="search"
                                       placeholder = "Titles,peoples,genres"
                                       // value={this.state.bid_period}
                                       onChange={(event) => {
                                           //searchbar filter 
                                            var searchVals = event.target.value.trim().split(" ");
                                            console.log("Last val : " + searchVals.length-1); //remove last space
                                            console.log("Search Vals : " + searchVals); 
                                            var movieS = [];
                                            this.state.allmovies.forEach(m => {
                                                searchVals.forEach(sv => {
                                                    if(m.actors.toLowerCase().indexOf(sv.toLowerCase()) > -1 || m.director.toLowerCase().indexOf(sv.toLowerCase()) > -1 || m.synopsis.toLowerCase().indexOf(sv.toLowerCase()) > -1 || m.title.toLowerCase().indexOf(sv.toLowerCase()) > -1)
                                                    {
                                                        movieS.push(m);
                                                    }
                                                })
                                            });
                                            var new_arr = []; //logic to remove duplicate entries
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
                                                    movies: new_arr,
                                                    beforefilterMovies: new_arr
                                                },this.filterMovies())
                                            //console.log("New array Length : " + new_arr.length);

                                            
                                        }}
                                    >
                                    </input></div>

                    <div className="row">{item}</div>
                </div>
            </div>
        )
    }

    render(){
        return(
            <div> 
                <AdminNavBar></AdminNavBar>
                <div>
                    {this.display_movies()}
                </div>
            </div>
        )
    }
}

export default withRouter(CustomerDashboard);
