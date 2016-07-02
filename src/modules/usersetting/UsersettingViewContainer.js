import {connect} from 'react-redux';
import UsersettingView from './UsersettingView';

export default connect(
  state => ({
    userName: state.getIn(['auth', 'currentUser', 'name'])
  })
)(UsersettingView);
