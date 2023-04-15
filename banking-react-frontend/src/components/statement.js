import React from "react";
import { Table } from "react-bootstrap";
import { withRouter } from "react-router-dom";

class Statement extends React.Component {
  state = {
    transactions: [],
    currentBalance: '',
    accountNumberState:''
  };


  componentDidMount() {
    const { match } = this.props;
    const accountNumber = match.params.accountNumber;
    fetch("http://localhost:8080/api/v1/accounts/statement/" + accountNumber)
      .then((response) => response.json())
      .then((data) => {
        this.setState({
            transactions:data.transactionHistory,
            currentBalance:data.currentBalance,
            accountNumberState:data.accountNumber
        });
      })
      .catch((error) => console.log(error));
  }

  render() {
    const { transactions,currentBalance,accountNumberState } =
      this.state;
    return (
      <div className="col-md-6 offset-md-3" style={{marginLeft:'0px', maxWidth:'inherit'}}>
        <h1 style={{textAlign:'center'}}>Account Statement</h1>
        <p className="successMsg" style={{textAlign:'center'}}>Current Balance: {currentBalance}</p>
        <p className="successMsg" style={{textAlign:'center'}}>Account Number: {accountNumberState}</p>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Transaction Number</th>
              <th>Amount</th>
              <th>Transaction Type</th>
              <th>Date and Time</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((transaction) => (
              <tr key={transaction.transactionNumber}>
                <td>
                    {transaction.transactionNumber}
                  
                </td>
                <td>{transaction.amount}</td>
                <td>{transaction.transactionType}</td>
                <td>{new Date(transaction.transactionDateTime).toLocaleString()}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
  }
}


export default withRouter(Statement);
