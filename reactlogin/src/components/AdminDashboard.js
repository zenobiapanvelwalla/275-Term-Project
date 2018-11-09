import React, {Component} from 'react';
import config from '../config.js';
import admin_dashboard from "../custom_css/admin_dashboard.css";
import admin_dshbd from "../custom_js/admin_dshbd";
import $ from "jquery";

class AdminDashboard extends Component {

    constructor(props){
        super(props);
        this.state={
          displayName:'ZENOBIA PANVELWALA'
        }
        
      }
    
    

    render() {
        return (
            <div>
                <div id="mySidenav" className="sidenav">
                {/*<a href="javascript:void(0)" className="closebtn" onClick="javascript:closeNav()">&times;</a>*/}
                <a href="#">About</a>
                <a href="#">Services</a>
                <a href="#">Clients</a>
                <a href="#">Contact</a>
            </div>
            
            {/* Use any element to open the sidenav */}
            <span onClick="openNav()">open</span>
            
            {/* <!-- Add all page content inside this div if you want the side nav to push page content to the right (not used if you only want the sidenav to sit on top of the page --> */}
            <div id="main">
                ...
            </div>
            
            </div>
    
    
    
        
          
        );
    }
}

export default AdminDashboard;