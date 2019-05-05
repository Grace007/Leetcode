--Trips 表中存所有出租车的行程信息。每段行程有唯一键 Id，Client_Id 和 Driver_Id 是 Users 表中 Users_Id 的外键。Status 是枚举类型，枚举成员为 (‘completed’, ‘cancelled_by_driver’, ‘cancelled_by_client’)。
--
--+----+-----------+-----------+---------+--------------------+----------+
--| Id | Client_Id | Driver_Id | City_Id |        Status      |Request_at|
--+----+-----------+-----------+---------+--------------------+----------+
--| 1  |     1     |    10     |    1    |     completed      |2013-10-01|
--| 2  |     2     |    11     |    1    | cancelled_by_driver|2013-10-01|
--| 3  |     3     |    12     |    6    |     completed      |2013-10-01|
--| 4  |     4     |    13     |    6    | cancelled_by_client|2013-10-01|
--| 5  |     1     |    10     |    1    |     completed      |2013-10-02|
--| 6  |     2     |    11     |    6    |     completed      |2013-10-02|
--| 7  |     3     |    12     |    6    |     completed      |2013-10-02|
--| 8  |     2     |    12     |    12   |     completed      |2013-10-03|
--| 9  |     3     |    10     |    12   |     completed      |2013-10-03|
--| 10 |     4     |    13     |    12   | cancelled_by_driver|2013-10-03|
--+----+-----------+-----------+---------+--------------------+----------+
--Users 表存所有用户。每个用户有唯一键 Users_Id。Banned 表示这个用户是否被禁止，Role 则是一个表示（‘client’, ‘driver’, ‘partner’）的枚举类型。
--
--+----------+--------+--------+
--| Users_Id | Banned |  Role  |
--+----------+--------+--------+
--|    1     |   No   | client |
--|    2     |   Yes  | client |
--|    3     |   No   | client |
--|    4     |   No   | client |
--|    10    |   No   | driver |
--|    11    |   No   | driver |
--|    12    |   No   | driver |
--|    13    |   No   | driver |
--+----------+--------+--------+
--写一段 SQL 语句查出 2013年10月1日 至 2013年10月3日 期间非禁止用户的取消率。基于上表，你的 SQL 语句应返回如下结果，取消率（Cancellation Rate）保留两位小数。
--
--+------------+-------------------+
--|     Day    | Cancellation Rate |
--+------------+-------------------+
--| 2013-10-01 |       0.33        |
--| 2013-10-02 |       0.00        |
--| 2013-10-03 |       0.50        |
--+------------+-------------------+


select
t.Request_at as day,
convert(cancal/total,decimal(10,2)) as 'Cancellation Rate'
from
(
  select
  t1.Request_at,
  count(if(t1.`Status` != "completed",1,null)) as cancal,
  count(1) as total
  from trips t1
    join users t2 on t2.Users_id = t1.Client_Id
    join users t3 on t3.Users_id = t1.driver_id
  where t2.Banned = 'No' and t3.banned = 'No' and t1.Request_at between '2013-10-01' and '2013-10-03'
  group by t1.Request_at
) t
;
