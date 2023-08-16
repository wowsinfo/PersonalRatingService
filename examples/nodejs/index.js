const pr = require("personal-rating");
let Reader = pr.usecase.PersonalRatingReader;
let Service = pr.service.RemoteDataServiceJs;

let service = new Service();
console.log("Loading remote string");
service.getRemoteStringPromise().then((data) => {
    console.log("Reading from remote string");
    let remote_reader = Reader.Companion.fromString(data);
    let emptyValue = remote_reader.getExpectedValue("4178491120");
    console.log(emptyValue);
    let value = remote_reader.getExpectedValue("4288558800");
    console.log(value);
});

console.log("Reading from file");
let reader = Reader.Companion.fromFile("../personal_rating.json");
let emptyValue = reader.getExpectedValue("4178491120");
console.log(emptyValue);
let value = reader.getExpectedValue("4288558800");
console.log(value);
