import React, {Component} from 'react';
import * as API from '../api/API';
// import {Checkbox} from 'react-select';
import Picky from 'react-picky';
import 'react-picky/dist/picky.css'; // Include CSS
import config from '../config.js';
import axios from 'axios';
import css from '../custom_css/moviefilter.css'


class MovieFilterYear extends Component {

    constructor(props) {
        super(props);
        this.state = {
              arrayValue: []

        };
        this.handleOnChangeYear = this.handleOnChangeYear.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        // console.log("Inside component will receive props");
        this.setState({ allMovies: nextProps.movies });  
      }

    handleOnChangeYear(value) {
        console.log("Val", value);
        this.setState({ arrayValue: value }, () => {
            this.props.filterMovie(this.state.arrayValue);
        });

    }


    render() {
        
        let minOffset = 0, maxOffset = 100;
        let thisYear = (new Date()).getFullYear();
        let allYears = [];
        for(let x = 0; x <= maxOffset; x++) {
            allYears.push(thisYear - x)
        }

        return (
            <div>
                  <Picky
                    value={this.state.arrayValue}
                    options={allYears}
                    onChange={this.handleOnChangeYear}
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

export default MovieFilterYear;