import React,{Component} from 'react';
import {withRouter} from 'react-router-dom';
import config from '../config.js';
import axios from 'axios';
import AdminNavBar from './AdminNavBar';
import customer from "../custom_css/browse_customers.css";

class BrowseCustomers extends Component{
    state = {
        list:true,
        movieHistory:false,
        topTen:false,
        topTenList:[],  //topTenList will be populated depending on what is selected from the dropdown
        topTenByLast24Hours:[],
        top10ByLastWeek:[],
        top10ByLastMonth:[],
        users:[],
        moviePlayingHistory:[]
    }
    constructor(props){
        super(props);
    }
    componentWillMount(){
        console.log("Inside browse customers page")
        let self = this;
        let path = "/users";
        //console.log(config.API_URL+path);
        axios.get(config.API_URL+path,{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log(response.data);
          self.setState({users:response.data.users});
        })
        .catch(function (error) {
          console.log(error);
        });

        //get top ten users in last 24 hours
        axios.get(config.API_URL+"/users/top-ten/24",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log(response.data);
          self.setState({ topTenByLast24Hours:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });

        //get top ten users in last 7 days (week)
        axios.get(config.API_URL+"/users/top-ten/7",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log(response.data);
          self.setState({ top10ByLastWeek:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });

        //get top ten users in last 30 days (month)
        axios.get(config.API_URL+"/users/top-ten/30",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log(response.data);
          self.setState({ top10ByLastMonth:response.data.message});
        })
        .catch(function (error) {
          console.log(error);
        });


    }

    getMoviePlayingHistory(userid){

        console.log("USER ID"+ userid);
        let self = this;
        let path = "/user-activities/movie-playing-history/"+userid;
        console.log("path: "+path);
        axios.get(config.API_URL+path,{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log(response.data.history);
          self.setState({moviePlayingHistory:response.data.history});
        })
        .catch(function (error) {
          console.log(error);
        });
    }
    render(){
    return (
        <div className="customer-container">
            <AdminNavBar/>
            <div className="row customer-row">
            <div className="col-md-3 ">
                <div className="list-group ">
                    <a href="#"  onClick={(event) => {
                                            this.setState({list:true, topTen:false});
                                            }} className={this.state.list==true?'list-group-item list-group-item-action active':'list-group-item list-group-item-action'}>Customers List</a>
                    <a href="#"  onClick={(event) => {
                                            this.setState({list:false, topTen:true});
                                        }} className={this.state.topTen==true?"list-group-item list-group-item-action active":"list-group-item list-group-item-action"}>Top 10</a>
                </div> 
		    </div>
            {this.state.list==true?
            <div className="card-default cardBC" id="users">
                <div className="card-header"><b>Customers List</b></div>
                <div className="table-responsive">
                <table className="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>Display Name</th>
                            <th>isVerified</th>
                            <th>Registration Date</th>
                            <th>
                                Action
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.users.map(user =>
                        <tr key={user.id}> 
                            <td>{user.email}</td>
                            <td>{user.displayName}</td>
                            <td>{user.verified==true?"YES":"NO"}</td>
                            <td>{user.registeredAt}</td>
                            <td><a href="#history" onClick={this.getMoviePlayingHistory.bind(this,user.id)}> Movie Playing History</a></td>
                            
                        </tr>
                    )}
                    </tbody>
                </table>
                </div>
                <div className="card-footer" id="history">
                <a href="#users" className="pull-right">Go To Top</a>
                {this.state.moviePlayingHistory.length>0?
                <div className="d-flex">
                    {/* A particular customer movie watching history in reverse chronological order */}
                    <table className="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Movie Title</th>
                            <th>Watched Date</th>
                            <th>Movie Availability</th>
                            <th>Genre</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.moviePlayingHistory.map((history)=>
                        <tr key={history.id}>
                            <td>{history.movie.title}</td>
                            <td>{history.updatedAt}</td>
                            <td>{history.movie.availability}</td>
                            <td>{history.movie.genre}</td>
                        </tr>
                        )}
                    </tbody>
                </table>
                </div>
                : null}
                </div>
            </div>
            :""}
            {/* Display top ten users */}
            {this.state.topTen== true?
            <div className="card-default cardBC">
            <div className="card-header"><b>Top 10 Customers</b></div>
            <div className="table-responsive">
            <div className="align-content-end">
                <select onChange={(event)=>{
                    console.log(event.target.value);
                    switch(event.target.value){
                        case 'last 24 hours':
                            this.setState({
                                topTenList: this.state.topTenByLast24Hours
                            });
                        break;

                        case 'last week':
                            this.setState({
                                topTenList :this.state.top10ByLastWeek
                            });
                        break;

                        case 'last month':
                            this.setState({
                                topTenList:this.state.top10ByLastMonth
                            });
                        break;
                    }
                }}>
                    <option value="" selected disabled>Choose Period</option>
                    <option value="last 24 hours">Last 24 Hours</option>
                    <option value="last week">Last Week</option>
                    <option value="last month">Last Month</option>
                </select>
            </div>
            <table className="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>Display Name</th>
                        <th>Play Count</th>
                    </tr>
                </thead>
                {this.state.topTenList.length>0?
                <tbody>
                    {this.state.topTenList.map((user)=>
                    <tr key={user.id}>
                        <td>{user.email}</td>
                        <td>{user.displayName}</td>
                        <td>{user.playCount}</td>
                    </tr>
                    )}
                </tbody>
                :null}
            </table>
            </div>
            
        </div>
            :""}
            </div>
        </div>
    )};
}

export default withRouter(BrowseCustomers);