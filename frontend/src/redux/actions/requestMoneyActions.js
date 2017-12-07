import * as types from './ActionTypes';
import axios from 'axios';

export function requestMoney({id, amount}) {
    return function(dispatch){
        return axios.post(`backend/wallets/${id}/requestmoney`, {amount})
            .then(function (response) {
                dispatch(requestMoneySuccess(response.data));
            })
            .catch(function (error) {
                // write error handling
            });
    };
}


export function requestMoneySuccess(wallet) {
    return {
        type: types.REQUEST_MONEY_SUCCESS,
        wallet: wallet
    };
}
