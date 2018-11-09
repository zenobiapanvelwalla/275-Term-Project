import React, {Component} from 'react';
// import PropTypes from 'prop-types';
import config from '../config.js';

class SignUp extends Component {

    // static propTypes = {
    //     handleSubmit: PropTypes.func.isRequired
    // };

    state = {
        email:'',
        displayName:'',
        userName: '',
        password: ''
    };

    componentWillMount(){
        this.setState({
            email:'',
            displayName:'',
            userName: '',
            password: ''
        });
    }
    handleSubmit(event) {
        event.preventDefault();
        var data = {
            email:this.refs.email.value,
            displayName: this.refs.displayName.value,
            password:this.refs.password.value
        }
        //config.API_URL  ==>use this before all api call urls
        // fetch('/api/form-submit-url', {
        //   method: 'POST',
        //   body: data,
        // });
        console.log(data);
      }
    render() {
        return (
            <div className="container">
                <div className="d-flex justify-content-center h-100">
                    <div className="card">
                        <div className="card-header">
                            <br/>
                            <h3>Sign Up</h3>
                            <div className="d-flex justify-content-end social_icon">
                                <span><i className="fab fa-facebook-square"></i></span>
                                <span><i className="fab fa-google-plus-square"></i></span>
                                <span><i className="fab fa-twitter-square"></i></span>
                            </div>
                        </div>
                        <div className="card-body">
                            <form onSubmit={this.handleSubmit.bind(this)}>
                                <div className="input-group form-group">
                                    <div className="input-group-prepend">
                                        <span className="input-group-text"><i className="fas fa-user"></i></span>
                                    </div>
                                    <input type="text" ref="email" className="form-control" placeholder="Email"/>

                                </div>
                                <div className="input-group form-group">
                                    <div className="input-group-prepend">
                                        <span className="input-group-text"><i className="fas fa-user"></i></span>
                                    </div>
                                    <input type="text" ref="displayName" className="form-control" placeholder="Display Name"/>

                                </div>
                                <div className="input-group form-group">
                                    <div className="input-group-prepend">
                                        <span className="input-group-text"><i className="fas fa-key"></i></span>
                                    </div>
                                    <input type="password" ref="password" className="form-control" placeholder="Password"/>
                                </div>
                                <div className="form-group">
                                    <input type="submit" value="Login" className="btn float-right login_btn" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default SignUp;