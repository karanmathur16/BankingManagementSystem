import React from 'react';
import { connect } from 'react-redux';
import { Form, Button } from 'react-bootstrap';
import { validateFields } from '../utils/common';
import { Link } from 'react-router-dom';
import { initiateLogin } from '../actions/auth';
import _ from 'lodash';
import { resetErrors } from '../actions/errors';

class Login extends React.Component {
  state = {
    username: '',
    password: '',
    errorMsg: ''
  };

  componentDidUpdate(prevProps) {
    if (!_.isEqual(prevProps.errors, this.props.errors)) {
      this.setState({ errorMsg: this.props.errors });
    }
  }

  componentWillUnmount() {
    this.props.dispatch(resetErrors());
  }

  handleLogin = (event) => {
    event.preventDefault();
    const { username, password } = this.state;
    const fieldsToValidate = [{ username }, { password }];

    const allFieldsEntered = validateFields(fieldsToValidate);
    if (!allFieldsEntered) {
      this.setState({
        errorMsg: 'Please enter all the fields'
      });
    } else {
      this.setState({
        errorMsg: ''
      }); 
      // login successful
      this.props.dispatch(initiateLogin(username, password));
    }
  };

  handleInputChange = (event) => {
    const { name, value } = event.target;

    this.setState({
      [name]: value
    });
  };

  render() {
    const { errorMsg } = this.state;
    return (
      <div className="login-page">
        <div className="login-form">
          <Form onSubmit={this.handleLogin}>
            {errorMsg && (
              <p className="errorMsg centered-message">
                {errorMsg}
              </p>
            )}
            <Form.Group controlId="username">
              <Form.Label>Enter UserName</Form.Label>
              <Form.Control
                type="text"
                name="username"
                placeholder="Enter Username"
                onChange={this.handleInputChange}
              />
            </Form.Group>
            <Form.Group controlId="password">
              <Form.Label>Enter Password</Form.Label>
              <Form.Control
                type="password"
                name="password"
                placeholder="Enter password"
                onChange={this.handleInputChange}
              />
            </Form.Group>
            <div className="action-items">
              <Button variant="primary" type="submit">
                Login
              </Button>
              <Link to="/register" className="btn btn-secondary">
                Register
              </Link>
            </div>
          </Form>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
    errors: state.errors
  });

export default connect(mapStateToProps)(Login);