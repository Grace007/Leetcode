--给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。
--
--+---------+------------------+------------------+
--| Id(INT) | RecordDate(DATE) | Temperature(INT) |
--+---------+------------------+------------------+
--|       1 |       2015-01-01 |               10 |
--|       2 |       2015-01-02 |               25 |
--|       3 |       2015-01-03 |               20 |
--|       4 |       2015-01-04 |               30 |
--+---------+------------------+------------------+
--例如，根据上述给定的 Weather 表格，返回如下 Id:
--
--+----+
--| Id |
--+----+
--|  2 |
--|  4 |
--+----+

select
t1.Id
from Weather t1
  join Weather t2 on t2.RecordDate = DATE_ADD(t1.RecordDate,INTERVAL -1 day)
where t2.Temperature < t1.Temperature