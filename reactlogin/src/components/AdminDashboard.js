import React, {Component} from 'react';
import config from '../config.js';
import admin_dashboard from "../custom_css/admin_dashboard.css";
import admin_dshbd from "../custom_js/admin_dshbd";
import $ from "jquery";
import NavBar from "./NavBar";

class AdminDashboard extends Component {

    constructor(props){
        super(props);
        this.state={
          displayName:'ZENOBIA PANVELWALA'
        }

        this.w3_close.bind(this);
        this.w3_open.bind(this);
        
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
    
    

    render() {
        return (
            <div>
                <NavBar/>
                <div className="w3-sidebar w3-bar-block w3-card w3-animate-left"  id="mySidebar">
                    <button className="w3-bar-item w3-button w3-large"
                    onClick={this.w3_close}>Close &times;</button>
                    <a href="#" className="w3-bar-item w3-button">Link 1</a>
                    <a href="#" className="w3-bar-item w3-button">Link 2</a>
                    <a href="#" className="w3-bar-item w3-button">Link 3</a>
                    </div>

                    <div id="main">

                        <div className="w3-teal">
                            <button id="openNav" className="w3-button w3-teal w3-xlarge" onClick={this.w3_open}>&#9776;</button>
                            <div className="w3-container">
                                <h1>My Page</h1>
                            </div>
                        </div>

                        <div className="w3-container">
                            <p>In this example, the sidebar is hidden (style="display:none")</p>
                            <p>It is shown when you click on the menu icon in the top left corner.</p>
                            <p>When it is opened, it shifts the page content to the right.</p>
                            <p>We use JavaScript to add a 25% left margin to the div element with id="main" when this happens. The value "25%" matches the width of the sidebar.</p>
                        </div>

                    </div>
            </div>
            
        );
    }
}

export default AdminDashboard;