
import Promise from 'bluebird';

export async function sendFirebaseMessage(channelName) {
  try {
    let response = await fetch('https://forces-assemble.herokuapp.com/api/v1/channels/' + channelName + '/events', {
      method: 'POST',
      headers: { 'Accept': 'application/json', 'Content-Type': 'application/json', },
      body: JSON.stringify({

        "notification" : {
        "body" : "Pure awesomeness!",
        "title" : "Reshout"
        },
          "data": {
          "autor": "Tomek",
          "body" : "Pure awesomeness!",
          "title" : "Reshout"
          }
          })})
          return response.status
  } catch(error) {  // Handle error
    console.error(error);
  }
};
