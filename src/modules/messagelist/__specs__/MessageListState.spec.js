/*eslint-disable max-nested-callbacks, no-unused-expressions*/

// import {Effects} from 'redux-loop';
// import sinon from 'sinon';
// import {describe, it, beforeEach, afterEach} from 'mocha';
// import {expect} from 'chai';
// import {initialState, dispatch} from '../../../../test/state';
// import * as MessageListStateActions from '../MessageListState';
//
// describe('MessageListState', () => {
//
//   // Example of how to test multiple dispatches in series
//   describe('increment', () => {
//     const getValue = state => state.getIn(['counter', 'value']);
//
//     it('should increment the value property by one', () => {
//       const [secondState] = dispatch(initialState, MessageListStateActions.increment());
//       expect(getValue(secondState)).to.equal(getValue(initialState) + 1);
//
//       const [thirdState] = dispatch(secondState, MessageListStateActions.increment());
//       expect(getValue(thirdState)).to.equal(getValue(secondState) + 1);
//     });
//   });
//
//   // Example of how to test side effects returned from reducers
//   describe('random', () => {
//
//     const [nextState, effects] = dispatch(initialState, MessageListStateActions.random());
//
//     it('should update loading bit', () => {
//       expect(nextState.getIn(['counter', 'loading'])).to.equal(true);
//     });
//
//     it('should trigger a requestRandomNumber side effect', () => {
//       expect(effects).to.eql(
//         Effects.promise(MessageListStateActions.requestRandomNumber)
//       );
//     });
//   });
//
//   // Example of how to test async action creators
//   describe('requestRandomNumber', () => {
//
//     // randomizer uses timeouts to delay response, let's make it execute
//     // instantly to improve test speed
//     const sandbox = sinon.sandbox.create();
//     beforeEach(() => sandbox.stub(global, 'setTimeout', setImmediate));
//     afterEach(() => sandbox.restore());
//
//     it('should generate a random number and dispatch it', async () => {
//       const action = await MessageListStateActions.requestRandomNumber();
//       expect(action.payload).to.be.a('number');
//
//       const [nextState] = dispatch(initialState, action);
//       expect(nextState.getIn(['counter', 'value'])).to.equal(action.payload);
//       expect(nextState.getIn(['counter', 'loading'])).to.equal(false);
//     });
//   });
// });
