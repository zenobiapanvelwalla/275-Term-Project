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
import details from '../custom_css/movie_detail.css';
import ReactStars  from 'react-stars';
import Video from "../components/Video";
import scrollToComponent from 'react-scroll-to-component';

class MovieDetail extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movie_id:'',
            movie_details:'',
            giveReview: false,
            rating:'',
            canUserWatchMovie : false,
            watchButtonMessage : '',
            canUserWatch : '',
            reviews:[],
            billing_option:false,
            seereviews:false,
            topTenByLast24Hours:'',
            top10ByLastWeek:'',
            top10ByLastMonth:'',
        }
        this.watchMovie = this.watchMovie.bind(this);
        this.getReviews = this.getReviews.bind(this);
        this.getReviews = this.getReviews.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
    }

    componentWillMount(){
        console.log("Inside movie detail fetching")
        let self = this;
        let a = this.props.match.params.movieId;
        this.setState({movie_id:a})


        //get top ten users in last 24 hours
        axios.get(config.API_URL+"/movies/play-count/"+a+"/24",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log("last day : " + JSON.stringify(response.data.message));
          self.setState({ topTenByLast24Hours:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });

        //get top ten users in last 7 days (week)
        axios.get(config.API_URL+"/movies/play-count/"+a+"/7",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log("last day : " + JSON.stringify(response.data.message));
          self.setState({ top10ByLastWeek:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });

        //get top ten users in last 30 days (month)
        axios.get(config.API_URL+"/movies/play-count/"+a+"/30",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log("last day : " + JSON.stringify(response.data.message));
          self.setState({ top10ByLastMonth:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });

        
        let path = "/movies/" + this.props.match.params.movieId;
        console.log(config.API_URL+path);
        axios.get(config.API_URL+path,{withCredentials: true})
        .then(function (response) {
          console.log("Message " + JSON.stringify(response.data.message.reviews));
        //   if(!response.data.message.reviews)
        //   {
        //       self.setState({reviews:response.data.message.reviews})
        //   }
          self.setState({movie_details:response.data.message,reviews:response.data.message.reviews },() => {
            //check user can watch movie or not
            let availability = self.state.movie_details.availability;
            let isSubscribed = localStorage.getItem('isSubscribed');
            let moviesPaidForList = localStorage.getItem('moviesPaidForList');
            self.setState({canUserWatch:response.data.canUserWatch},() => {
                if(self.state.canUserWatch == true)
                {
                    if(localStorage.getItem('role') == 'ADMIN')
                    {
                        self.setState({canUserWatchMovie:true});
                    }
                    if(isSubscribed == "true" && availability == 'SubscriptionOnly')
                    {
                        self.setState({canUserWatchMovie:true});
                    }
                    if(isSubscribed == "true" && availability == "Paid")
                    {
                        self.setState({canUserWatchMovie:true});
                    }
                    if(availability == 'Free')
                    {
                        self.setState({canUserWatchMovie:true});
                    }
                    if(localStorage.getItem("moviesPaidForList").includes(parseInt(self.props.match.params.movieId)))
                    {
                        self.setState({canUserWatchMovie:true});
                    }
                    //when and who will pay
                    else{
                        let amount = 0;
                        if(isSubscribed == "true" && availability == "PayPerViewOnly")
                        {
                            amount = amount + (self.state.movie_details.price * .5);
                            localStorage.setItem("amount",amount.toString());
                            localStorage.setItem("page","moviedetail");
                            // localStorage.setItem("movieId",this.props.match.params.movieId)
                            localStorage.setItem("paymentType",availability)
                        }
                        if(isSubscribed == "false" && availability == "PayPerViewOnly")
                        {
                            amount = amount + self.state.movie_details.price;
                            localStorage.setItem("amount",amount.toString());
                            localStorage.setItem("page","moviedetail");
                            // localStorage.setItem("movie_id",this.props.match.params.movieId)
                            localStorage.setItem("paymentType",availability)
                        }
                        if(isSubscribed == "false" && availability == "Paid")
                        {
                            amount = amount + self.state.movie_details.price;
                            localStorage.setItem("amount",amount.toString());
                            localStorage.setItem("page","moviedetail");
                            // localStorage.setItem("movie_id",this.state.movie_id);
                            localStorage.setItem("paymentType",availability)
                        }
                    }
                    
                }
            });
          });
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    handleDelete(){
        console.log("Inside handle delete");
        console.log(this.props.match.params.movieId);
        let self = this;
        let path = '/movies/delete/' + this.props.match.params.movieId.toString();
        axios.delete(config.API_URL+path)
        .then(function (response) {
          console.log("Message " + JSON.stringify(response.data.message));
        //   this.setState({movies:response.data.message[0]});
            if(response.data.success)
            {
                self.props.history.push('/customerdashboard');
            }
            else{
                //this.setState({billing_option:true})
                alert("Some Error Occured");
            }
        })
        .catch(function (error) {
          console.log(error);
        });

    }

    handleVideoDetails = (details) => {
        this.setState({videoDetails:details})            
    }

    watchMovie(event){
         if(this.state.canUserWatchMovie){
            let watchingDetails = {
                userId : localStorage.getItem('user_id'),
                movieId : this.props.match.params.movieId,
                url : this.state.movie_details.movieUrl,
                checkpoint : 0
            }
            localStorage.setItem('watchingDetails',JSON.stringify(watchingDetails));
            console.log("************************" + JSON.stringify(watchingDetails));
            this.props.history.push('/video');
        }
        else{

            this.setState({billing_option:true});
        }
        }

    getReviews(){
        console.log("Reviews Initially : " + this.state.reviews);
        const item = this.state.reviews.map((review,index) =>{

            return(
                    <div className = "">
                    <hr size="30"></hr>
                    <b>{review.userName}</b>
                    <ReactStars
                        count={5}
                        value={review.starRating}
                        size={17}
                        color2={'#ffd700'} 
                        edit = {false}/>
                    <p>{review.reviewText}</p>
                          
                    </div>

            )
        });
        return(
            <div>{item}</div>
        )

        
    }

    render(){
        
        return (
            <div className="moviedetail_body">
                {localStorage.getItem('role') == 'ADMIN' ? <AdminNavBar/> : <NavBar></NavBar>}
                <div className = "row">
                    <div className = "col-md-6 sectionM" id="pic-1"><img className="imageSection" src={this.state.movie_details.imageUrl } /></div>
                    <div className = "col-md-6 sectionM">

                        {/* title */}
                        <div className="row">
                            <h3 className="text-left">{this.state.movie_details.title + "(" +  this.state.movie_details.year + ")"}</h3>
                            {!this.state.canUserWatch ? <span className="badge badge-danger">This movie is no longer available in our server.</span> : null}
                            {/* add update delete button if the user is admin */}
                            {localStorage.getItem('role')=='ADMIN' ? 
                                <div>
                                    <button className="btn btn-outline-warning btn-sm mt-1" onClick={this.handleDelete}>Delete</button>
                                    <button className="btn btn-outline-warning btn-sm mt-1" onClick={(e) => {
                                        console.log("Movvvvvvvvvvvvvvvvvvv" + this.state.movie_details.movieId)
                                        let path = "/updatemovies/" +  this.state.movie_details.movieId;
                                        this.props.history.push(path)
                                    }
                                    }>Update</button>
                                </div> : null
                            }
                        </div>

                        {localStorage.getItem('role') == 'ADMIN' ?
                        <div className="row">
                            <b>Play Count - </b>
                            <button className="badge">last day : {this.state.topTenByLast24Hours}</button>
                            <button className="badge">last week : {this.state.top10ByLastWeek}</button>
                            <button className="badge">last month : {this.state.top10ByLastMonth}</button>
                        </div>
                        : null}
                    
                        <div className="row">
                            <ReactStars
                                    value = {this.state.movie_details.avgStarRating}
                                    count={5}
                                    size={20}
                                    color2={'#ffd700'}
                                    edit = {false} 
                            />
                        </div>

                        <div className="row">
                            {/* Add Review */}
                            {localStorage.getItem('role') == 'ADMIN' ? null : 
                            <div>
                            <button type="button" onClick={(event) => {this.setState({giveReview:true})}} className="btn btn-link btn-sm ml-0 pl-0 "><p className = "btnLink"><u>Add Review</u></p></button>
                            <button type="button" onClick={(event) => {
                                this.setState({seereviews:true}, ()=> {
                                    scrollToComponent(this.refs.r, {
                                        offset: 22,
                                        align: 'middle',
                                        duration: 1500
                                    });
                                    
                                })

                                }} className="btn btn-link btn-sm ml-0 pl-0 ml-0 pl-0 "><p className = "btnLink"><u>See Reviews</u></p></button>
                            </div>}
                        </div>

                        <div className="row">
                            <div className="col-md-6">
                            {/* if customer and started watching reviews */}
                            { !this.state.giveReview ? null : 
                            <div className="border reviewSection text-left pt-2 pl-2 pr-2 pb-2">   
                                <b>Add Rating</b>
                                <ReactStars
                                value = {this.state.rating}
                                count={5}
                                onChange={(event)=>{
                                    this.setState({rating : event})
                                    console.log("Rating : " + event);
                                }}
                                size={24}
                                color2={'#ffd700'} />
                                <textarea placeholder="Add Review" ref="reviewText" className="form-control"  rows="3"></textarea>  
                                <button className="btn btn-outline-primary btn-sm mt-1" onClick={(event) => {
                                    if(this.state.rating <= 0)
                                    {
                                        alert("Please give rating");
                                    }
                                    else
                                    {
                                        var payload = {
                                            userId : localStorage.getItem('user_id'),
                                            userName : JSON.parse(localStorage.getItem('user_details')).displayName,
                                            movieId : this.props.match.params.movieId ,
                                            starRating : this.state.rating,
                                            reviewText : this.refs.reviewText.value
                                        }
                                        var alreadyReviewed = false;
                                        if(this.state.reviews){
                                        this.state.reviews.map((review) => {
                                            if(review.userId == payload.userId)
                                            {
                                                alreadyReviewed = true;
                                            }
                                        })}
                                        
                                        if(alreadyReviewed == true)
                                        {
                                            alert("You have already reviewed.")
                                            this.setState({giveReview:false})
                                        }
                                        else{
                                            console.log("Review : " + JSON.stringify(payload));
                                            let self = this;
                                            axios.post(config.API_URL+'/movies/add-review',payload)
                                            .then(function (response) {
                                            // console.log("Attributes " + JSON.stri    ngify(response.data));
                                            if(response.data.success){
                                                self.setState({movie_details:response.data.updatedMovie,reviews:response.data.reviews},()=>
                                                    {
                                                        console.log("UPDATED MOVIE : " + JSON.stringify(response.data.updatedMovie));
                                                        // let reviews_new =  response.data.updatedMovie.reviews
                                                        // console.log("Reviews : " + reviews_new);
                                                        // self.setState({review:reviews_new})
                                                        self.setState({giveReview:false} , () => {
                                                            self.setState({seereviews:false});
                                                        })
                                                        
                                                    });
                                            }
                                        
                                            else
                                            {
                                                alert("you can not give reviews without watching movies");
                                                self.setState({giveReview:false})
                                            }
                                            })
                                            .catch(function (error) {
                                            console.log(error);
                                            });
                                        }
                                        
                                    }
                                }}>Submit</button>
                                <button className="btn btn-outline-primary btn-sm mt-1" onClick={(event) => {this.setState({giveReview:false})}}>Cancel</button>
                            </div>}
                        </div>
                    </div>

                    <div className="row">
                        <p className="small text-left detailText">{this.state.movie_details.synopsis}</p>
                    </div>
                    <div className="row detailText">
                                <b>Director: </b><p>{this.state.movie_details.director}</p>
                    </div>
                    <div className="row detailText">
                                <b>Cast: </b><p>{this.state.movie_details.actors}</p>
                    </div>
                    {this.state.canUserWatch==true?
                    <div className="row">
                        <button type="button" onClick={this.watchMovie} className="btn btn-danger">Watch Now</button>     
                    </div>
                    :""}
                    <br/>
                    

                    {this.state.billing_option ?
                    <div className="row">
                        <button type="button" onClick={() => {
                            this.props.history.push('/billing');
                        }} className="btn btn-info">Pay for this movie</button>     
                        <button type="button" onClick={() => {
                            this.props.history.push('/user-profile');
                        }} className="ml-2 btn btn-info">Subscribe</button>     
                    </div> : null }

                    <div className="row">
                        <div className="line"></div>
                    </div>

                    </div>
                </div>

                {/* //reviews section */}
                {this.state.seereviews ? <div className="row">
                <div ref="r" class="col-md-12">
		            <h5 className="ml-5">Reviews</h5>
		        </div>
                    {this.getReviews()}
                </div> : null}

                {/* admin graph */}
                
                
            </div>

        )
    }

}

export default withRouter(MovieDetail);