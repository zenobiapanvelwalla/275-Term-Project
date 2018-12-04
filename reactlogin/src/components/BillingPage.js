import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import NavBar from './NavBar';
import billing from "../custom_css/billing.css";

//https://www.w3schools.com/howto/howto_css_checkout_form.asp
class BillingPage extends Component {

    state={
        
    }

    constructor(props){
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e){
        e.preventDefault();
        console.log("Post Request To Billing Page");
        //movie id, movie type (eg. pay per view or  paid) and amount to be paid, to be sent in the request body

        
    }

    
    

    render() {
        return (
            <div className="billing-container">
                <NavBar/>
                
              
                <div className="row">
                    <div className="col-75B col-75">
                        
                            <form action="/billing">

                                <div className="row rowB">
                                    {/* <div className="col-50B col-50">
                                        <h3>Billing Address</h3>
                                        <label htmlFor="fname"><i className="fa fa-user"></i> Full Name</label>
                                        <input type="text" id="fname" name="firstname" placeholder="John M. Doe"/>
                                        <label htmlFor="email"><i className="fa fa-envelope"></i> Email</label>
                                        <input type="text" id="email" name="email" placeholder="john@example.com"/>
                                        <label htmlFor="adr"><i className="fa fa-address-card-o"></i> Address</label>
                                        <input type="text" id="adr" name="address" placeholder="542 W. 15th Street"/>
                                        <label htmlFor="city"><i className="fa fa-institution"></i> City</label>
                                        <input type="text" id="city" name="city" placeholder="New York"/>

                                        <div className="row">
                                            <div className="col-50B col-50">
                                                <label htmlFor="state">State</label>
                                                <input type="text" id="state" name="state" placeholder="NY"/>
                                            </div>
                                            <div className="col-50B col-50">
                                                <label htmlFor="zip">Zip</label>
                                                <input type="text" id="zip" name="zip" placeholder="10001"/>
                                            </div>
                                        </div>
                                    </div> */}

                                 <div className="col-75B col-75">
                                        <h3>Payment</h3>
                                        <label className="labelB" htmlFor="fname">Accepted Cards</label>
                                        <div className="icon-container">
                                        <i className="fa fa-cc-visa visa"></i>
                                        <i className="fa fa-cc-amex amex"></i>
                                        <i className="fa fa-cc-mastercard mastercard"></i>
                                        <i className="fa fa-cc-discover discover"></i>
                                        </div>
                                        <label className="labelB" htmlFor="cname">Name on Card</label>
                                        <input className="inputB" type="text" id="cname" name="cardname" placeholder="John More Doe"/>
                                        <label className="labelB" htmlFor="ccnum">Credit card number</label>
                                        <input className="inputB" type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444"/>
                                        <label className="labelB" htmlFor="expmonth">Exp Month</label>
                                        <input className="inputB" type="text" id="expmonth" name="expmonth" placeholder="September"/>

                                        <div className="row">
                                        <div className="col-50B col-50">
                                            <label className="labelB" htmlFor="expyear">Exp Year</label>
                                            <input className="inputB" type="text" id="expyear" name="expyear" placeholder="2018"/>
                                        </div>
                                        <div className="col-50B col-50">
                                            <label className="labelB" htmlFor="cvv">CVV</label>
                                            <input className="inputB" type="text" id="cvv" name="cvv" placeholder="352"/>
                                        </div>
                                        </div>
                                    </div>

                                </div>
                                
                                <input type="submit" value="Continue to checkout" className="btn btnB"/>
                            </form>
                        
                    </div>

                    <div className="col-25B col-25">
                        <div className="billing-container">
                            <h4>Cart 
                                <span className="price">
                                <i className="fa fa-shopping-cart"></i> 
                                <b>1</b>
                                </span>
                            </h4>
                            {/* <p><a href="#">Product 1</a> <span className="price">$15</span></p>
                            <p><a href="#">Product 2</a> <span className="price">$5</span></p>
                            <p><a href="#">Product 3</a> <span className="price">$8</span></p> */}
                            <p><a href="#">Billing detail, based on pay per view or subscripttion</a></p>
                            <hr/>
                            <p>Total <span className="price"><b>$Total amount (whatever it is)</b></span></p>
                        </div>
                    </div>
                    </div>
                
            </div>
           
        );
    }
}

export default withRouter(BillingPage);