import {Map} from 'immutable';
import {loop, Effects} from 'redux-loop';
import {generateRandomNumber} from '../../services/randomNumberService';
import {sendFirebaseMessage} from '../../services/requests';

// Initial state
const initialState = Map({
  value: 0,
  loading: false
});

// Actions
const INCREMENT = 'CounterState/INCREMENT';
const RESET = 'CounterState/RESET';
const RANDOM_REQUEST = 'CounterState/RANDOM_REQUEST';
const RANDOM_RESPONSE = 'CounterState/RANDOM_RESPONSE';

// Action creators
export function increment() {
  return {type: INCREMENT};
}

export function reset() {
  return {type: RESET};
}

export function random() {
  return {
    type: RANDOM_REQUEST
  };
}

export async function requestRandomNumber() {
  return {
    type: RANDOM_RESPONSE,
    payload: await generateRandomNumber()
  };
}

export async function sendMessage() {
  return {
    type: RANDOM_RESPONSE,
    payload: await sendFirebaseMessage('tomek_is_awesome_channel')
  };
}

// Reducer
export default function CounterStateReducer(state = initialState, action = {}) {
  switch (action.type) {
    case INCREMENT:
      return state.update('value', value => value + 2);

    case RESET:
      return initialState;

    case RANDOM_REQUEST:
      return loop(
        state.set('loading', true),
        Effects.promise(sendMessage)
      );

    case RANDOM_RESPONSE:
      return state
        .set('loading', false)
        .set('value', action.payload);

    default:
      return state;
  }
}
