import React, {Component} from 'react';
import * as API from '../api/API';
// import {Checkbox} from 'react-select';
import Picky from 'react-picky';
import 'react-picky/dist/picky.css'; // Include CSS
import config from '../config.js';
import axios from 'axios';

class MovieFilter extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies : [],
            options : [
                { id: 'Thing 1', name: 1},
                { id: 'Thing 2', name: 2},
              ],
              value: null,
              arrayValue: []
        };
        this.selectOption = this.selectOption.bind(this);
        this.selectMultipleOption = this.selectMultipleOption.bind(this);
    }

    
    selectOption(value) {
        console.log("Vals", value);
        this.setState({ value });
    }

    selectMultipleOption(value) {
        console.log("Val", value);
        this.setState({ arrayValue: value });
        console.log(this.props.filterMovie);  
        let self = this;
        axios.get(config.API_URL+'/admin/movies')
        .then(function (response) {
          console.log("Message " + JSON.stringify(response.data.message));
          self.setState({movies:response.data.message});
          self.setState({
            movies: self.state.movies.filter((item) => 1 != item.movieId)
          });
          this.props.filterMovie(this.state.movies);
        })
        .catch(function (error) {
          console.log(error);
        });  
    }

    render() {
        return (
            <div>
                  <Picky
                    value={this.state.arrayValue}
                    options={this.state.options}
                    onChange={this.selectMultipleOption}
                    open={true}
                    valueKey="name"
                    labelKey="id"
                    multiple={true}
                    includeSelectAll={true}
                    includeFilter={true}
                    dropdownHeight={600}
            />
            </div>
        );
    }
}

export default MovieFilter;