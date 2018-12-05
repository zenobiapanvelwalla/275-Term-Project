import React, {Component} from 'react';
import * as API from '../api/API';
// import {Checkbox} from 'react-select';
import Picky from 'react-picky';
import 'react-picky/dist/picky.css'; // Include CSS
import config from '../config.js';
import axios from 'axios';
import css from '../custom_css/moviefilter.css'
import ReactStars  from 'react-stars';


class MovieFilterRating extends Component {

    constructor(props) {
        super(props);
        // this.state = {
        //     movies : [],
        //       actor : [],
        //       value: null,
        //       arrayValue: [],
        //       allMovies : props.movies
        // };
        // this.setState({allMovies : this.props.movies});
        this.handleOnChangeRating1 = this.handleOnChangeRating1.bind(this);
        this.handleOnChangeRating2 = this.handleOnChangeRating2.bind(this);
        this.handleOnChangeRating3 = this.handleOnChangeRating3.bind(this);
        this.handleOnChangeRating4 = this.handleOnChangeRating4.bind(this);
        this.handleOnChangeRating0 = this.handleOnChangeRating0.bind(this);

        // console.log("Inside Constructor : " + this.props.movies);
    }

    // componentWillReceiveProps(nextProps) {
    //     console.log("Inside component will receive props");
    //     this.setState({ allMovies: nextProps.movies });  
    //   }

    handleOnChangeRating0() {
        this.props.filterMovie(0);
    }
    handleOnChangeRating1() {
        this.props.filterMovie(1);
    }
    handleOnChangeRating2() {
        this.props.filterMovie(2);
    }
    handleOnChangeRating3() {
        this.props.filterMovie(3);
    }
    handleOnChangeRating4() {
        this.props.filterMovie(4);
    }
    
    render() {
    
        return (
            <div>
                <div className="mt-0"><a onClick={this.handleOnChangeRating4}> <ReactStars
                        count={5}
                        value={4}
                        size={17}
                        color2={'#ffd700'} 
                        edit = {false}/>
                    </a> 
                </div> 
                <div className="mt-0"><a onClick={this.handleOnChangeRating3}> <ReactStars
                        count={5}
                        value={3}
                        size={17}
                        color2={'#ffd700'} 
                        edit = {false}/>
                    </a> 
                </div>  
                <div onClick={this.handleOnChangeRating2}> <ReactStars
                        count={5}
                        value={2}
                        size={17}
                        color2={'#ffd700'} 
                        edit = {false}/> 
                </div> 
                <div><a onClick={this.handleOnChangeRating1}> <ReactStars
                        count={5}
                        value={1}
                        size={17}
                        color2={'#ffd700'} 
                        edit = {false}/>
                    </a> 
                </div> 
                <div><a onClick={this.handleOnChangeRating0}> <ReactStars
                        count={5}
                        value={0}
                        size={17}
                        color2={'#ffd700'} 
                        edit = {false}/>
                    </a> 
                </div> 

            </div>
        );
    }
}

export default MovieFilterRating;