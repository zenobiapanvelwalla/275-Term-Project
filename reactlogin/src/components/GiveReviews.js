import React, {Component} from 'react';
import * as API from '../api/API';
// import {Checkbox} from 'react-select';
import Picky from 'react-picky';
import 'react-picky/dist/picky.css'; // Include CSS
import config from '../config.js';
import axios from 'axios';
import css from '../custom_css/moviefilter.css'
import ReactStars  from 'react-stars';
import Modal from 'react-modal';

const customStyles = {
    content : {
      top                   : '50%',
      left                  : '50%',
      right                 : 'auto',
      bottom                : 'auto',
      marginRight           : '-50%',
      transform             : 'translate(-50%, -50%)'
    }
  };


class GiveReviews extends Component {

    constructor(props) {
        super(props)
        this.state = {
            modalIsOpen: true,
            rating:0
          };
          this.openModal = this.openModal.bind(this);
          this.afterOpenModal = this.afterOpenModal.bind(this);
          this.closeModal = this.closeModal.bind(this);
    }

    openModal() {
        this.setState({modalIsOpen: true});
      }
     
      afterOpenModal() {

      }
     
      closeModal() {
        this.setState({modalIsOpen: false});
      }

   
    render() {
    
        return (
            <div>
            <button onClick={this.openModal}>Open Modal</button>
            <Modal
                isOpen={this.state.modalIsOpen}
                onAfterOpen={this.afterOpenModal}
                onRequestClose={this.closeModal}
                style={customStyles}
                contentLabel="Example Modal"
                >
                <h4>How do you like the movie</h4>
                <ReactStars
                    value = {this.state.rating}
                    count={5}
                    onChange={(event)=>{
                        this.setState({rating : event})
                        console.log("Rating : " + event);
                    }}
                    size={24}
                    color2={'#ffd700'} />

                    <textarea className="form-control"  rows="3"></textarea>
                 <br/>   
                <button onClick={(event) => {
                    if(this.state.rating <= 0)
                    {

                    }
                    else
                    {
                        var payload = {
                            userId : 1,
                            movieId : 1,
                            starRating : this.state.rating,
                            reviewText : ''
                        }

                        let self = this;
                        axios.post(config.API_URL+'/movies/add-review',payload)
                        .then(function (response) {
                        console.log("Attributes " + JSON.stringify(response.data));
                        self.setState({actor:response.data});
                        })
                        .catch(function (error) {
                        console.log(error);
                        });
                    }
                }}>Submit</button>
                <button onClick={this.closeModal}>Cancel</button>
                </Modal>
            </div>
        );
    }
}

export default GiveReviews;