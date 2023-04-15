import React from "react";
import _ from "lodash";
import { connect } from "react-redux";
import { Form, Table,Button } from "react-bootstrap";
import { resetErrors } from "../actions/errors";
import AccountForm from './accountForm';
import { Link } from 'react-router-dom';
import Header from "./Header";

class Profile extends React.Component {
  state = {
    username: "",
    email: "",
    errorMsg: "",
    isSubmitted: false,
    accounts: [],
    selectedType: 'summary',
    user_id:'',
    type:''
  };

  setSelectedType = (selectedType) => {
    this.setState({ selectedType });
    if(selectedType === 'summary') this.refreshPage()
  };

  refreshPage() {
    window.location.reload();
  }

  componentDidMount() {
    const { profile } = this.props;
    const userid = localStorage.getItem("user_id");
    this.setState({user_id:userid})
    if (!_.isEmpty(profile)) {
      const { username, email,type } = profile;
      this.setState({
        username,
        email,
        type
      });
    }
    fetch("http://localhost:8080/api/v1/user/" + userid)
      .then((response) => response.json())
      .then((data) => {
        this.setState({
          username: data.username,
          email: data.email,
          type:data.type
        });
      })
      .catch((error) => console.log(error));

    fetch("http://localhost:8080/api/v1/accounts/user/" + userid)
      .then((response) => response.json())
      .then((data) => {
        this.setState({
          accounts: data,
        });
      })
      .catch((error) => console.log(error));
  }

  componentDidUpdate(prevProps) {
    if (!_.isEqual(prevProps.errors, this.props.errors)) {
      this.setState({
        errorMsg: this.props.errors,
      });
    }
    if (!_.isEqual(prevProps.profile, this.props.profile)) {
      const { username, email } = this.props.profile;
      this.setState({ username, email });
    }
  }

  componentWillUnmount() {
    this.props.dispatch(resetErrors());
  }

  handleAccountClick = (accountNumber) => {
    this.props.history.push(`/statement/${accountNumber}`);
  };

  render() {
    const { errorMsg, username, email, isSubmitted, accounts,selectedType,type } = this.state;
    return (
      <div>
        <header>
        <div className="links">
        <Link to="/profile" className="link">
          Account
        </Link>
        <Link to="/" className="link">
          Logout
        </Link>
      </div>
      </header>
      <div>
        <Form onSubmit={this.handleSubmit} className="profile-form">
          {errorMsg && errorMsg.update_error ? (
            <p className="errorMsg centered-message">{errorMsg.update_error}</p>
          ) : (
            isSubmitted && (
              <p className="successMsg centered-message">
                Profile updated successfully.
              </p>
            )
          )}
          <div className="account">
          <Button
            variant="primary"
            className={`${
              selectedType === 'withdraw' ? 'active account-btn' : 'account-btn'
            }`}
            onClick={() => this.setSelectedType('withdraw')}
          >
            Withdraw
          </Button>
          <Button
            variant="secondary"
            className={`${
              selectedType === 'deposit' ? 'active account-btn' : 'account-btn'
            }`}
            onClick={() => this.setSelectedType('deposit')}
          >
            Deposit
          </Button>
          <Button
            variant="info"
            className={`${
              selectedType === 'transfer' ? 'active account-btn' : 'account-btn'
            }`}
            onClick={() => this.setSelectedType('transfer')}
          >
            Funds Transfer
          </Button>
          <Button
            variant="info"
            className={`${
              selectedType === 'summary' ? 'active account-btn' : 'account-btn'
            }`}
            onClick={() => this.setSelectedType('summary')}
          >
            Summary
          </Button>
          {type==="ADMIN" &&
         
          <Button
            variant="info"
            className='account-btn'
          >
            <a href={`createAccount/`} style={{color:'white'}}>Create Account</a>
          </Button>
          }
          </div>
          <div>
          {selectedType === 'withdraw' || selectedType === 'deposit' || selectedType === 'statement' || selectedType === 'transfer' ? (
            <AccountForm selectedType={selectedType} />
          ) : (
            null
          )}
        </div>
          <Form.Group controlId="email">
            <Form.Label>Email address: </Form.Label>
            <span className="label-value">{email}</span>
          </Form.Group>
          <Form.Group controlId="first_name">
            <Form.Label>UserName: </Form.Label>
            <span className="label-value">{username}</span>
          </Form.Group>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>Account Number</th>
                <th>Balance</th>
              </tr>
            </thead>
            <tbody>
              {accounts.map((account) => (
                <tr key={account.idaccounts}>
                  <td>
                   <a href={`statement/${account.accountNumber}`}>
                    {account.accountNumber}
                    </a>
                    </td>
                  <td>{account.availableBalance}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Form>
      </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  profile: state.profile,
  errors: state.errors,
});

export default connect(mapStateToProps)(Profile);
