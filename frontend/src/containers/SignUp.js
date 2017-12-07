import React from 'react';
import { connect } from 'react-redux';
import { signup } from '../redux/actions/signupActions';


class SignUp extends React.Component {

    constructor(props) {
        super(props);
        this.state = {email: '', password: ''};
        this.signup = this.signup.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    signup(event) {
        event.preventDefault();
        const { dispatch } = this.props;
        dispatch(signup(this.state));
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]:  value
        });
    }


    render() {
        return (
            <div className='col-xs-12 col-md-6 col-md-offset-3'>
                <h1>Sign Up</h1>
                <form role='form'>
                    <div className='form-group'>
                        <input type='text'
                               className='form-control input-lg'
                               value={this.state.email}
                               onChange={this.handleChange}
                               name={"email"}
                               placeholder='Email' />
                    </div>
                    <div className='form-group'>
                        <input type='password'
                               className='form-control input-lg'
                               value={this.state.password}
                               onChange={this.handleChange}
                               name={"password"}
                               placeholder='Password' />
                    </div>
                    <button type='submit'
                            className='btn btn-default'
                            onChange={this.handleChange}
                            onClick={this.signup}>Sign up</button>

                </form>
            </div>
        );
    }
}

function mapStateToProps(state, ownProps) {
    const { session} = state;
    return {
        session
    }
}

export default connect(mapStateToProps)(SignUp)