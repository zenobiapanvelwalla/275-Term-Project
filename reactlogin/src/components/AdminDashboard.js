import React, {Component} from 'react';
import config from '../config.js';
import admin_dashboard from "../custom_css/admin_dashboard.css";
import admin_dshbd from "../custom_js/admin_dshbd.js";
import $ from "jquery";
import AdminNavBar from "./AdminNavBar";
import Stepper from 'react-stepper-horizontal';

class AdminDashboard extends Component {
    
    constructor(props){
        super(props);
        this.state={
          displayName:'ZENOBIA PANVELWALA',
          activeStep :0
         
        };
        
        

        this.w3_close.bind(this);
        this.w3_open.bind(this);
        const year = (new Date()).getFullYear();
        this.years = Array.from(new Array(20),(val, index) => index + year);
      }
      
    w3_open() {
        document.getElementById("main").style.marginLeft = "25%";
        document.getElementById("mySidebar").style.width = "25%";
        document.getElementById("mySidebar").style.display = "block";
        document.getElementById("openNav").style.display = 'none';
    }
    w3_close() {
        document.getElementById("main").style.marginLeft = "0%";
        document.getElementById("mySidebar").style.display = "none";
        document.getElementById("openNav").style.display = "inline-block";
    }
    
    // https://www.w3schools.com/w3css/tryit.asp?filename=tryw3css_sidebar_shift

    incrementStep(){
        let increment  = this.state.activeStep + 1;
        this.setState({
            activeStep : increment
        });
    }
    decrementStep(){
        
        let decrement = this.state.activeStep - 1;
        this.setState({
            activeStep : decrement
        });
    }
    handleSubmit(e){
        e.preventDefault();
        alert("Movie added!");
        
    }

    render() {

        const { steps, currentStep } = this.state;
        const buttonStyle = { background: '#E0E0E0', width: 200, padding: 16, textAlign: 'center', margin: '0 auto', marginTop: 32 };

        let minOffset = 0, maxOffset = 100;
        let thisYear = (new Date()).getFullYear();
        let allYears = [];
        for(let x = 0; x <= maxOffset; x++) {
            allYears.push(thisYear - x)
        }

        const yearList = allYears.map((x) => {return(<option key={x}>{x}</option>)});
        return (
            <div>
                <AdminNavBar/>
                <div className="container movie-container">
                <Stepper steps={ [{title: 'Step One'}, {title: 'Step Two'}] } activeStep={ this.state.activeStep } activeColor={"#064c92"} completeColor={"#064c92"}/>
                
                <form className="align-middle" onSubmit={this.handleSubmit.bind(this)}> 
                {this.state.activeStep==0?
                <div>
                    <div className="form-group">
                        <input ref="title" type="text" className="form-control" id="exampleInputEmail1"  placeholder="Enter title"/>
                    </div>
                    <div className="form-group">
                        <input ref="genre" type="text" className="form-control" id="exampleInputPassword1" placeholder="Enter genre"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleSelect1" className="bmd-label-floating pull-left">Year of release</label>
                        <select  ref="yearOfRelease" className="form-control" id="exampleSelect1">
                        {yearList}
                        </select>
                    </div>
                    <div className="form-group">
                        <input type="text" className="form-control" id="exampleInputPassword1" placeholder="Enter studio name"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleTextarea" className="bmd-label-floating">Synopsis</label>
                        <textarea className="form-control" id="exampleTextarea" rows="3"></textarea>
                    </div>
                    <div className="form-group">
                        <input type="text" className="form-control" id="exampleInputPassword1" placeholder="Enter image URL"/>
                    </div>
                    <div className="form-group">
                        <input type="text" className="form-control" id="exampleInputPassword1" placeholder="Enter movie URL"/>
                    </div>
                    <a onClick={this.incrementStep.bind(this)} className="btn btn-warning">Continue ></a>
                </div>
                :""}
                {this.state.activeStep==1?
                <div>
                    
                    
                    <div className="form-group">
                        <label htmlFor="exampleTextarea" className="bmd-label-floating">Enter actors</label>
                        <textarea className="form-control" id="exampleTextarea" rows="3"></textarea>
                    </div>
                    <div className="form-group">
                        <input type="text" className="form-control" id="exampleInputPassword1" placeholder="Enter director"/>
                    </div>
                    <div className="form-group">
                        <input type="text" className="form-control" id="exampleInputPassword1" placeholder="Enter country of origin"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleSelect1" className="bmd-label-floating pull-left">MPAA film ratings</label>
                        <select   className="form-control" id="exampleSelect1">
                        <option key="PG">PG - Parental Guidance Suggested</option>
                        <option key="G">G - General Audiences</option>
                        <option key="PG-13">PG-13 - Parents Strongly Cautioned</option>
                        <option key="R">R -  Restricted</option>
                        <option key="NC-17">NC-17 - Adults Only</option>
                        <option key="UR">UR - Unrated</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleSelect1" className="bmd-label-floating pull-left">Availability</label>
                        <select   className="form-control" id="exampleSelect1">
                        <option key="Free">Free -  Available to all users</option>
                        <option key="SubscriptionOnly">SubscriptionOnly - Available only to subscription users</option>
                        <option key="PayPerViewOnly">PayPerViewOnly - Available only for pay-per-view, even for subscription users</option>
                        <option key="Paid">Paid - Available to subscription users without charge, and also available on pay-per-view basis to non-subscription users</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <input type="text" className="form-control" id="exampleInputPassword1" placeholder="Enter price (if pay-per-view)"/>
                    </div>
                    <a onClick={this.decrementStep.bind(this)} className="btn btn-primary add-movie-back">Back</a>
                    <button type="submit" className="btn btn-primary">Submit</button>
                
                </div>
                :""}
                </form>
                
                 </div>
            </div>
            
        );
    }
}

export default AdminDashboard;