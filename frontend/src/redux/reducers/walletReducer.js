import * as types from '../actions/ActionTypes';


export default function walletReducer(state = null, action){
    switch(action.type){
        case types.LOAD_WALLET_SUCCESS:
            return action.wallet;
        case types.SEND_MONEY_SUCCESS:
            return action.wallet;
        case types.REQUEST_MONEY_SUCCESS:
            return action.wallet;
        default:
            return state;
    }
}