console.log(document);

// let header = document.children[0].children[1].children[0];

// header.textContent = "I have been changed";


// selectors

// Single element selectors
// Get element by ID (return a single element)
const header = document.getElementById("header");
console.log(header);


// Query selector (returns first matching instance)
const paragraph = document.querySelector("#paragraph");
const paragraphClass = document.querySelector(".para");
console.log(paragraph);
console.log(paragraphClass);

// Multiple element selectors
const paragraphs = document.getElementsByTagName("p");
console.log(paragraphs);

const paragraphsQuery = document.querySelectorAll(".para");
console.log(paragraphsQuery);


// DOM Manipulation
header.textContent = "I have been changed again";
paragraph.innerHTML = `<strong>BOLD TEXT</strong>`;

// Creating new element
const newDiv = document.createElement("div");
const newParagraph = document.createElement("p");

newDiv.textContent = 'I am a new div';
newDiv.className = 'container';

// Appending to the DOM
const body = document.querySelector("body");
// console.log(body);
// body.prepend(newDiv);
body.insertBefore(newDiv, paragraphs[2]);

newDiv.appendChild(newParagraph);

newParagraph.textContent = "New paragraph text";

// Remove element
newParagraph.remove();

body.removeChild(newDiv);


// Events and Listeners
const button1 = document.querySelector("button");
// console.log(button1);
// Add an event listener to this element
button1.addEventListener('click', (event) => {
    console.log('Button has been clicked!');
    console.log(event.target);
});

const parent = document.querySelector("#parent");
const child = document.querySelector("#child");

parent.addEventListener('click', () => {
    console.log("Parent clicked");
}, true)

child.addEventListener('click', () => {
    console.log("Child clicked");
}, false)