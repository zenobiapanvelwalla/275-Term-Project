import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import NavBar from './NavBar';
import config from '../config.js';
import axios from 'axios';
import profile from "../custom_css/profile.css";


class UserProfile extends Component {

    state={
        billingStatus:true,
        subscribe:false,
        amountPaid:0
    }

    constructor(props){
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.updateAmount = this.updateAmount.bind(this);
        
    }
    componentDidMount(){
      //get the logged in user's subscription details
    }
    updateAmount(e){
        e.preventDefault();
        var amount = this.ref.months * 10;
        this.setState({
            amountPaid: amount
        });
        console.log("Amount to be paid:", this.state.amountPaid);
    }
    handleSubmit(e){
        e.preventDefault();
        console.log("Post Request To Billing Page");
        localStorage.setItem("subscriptionMonths",this.ref.months.value);
        let amount = this.ref.months.value*10;
        localStorage.setItem("page","subscription");
        localStorage.setItem("amount",amount);
        this.props.history.push('/billing');
        

    }

    
    

    render() {
        let months = [];
                for(let x = 1; x <= 12; x++) {
                    months.push(x)
                }

        const monthsList = months.map((x) => {return(<option key={x}>{x}</option>)});
        return (
            <div className="profile-container">
                <NavBar/>
                
                
	            <div className="row profile-row">
                <div className="col-md-3 ">
                    <div className="list-group ">
                        <a href="#" id="bill" onClick={(event) => {
                                               this.setState({billingStatus:true, subscribe:false});
                                              }} className={this.state.billingStatus==true?'list-group-item list-group-item-action active':'list-group-item list-group-item-action'}>Billing Status</a>
                        <a href="#" id="subscribe" onClick={(event) => {
                                               this.setState({billingStatus:false, subscribe:true});
                                           }} className={this.state.subscribe==true?"list-group-item list-group-item-action active":"list-group-item list-group-item-action"}>Subscribe</a>
                    </div> 
		        </div>
                {/* Billing Status Section */}
                {this.state.billingStatus == true?
		        <div className="col-md-9">
		        <div className="cardU">
		        <div className="card-body">
		            <div className="row">
		                <div className="col-md-12">
		                    <h4>Your Billing Status</h4>
		                    <hr/>
		                </div>
		            </div>
		            <div className="row">
		                <div className="col-md-12">
		                    
                        <table className="table table-hover table-sm">
                            <thead>
                              <tr>
                              <th>Months</th>
                              <th>From</th>
                              <th>To</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                              <td>4</td>
                              <td>2kdsfj</td>
                              <td>sdfs</td>
                              </tr>
                            </tbody>
                        </table>
                        
		                </div>
		            </div>
		            
		        </div>
		    </div>
		</div>
                :""}

                {/* Subscribe Section */}
                
                {this.state.subscribe == true?
                <div className="col-md-9">
                <div className="cardU">
                    <div className="card-body">
                        <div className="row">
                            <div className="col-md-12">
                                <h4>Subscribe</h4>
                                <hr/>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-md-12">
                                <form onSubmit={(event) => {
                                               this.handleSubmit(event)
                                           }}>
                                  
                                  
                                  <div className="form-group row">
                                    <label htmlFor="select" className="col-4 col-form-label">Months</label> 
                                    <div className="col-8">
                                      <select ref="months" id="select" name="select" className="custom-select">
                                        <option value="" disabled="true">Select Period</option>
                                            {monthsList}
                                      </select>
                                    </div>
                                  </div>
                                  
                                  <div className="form-group row">
                                    <div className="offset-4 col-8">
                                      <button name="submit" type="submit" className="btn btn-primary sub-button">Subscribe</button>
                                    </div>
                                  </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                :""}
</div>
                

                



                
            </div>
           
        );
    }
}

export default withRouter(UserProfile);