# acc-transaction-batch
Accounts Transaction Batch

Spring Batch Transaction Processing
This Spring Boot application demonstrates batch processing and RESTful API.

Requirenment requested as follows.
-------------------------------------------------------------------
Prepare a batch job to consume the attached text file.
Prepare RESTFUL API to (1) Retrieve & (2) Update (description) the records
Must have authentication
Must have pagination
Can search by customer ID or account number(s) or description
For Update API, must able to handle concurrent update.
----------------------------------------------------------------------
Notes :-

Free to design the database structure.
Allowed to use many classes. (OOP is preferable)
Must follow RESTFUL API standard.
Code need to be shared.
Please brief if you are using any Design pattern and why you choose it.
Documentation is required. (Class diagram and activity diagram)
--------------------------------------------------------------------------
Tech Stack
--------------------------------------------------------------------------
1. Java JDK 17.
2. Spring boot.
3. RestFul API.
4. Spring Data JPA - Paging Repository.
5. Spring Security.
6. Spring Batch.
7. h2 Database
8. Intigration Testing Mockito.

--------------------------------------------------------------------------
Getting Started
--=======================================================================--
Prerequisites
Java JDK 11 or later
Apache Maven
Postman (for testing)


Setup to run.
First Clone the repository:

git clone https://github.com/asifinet/acc-transaction-batch.git
cd acc-transaction-batch
Build the project:


mvn clean install
Running the Application
Navigate to the project root directory:


cd acc-transaction-batch
Run the Spring Boot application:

mvn spring-boot:run
Testing Endpoints with Postman
Open Postman.

Test the endpoints using authentication:
username = asif, password = asif

Set the request type to GET.
Enter the endpoint URL: http://localhost:8011/api/transactions?description=FUND TRANSFER&page=0&size=5
Go to the Authorization tab and select Basic Auth and enter userid and password as, asif/asif

Enter the username and password configured in your application properties.

Click Send.

Example Request:
1. /api/transactions                    -->  For Retrival      GET
REQUEST
URL - http://localhost:8011/api/transactions?description=FUND TRANSFER&page=0&size=5

RESPONSE
{
    "content": [
        {
            "id": 1,
            "accountNumber": "8872838283",
            "trxAmount": 123.00,
            "description": "FUND TRANSFER",
            "trxDate": "2019-09-12",
            "trxTime": "11:11:11",
            "customerId": "222"
        },
        {
            "id": 3,
            "accountNumber": "8872838283",
            "trxAmount": 1223.00,
            "description": "FUND TRANSFER",
            "trxDate": "2019-10-11",
            "trxTime": "11:11:11",
            "customerId": "222"
        },
        {
            "id": 4,
            "accountNumber": "8872838283",
            "trxAmount": 1233.00,
            "description": "3rd Party FUND TRANSFER",
            "trxDate": "2019-11-11",
            "trxTime": "11:11:11",
            "customerId": "222"
        },
        {
            "id": 5,
            "accountNumber": "8872838283",
            "trxAmount": 1243.00,
            "description": "3rd Party FUND TRANSFER",
            "trxDate": "2019-08-11",
            "trxTime": "11:11:11",
            "customerId": "222"
        },
        {
            "id": 6,
            "accountNumber": "8872838283",
            "trxAmount": 12553.00,
            "description": "3rd Party FUND TRANSFER",
            "trxDate": "2019-07-11",
            "trxTime": "11:11:11",
            "customerId": "222"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 5,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalElements": 30,
    "totalPages": 6,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 5,
    "empty": false
}
2. REQUEST
URL - http://localhost:8011/api/transactions?accountNumber=8872838283&page=0&size=2   
RESPONSE
{
    "content": [
        {
            "id": 1,
            "accountNumber": "8872838283",
            "trxAmount": 123.00,
            "description": "FUND TRANSFER",
            "trxDate": "2019-09-12",
            "trxTime": "11:11:11",
            "customerId": "222"
        },
        {
            "id": 2,
            "accountNumber": "8872838283",
            "trxAmount": 1123.00,
            "description": "ATM WITHDRWAL",
            "trxDate": "2019-09-11",
            "trxTime": "11:11:11",
            "customerId": "222"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageSize": 2,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 7,
    "totalElements": 14,
    "size": 2,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
3.REQUEST
URL - http://localhost:8011/api/transactions?customerId=222&page=0&size=5
RESPONSE
{
    "content": [
        {
            "id": 1,
            "accountNumber": "8872838283",
            "trxAmount": 123.00,
            "description": "FUND TRANSFER",
            "trxDate": "2019-09-12",
            "trxTime": "11:11:11",
            "customerId": "222"
        },
        {
            "id": 2,
            "accountNumber": "8872838283",
            "trxAmount": 1123.00,
            "description": "ATM WITHDRWAL",
            "trxDate": "2019-09-11",
            "trxTime": "11:11:11",
            "customerId": "222"
        },
        {
            "id": 3,
            "accountNumber": "8872838283",
            "trxAmount": 1223.00,
            "description": "FUND TRANSFER",
            "trxDate": "2019-10-11",
            "trxTime": "11:11:11",
            "customerId": "222"
        },
        {
            "id": 4,
            "accountNumber": "8872838283",
            "trxAmount": 1233.00,
            "description": "3rd Party FUND TRANSFER",
            "trxDate": "2019-11-11",
            "trxTime": "11:11:11",
            "customerId": "222"
        },
        {
            "id": 5,
            "accountNumber": "8872838283",
            "trxAmount": 1243.00,
            "description": "3rd Party FUND TRANSFER",
            "trxDate": "2019-08-11",
            "trxTime": "11:11:11",
            "customerId": "222"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 5,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalElements": 31,
    "totalPages": 7,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 5,
    "empty": false
}

4. /api/transactions/updatedescription  -->  For Update        PUT
REQUEST
URL - http://localhost:8011/api/transactions/updatedescription/
BODY - 
{   "id": "22",
    "description": "BILL PAYMENT21"
}

RESPONSE

{
    "id": 22,
    "accountNumber": "8872838299",
    "trxAmount": 1223233.00,
    "description": "BILL PAYMENT21",
    "trxDate": "2019-09-11",
    "trxTime": "11:11:11",
    "customerId": "222"
}

5. GET Request to test concurrent update. 
 http://localhost:8011/api/transactions/concurrent-test

RESPONSE
Thread 39 - Concurrent Update 39
Thread 40 - Concurrent Update 40
Thread 41 - Concurrent Update 41
Thread 42 - Concurrent Update 42
Thread 43 - Concurrent Update 43
Thread 44 - Concurrent Update 44
Thread 45 - Concurrent Update 45
Thread 46 - Concurrent Update 46
Thread 47 - Concurrent Update 47
Thread 48 - Concurrent Update 48
Thread 49 - Concurrent Update 49
Thread 50 - Concurrent Update 50
Thread 51 - Concurrent Update 51
Thread 52 - Concurrent Update 52
Thread 53 - Concurrent Update 53
Thread 54 - Concurrent Update 54
Thread 55 - Concurrent Update 55
Thread 56 - Concurrent Update 56
Thread 57 - Concurrent Update 57
Thread 58 - Concurrent Update 58
Thread 59 - Concurrent Update 59
Thread 60 - Concurrent Update 60
Thread 61 - Concurrent Update 61
Thread 62 - Concurrent Update 62
Thread 63 - Concurrent Update 63
Thread 64 - Concurrent Update 64
Thread 65 - Concurrent Update 65
Thread 66 - Concurrent Update 66
Thread 67 - Concurrent Update 67
Thread 68 - Concurrent Update 68
Thread 69 - Concurrent Update 69
Thread 70 - Concurrent Update 70
Thread 71 - Concurrent Update 71
Thread 72 - Concurrent Update 72
Thread 73 - Concurrent Update 73
Thread 74 - Concurrent Update 74
Thread 75 - Concurrent Update 75
Thread 76 - Concurrent Update 76
Thread 77 - Concurrent Update 77
Thread 78 - Concurrent Update 78
Thread 79 - Concurrent Update 79
Thread 80 - Concurrent Update 80
Thread 81 - Concurrent Update 81
Thread 82 - Concurrent Update 82
Thread 83 - Concurrent Update 83
Thread 84 - Concurrent Update 84
Thread 85 - Concurrent Update 85
Thread 86 - Concurrent Update 86
Thread 87 - Concurrent Update 87
Thread 88 - Concurrent Update 88


GET http://localhost:8011/api/transactions
Authorization: Basic base64-encoded-credentials
You can also test other endpoints and their functionalities using similar steps.

Configuration
The application's configuration is located in the src/main/resources/application.yml file. Customize the settings according to your needs.

Troubleshooting
If you encounter any issues while starting the application, please check the console logs for error messages.
If you're having trouble with Postman, ensure you're using the correct endpoint URLs and authentication credentials.
Contributors
Your Name asif_inet@hotmail.com


