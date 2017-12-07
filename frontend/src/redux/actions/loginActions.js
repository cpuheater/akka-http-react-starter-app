import * as types from './ActionTypes';
import axios from 'axios';
import {browserHistory} from 'react-router';

function loginSuccess(user) {
    return { type: types.LOG_IN_SUCCESS, user};
}


export function login(credentials) {

    return function(dispatch){
        return axios.post('backend/login', credentials)
            .then(function (response) {
                dispatch(loginSuccess(response.data));
                browserHistory.push('/');
            })
            .catch(function (error) {
                // write error handling
            });
    };
}


export function logout() {
    sessionStorage.removeItem('user');
    return {type: types.LOG_OUT}
}