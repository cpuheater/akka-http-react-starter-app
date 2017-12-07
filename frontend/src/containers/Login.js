import React from 'react';
import { connect } from 'react-redux';
import { login } from '../redux/actions/loginActions';


class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {email: '', password: ''};
        this.login = this.login.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    login(event) {
        event.preventDefault();
        const { dispatch } = this.props;
        dispatch(login(this.state));
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
                <h1>Login</h1>
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
                            onClick={this.login}>Login</button>
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

export default connect(mapStateToProps)(Login)