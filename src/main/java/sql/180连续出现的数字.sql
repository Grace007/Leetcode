--编写一个 SQL 查询，查找所有至少连续出现三次的数字。
--
--+----+-----+
--| Id | Num |
--+----+-----+
--| 1  |  1  |
--| 2  |  1  |
--| 3  |  1  |
--| 4  |  2  |
--| 5  |  1  |
--| 6  |  2  |
--| 7  |  2  |
--+----+-----+
--例如，给定上面的 Logs 表， 1 是唯一连续出现至少三次的数字。
--
--+-----------------+
--| ConsecutiveNums |
--+-----------------+
--| 1               |
--+-----------------+


select
distinct num as ConsecutiveNums
from
(
               select
                      num,
                      case
                      when @prev = num then @count := @count + 1 #重复的+1
when (@prev := num) is not null then @count := 1	#不重复的，重置count为1,并且重置prev为新的数据
end as numcount
from logs t1, (select @prev := null,@count := 0) t2
) t1
where numcount >=3
;