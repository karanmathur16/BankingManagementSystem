import React from "react";
import { Form, Button } from "react-bootstrap";
import { withRouter,Link } from "react-router-dom";
import Header from "./Header";



class CreateAccount extends React.Component {
  state = {
    availableBalance: "",
    accountNumber: "",
    accountType: "",
    accountStatus: "ACTIVE",
    userId: "",
    successMsg: "",
    errorMsg: "",
  };

  createAccount = async (event) => {
    event.preventDefault();
    const {
      accountNumber,
      accountType,
      accountStatus,
      availableBalance,
      userId,
    } = this.state;
    const response = await fetch(
      "http://localhost:8080/api/v1/accounts/create",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          accountNumber,
          accountType,
          accountStatus,
          availableBalance,
          userId,
        }),
      }
    );
    let data = await response.json();
    if (response.ok) {
      // Update the state to reflect the successful deposit
      this.setState({
        successMsg: {
          deposit_success: "Account Created Successfully",
        },
      });
    } else {
      this.setState({
        errorMsg: {
          deposit_error: data.error,
        },
      });
    }
  };

  handleAmountChange = (event) => {
    this.setState({ availableBalance: event.target.value });
  };

  handleAccountTypeChange = (event) => {
    this.setState({ accountType: event.target.value });
  };

  handleAccountChange = (event) => {
    this.setState({ accountNumber: event.target.value });
  };

  handleUserChange = (event) => {
    this.setState({ userId: event.target.value });
  };

  render() {
    const { accountNumber, accountType, availableBalance, successMsg, userId } =
      this.state;
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
        <div className="account-form col-md-6 offset-md-3">
          {successMsg && successMsg.deposit_success && (
            <p className="successMsg">{successMsg.deposit_success}</p>
          )}
          <Form className="account-form">
            <Form.Group controlId="accnt_no">
              <Form.Label>Account number: </Form.Label>
              <Form.Control
                type="text"
                placeholder={`Enter Account Number`}
                value={accountNumber}
                onChange={this.handleAccountChange}
              />
            </Form.Group>
            <Form.Group controlId="accnt_no">
              <Form.Label>Account type: </Form.Label>
              <Form.Control
                type="text"
                placeholder={`Enter Account Type`}
                value={accountType}
                onChange={this.handleAccountTypeChange}
              />
            </Form.Group>
            <Form.Group controlId="accnt_no">
              <Form.Label>Initial Account Balance: </Form.Label>
              <Form.Control
                type="text"
                placeholder={`Enter Initial Account Balance: `}
                value={availableBalance}
                onChange={this.handleAmountChange}
              />
            </Form.Group>
            <Form.Group controlId="accnt_no">
              <Form.Label>User ID: </Form.Label>
              <Form.Control
                type="text"
                placeholder={`Enter User ID of the user: `}
                value={userId}
                onChange={this.handleUserChange}
              />
            </Form.Group>
            <Button
              variant="primary"
              type="submit"
              onClick={this.createAccount}
            >
              Submit
            </Button>
          </Form>
        </div>
      </div>
    );
  }
}

export default withRouter(CreateAccount);
