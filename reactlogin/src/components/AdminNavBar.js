import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import navbar from '../custom_css/navbar.css';
import config from '../config.js';
import axios from 'axios';

class AdminNavBar extends Component
{
    render(){
        return(
            <div>
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <a href="#" className="navbar-brand">baby MOVIE CENTRAL</a>
                    <button className="navbar-toggler" data-target="#navigation" data-control="navigation" data-toggle="collapse">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navigation">
                        <ul className="nav navbar-nav">
                            <li className="nav-item active">
                                <a className="nav-link" onClick={() => {this.props.history.push('/customerdashboard');}}>Home</a>
                            </li>
                            <li className="nav-item">
                                <a onClick={() => {this.props.history.push('/addmovies')}} className="nav-link">Add Movie</a>
                            </li>

                            <li className="nav-item">
                                <a onClick={() => {this.props.history.push('/browse-customers')}} className="nav-link">Customer Activity</a>
                            </li>

                            <li className="nav-item">
                                <a onClick={() => {this.props.history.push('/financial-report')}} className="nav-link">Financial Report</a>
                            </li>

                            <li className="nav-item">
                                <a onClick={() => {this.props.history.push('/movie-activity-report')}} className="nav-link">Movie Activity Report</a>
                            </li>
                            
                            <li className="nav-item float-right">
                                <a className="nav-link" onClick={() => {
                                    localStorage.clear();
                                    let self = this;
                                    axios.get(config.API_URL+'/logout',{withCredentials: true})
                                    .then(function (response) {
                                        self.props.history.push('/')})
                                    .catch(function (error) {
                                    console.log(error);
                                    });
                                }}>Logout</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        )
    }
}

export default withRouter(AdminNavBar);