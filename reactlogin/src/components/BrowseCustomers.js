import React,{Component} from 'react';
import {withRouter} from 'react-router-dom';
import AdminNavBar from './AdminNavBar';
import customer from "../custom_css/browse_customers.css";

class BrowseCustomers extends Component{
    state = {
        list:true,
        movieHistory:false,
        topTen:false,
        topTenList:[],
        data:{ 
            topTenByLast24Hours:[],
            top10ByLastWeek:[],
            top10ByLastMonth:[]
        }
    }
    constructor(props){
        super(props);
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
            <div className="card-default cardBC">
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
                            
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Zenobia Panvelwala</td>
                            <td>ZenobiaRockSTAR</td>
                            <td>If true show some icon</td>
                            <td>12-02-2018 11:59:00</td>
                            <td><a href="#">View Movie Playing 'History'</a></td>
                            <td className="text-center">
                            <div className="radial-bar radial-bar-25 radial-bar-xs" data-label="25%"></div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                </div>
                <div className="card-footer">
                <div className="d-flex">
                    {/* A particular customer movie watching history in reverse chronological order */}
                    <table className="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Movie Title</th>
                            <th>Watched Date</th>
                            <th>Number of times watched</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Spider Man</td>
                            <td>12-02-2000</td>
                            <td>4</td>
                        </tr>
                    </tbody>
                </table>
                </div>
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
                        case 'last 24 hours':this.state.topTenList = this.state.data.topTen.topTenByLast24Hours;
                        break;
                        case 'last week':this.state.topTenList = this.state.data.topTen.top10ByLastWeek;
                        break;
                        case 'last month':this.state.topTenList = this.state.data.topTen.top10ByLastMonth;
                        break;
                    }
                }}>
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
                        <th>isVerified</th>
                        <th>Registration Date</th>
                        <th>
                        
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Zenobia Panvelwala</td>
                        <td>ZenobiaRockSTAR</td>
                        <td>If true show some icon</td>
                        <td>12-02-2018 11:59:00</td>
                        <td><a href="#">View Movie Playing 'History'</a></td>
                        <td className="text-center">
                        <div className="radial-bar radial-bar-25 radial-bar-xs" data-label="25%"></div>
                        </td>
                    </tr>
                </tbody>
            </table>
            </div>
            
        </div>
            :""}
            </div>
        </div>
    )};
}

export default withRouter(BrowseCustomers);