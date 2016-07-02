// import {Map} from 'immutable';
// import {loop, Effects} from 'redux-loop';

// Initial state
// const initialState = Map({
//   value: 0,
//   loading: false
// });

// Actions
const INCREMENT = 'MessageListState/INCREMENT';

// Action creators
export function increment() {
  return {type: INCREMENT};
}

// Reducer
export default function MessageListStateReducer() {
  // switch (action.type) {
  //   case INCREMENT:
  //     return state.update('value', value => value + 2);
  //
  //   case RESET:
  //     return initialState;
  //
  //   case RANDOM_REQUEST:
  //     return loop(
  //       state.set('loading', true),
  //       Effects.promise(requestRandomNumber)
  //     );
  //
  //   case RANDOM_RESPONSE:
  //     return state
  //       .set('loading', false)
  //       .set('value', action.payload);
  //
  //   default:
      // return state;
  // }
}
