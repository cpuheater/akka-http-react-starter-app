import * as types from './ActionTypes';
import axios from 'axios';

export function loadWallet(userId) {
    return function(dispatch){
        return axios.get('backend/wallets/'+userId)
            .then(function (response) {
                dispatch(loadWalletSuccess(response.data));
            })
            .catch(function (error) {
                debugger
                // write error handling
            });
    };
}


export function loadWalletSuccess(wallet) {
    return {
        type: types.LOAD_WALLET_SUCCESS,
        wallet: wallet
    };
}
