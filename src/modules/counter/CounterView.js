import * as CounterState from './CounterState';
import * as NavigationState from '../../modules/navigation/NavigationState';
import React, {PropTypes} from 'react';
import {
  StyleSheet,
  TouchableOpacity,
  Image,
  Text,
  View,
  NativeModules
} from 'react-native';

const CounterView = React.createClass({
  propTypes: {
    counter: PropTypes.number.isRequired,
    userName: PropTypes.string,
    userProfilePhoto: PropTypes.string,
    loading: PropTypes.bool.isRequired,
    dispatch: PropTypes.func.isRequired,
    onNavigate: PropTypes.func.isRequired
  },
  increment() {
    this.props.dispatch(CounterState.increment());
  },
  reset() {
    this.props.dispatch(CounterState.reset());
  },
  random() {
    this.props.dispatch(CounterState.random());
  },
  bored() {
    this.props.dispatch(NavigationState.pushRoute({key: 'Color'}));
  },
  componentDidMount() {
  //  NativeModules.NotificationModule.sendNotification();
//    subscribeToChannel("tomek_is_awesome_channel");
  },
  renderUserInfo() {
    if (!this.props.userName) {
      return null;
    }

    return (
      <View style={styles.userContainer}>
        <Image
          style={styles.userProfilePhoto}
          source={{
            uri: this.props.userProfilePhoto,
            width: 80,
            height: 80
          }}
        />
        <Text style={styles.linkButton}>
          Welcome, {this.props.userName}!
        </Text>
      </View>
    );
  },
  render() {
    const loadingStyle = this.props.loading
      ? {backgroundColor: '#eee'}
      : null;

    return (
      <View style={styles.container}>

        {this.renderUserInfo()}

        <TouchableOpacity
          onPress={this.increment}
          style={[styles.counterButton, loadingStyle]}>
          <Text style={styles.counter}>
            {this.props.counter}
          </Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={this.reset}>
          <Text style={styles.linkButton}>
            Reset
          </Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={this.random}>
          <Text style={styles.linkButton}>
            Random
          </Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={this.bored} accessible={true}>
          <Text style={styles.linkButton}>
            {'I\'m bored!'}
          </Text>
        </TouchableOpacity>

      </View>
    );
  }
});
function updateToken() {
  fetch('https://forces-assemble.herokuapp.com/api/v1/users/tomek_is_awesome/notification-token', {
    method: 'PUT',
    headers: { 'Accept': 'application/json', 'Content-Type': 'application/json', },
    body: JSON.stringify({ 'notification-token': 'abcdefg12345' })
  })
    .then((responseText) => {
      console.log("updateToken", responseText);
    })
    .catch((error) => {
      console.warn("updateToken", error);
    });
};

function subscribeToChannel(channelName, userId) {
  console.log("subscribeToChannel" );
  fetch('https://forces-assemble.herokuapp.com/api/v1/channels/' + channelName + '/subscribers', {
    method: 'POST',
    headers: { 'Accept': 'application/json', 'Content-Type': 'application/json', },
    body: JSON.stringify({ 'user-id': userId })
  })
    .then((responseText) => {
      console.log("subscribeToChannel", responseText);
    })
    .catch((error) => {
      console.warn("subscribeToChannel", error);
    });
};

const circle = {
  borderWidth: 0,
  borderRadius: 40,
  width: 80,
  height: 80
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'white'
  },
  userContainer: {
    justifyContent: 'center',
    alignItems: 'center'
  },
  userProfilePhoto: {
    ...circle,
    alignSelf: 'center'
  },
  counterButton: {
    ...circle,
    backgroundColor: 'green',
    alignItems: 'center',
    justifyContent: 'center',
    margin: 20
  },
  counter: {
    color: 'white',
    fontSize: 20,
    textAlign: 'center'
  },
  welcome: {
    textAlign: 'center',
    color: 'black',
    marginBottom: 5,
    padding: 5
  },
  linkButton: {
    textAlign: 'center',
    color: '#CCCCCC',
    marginBottom: 10,
    padding: 5
  }
});

export default CounterView;
