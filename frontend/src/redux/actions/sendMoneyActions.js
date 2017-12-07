import * as types from './ActionTypes';
import axios from 'axios';

export function sendMoney({id, amount}) {
    return function(dispatch){
        return axios.post(`backend/wallets/${id}/sendmoney`, {amount})
            .then(function (response) {
                dispatch(sendMoneySuccess(response.data));
            })
            .catch(function (error) {
                // write error handling
            });
    };
}


export function sendMoneySuccess(wallet) {
    return {
        type: types.SEND_MONEY_SUCCESS,
        wallet
    };
}
