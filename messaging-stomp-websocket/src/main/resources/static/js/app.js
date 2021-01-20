let stompClient = null;

function connect() {
    let socket = new SockJS('/messaging-stomp-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', (greeting) => {
            showGreeting(JSON.parse(greeting.body).content);
        })
    });
}

function setConnected(connected) {
    $('#connect').prop('disabled', connected);
    $('#disconnect').prop('disabled', !connected);
    if (connected) {
        $('#conversation').show();
    } else {
        $('#conversation').hide();
    }
    $('#greetings').html('');
}

function showGreeting(message) {
    $('#greetings').append('<tr><td>' + message + '</td></tr>');
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log('Disconnected');
}

function sendName() {
    stompClient.send(
        '/app/hello',
        {},
        JSON.stringify({'name': $('#name').val()})
    );
}

$(() => {
    $('form').on('submit', (event) => {
        event.preventDefault();
    });
    $('#connect').click(() => connect());
    $('#disconnect').click(() => disconnect());
    $('#send').click(() => sendName());
});

