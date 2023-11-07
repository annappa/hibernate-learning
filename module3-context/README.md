## Info


## General
* Context stores the entities. It's a middle layer between application and database. 
* All operations like persist, merge, remove etc from entity manager are with context. It won't trigger the sql queries unless we commit the transaction.
* All the operations with the transaction deal with the context
* At the end of the transaction, state of the context will be macthed with the database
* 