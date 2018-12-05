import React, {Component} from 'react';
import * as API from '../api/API';
// import {Checkbox} from 'react-select';
import Picky from 'react-picky';
import 'react-picky/dist/picky.css'; // Include CSS
import config from '../config.js';
import axios from 'axios';
import css from '../custom_css/moviefilter.css'
// import styled from 'styled-components';


class MovieFilterActors extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies : [],
              actor : [],
              value: null,
              arrayValue: [],
              allMovies : props.movies
        };
        this.setState({allMovies : this.props.movies});
        this.handleMovieChange = this.handleMovieChange.bind(this);
        this.handleOnChangeActors = this.handleOnChangeActors.bind(this);
        console.log("Inside Constructor : " + this.props.movies);
    }

    componentWillReceiveProps(nextProps) {
        // console.log("Inside component will receive props");
        this.setState({ allMovies: nextProps.movies });  
      }

    componentDidMount(){
        let self = this;
        axios.get(config.API_URL+'/movies/get-unique-movie-attributes')
        .then(function (response) {
          console.log("Attributes " + JSON.stringify(response.data.directors));
        //   this.setState({movies:response.data.message[0]});
        self.setState({actor:response.data.actors});
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    handleOnChangeActors(value) {
        console.log("Val", value);
        this.setState({ arrayValue: value }, () => {
            this.props.filterMovie(this.state.arrayValue);
        }); 

    }

    handleMovieChange() {
        this.setState({
            movies: this.props.movies.filter((item) => 1 != item.movieId)
          }, () => {
            this.props.filterMovie(this.state.movies);
          });
    }


    render() {
        
       

        return (
            <div>
                  <Picky
                    value={this.state.arrayValue}
                    options={this.state.actor}
                    onChange={this.handleOnChangeActors}
                    // open={true}
                    // valueKey="name"
                    // labelKey="id"   
                    multiple={true}
                    includeSelectAll={true}
                    // includeFilter={true}
                    // dropdownHeight="300px"
                    numberDisplayed = "2"
                    className = "b"
            />
            </div>
        );
    }
}

export default MovieFilterActors;