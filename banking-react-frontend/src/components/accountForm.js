import React from "react";
import { Form, Button } from "react-bootstrap";
import { connect } from "react-redux";
import { validateFields } from "../utils/common";

class AccountForm extends React.Component {
  state = {
    amount: "",
    editAccount: false,
    accountNumber: "",
    fromAccount: "",
    toAccount: "",
    ifsc: "",
    errorMsg: "",
    successMsg: "",
  };

  handleUpdateAccount = (ifsc) => {
    const fieldsToValidate = [{ ifsc }];

    const allFieldsEntered = validateFields(fieldsToValidate);
    if (!allFieldsEntered) {
      this.setState({
        errorMsg: {
          update_error: "Please enter ifsc code.",
        },
      });
    } else {
      this.setState({
        errorMsg: "",
      });
    }
  };

  handleWithdraw = async () => {
    const { accountNumber, amount } = this.state;
    const response = await fetch(
      "http://localhost:8080/api/v1/transactions/withdraw",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ accountNumber, amount }),
      }
    );
    let data = await response.json();
    if (response.ok) {
      // Update the state to reflect the successful deposit
      this.setState({
        amount: "",
        successMsg: {
          withdraw_success: "Amount withdrawn successfully.",
        },
      });
    } else {
      this.setState({
        errorMsg: {
          withdraw_error: "Not enough funds to transfer",
        },
      });
    }
  };

  handleDeposit = async () => {
    const { accountNumber, amount } = this.state;
    const response = await fetch(
      "http://localhost:8080/api/v1/transactions/deposit",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ accountNumber, amount }),
      }
    );
    let data = await response.json();
    if (response.ok) {
      // Update the state to reflect the successful deposit
      this.setState({
        amount: "",
        successMsg: {
          deposit_success: "Amount deposited successfully.",
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

  handleTransfer = async () => {
    const { fromAccount, toAccount, amount } = this.state;
    const response = await fetch(
      "http://localhost:8080/api/v1/transactions/fund-transfer",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ fromAccount, toAccount, amount }),
      }
    );
    let data = await response.json();
    if (response.ok) {
      // Update the state to reflect the successful deposit
      this.setState({
        amount: "",
        successMsg: {
          transfer_success:
            data.message + " with TransactionId: " + data.transactionId,
        },
      });
    } else {
      this.setState({
        errorMsg: {
          transfer_error: "Not enough funds to transfer",
        },
      });
    }
  };

  handleAmountChange = (event) => {
    let numericValue = event.target.value;
    if (isNaN(numericValue) || numericValue < 0) {
      this.setState({
        errorMsg: {
          deposit_error: "Invalid Amount Entered",
        },
      });
    } else this.setState({ amount: numericValue });
  };

  handleAccountChange = (event) => {
    this.setState({ accountNumber: event.target.value });
  };

  handleFromAccountChange = (event) => {
    this.setState({ fromAccount: event.target.value });
  };

  handleToAccountChange = (event) => {
    this.setState({ toAccount: event.target.value });
  };

  handleEditAccount = (event) => {
    event.preventDefault();
    this.setState((prevState) => ({ editAccount: !prevState.editAccount }));
  };

  handleInputChange = (event) => {
    this.setState({
      ifsc: event.target.value,
    });
  };

  handleOnSubmit = (event) => {
    event.preventDefault();

    const { selectedType } = this.props;

    if (selectedType === "withdraw") {
      this.handleWithdraw();
    } else if (selectedType === "transfer") {
      this.handleTransfer();
    } else if (selectedType === "statement") {
    } else {
      this.handleDeposit();
    }
  };

  render() {
    const { selectedType } = this.props;
    const { errorMsg, accountNumber, successMsg, fromAccount, toAccount } =
      this.state;
    const type = selectedType.charAt(0).toUpperCase() + selectedType.slice(1);
    let transferFunds = true ? selectedType === "transfer" : false;

    return transferFunds ? (
      <div className="account-form">
        {successMsg && successMsg.transfer_success && (
          <p className="successMsg">{successMsg.transfer_success}</p>
        )}
        {errorMsg && errorMsg.transfer_error && (
          <p className="errorMsg">{errorMsg.transfer_error}</p>
        )}
        <Form className="account-form">
          <Form.Group controlId="type">
            <Form.Label>{type}</Form.Label>
          </Form.Group>
          <hr />
          <Form.Group controlId="accnt_no">
            <Form.Label>From Account number: </Form.Label>
            <Form.Control
              type="text"
              placeholder={`Enter From Account Number`}
              value={fromAccount}
              onChange={this.handleFromAccountChange}
            />
          </Form.Group>
          <Form.Group controlId="accnt_no">
            <Form.Label>To Account number: </Form.Label>
            <Form.Control
              type="text"
              placeholder={`Enter To Account Number`}
              value={toAccount}
              onChange={this.handleToAccountChange}
            />
          </Form.Group>
          <Form.Group controlId="amount">
            <Form.Label>Amount:</Form.Label>
            <Form.Control
              type="number"
              placeholder={`Enter amount to ${selectedType}`}
              value={this.state.amount}
              onChange={this.handleAmountChange}
            />
          </Form.Group>
          <Button variant="primary" type="submit" onClick={this.handleOnSubmit}>
            Submit
          </Button>
        </Form>
      </div>
    ) : (
      <div className="account-form">
        {successMsg && successMsg.withdraw_success && (
          <p className="successMsg">{successMsg.withdraw_success}</p>
        )}
        {successMsg && successMsg.deposit_success && (
          <p className="successMsg">{successMsg.deposit_success}</p>
        )}
        {errorMsg && errorMsg.withdraw_error && (
          <p className="errorMsg">{errorMsg.withdraw_error}</p>
        )}
        {errorMsg && errorMsg.add_error && (
          <p className="errorMsg">{errorMsg.add_error}</p>
        )}
        <Form className="account-form">
          <Form.Group controlId="type">
            <Form.Label>{type}</Form.Label>
          </Form.Group>
          <hr />
          <Form.Group controlId="accnt_no">
            <Form.Label>Account number: </Form.Label>
            <Form.Control
              type="text"
              required
              placeholder={`Enter Account Number`}
              value={accountNumber}
              onChange={this.handleAccountChange}
            />
          </Form.Group>
          <Form.Group controlId="amount">
            <Form.Label>Amount:</Form.Label>
            <Form.Control
              type="number"
              min="0"
              required
              placeholder={`Enter amount to ${selectedType}`}
              value={this.state.amount}
              onChange={this.handleAmountChange}
            />
          </Form.Group>
          <Button variant="primary" type="submit" onClick={this.handleOnSubmit}>
            Submit
          </Button>
        </Form>
      </div>
    );
  }
}

export default connect()(AccountForm);
