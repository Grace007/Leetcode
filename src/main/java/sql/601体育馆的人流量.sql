--X 市建了一个新的体育馆，每日人流量信息被记录在这三列信息中：序号 (id)、日期 (date)、 人流量 (people)。
--
--请编写一个查询语句，找出高峰期时段，要求连续三天及以上，并且每天人流量均不少于100。
--
--例如，表 stadium：
--
--+------+------------+-----------+
--| id   | date       | people    |
--+------+------------+-----------+
--| 1    | 2017-01-01 | 10        |
--| 2    | 2017-01-02 | 109       |
--| 3    | 2017-01-03 | 150       |
--| 4    | 2017-01-04 | 99        |
--| 5    | 2017-01-05 | 145       |
--| 6    | 2017-01-06 | 1455      |
--| 7    | 2017-01-07 | 199       |
--| 8    | 2017-01-08 | 188       |
--+------+------------+-----------+
--对于上面的示例数据，输出为：
--
--+------+------------+-----------+
--| id   | date       | people    |
--+------+------------+-----------+
--| 5    | 2017-01-05 | 145       |
--| 6    | 2017-01-06 | 1455      |
--| 7    | 2017-01-07 | 199       |
--| 8    | 2017-01-08 | 188       |
--+------+------------+-----------+
--Note:
--每天只有一行记录，日期随着 id 的增加而增加。

select
t2.id ,
t2.visit_date,
t2.people
from
(
                 select
                        *
                 from
                 (
                   select
                   t1.*,
                   case
                   when t1.people >= 100 and @start_date is null then @start_date := t1.visit_date
	when t1.people >= 100 then @start_date
	else @start_date :=  null
end as start_date,
case
when t1.people >= 100 and @start_date is not null and @start_date = t1.visit_date  then @total := 1
	when t1.people >= 100 and @start_date is not null  then @total := @total+1
	else @total := 0
	end as total
from stadium t1,(select @start_date := null,@total := null ) t2
) t1
where t1.total = 3
)t1
join
(
select
t1.*,
case
when t1.people >= 100 and @start_date1 is null then @start_date1 := t1.visit_date
when t1.people >= 100 then @start_date1
	else @start_date1 :=  null
end as start_date,
case
when t1.people >= 100 and @start_date1 is not null and @start_date1 = t1.visit_date  then @total1 := 1
	when t1.people >= 100 and @start_date1 is not null  then @total1 := @total1+1
	else @total1 := 0
	end as total
from stadium t1,(select @start_date1 := null,@total1 := null ) t2
) t2 on t2.start_date = t1.start_date
;









