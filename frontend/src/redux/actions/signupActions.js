import * as types from './ActionTypes';
import axios from 'axios';
import {browserHistory} from 'react-router';

function signupSuccess(user) {
    return { type: types.SIGN_UP_SUCCESS, user};
}

export function signup(credentials) {

    return function(dispatch){
        return axios.post('backend/signup', credentials)
            .then(function (response) {
                dispatch(signupSuccess(response.data));
                browserHistory.push('/');
            })
            .catch(function (error) {
                // write error handling
            });
    };
}
