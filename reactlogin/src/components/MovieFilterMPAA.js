import React, {Component} from 'react';
import * as API from '../api/API';
// import {Checkbox} from 'react-select';
import Picky from 'react-picky';
import 'react-picky/dist/picky.css'; // Include CSS
import config from '../config.js';
import axios from 'axios';
import css from '../custom_css/moviefilter.css'


class MovieFilterMPAA extends Component {

    constructor(props) {
        super(props);
        this.state = {
              arrayValue: [],
              MPAARating:["PG - Parental Guidance Suggested",
                        "G - General Audiences",
                            "PG-13 - Parents Strongly Cautioned",
                            "R -  Restricted","NC-17 - Adults Only","UR - Unrated"]

        };
        this.handleOnChangeMPAA = this.handleOnChangeMPAA.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        // console.log("Inside component will receive props");
        this.setState({ allMovies: nextProps.movies });  
      }

    handleOnChangeMPAA(value) {
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
                    options={this.state.MPAARating}
                    onChange={this.handleOnChangeMPAA}
                    // open={true}
                    // valueKey="name"
                    // labelKey="id"   
                    multiple={true}
                    includeSelectAll={true}
                    // includeFilter={true}
                    // dropdownHeight="300px"
                    numberDisplayed = "2"
                    className = "c"
            />
            </div>
        );
    }
}

export default MovieFilterMPAA;