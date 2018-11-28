import React, {Component} from 'react';
import {Link, withRouter} from "react-router-dom";
import ReactDOM from 'react-dom';
import {Typeahead} from 'react-bootstrap-typeahead';
import admin_dshbd from "../custom_js/admin_dshbd.js";
import 'react-bootstrap-typeahead/css/Typeahead.css';
import NavBar from './NavBar';
import AdminDashboard from './AdminDashboard.js';
import AdminNavBar from './AdminNavBar.js';
import config from '../config.js';
import axios from 'axios';

class AdminUpdateMovies extends Component {

    constructor(props) {
        super(props);
        this.state = {
            genres : [
                'Comedy',
                'Romantic',
                'Horror',
                'Action',
                'Drama'
              ],
            shareholders: [{ name: '' }],
            priceEnable:false,
            messageEnable : false,
            message : ""

            // isProjectName:true,
            // isProjectDescription:true,
            // isBudgetMin:true,
            // isBudgetMax:true,
            // isProjectFile:true,
            // isSelectedSkills:true,
            // projectId: '',
            // skills:[],
            // projectFile: '',
            // message: '',
        };
        // this.handleOptionSelected = this.handleOptionSelected.bind(this);

        this.handleSelectAvailability = this.handleSelectAvailability.bind(this);
        this.handleShareholderNameChange = this.handleShareholderNameChange.bind(this);
        this.handleAddShareholder = this.handleAddShareholder.bind(this);
        this.handleRemoveShareholder = this.handleRemoveShareholder.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    

    componentDidMount(){
        payload = {movieId : 1}
        axios.post(config.API_URL+'/movies/store', payload)
        .then(function (response) {
          console.log(response);
          this.setState({message: "Hey" , messageEnable : true})
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    componentDidUpdate() {
        ReactDOM.findDOMNode(this).scrollTop = 0
    }

    componentWillReceiveProps(nextProps){

    }

    handleSubmit(e){
        e.preventDefault();
        console.log("Inside handleSubmit ");
        let actors = "";
        console.log("aCTORS" + JSON.stringify(this.state.shareholders))
        for (var key in this.state.shareholders) {
            if(this.state.shareholders[key].name.toString() != "")
                actors = actors + "," + this.state.shareholders[key].name.toString();
        }
        actors = actors.indexOf(0) == '0' ? actors.substring(1) : actors;
        console.log("string " + actors)
        var data = {
            "title": this.refs.title.value,
            "genre":this.refs.genre.value,
            "year":this.refs.year.value,
            "studio":this.refs.studio.value,
            "synopsis":this.refs.synopsis.value,
            "imageUrl":this.refs.image.value,
            "movieUrl":this.refs.movieurl.value,
            "actors":actors,
            "director":this.refs.director.value,
            "country":this.refs.country.value,
            "rating":this.refs.rating.key,
            "availability":this.refs.availability.key,
            "price":this.refs.price.value
            }
        let self = this;
        console.log("User Data:" + JSON.stringify(data))
        axios.post(config.API_URL+'/movies/store',data)
        .then(function (response) {
          console.log(response);
          this.setState({message: "Hey" , messageEnable : true})
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    handleSelectAvailability(e){
        e.preventDefault();
        console.log("Inside handle selected ");
        var availability = this.refs.availability.value;
        console.log(availability);
        if(availability == "Paid")
        {
            console.log("Inside if");
            this.state.priceEnable = true;
        }
    }

    handleShareholderNameChange = (idx) => (evt) => {
        const newShareholders = this.state.shareholders.map((shareholder, sidx) => {
          if (idx !== sidx) return shareholder;
          return { ...shareholder, name: evt.target.value };
        });
        
        this.setState({ shareholders: newShareholders });
        console.log("aCTORS" + JSON.stringify(this.state.shareholders))
      }

    handleAddShareholder = () => {
        this.setState({ shareholders: this.state.shareholders.concat({ name: '' }) });
        console.log("aCTORS" + JSON.stringify(this.state.shareholders))
      }
      
      handleRemoveShareholder = (idx) => () => {
        this.setState({ shareholders: this.state.shareholders.filter((s, sidx) => idx !== sidx) });
        console.log("aCTORS" + JSON.stringify(this.state.shareholders))
    }

    render(){

        let minOffset = 0, maxOffset = 100;
        let thisYear = (new Date()).getFullYear();
        let allYears = [];
        for(let x = 0; x <= maxOffset; x++) {
            allYears.push(thisYear - x)
        }

        const yearList = allYears.map((x) => {return(<option key={x}>{x}</option>)});

        return(
            <div className="backgroundLogin">
            <AdminNavBar/>
            <div className="movie-container form-group" onClick={() => {
                this.setState({messageEnable : false})
            }}>

                <div className="form-row align-items-center ">
                <h3 className=""> Add Movies </h3>
                </div>
                {  this.state.messageEnable ? 
                <div className="form-row align-items-center pt-2">
                    <div className="alert alert-info" role="alert">
                        {this.state.message}
                    </div>
                </div> : null}
                

                <div className="form-row align-items-center pt-2">
                    <div className="col-sm-6"><input ref="title" type="text" className="form-control" id="movie_title"  placeholder="Enter title"/></div>
                    <div className="col-sm-6"><input ref="genre" type="text" className="form-control" id="movie_genre"  placeholder="Enter Genre"/></div>
                </div>

                <div className="form-row align-items-center pt-3">
                    <div className="col-sm-3"><select className="form-control" ref="year" className="form-control" id="exampleSelect1" placeholder="Release Year">
                            <option value="" disabled selected>Release Year</option>
                            {yearList}
                    </select></div> 
                    <div className="col-sm-9"><input ref="studio" type="text" className="form-control" id="movie_studio"  placeholder="studio"/></div> 
                </div>

                <div className="form-row align-items-center pt-3">
                    <div className="col-sm-8"><input ref="synopsis" type="text" className="form-control" id="movie_synopsis"  placeholder="Synopsis"/></div> 
                    <div className="col-sm-4"><input ref="country" type="text" className="form-control" id="movie_country"  placeholder="Country"/></div> 
                </div>

                <div className="form-row align-items-center pt-3">
                    <div className="col-sm-12"><input ref="image" type="text" className="form-control" id="movie_image"  placeholder="image"/></div> 
                </div>

                <div className="form-row align-items-center pt-3">
                    <div className="col-sm-12"><input ref="movieurl" type="text" className="form-control" id="movie_movieurl"  placeholder="Add Movie Video"/></div> 
                </div>

                <div className="form-row align-items-center pt-3">
                    <div className="col-sm-6">
                        <select className="form-control" ref="rating" id="rating">
                        <option key="PG">PG - Parental Guidance Suggested</option>
                        <option key="G">G - General Audiences</option>
                        <option key="PG-13">PG-13 - Parents Strongly Cautioned</option>
                        <option key="R">R -  Restricted</option>
                        <option key="NC-17">NC-17 - Adults Only</option>
                        <option key="UR">UR - Unrated</option>
                        </select>
                    </div>
                    <div className="col-sm-6"><input ref="director" type="text" className="form-control" id="movie_director"  placeholder="Add Director (Optional)" /></div> 
                </div>

                <div className="form-row align-items-center pt-3">
                    <div className="col-sm-7">
                        <select ref="availability" className="form-control" id="rating" onChange={(event) => {this.handleSelectAvailability(event)}}>
                        <option value key="FA">Free</option>
                        <option key="SO">SubscriptionOnly</option>
                        <option key="PPVO">PayPerViewOnly</option>
                        <option key="P">Paid</option>
                        </select>
                    </div>
                    <div className="col-sm-5"><input ref="price" type="text" className="form-control" id="movie_price"  placeholder="Price" disabled={!this.state.priceEnable}/></div> 
                </div>
               


                <div className="form-row pt-3 ml-0">
                    <div className="col-sm-12 ml-0">{this.state.shareholders.map((shareholder, idx) => (
                                                        <div className="col-sm-12">
                                                            <input className="left"
                                                            type="text"
                                                            placeholder={`Shareholder #${idx + 1} name`}
                                                            value={shareholder.name}
                                                            onChange={this.handleShareholderNameChange(idx)}
                                                            />
                                                            <button type="button" onClick={this.handleRemoveShareholder(idx)} ><span><i className="fab fa fa-remove"></i></span></button>
                                                        </div>
                                                        ))}</div>
                </div>
                <div className="col-sm-2"></div><button type="button" onClick={this.handleAddShareholder} className="small">Add Shareholder</button>

                <div className="form-row pt-3 ml-0">
                    <div className="col-sm-12"><button type="button" className="btn btn-primary btn-lg btn-block" onClick={(event) => {this.handleSubmit(event)}}>Submit</button></div>
                </div>
            </div>
            </div>
        )
    }
}

export default withRouter(AdminUpdateMovies);
