//Literal
let a = 10;
console.log(a);

let b = "Hello"; //string
let c = true; //boolean
let nullValue = null; //null
let undefinedValue; //undefined
let obj = { name: "John", age: 30 }; //object
let arr = [1, 2, 3]; //array
let bigIntValue = 1234567890123456789012345678901234567890n; //big integer
let symbolValue = Symbol("symbol"); //symbol
let func = function() { console.log("function"); }; //function
let regex = /ab+c/; //regular expression
let dateValue = new Date(); //date
let mapValue = new Map(); //map
let setValue = new Set(); //set



//10 is a value which is assgined to variable a. This is called literal operator. The value 10 is a literal value which is assigned to variable a. The literal operator is used to assign a value to a variable. The value can be of any data type like number, string, boolean, etc. In this case, the value is a number.
// it can be number , string, boolean, object, array, big integer etc. The literal operator is used to assign a value to a variable. The value can be of any data type like number, string, boolean, etc. In this case, the value is a number.


console.log(typeof a); //number
console.log(typeof b); //string
console.log(typeof c);
console.log(typeof nullValue); //object
console.log(typeof undefinedValue);



//p1 == p2 loose equality operator checking the value of p1 and p2. If the value is same then it will return
// pizza == pizza
let p2 = 10;
console.log(p1 == p2); //true

//p1 === p2 strict equality operator  checking the value and data type of p1 and p2. If the value and data type is same then it will return true otherwise false. In this case, the value and data type of p1 and p2 is same so it will return true. The strict equality operator is used to compare the value and data type of two variables. It checks both the value and data type of the variables. If both the value and data type is same then it will return true otherwise false. In this case, the value and data type of p1 and p2 is same so it will return true.
// dominas === pizzahut which means content is different and brand is different so it will return false. The strict equality operator is used to compare the value and data type of two variables. It checks both the value and data type of the variables. If both the value and data type is same then it will return true otherwise false. In this case, the value and data type of p1 and p2 is same so it will return true.
console.log(p1 === p2); //true

//p1 != p2 loose inequality operator
console.log(p1 != p2); //false

//p1 !== p2 strict inequality operator
console.log(p1 !== p2); //false

console.log(5 == "5"); //true number = number
console.log(5 === "5"); //false number is not equal to string
