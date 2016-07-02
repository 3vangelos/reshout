import * as MessageListState from './MessageListState';
import React, {PropTypes} from 'react';
import {
  StyleSheet,
  Image,
  TextInput,
  Text,
  View
} from 'react-native';

const MessageListView = React.createClass({
  propTypes: {
    counter: PropTypes.number.isRequired,
    userName: PropTypes.string,
    userProfilePhoto: PropTypes.string,
    loading: PropTypes.bool.isRequired,
    dispatch: PropTypes.func.isRequired,
    onNavigate: PropTypes.func.isRequired
  },
  increment() {
    this.props.dispatch(MessageListState.increment());
  },
  reset() {
    this.props.dispatch(MessageListState.reset());
  },
  random() {
    this.props.dispatch(MessageListState.random());
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

    return (
      <View style={styles.container}>

        <View style={styles.row}>
          <View style={styles.userProfilePhoto}/>
          <TextInput
            placeholder='Message'
            style={{
              height: 40,
              width: 80,
              borderColor: 'gray',
              borderWidth: 1,
              backgroundColor: 'white',
              margin: 5
            }}
          />
        </View>

        {this.renderUserInfo()}
      </View>
    );
  }
});

const circle = {
  borderWidth: 0,
  borderRadius: 40,
  width: 80,
  height: 80
};

const smallCircle = {
  borderWidth: 0,
  borderRadius: 20,
  width: 40,
  height: 40
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 64,
    flexDirection: 'column',
    alignItems: 'flex-start',
    backgroundColor: 'white'
  },
  row: {
    alignItems: 'center',
    alignSelf: 'stretch',
    flexDirection: 'row',
    backgroundColor: 'blue'
  },
  rowContainer: {
    flexDirection: 'row',
    justifyContent: 'flex-start',
    alignItems: 'center',
    backgroundColor: 'red'
  },
  userContainer: {
    justifyContent: 'center'
  },
  userProfilePhoto: {
    ...smallCircle,
    backgroundColor: 'yellow',
    justifyContent: 'center',
    alignSelf: 'center',
    margin: 5
  },
  counterButton: {
    ...circle,
    backgroundColor: 'green',
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

export default MessageListView;
