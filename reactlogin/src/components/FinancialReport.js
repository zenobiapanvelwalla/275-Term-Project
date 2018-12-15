import React,{Component} from 'react';
import {Pie, Bar} from 'react-chartjs-2';
import AdminNavBar from './AdminNavBar';
import config from '../config.js';
import axios from 'axios';
import chartcss from '../custom_css/chart.css';
//https://github.com/jerairrest/react-chartjs-2/blob/master/example/src/components/bar.js
class FinancialReport extends Component{
    constructor(props){
        super(props);
        //axios call to get the report data
        this.state = {
            uniquePayPerViewUsersCount:null,
            uniquePayPerViewUsersMonths:null,
            chartData:{
                labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                datasets:[
                    {
                        label:'Number Of Unique Subscription Users',
                        data: [
                            12,
                            23,
                        ],
                        backgroundColor: [
                            'rgba(0, 255, 0, 0.3)',
                            'rgba(0, 0, 255, 0.3)'
                        ]
                    }
                ] 
            },
            chartData1:{
                labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                datasets:[
                    {
                        label:'Unique PayPerView Users',
                        data: [],
                        backgroundColor: [
                            'rgba(0, 255, 0, 0.3)',
                            'rgba(0, 0, 255, 0.3)'
                        ]
                    }
                ] 
            },
            chartData2:{
                labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                datasets:[
                    {
                        label:'Total Unique Active Users',
                        data: [
                            12,
                            23,
                        ],
                        backgroundColor: [
                            'rgba(255,99,132,0.6)',
                            'rgba(54,162,235,0.6)'
                        ]
                    }
                ] 
            },
            chartData3:{
                labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                datasets:[
                    {
                        label:'Total Unique Users',
                        data: [
                            12,
                            23,
                        ],
                        backgroundColor: [
                            'rgba(255,99,132,0.6)',
                            'rgba(54,162,235,0.6)'
                        ]
                    }
                ] 
            },
            chartData4:{
                labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                datasets:[
                    {
                        label:'Monthly Subscription Income',
                        data: [
                            12,
                            23,
                        ],
                        backgroundColor: [
                            'rgba(255,99,132,0.6)',
                            'rgba(54,162,235,0.6)'
                        ]
                    }
                ] 
            },
            chartData5:{
                labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                datasets:[
                    {
                        label:'Monthly Pay Per View Income',
                        data: [
                            12,
                            23,
                        ],
                        backgroundColor: [
                            'rgba(255,99,132,0.6)',
                            'rgba(54,162,235,0.6)'
                        ]
                    }
                ] 
            },
            chartData6:{
                labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                datasets:[
                    {
                        label:'Monthly Total Income',
                        data: [
                            12,
                            23,
                        ],
                        backgroundColor: [
                            'rgba(255,99,132,0.6)',
                            'rgba(54,162,235,0.6)'
                        ]
                    }
                ] 
            }
        }
    }
    componentDidMount(){
        //unique pay per view users
        let self = this;
        axios.get(config.API_URL+"/count-unique-pay-per-view-users",{withCredentials: true})
          .then(function (response) {
            //console.log("Message " + JSON.stringify(response));
            console.log(response.data.message);
            self.setState({
                chartData1:{
                    labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                    datasets:[
                        {
                            label:'Unique PayPerView Users',
                            data: response.data.message.userCount,
                            backgroundColor: [
                                'rgba(0, 255, 0, 0.3)',
                                'rgba(0, 0, 255, 0.3)'
                            ]
                        }
                    ] 
                }
            });
          })
          .catch(function (error) {
            console.log(error);
          });
          //Unique Subscription users
          axios.get(config.API_URL+"/count-unique-subscription-users",{withCredentials: true})
          .then(function (response) {
            //console.log("Message " + JSON.stringify(response));
            console.log(response.data.message);
            self.setState({
                chartData:{
                    labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                    datasets:[
                        {
                            label:'Unique Subscription Users',
                            data: response.data.message.userCount,
                            backgroundColor: [
                                'rgba(0, 255, 0, 0.3)',
                                'rgba(0, 0, 255, 0.3)'
                            ]
                        }
                    ] 
                }
            });
          })
          .catch(function (error) {
            console.log(error);
          });
        //Total Unique Active Users
        axios.get(config.API_URL+"/count-total-unique-active-users",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log(response.data.message);
          self.setState({
            chartData2:{
                  labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                  datasets:[
                      {
                          label:'Total Unique Active Users',
                          data: response.data.message.userCount,
                          backgroundColor: [
                              'rgba(0, 255, 0, 0.3)',
                              'rgba(0, 0, 255, 0.3)'
                          ]
                      }
                  ] 
              }
          });
        })
        .catch(function (error) {
          console.log(error);
        });

        //Total Unique Users
        axios.get(config.API_URL+"/count-unique-registered-users",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log(response.data.message);
          self.setState({
            chartData3:{
                  labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                  datasets:[
                      {
                          label:'Total Unique Users',
                          data: response.data.message.userCount,
                          backgroundColor: [
                              'rgba(0, 255, 0, 0.3)',
                              'rgba(0, 0, 255, 0.3)'
                          ]
                      }
                  ] 
              }
          });
        })
        .catch(function (error) {
          console.log(error);
        });

        //Monthly Subscription Income, pay per view income, monthly total income.
        axios.get(config.API_URL+"/monthlyincome",{withCredentials: true})
        .then(function (response) {
          //console.log("Message " + JSON.stringify(response));
          console.log(response.data);
          self.setState({
            chartData4:{
                  labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                  datasets:[
                      {
                          label:'Monthly Subscription Income',
                          data: response.data.subscriptionIncome.income,
                          backgroundColor: [
                              'rgba(0, 255, 0, 0.3)',
                              'rgba(0, 0, 255, 0.3)'
                          ]
                      }
                  ] 
              },
              chartData5:{
                labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                datasets:[
                    {
                        label:'Monthly Pay Per View Income',
                        data: response.data.payPerMovieIncome.income,
                        backgroundColor: [
                            'rgba(255,99,132,0.6)',
                            'rgba(54,162,235,0.6)'
                        ]
                    }
                ] 
            },
            chartData6:{
                labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                datasets:[
                    {
                        label:'Monthly Total Income',
                        data: response.data.totalIncome.income,
                        backgroundColor: [
                            'rgba(255,99,132,0.6)',
                            'rgba(54,162,235,0.6)'
                        ]
                    }
                ] 
            }
          });
        })
        .catch(function (error) {
          console.log(error);
        });
      
      
      }
    render() {
        return(
            <div className="chart">
                <AdminNavBar/>
                <div className="row chart-row">
                    <div className="col-sm-6">
                        <Bar
                            data={this.state.chartData}
                            options={{}}
                            width={100}
                            height={20}
                        />
                    </div>
                    <div className="col-sm-6">
                    <Bar
                            data={this.state.chartData1}
                            options={{maintainAspectRatio: false}}
                            width={100}
                            height={20}
                        />
                    </div>
               </div>
               <div className="row chart-row">
                    <div className="col-sm-6">
                        <Bar
                            data={this.state.chartData2}
                            options={{}}
                            width={100}
                            height={20}
                        />
                    </div>
                    <div className="col-sm-6">
                    <Bar
                            data={this.state.chartData3}
                            options={{maintainAspectRatio: false}}
                            width={100}
                            height={20}
                        />
                    </div>
               </div>
               <div className="row chart-row">
                    <div className="col-sm-4">
                        <Bar
                            data={this.state.chartData4}
                            options={{}}
                            width={100}
                            height={20}
                        />
                    </div>
                    <div className="col-sm-4">
                    <Bar
                            data={this.state.chartData5}
                            options={{maintainAspectRatio: false}}
                            width={100}
                            height={20}
                        />
                    </div>
                    <div className="col-sm-4">
                    <Bar
                            data={this.state.chartData6}
                            options={{maintainAspectRatio: false}}
                            width={100}
                            height={20}
                        />
                    </div>
               </div>
                
            </div>
        )
    }
}
export default FinancialReport;