import { SIGN_IN, BASE_API_URL } from '../utils/constants';
import axios from 'axios';
import { getErrors } from './errors';
import { history } from '../router/appRouter';

export const signIn = (user) => ({
  type: SIGN_IN,
  user
});

export const initiateLogin = (username, password) => {
  return async (dispatch) => {
    try {
      const result = await axios.post(`${BASE_API_URL}/user/login`, {
        username,
        password
      });
      const user = result.data;
      localStorage.setItem('user_id', user.idusers);
      dispatch(signIn(user));
      history.push('/profile')
    } catch (error) {
      console.log('error');
      error.response && dispatch(getErrors(error.response.data));
    }
  };
};

export const registerNewUser = (data) => {
  return async (dispatch) => {
    try {
      await axios.post(`${BASE_API_URL}/user/create`, data);
      return { success: true };
    } catch (error) {
      console.log('error', error);
      error.response && dispatch(getErrors(error.response.data));
      return { success: false };
    }
  };
};