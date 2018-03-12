# Database Schema
Table schema for RESPONSE_TYPE:

| Field     | Type        | Null | Key | Default | Extra |
|:----------|:------------|:-----|:---:|:--------|:------|
| resp_code | int(11)     | NO   | PRI | NULL    |       |
| resp_name | varchar(64) | NO   |     | NULL    |       |

Contents in this table:

| resp_code | resp_name              |
|----------:|:-----------------------|
|         0 | SUCCESS                |
|         1 | NOT_EXIST              |
|         2 | ALREADY_EXISTS         |
|         3 | NOT_AVAILABLE          |
|         4 | BAD_COMMAND            |
|         5 | REQUEST_TIMEOUT        |
|         6 | CONNECTION_RESET       |
|         7 | CONNECTION_ESTABLISHED |
|         8 | CONNECTION_CLOSED      |
|         9 | DECODE_RESPONSE_FAILED |

