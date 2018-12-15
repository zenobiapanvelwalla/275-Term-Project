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

class AddMovies extends Component {

    constructor(props) {
        super(props);
        this.state = {
            genres : [
                'Comedy',
                'Romantic',
                'Horror',
                'Action',
                'Drama',
                'Animation'
              ],
            shareholders: [{ name: '' }],
            priceEnable:false,
            messageEnable : false,
            msgResult:true

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

    // validateProjectName() {
    //     if (this.state.projectData.projectname != '')
    //     {
    //         return (true)
    //     }
    //     return (false)
    // }

    // validateDescription(){
    //     if (this.state.projectData.projectdescription != '')
    //     {
    //         return (true)
    //     }
    //     return (false)
    // }
    // validateBudget() {
    //     if (this.state.projectData.projectBudgetMin != '' && this.state.projectData.projectBudgetMax != '' && this.state.projectData.projectBudgetMin<this.state.projectData.projectBudgetMax)
    //     {
    //         return (true)
    //     }
    //     return (false)
    // }

    // validateProjectFile(){
    //     if (this.state.projectData.projectFile != '')
    //     {
    //         return (true)
    //     }
    //     return (false)
    // }
    // validateSkills(){
    //     if (this.state.projectData.selectedskills.length > 2)
    //     {
    //         return (true)
    //     }
    //     return (false)
    // }

    // handleSubmit = () => {

    //     if(this.validateProjectName() == true) {
    //         if(this.validateDescription() == true) {
    //             if(this.validateProjectFile() == true) {
    //                 if(this.validateSkills() == true) {
    //                     if(this.validateBudget() == true) {
    //                         this.props.dispatch(this.props.addProject(this.state.projectData))
    //                     .then(() => this.props.history.push('/dashboard'));
    //                     }
    //                     else{
    //                         this.setState({isBudgetMin: false})
    //                     }
    //                 }
    //                 else{
    //                     this.setState({isSelectedSkills: false})
    //                 }
    //             }
    //             else{
    //                 this.setState({isProjectFile: false})
    //             }
    //         }
    //         else {
    //             this.setState({isProjectDescription: false})
    //         }
    //     }
    //     else {
    //         this.setState({isProjectName: false})
    //     }
    // }

    // handleFileUpload = (event) => {

    //     const payload = new FormData();

    //     payload.append('myfile', event.target.files[0]);

    //     API.uploadFile(payload)
    //         .then((response) => {
    //             if (response.success) {
    //                 alert("File uploaded: Upload again to replace file");
    //                 this.setState({
    //                     projectData: {
    //                         ...this.state.projectData,
    //                         projectFile: "./uploads/doc" + response.filename
    //                     }
    //                 });
    //                 this.setState({isProjectFile: true});

    //             }
    //         });
    // };


    // handleOptionSelected(option){
    //     this.setState({
    //         projectData: {
    //             ...this.state.projectData,
    //             selectedskills:option
    //         }
    //     });

    // }

    componentDidMount(){
        // var payload ={id:'admin@gmail.com'};
        // API.fetchskills(payload)
        //     .then(
        //         (response) =>{
        //             console.log(response);
        //             console.log("-----------------------");
        //             this.setState({
        //                 skills : response.skill
        //             });
        //             console.log(this.state.skills)
        //         }
        //     );
    }

    componentDidUpdate() {
        ReactDOM.findDOMNode(this).scrollTop = 0
    }

    componentWillReceiveProps(nextProps){
        // if (nextProps.isProjectAdded === true) {
        //     nextProps.history.push('/dashboard');
        // }


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
        actors = actors.indexOf(0) == ',' ? actors.substring(1) : actors;
        console.log("string Actors" + this.refs.availability.value)
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
            "rating":this.refs.rating.value,
            "availability":this.refs.availability.value,
            "price":this.refs.price.value
            }
        let self = this;
        console.log("User Data:" + JSON.stringify(data))
        axios.post(config.API_URL+'/movies/store',data)
        .then(function (response) {
          console.log(response);
          if(response.data.success)
          {
            self.props.history.push('/customerdashboard');
          }
          else{
            self.setState({msgResult:false})
          }
          
        })
        .catch(function (error) {
          self.setState({msgResult:false})
          console.log(error);
        });
    }

    handleSelectAvailability(e){
        e.preventDefault();
        console.log("Inside handle selected ");
        var availability = this.refs.availability.value;
        console.log(availability);
        if(availability === "Paid" || availability == "PayPerViewOnly")
        {
            console.log("Inside if");
            this.state.priceEnable = true;
        }
        else{
            this.setState({priceEnable:false});
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
            <div className="">
            <AdminNavBar/>
            <div className="movie-container form-group abc" onClick={() => {
                this.setState({messageEnable : false})
            }}>

                <div className="form-row align-items-center ">
                <h3 className=""> Add Movies </h3>
                </div>
                { this.state.msgResult ? null : <div className="text-left text-small small alert alert-warning">Movie Added Successfully.</div>}
                

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
                        <option key="FA">Free</option>
                        <option key="SO">SubscriptionOnly</option>
                        <option key="PPVO">PayPerViewOnly</option>
                        <option key="P">Paid</option>
                        </select>
                    </div>
                    <div className="col-sm-5"><input ref="price" type="number" className="form-control" id="movie_price"  placeholder="Price" disabled={!this.state.priceEnable}/></div> 
                </div>
               


                <div className="form-row pt-3 ml-0">
                    <div className="col-sm-12 ml-0">{this.state.shareholders.map((shareholder, idx) => (
                                                        <div className="col-sm-12">
                                                            <input className="left"
                                                            type="text"
                                                            placeholder={`Actor #${idx + 1} name`}
                                                            value={shareholder.name}
                                                            onChange={this.handleShareholderNameChange(idx)}
                                                            />
                                                            <button type="button" onClick={this.handleRemoveShareholder(idx)} ><span><i className="fab fa fa-remove"></i></span></button>
                                                        </div>
                                                        ))}</div>
                </div>
                <div className="col-sm-2"></div><button type="button" onClick={this.handleAddShareholder} className="small">Add Actors</button>

                <div className="form-row pt-3 ml-0">
                    <div className="col-sm-12"><button type="button" className="btn btn-primary btn-lg btn-block" onClick={(event) => {this.handleSubmit(event)}}>Submit</button></div>
                </div>
            </div>
            </div>
        )
    }
}

export default withRouter(AddMovies);
