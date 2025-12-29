var variableName;
// As JavaScript is a dynamically typed language, variables can be coerced
// There is automatic conversion of values from one data type to another

let number = 10;
const locations = "Texas";

// console.log(typeof number);
// console.log(typeof locations);

number = "30";
// locations = 40;

// console.log(typeof number);
// console.log(locations);

// Type Coercion 
// Using a operation with mismatched types
// String coercion

// console.log(typeof ('5' + 3));
// console.log('5' + true);

console.log('5' - 3);
console.log('5' - true);

// Explicit coercion
console.log(typeof String(234));
console.log(typeof (234).toString());


console.log(5 == '5');
console.log(5 === '5');

// const objectCheck = {name: "Greg"};
// if(objectCheck){

// }


// Arrays
const fruits = ['apple', 'banana', 'orange'];
console.log(fruits);

// array constructor
const numbers = new Array(1, 2, 3);
console.log(numbers);

// Empty array with length
const empty = new Array(5);
console.log(empty);

const random = ['Apple', 23, {x: "Hello"}];
console.log(random);


// Array methods
// add / remove elements
fruits.push("Kiwi");
console.log(fruits);
fruits.pop(); // remove from the end
console.log(fruits);
fruits.unshift("Kiwi");
console.log(fruits);
fruits.shift(); // remove from the start
console.log(fruits);


// Transformations
const doubled = numbers.map(x => x * 2);
console.log(doubled);
console.log(numbers);
const evens = numbers.filter(x => x % 2 === 0);
console.log(evens);

// removing elements from an array
random.splice(0, 1);
console.log(random);



// Scopes
// Global Scope
const globalVar = 'I am global'; // This can be accessed from anywhere

testFunction();

console.log(variableName);

// Function / Local scope
function testFunction() {
    const functionVar = 'I am function-scoped'; // This can only be accessed within this function

    if (true) {
        // Block scoped is only done through let / const
        var blockVar = 'I am block scoped';
        console.log(globalVar);
        console.log(functionVar);
    }

    console.log(blockVar); 
    console.log(variableName);
}

variableName = "Hello";


console.log(greet('Greg'));

function greet(name){
    return `Hello, ${name}`;
}

// Arrow Function
const addArrow = (a, b) => {
    return a + b;
}

// Implicit return (single expression)
const addImplicit = (a, b) => a + b;

// Single paramter (parentheses are optional)
const square = x => x * x;

const anon = function() {
    return 'ANonymous';
}


// No parameters
// const greet = () => 'Hello';

const person = {
    name: "Mike",
    // greet: () => console.log(`Hello, I'm ${this.name}`),
    greet: function() {
        console.log(`Hello, I'm ${this.name}`)
    }
}

person.greet();

let stringWithFormatting = `
hello
there

${person.name}

`

console.log(stringWithFormatting);


// Closures
// Lexical Scope
function closureExample(){
    let closureVariable = "Hello";

    let internalFunction = function() {
        console.log(closureVariable);
    }

    return internalFunction;
}

let closureFunction = closureExample();

closureFunction();


class Person {
    // constructor
    constructor(name, age){
        this.name = name;
        this.age = age;
    }

    // Instance methods
    greet(){
        return `Hello, my name is ${this.name}`;
    }

    // static methods
    static species(){
        return 'Home Sapien';
    }
}

const alice = new Person('Alice', 25);
console.log(alice.greet());
console.log(Person.species());

class Student extends Person {
    constructor(name, age, major){
        super(name, age);
        this.major = major;
    }

    study(){
        return `${this.name} is studying ${this.major}`;
    }

    // override parent methods
    greet(){
        return `${super.greet()} and I am studying ${this.major}`;
    }
}

const bob = new Student("Bob", 40, "Computer Science");
console.log(bob.study());
console.log(bob.greet());