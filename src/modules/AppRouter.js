/*eslint-disable react/prop-types*/

import React from 'react';
import MessageListViewContainer from './messagelist/MessageListViewContainer';
import ColorViewContainer from './colors/ColorViewContainer';

/**
 * AppRouter is responsible for mapping a navigator scene to a view
 */
export default function AppRouter(props) {
  const onNavigate = props.onNavigate;
  const key = props.scene.route.key;

  if (key === 'Counter') {
    return <MessageListViewContainer onNavigate={onNavigate} />;
  }

  if (key.indexOf('Color') === 0) {
    const index = props.scenes.indexOf(props.scene);
    return (
      <ColorViewContainer
        index={index}
      />
    );
  }

  throw new Error('Unknown navigation key: ' + key);
}
