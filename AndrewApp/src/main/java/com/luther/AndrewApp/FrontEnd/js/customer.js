var refreshButton = document.getElementById("refreshButton");

//add events to those 2 buttons
refreshButton.addEventListener("click", refresh);

const socket = new WebSocket("ws://localhost:8025/form");
const button1 = document.querySelector("#request_similar");
const button2 = document.querySelector("#form");
const buttonOrder = document.querySelector("#order");
const buttonPay = document.querySelector("#pay");

socket.onopen = function(e) {
  alert("[open] Connection established");
};

socket.onmessage = function(event) {
  alert(`[message] Data received from server: ${event.data}`);
};

socket.onclose = function(event) {
  if (event.wasClean) {
    alert(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
  } else {
    // e.g. server process killed or network down
    // event.code is usually 1006 in this case
    alert('[close] Connection died');
  }
};

socket.onerror = function(error) {
  alert(`[error] ${error.message}`);
};

button1.addEventListener("click", () => {
  const similars = document.querySelector("#search_similar");
  const data = `${similars.value}`;
  console.log(data)

  // Send composed message to the server
  socket.send(data);

  // clear input fields
  similars.value = "";
});

button2.addEventListener("click", () => {
  login();
  socket.send(data)
});

buttonOrder.addEventListener("click", () => {
  order();
  socket.send(data)
});

buttonPay.addEventListener("click", () => {
  pay();
  socket.send(data)
});

const fetchValue = id => document.getElementById( id ).value;

function login() {
    const id = fetchValue( "id" );
    const name = fetchValue( "name" );
    const dob = fetchValue( "dob" );
    const guest = fetchValue( "guest" );
    data = [id, name, dob, guest]
}

function order() {
  const food = fetchValue( "food" );
  const dish = fetchValue( "dish" );
  data = [food, dish]
}

function pay() {
  const point = fetchValue( "point" );
  const tip = fetchValue( "tip" );
  data = [point, tip]
}

function refresh() {
    location.reload();
}
