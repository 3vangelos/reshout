
import Promise from 'bluebird';

export async function sendFirebaseMessage(channelName) {
  try {
    let response = await fetch('https://forces-assemble.herokuapp.com/api/v1/channels/' + channelName + '/events', {
      method: 'POST',
      headers: { 'Accept': 'application/json', 'Content-Type': 'application/json', },
      body: JSON.stringify({
          "title": "Hello",
          "body": "World!",
          "data": {
          "custom-param1": 123,
          "something-else": "foo"
          }
          })})
          return response.status 
  } catch(error) {  // Handle error
    console.error(error);
  }
};
