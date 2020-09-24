$(() => {
    let stompClient = null;

    function updateConnectionStatus(isConnected) {
        $('#username').prop('disabled', isConnected);
        $('#connectBtn').prop('disabled', isConnected);
        $('#disconnectBtn').prop('disabled', !isConnected);
        if (isConnected) {
            $('#messages').innerHTML = '';
        }
    }

    function onMessage(message) {
        const body = JSON.parse(message.body);
        $('<p>' + body.sender + ': ' + body.text + '</p>').appendTo($('#messages'));
    }

    let sessionId = "";
    function connect() {
        const socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({user:'jan'}, () => {
           /* let url = stompClient.ws._transport.url;
            console.log("Your current session is: " + url);
            url = url.replace("ws://localhost:8080/",  "");
            url = url.replace("/websocket", "");
            url = url.replace(/^[0-9]+\//, "");
            console.log("Your current session is: " + url);
            sessionId = url.substr(9, 8);
            console.log(sessionId);*/
            updateConnectionStatus(true);
            stompClient.subscribe('/chat-topic/messages', onMessage);
            //stompClient.subscribe('/queue/specific-user-user' + sessionId, onMessage)
        });
    }

    function disconnect() {
        stompClient.disconnect();
        updateConnectionStatus(false);
    }

    function send() {
        const message = JSON.stringify({
            sender: $('#username').val(),
            recipient: $('#recipient').val(),
            text: $('#message').val(),
        });
        stompClient.send('/ws/chat', {}, message);
    }

   updateConnectionStatus(false);
   $('#connectBtn').click(connect);
   $('#disconnectBtn').click(disconnect);
   $('#sendBtn').click(send);
});
