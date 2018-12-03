import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import Modal from 'react-modal';
import config from '../config.js';
import axios from 'axios';

  var wrapperStyle = {
      fontFamily: "Lato",
      fontSize: "1 rem",
      textAlign: "center",
      // boxSizing: "border-box",
      color: "#333"
  };

  var dialogStyle = {
      border: "solid 1px #ccc",
      margin: "10px auto",
      padding: "20px 30px",
      display: "inline-block",
      boxShadow: "0 0 4px #ccc",
      backgroundColor: "#FAF8F8",
      overflow: "hidden",
      position: "relative",
      maxWidth: "450px"
  };
  var inputStyle = {
      margin: "0 5px" ,
      textAlign: "center",
      lineHeight: "80px",
      fontSize: "20px",
      // border_bottom: "solid 1px #ccc",
      boxShadow: "0 0 5px #ccc inset",
      outline: "none",
      width: "40%",
      transition: "all .2s ease-in-out",
      borderRadius: "3px",
      width: "40px",
      height: "40px",
      marginLeft: "15px"
  };
  var marginTopFormStyle = {
      marginTop: "25px",
  };
  var buttonStyle={
      display: "inherit",
      marginLeft: "auto",
      align:"center",
      height: "30px",
      marginTop: "10px",
      textAlign: "center",
      padding: "0px",
      width:"70px"
  }
  
  const customStyles = {
    content : {
      top                   : '40%',
      left                  : '50%',
      right                 : 'auto',
      bottom                : 'auto',
      marginRight           : '-50%',
      transform             : 'translate(-50%, -50%)'
    }
  };
 
class VerifyUser extends Component {
  constructor() {
    super();
 
    this.state = {
      modalIsOpen: true,
    };
 
    this.openModal = this.openModal.bind(this);
    this.afterOpenModal = this.afterOpenModal.bind(this);
    this.closeModal = this.closeModal.bind(this);
    this.verificationCheck = this.verificationCheck.bind(this);
  }
 
  openModal() {
    this.setState({modalIsOpen: true});
  }
 
  afterOpenModal() {
    // references are now sync'd and can be accessed.
    // this.subtitle.style.color = '#f00';
  }
 
  closeModal() {
    this.setState({modalIsOpen: false});
  }

  verificationCheck(e)
  {
    let payload = {
      verification_code : parseInt(this.refs.a.value + this.refs.b.value + this.refs.c.value + this.refs.d.value)
    }
    let path = "/users/verify/" + parseInt(localStorage.getItem('user_id'));
    let self = this;
    axios.post(config.API_URL+path,payload)
      .then(function (response) {
        console.log(response);
        if(response.data.success)
        {
          self.props.history.push('/');
        }
      })
      .catch(function (error) {
      console.log(error);
      });
    
    console.log(payload);
  }

 
  render() {
    const { code, password } = this.state;
    return (
      <div>
        <Modal
          isOpen={this.state.modalIsOpen}
          onAfterOpen={this.afterOpenModal}
          onRequestClose={this.closeModal}
          style={customStyles}
          contentLabel="Example Modal"
        >
 
                <div id="wrapper" style={wrapperStyle}>
                        <div id="dialog" style={dialogStyle}>

                            <h6>Please enter the 4-digit verification code we sent via email:</h6>

                            <div id="form">
                                <input type="text" ref="a" id="text1" style={inputStyle} maxLength="1" size="1" min="0" max="9" pattern="[0-9]{1}" />
                                <input type="text" ref="b" id="text2" style={inputStyle} maxLength="1" size="1" min="0" max="9" pattern="[0-9]{1}" />
                                <input type="text" ref="c" id="text3" style={inputStyle} maxLength="1" size="1" min="0" max="9" pattern="[0-9]{1}" />
                                <input type="text" ref="d" id="text4" style={inputStyle} maxLength="1" size="1" min="0" max="9" pattern="[0-9]{1}" />
                                <button className="btn btn-success" onClick={() =>{this.verificationCheck()}} style={buttonStyle}>Verify</button>
                            </div>
                        </div>
                    </div>
        </Modal>
      </div>
    );
  }
}

export default withRouter(VerifyUser);