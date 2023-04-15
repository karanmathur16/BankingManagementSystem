import React from "react";
import { connect } from "react-redux";
import { Route, Switch, Router  } from "react-router-dom";
import Login from "../components/Login";
import Register from "../components/register";
import Profile from "../components/profile";
import { createBrowserHistory } from "history";
import Header from "../components/Header"
import Statement from "../components/statement";
import CreateAccount from "../components/createAccount";

export const history = createBrowserHistory();

const AppRouter = () => {
  return (
    <Router history={history}>
      <div>
        <Header/>
        <div className="container">
          <Switch>
            <Route path="/" component={Login} exact={true} />
            <Route path="/register" component={Register} />
            <Route path="/profile" component={Profile} />
            <Route path="/statement/:accountNumber" component={Statement} />
            <Route path="/createAccount/" component={CreateAccount} />
          </Switch>
        </div>
      </div>
    </Router>
  );
};

const mapStateToProps = (state) => ({
  auth: state.auth,
});

export default connect(mapStateToProps)(AppRouter);
