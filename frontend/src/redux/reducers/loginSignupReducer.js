import {browserHistory} from 'react-router';
import * as types from '../actions/ActionTypes';

export default function loginReducer(state = false, action) {
    switch(action.type) {
        case types.LOG_IN_SUCCESS:
            sessionStorage.setItem('user', JSON.stringify(action.user));
            return !!sessionStorage.user;
        case types.SIGN_UP_SUCCESS:
            sessionStorage.setItem('user', JSON.stringify(action.user));
            return !!sessionStorage.user;
        case types.LOG_OUT:
            browserHistory.push('/login');
            return !!sessionStorage.user;
        default:
            return state;
    }
}