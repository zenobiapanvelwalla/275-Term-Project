import React, {Component} from 'react';
import { Route, withRouter } from 'react-router-dom';
import {BrowserRouter as Router,Switch} from 'react-router-dom';
import Login from "./Login";
import SignUp from "./SignUp";
import NavBar from "./NavBar";
import AdminNavBar from "./AdminNavBar";
import AdminDashboard from "./AdminDashboard";
import AddMovies from "./AddMovies";


class NewerHomePage extends Component {

    render() {
        return (
            <div>
            <Router>
                <Switch>
                    <Route exact path="/signup" component={SignUp}/>
                    <Route exact path="/" component={Login}/>
                    <Route exact path="/dashboard" component={AdminDashboard}/>
                    <Route exact path="/navbar" component={NavBar}/>
                    <Route exact path="/addmovies" component={AddMovies}/>
                    
                </Switch>
            </Router>

            </div>
        );
    }
}

export default withRouter(NewerHomePage);