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

    function connect() {
        const socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            updateConnectionStatus(true);
            stompClient.subscribe('/user/queue/messages', onMessage)
        });
    }

    function disconnect() {
        stompClient.disconnect();
        updateConnectionStatus(false);
    }

    function send() {
        const message = JSON.stringify({
            sender: $('#username').val(),
            text: $('#message').val(),
        });
        stompClient.send('/ws/chat', {}, message);
    }

   updateConnectionStatus(false);
   $('#connectBtn').click(connect);
   $('#disconnectBtn').click(disconnect);
   $('#sendBtn').click(send);
});