# patientrecord
Simple patient enrollment system


### Tables
#### patient table

| id | age | email | history | is_deleted | name | patient_no | phone |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 31 | jane@email.com | hamstring injury | false | Jane Doe | 2F73C123E6984 | 09000000000

#### role_table table

| id | name |
| :---: | :---: |
| 1 | ROLE_ADMIN |
| 2 | ROLE_USER |

#### user_table table

| id | password | username | role_id |
| :---: | :---: | :---: | :---: |
| 1 | encrypted password | adminTester | 1 |