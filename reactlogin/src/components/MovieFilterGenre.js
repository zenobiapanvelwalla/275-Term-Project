import React, {Component} from 'react';
import * as API from '../api/API';
// import {Checkbox} from 'react-select';
import Picky from 'react-picky';
import 'react-picky/dist/picky.css'; // Include CSS
import config from '../config.js';
import axios from 'axios';
import css from '../custom_css/moviefilter.css'


class MovieFilterGenre extends Component {

    constructor(props) {
        super(props);
        this.state = {
              arrayValue: [],
              genre:[
                'Comedy',
                'Romantic',
                'Horror',
                'Action',
                'Drama',
                'Animation'
              ]

        };
        this.handleOnChangeGenre = this.handleOnChangeGenre.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        // console.log("Inside component will receive props");
        this.setState({ allMovies: nextProps.movies });  
      }

    handleOnChangeGenre(value) {
        console.log("Val", value);
        this.setState({ arrayValue: value }, () => {
            this.props.filterMovie(this.state.arrayValue);
        });

    }


    render() {
        
       

        return (
            <div>
                  <Picky
                    value={this.state.arrayValue}
                    options={this.state.genre}
                    onChange={this.handleOnChangeGenre}
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

export default MovieFilterGenre;