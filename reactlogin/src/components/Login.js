import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import NavBar from './NavBar';
import login from "../custom_css/login.css";


class Login extends Component {

    state={
        userdata: {
            emailorusername: '',
            password: '',
        },
        emailorusernameValid: true,
        passwordValid: true,
        msg:false
    }

    constructor(props){
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e){
        e.preventDefault();
        console.log("Call to page ");
        if(this.validateUsername() === true)
        {
            if(this.validatePassword() === true)
            {
                
            }
            else
            {
                this.setState({passwordValid: false})
            }
        }
        else
        {
            //this.state.usernameValid = false;
            this.setState({emailorusernameValid: false})
        }
    }

    validateUsername() {
        var username = this.refs.email.value;
        if (username !== '')
        {
            return (true)
        }
        return (false)
    }
    validatePassword(){
        var password = this.refs.password.value;
        if (password !== '')
        {
            return (true)
        }
        return (false)
    }

    render() {
        return (
            <div>
                <NavBar/>
                <div className="container">
                <div className="d-flex justify-content-center h-100">
                    <div className="card">
                        <div className="card-header">
                            <br/>
                            <h3>Sign In</h3>
                            <div className="d-flex justify-content-end social_icon">
                                <span><i className="fab fa-facebook-square"></i></span>
                                <span><i className="fab fa-google-plus-square"></i></span>
                                <span><i className="fab fa-twitter-square"></i></span>
                            </div>
                        </div>
                        <div >
                            {this.props.loginMsg && (
                                <div className="alert alert-warning text-danger small" role="alert">
                                    {this.props.loginMsg}
                                </div>
                            )}
                        </div>
                        <div className="card-body">
                                <div className="input-group form-group">
                                    <div className="input-group-prepend">
                                        <span className="input-group-text"><i className="fas fa-user"></i></span>
                                    </div>
                                    { this.state.emailorusernameValid ? null : <div className="text-input-error-wrapper text-left errormessage">Username is required.</div>}
                                    <input type="text" name = "email" className="form-control" placeholder="email"
                                           ref="email"
                                           onFocus={(event) => {
                                           this.setState({emailorusernameValid: true, msg : false});
                                    }} />

                                </div>
                                <div className="input-group form-group">
                                    <div className="input-group-prepend">
                                        <span className="input-group-text"><i className="fas fa-key"></i></span>
                                    </div>
                                    { this.state.passwordValid ? null : <div className="text-input-error-wrapper text-left errormessage">Password is required.</div>}
                                    <input name = "password" type="password" className="form-control" placeholder="password"
                                           ref = "password"
                                           onFocus={(event) => {
                                               this.setState({passwordValid: true , msg: false});
                                           }}
                                    />
                                </div>
                                <div className="form-group">
                                    <button name="Login" value="Login" className="btn float-right login_btn"
                                           onClick={(event) => {
                                               this.handleSubmit(event)
                                           }}>Sign In</button>
                                </div>
                        </div>
                        <div className="card-footer">
                            <div className="d-flex justify-content-center links">
                                Don't have an account?<a href="signup">Sign Up</a>
                            </div>
                            <div className="d-flex justify-content-center">
                                <a href="signup">Forgot your password?</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        );
    }
}

export default withRouter(Login);