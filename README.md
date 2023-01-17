# transactionsapi-java
java based repository to create transactions based API for banks 

## pre requisites
need to have mongo db installed on localserver
on port 27017
with DB name : myTestDb1
and Documents : account and transaction

else we can update these values in the application.properties files and DTO files

## implementation
endpoints:\
Create new account:     localhost:8080/v1/bankaccount/      POST\
fetch all accounts:     localhost:8080/v1/bankaccount/      GET\
fetch specific account: localhost:8080/v1/bankaccount/{id}  GET\
\
create new account with parameters:\
{\
    "firstName": "akshat", -> string, required\
    "middleName": "singh", -> string, optional\
    "lastName": "rana", -> string, required\
    "balance": 50, -> required, min 50\
    "email" : "akki.rana87@gmail.com" -> required, valid email\
}\
\
endpoints:\
Create new transaction:     localhost:8080/v1/transaction/      POST\
fetch all transaction:      localhost:8080/v1/transaction/      GET\
fetch specific transaction: localhost:8080/v1/transaction/{id}  GET\
\
create new transaction with parameters:\
{\
    "initiatorAccountNumber": "RABO22b27be825924b9a8d40789430e1b160", -> required, with regex\
    "recieverAccountNumber": "RABOf895927543084cf8a5473b449b87c7a2", -> required, with regex\
    "amount": 110, -> required\
    "message": "message for first transaction 20 euro", -> optional\
    "reference": "reference for first transaction 20 euro" -> optional\
}\
\
## implementation for transaction
check if balance allows transaction\
create new transaction\
link transaction to initiator account\
link transaction to reciever account\

## toDO / missing component
make checking of balance before initiating transaction Synchronized\
implementing logic for scenario where any 1 part of transaction fails (eg - adding balance to reciever)\

## trace-id
request header parameter trace-id has been made mandatory for error debugging