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
import style from '../custom_css/admin_dashboard.css';
import MovieFilterActors from './MovieFilterActors';
import MovieFilterRating from './MovieFilterRating';
import MovieFilterGenre from './MovieFilterGenre';
import MovieFilterYear from './MovieFilterYear';
import MovieFilterMPAA from './MovieFilterMPAA';


import NavBar from './NavBar';

var imagecard = {
    margin: "20px !important" 
}

class CustomerDashboard extends Component {

    constructor(props) {
        super(props);
        this.state = {
            temp:0,
            searchtext:[],
            movies:[],
            allmovies:[],
            currentPage: 1,
            ItemPerPage: 10,
            beforefilterMovies:[],
            selectedDirectors:[],
            selectedActors:[],
            selectedGenre:[],
            selectedYear:[],
            selectedMPAA:[],
            selectedRating:0,
        };
    this.handleMovieChange = this.handleMovieChange.bind(this);
    this.handleActorChange = this.handleActorChange.bind(this);
    this.handleRatingChange = this.handleRatingChange.bind(this);
    this.handleGenreChange = this.handleGenreChange.bind(this);
    this.handleYearChange = this.handleYearChange.bind(this);
    this.handleMPAAChange = this.handleMPAAChange.bind(this);
    this.filterMovies = this.filterMovies.bind(this);
    this.handleClick = this.handleClick.bind(this);
    }
    componentDidMount(){
        let self = this;
        let path = "";
        if(localStorage.getItem('role') == 'ADMIN')
        {
            path = config.API_URL+'/admin/movies'
        }
        else{
            path = config.API_URL+'/get-movies-for-customer/'
       }
        axios.get(path,{withCredentials: true})
        .then(function (response) {
        console.log("Movies " + JSON.stringify(response.data.message));
        //   this.setState({movies:response.data.message[0]});
          self.setState({movies:response.data.message, allmovies:response.data.message, beforefilterMovies:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    handleClick(event) {
        this.setState({
            currentPage: Number(event.target.id)
        });
    }

    filterMovies(){

        this.setState({movies : this.state.beforefilterMovies}, () => {
        //director filter start
        if(this.state.movies.length != 0){
            let showMovies = [];
            if(this.state.selectedDirectors.length != 0){
                console.log("Inside Director Filter");
                // console.log("Selected Dir Values :  " + this.state.selectedDirectors);
                // console.log("Movies from parent " + this.state.beforefilterMovies);
                this.state.movies.map((movie) => {
                    if(this.state.selectedDirectors.includes(movie.director))
                    {
                        console.log("inside if");
                        showMovies.push(movie);
                    }
                })
             }
            else{
                this.state.movies.map((movie) => {
                        showMovies.push(movie);
                    })
            }

            // console.log("Filter after director : " + showMovies);

            //actors search
            if(this.state.selectedActors.length != 0){
                console.log("Inside Actor Filter");
                // console.log("Selected Dir Values :  " + this.state.selectedActors);
                // console.log("Movies from parent " + this.state.movies);
                var moviesActor = [];
                showMovies.map((movie) => {
                    var count = this.state.selectedActors.length;
                    this.state.selectedActors.map((actor) => {
                        if(movie.actors.toLowerCase().includes(actor.toLowerCase())){
                            if(!moviesActor.includes(movie))
                                {moviesActor.push(movie);}
                        }
                    })
                })
                showMovies = moviesActor;
            }

            //Rating search
            if(this.state.selectedRating >= 0){
                console.log("Inside Actor Filter");
                // console.log("Selected Dir Values :  " + this.state.selectedActors);
                // console.log("Movies from parent " + this.state.movies);
                var moviesRating = [];
                showMovies.map((movie) => {
                    if(this.state.selectedRating <= movie.avgStarRating)
                    {
                        moviesRating.push(movie);
                    }
                })
                showMovies = moviesRating;
            }

            console.log("Before Genre Search")

            //Genre search
            if(this.state.selectedGenre.length != 0){
                console.log("Inside Genre Filter");
                // console.log("Selected Dir Values :  " + this.state.selectedActors);
                // console.log("Movies from parent " + this.state.movies);
                var moviesGenre = [];
                showMovies.map((movie) => {
                    if(this.state.selectedGenre.includes(movie.genre))
                    {
                        moviesGenre.push(movie);
                    }
                })
                showMovies = moviesGenre;
            }

            //Year search
            if(this.state.selectedYear.length != 0){
                console.log("Inside Genre Filter");
                // console.log("Selected Dir Values :  " + this.state.selectedActors);
                // console.log("Movies from parent " + this.state.movies);
                var moviesYear = [];
                console.log("SELECTED YEARS" + this.state.selectedYear);
                showMovies.map((movie) => {
                    console.log("YEAR" + (movie.year));
                    if(this.state.selectedYear.includes(parseInt(movie.year)))
                    {
                        moviesYear.push(movie);
                    }
                })
                showMovies = moviesYear;
            }

            //MPAA search
            if(this.state.selectedMPAA.length != 0){
                console.log("Inside Genre Filter");
                // console.log("Selected Dir Values :  " + this.state.selectedActors);
                // console.log("Movies from parent " + this.state.movies);
                var moviesMPAA = [];
                console.log("SELECTED MPAA" + this.state.selectedMPAA);
                showMovies.map((movie) => {
                    console.log("MPAAAAAAAaaaaa" + (movie.rating));
                    if(this.state.selectedMPAA.includes(movie.rating))
                    {
                        moviesMPAA.push(movie);
                    }
                })
                showMovies = moviesMPAA;
            }


            this.setState({movies:showMovies})
        }
        

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
            this.filterMovies();
            console.log("Selected Rating " + this.state.selectedRating);
        });
    }

    handleGenreChange(data){
        console.log("E : " + data);
        console.log("It is called from Rating child");
        this.setState({ selectedGenre: data},() => {
            this.filterMovies();
            console.log("Selected Genre " + this.state.selectedGenre);
        });
    }

    handleYearChange(data){
        console.log("E : " + data);
        console.log("It is called from Rating child");
        this.setState({ selectedYear: data},() => {
            this.filterMovies();
            console.log("Selected Year " + this.state.selectedYear);
        });
    }

    handleMPAAChange(data){
        console.log("E : " + data);
        console.log("It is called from Rating child");
        this.setState({ selectedMPAA: data},() => {
            this.filterMovies();
            console.log("Selected MPAA " + this.state.selectedMPAA);
        });
    }
  
    display_movies()
    {

        const { movies, currentPage, ItemPerPage } = this.state;
        const indexOfLastTodo = currentPage * ItemPerPage;
        const indexOfFirstTodo = indexOfLastTodo - ItemPerPage;
        const currentTodos = movies.slice(indexOfFirstTodo, indexOfLastTodo);

        const item = currentTodos.map((movie,index) =>{

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

        const pageNumbers = [];
        for (let i = 1; i <= Math.ceil(movies.length / ItemPerPage); i++) {
            pageNumbers.push(i);
        }
        const renderPageNumbers = pageNumbers.map(number => {
            return (
                <li
                    key={number}
                    id={number}
                    onClick={this.handleClick}
                    className=" page-link"
                >
                    {number}
                </li>
            );
        });


        return(
            <div className="row">
                <div className="col-sm-2 mycontent-left">
                <br/>
                <div className="text-left text-small small pl-1"><b>Directors</b></div>
                <div><MovieFilter filterMovie = {this.handleMovieChange}/></div>
                <br/>
                <div  className="text-left text-small small pl-1"><b>Actors</b></div>
                <div><MovieFilterActors filterMovie = {this.handleActorChange}/></div>
                <br/>
                <div  className="text-left text-small small pl-1"><b>Rating</b></div>
                <div><MovieFilterRating filterMovie = {this.handleRatingChange}/></div>
                <br/>
                <div  className="text-left text-small small pl-1"><b>Genre</b></div>
                <div><MovieFilterGenre filterMovie = {this.handleGenreChange}/></div>
                <br/>
                <div  className="text-left text-small small pl-1"><b>Year</b></div>
                <div><MovieFilterYear filterMovie = {this.handleYearChange}/></div>
                <br/>
                <div  className="text-left text-small small pl-1"><b>MPAA Rating</b></div>
                <div><MovieFilterMPAA filterMovie = {this.handleMPAAChange}/></div>
                
                </div>
                <div className="col-sm-10">
                <div className="row col-sm-12">
                                        <input type="text" className="mt-3  form-control"
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
                    <div className="row">
                        <ul id="page-numbers" className="pagination justify-content-center">
                            {renderPageNumbers}
                        </ul>
                    </div>
                </div>
            </div>
        )
    }

    render(){
        return(
            <div> 
                {localStorage.getItem('role') == 'ADMIN' ? <AdminNavBar/> : <NavBar></NavBar>}
                <div>
                    {this.display_movies()}
                </div>
            </div>
        )
    }
}

export default withRouter(CustomerDashboard);
