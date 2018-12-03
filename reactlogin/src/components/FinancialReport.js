import React,{Component} from 'react';
import {Pie, Bar} from 'react-chartjs-2';
import AdminNavBar from './AdminNavBar';
import chartcss from '../custom_css/chart.css';
//https://github.com/jerairrest/react-chartjs-2/blob/master/example/src/components/bar.js
class FinancialReport extends Component{
    constructor(props){
        super(props);
        //axios call to get the report data
        this.state = {
            chartData:{
                labels: ['Jan','Feb'],
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
                labels: ['Jan','Feb'],
                datasets:[
                    {
                        label:'Unique PayPerView Users',
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
            chartData2:{
                labels: ['Jan','Feb'],
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
                labels: ['Jan','Feb'],
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
                labels: ['Jan','Feb'],
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
                labels: ['Jan','Feb'],
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
                labels: ['Jan','Feb'],
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