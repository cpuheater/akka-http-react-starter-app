import React from 'react';
import { connect } from 'react-redux';
import { loadWallet } from '../redux/actions/loadWalletActions';
import { sendMoney } from '../redux/actions/sendMoneyActions';
import { requestMoney } from '../redux/actions/requestMoneyActions';

class Dashboard extends React.Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.sendMoney = this.sendMoney.bind(this);
        this.requestMoney = this.requestMoney.bind(this);
        this.state = {send: '', request: ''};
    }

    componentWillMount() {
        const user = JSON.parse(sessionStorage.user);
        this.props.loadWallet(user.id);
    }

    sendMoney(event) {
        event.preventDefault();
        const user = JSON.parse(sessionStorage.user);
        this.props.sendMoney({amount: parseInt(this.state.send), id: user.id});
    }

    requestMoney(event) {
        event.preventDefault();
        const user = JSON.parse(sessionStorage.user);
        this.props.requestMoney({amount: parseInt(this.state.request), id: user.id})
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
        const {wallet} = this.props;
        const user = JSON.parse(sessionStorage.user);
        return (
            <div>

              <div className="row" style={{marginBottom: "10px"}}>
                  <div className="col-xs-12 col-md-6 col-md-offset-3 text-center">
                      <h1>{user.email}</h1>
                      <h1>Wallet Balance</h1>
                      { !(wallet ==null) &&
                          <h1 style={{fontSize: "60px"}}>{wallet.balance} USD</h1>
                      }
                  </div>
              </div>
              <div className="row" style={{marginBottom: "10px"}}>
                  <div className="col-xs-12 col-md-6 col-md-offset-3" >
                      <form>
                          <div className="form-group">
                              <input className="form-control"
                                     name="send"
                                     value={this.state.password}
                                     onChange={this.handleChange}
                                     placeholder="Amount">
                              </input>
                          </div>
                          <button type="submit" className="btn btn-default" onClick={this.sendMoney}>Send Money</button>
                      </form>
                  </div>
              </div>
              <div className="row">
                  <div className="col-xs-12 col-md-6 col-md-offset-3" >
                      <form>
                          <div className="form-group">
                              <input name="request"
                                     value={this.state.password}
                                     onChange={this.handleChange}
                                     className="form-control"
                                     placeholder="Amount">
                              </input>
                          </div>
                          <button type="submit" className="btn btn-default" onClick={this.requestMoney}>Request Money</button>
                      </form>
                  </div>
              </div>
            </div>
        );
    }
}

export default connect(
    ({ wallet}) => ({
        wallet
    }),
    { loadWallet,  sendMoney, requestMoney}
)((Dashboard));
