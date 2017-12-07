import React, { Component} from 'react';
import { connect } from 'react-redux';
import { logout } from '../redux/actions/loginActions';

class App extends Component {

    constructor(props, context){
        super(props, context);
        this.logout = this.logout.bind(this);
    }

    logout() {
        const { dispatch } = this.props;
        dispatch(logout());
    }

    render() {
        return (
            <div className="container-fluid">
              <div className="header clearfix">
                <nav>
                  {
                    !!!sessionStorage.user  &&
                    <ul className="nav pull-right main-font">
                    <li role="presentation"><a  href="/login">Login</a></li>
                    <li role="presentation"><a  href="/signup">Signup</a></li>
                    </ul>
                  }
                  {
                      sessionStorage.user  &&
                      <ul className="nav pull-right main-font">
                          <li role="presentation"><a  href="/login" onClick={this.logout}>Logout</a></li>
                      </ul>
                  }
                </nav>
              </div>
              <div>
                  {this.props.children}
              </div>
            </div>
        )
    }
};


function mapStateToProps(state, ownProps) {
    const { session} = state;
    return {
        session
    }
}

export default connect(mapStateToProps)(App)