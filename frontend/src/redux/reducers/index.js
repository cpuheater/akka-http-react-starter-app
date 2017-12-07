import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import session from './loginSignupReducer';
import wallet from './walletReducer';

export default combineReducers({
    session,
    wallet,
    routing: routerReducer
});
