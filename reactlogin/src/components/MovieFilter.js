import React, {Component} from 'react';
import * as API from '../api/API';
// import {Checkbox} from 'react-select';
import Picky from 'react-picky';
import 'react-picky/dist/picky.css'; // Include CSS
import config from '../config.js';
import axios from 'axios';
import css from '../custom_css/moviefilter.css'
// import styled from 'styled-components';


class MovieFilter extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies : [],
            options : [
                { id: 'Thing 1', name: 1},
                { id: 'Thing 2', name: 2},
              ],
              director : [],
              value: null,
              arrayValue: [],
              allMovies : props.movies
        };
        this.setState({allMovies : this.props.movies});
        this.handleMovieChange = this.handleMovieChange.bind(this);
        this.handleOnChangeDirector = this.handleOnChangeDirector.bind(this);
        console.log("Inside Constructor : " + this.props.movies);
    }

    componentWillReceiveProps(nextProps) {
        console.log("Inside component will receive props");
        this.setState({ allMovies: nextProps.movies });  
      }

    componentDidMount(){
        let self = this;
        axios.get(config.API_URL+'/movies/get-unique-movie-attributes')
        .then(function (response) {
          console.log("Attributes " + JSON.stringify(response.data.directors));
        //   this.setState({movies:response.data.message[0]});
        self.setState({director:response.data.directors});
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    handleOnChangeDirector(value) {
        // console.log("Val", value);
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
            <div className="h">
                  <Picky
                    value={this.state.arrayValue}
                    options={this.state.director}
                    onChange={this.handleOnChangeDirector}
                    // open={true}
                    // valueKey="name"
                    // labelKey="id"   
                    multiple={true}
                    includeSelectAll={true}
                    // keepOpen={true}
                    // includeFilter={true}
                    numberDisplayed = "2"
                    className = "b"
            />
            </div>
        );
    }
}

export default MovieFilter;