import React, { Component } from 'react';

import './App.css';

function Message(props){
  return (
      <li className="message">
        <div className="sender">{props.msender}</div>
        <div className="text-wrapper">
          <div className="text">{props.mbody}</div>
          <div className="time">{props.mtimestamp}</div>
        </div>
      </li>
  );
}

class App extends Component {
  state = {
    newMessageSender: '',
    newMessageBody: '',
    messageFeed: []
  };

  renderMessage(mId, mSender, mBody, mTimestamp) {
      return <Message
          mid = {mId}
          msender = {mSender}
          mbody = {mBody}
          mtimestamp = {mTimestamp}
      />
    }


  componentDidMount() {
      this.getMessages()
          .then(res => this.setState({messageFeed : res}))
          .catch(err => console.log(err));
  }

    async getMessages() {
    const response = await fetch('/restChat');
    const body = await response.json();
    if (response.status !== 200) throw Error(body.message);
    body.reverse();
    return body;
  };

  handleSubmit = async e => {
    e.preventDefault();
    await fetch('/restChat', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ msender: this.state.newMessageSender, mbody: this.state.newMessageBody}),
    });
      this.getMessages()
          .then(res => this.setState({messageFeed : res}))
          .catch(err => console.log(err));
  };

  render() {
    const messages = this.state.messageFeed;
    const messagesView = messages.map((value, index) =>
    {
      return (
          this.renderMessage(value.mid, value.msender, value.mbody, value.mtimestamp)
      );
    });
    return (
        <div className="app">
          <header className="app-header">
            <div className="chat-title">
              <h1>Grid Student Practice</h1>
              <h2>Chat Service <span role="img" aria-label="rocket">🚀</span></h2>
            </div>
          </header>
          <div className = "submit-wrapper">
            <form className= "submit-form" onSubmit={this.handleSubmit}>
                  <input className= "sender-form"
                      type="text"
                      name="msender"
                      placeholder="Enter sender name"
                      value={this.state.newMessageSender}
                      onChange={e => this.setState({ newMessageSender: e.target.value })}/>
                  <input className= "message-form"
                      type="text"
                      name="mbody"
                      placeholder="Enter your message"
                      value={this.state.newMessageBody}
                      onChange={e => this.setState({ newMessageBody: e.target.value })}/>
                <input className= "submit-button" type="submit" value="Submit" />
            </form>
          </div>
            <ul className = "message-feed"> {messagesView} </ul>
        </div>
    );
  }
}

export default App;