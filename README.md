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

GET http://localhost:8080/api/transactions
Authorization: Basic base64-encoded-credentials
You can also test other endpoints and their functionalities using similar steps.

Configuration
The application's configuration is located in the src/main/resources/application.yml file. Customize the settings according to your needs.

Troubleshooting
If you encounter any issues while starting the application, please check the console logs for error messages.
If you're having trouble with Postman, ensure you're using the correct endpoint URLs and authentication credentials.
Contributors
Your Name asif_inet@hotmail.com


