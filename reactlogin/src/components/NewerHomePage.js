import React, {Component} from 'react';
import { Route, withRouter } from 'react-router-dom';
import {Link,BrowserRouter as Router,Switch} from 'react-router-dom';
import * as API from '../api/API';
import Login from "./Login";
import Message from "./Message";
import Welcome from "./Welcome";
import SignUp from "./SignUp";
import NavBar from "./NavBar";
import AdminDashboard from "./AdminDashboard"


class NewerHomePage extends Component {

    state = {
        isLoggedIn: false,
        message: '',
        username: ''
    };

    handleSubmit = (userdata) => {
        API.doLogin(userdata)
            .then((status) => {
                if (status === 201) {
                    this.setState({
                        isLoggedIn: true,
                        message: "Welcome to my App..!!",
                        username: userdata.username
                    });
                    this.props.history.push("/welcome");
                } else if (status === 401) {
                    this.setState({
                        isLoggedIn: false,
                        message: "Wrong username or password. Try again..!!"
                    });
                }
            });
    };

    render() {
        return (
            <div>
            <Router>
                <Switch>
                    <Route exact path="/" render={() => (
                        <div>
                            <Message message="You have landed on my App !!"/>
                            <button className="btn btn-success" onClick={() => {
                                this.props.history.push("/login");
                            }}>
                                Login
                            </button>
                        </div>
                    )}/>

                    <Route exact path="/login" render={() => (
                        <div>
                            <Login handleSubmit={this.handleSubmit}/>
                            <Message message={this.state.message}/>
                        </div>
                    )}/>
                    <Route exact path="/welcome" render={() => (
                        <Welcome username={this.state.username}/>
                    )}/>
                    <Route path="/signup" component={SignUp}/>
                    <Route path="/dashboard" component={AdminDashboard}/>
                    <Route path="/navbar" component={NavBar}/>
                </Switch>
            </Router>

            </div>
        );
    }
}

export default withRouter(NewerHomePage);