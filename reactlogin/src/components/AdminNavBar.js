import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';


class AdminNavBar extends Component
{
    render(){
        return(
            <div>
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <a href="#" className="navbar-brand">Logo</a>
                    <button className="navbar-toggler" data-target="#navigation" data-control="navigation" data-toggle="collapse">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navigation">
                        <ul className="nav navbar-nav">
                            <li className="nav-item active">
                                <a href="#" className="nav-link">Dashboard</a>
                            </li>
                            <li className="nav-item">
                                <a href="#" className="nav-link">Movies</a>
                            </li>
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" data-toggle="dropdown" id="preview" href="#" role="button" aria-haspopup="true" aria-expanded="false">Manage Movies</a>
                                <div className="dropdown-menu" >
                                    <a className="dropdown-item" href="#">Add</a>
                                    <a className="dropdown-item" href="#">Update</a>
                                    <a className="dropdown-item" href="#">All Movies</a>
                                    
                                </div>
                            </li>
                            <li className="nav-item">
                                <a href="#" className="nav-link">Manage Users</a>
                            </li>
                            <li className="nav-item">
                                <a href="#" className="nav-link">Logout</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        )
    }
}

export default withRouter(AdminNavBar);