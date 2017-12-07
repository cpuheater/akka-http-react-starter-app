import 'rxjs';
import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, Route, browserHistory, IndexRoute } from 'react-router';
import { syncHistoryWithStore } from 'react-router-redux';
import configureStore from './redux/store/configureStore';
import App from './containers/App';
import Dashboard from './containers/Dashboard';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './public/style/styles.css';
import Login from './containers/Login';
import Signup from './containers/SignUp';

const store = configureStore();
const history = syncHistoryWithStore(
  browserHistory,
  store
);

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Route path='/' component={App} >
        <IndexRoute component={Dashboard} onEnter={requireAuth}/>
        <Route path='login' component={Login} />
        <Route path='signup' component={Signup} />
      </Route>
    </Router>
  </Provider>,
  document.getElementById('app')
);


function requireAuth(nextState, replace) {
    if (!sessionStorage.user) {
        replace({
            pathname: '/login',
            state: { nextPathname: nextState.location.pathname }
        })
    }
}
