--编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。
--
--+----+--------+
--| Id | Salary |
--+----+--------+
--| 1  | 100    |
--| 2  | 200    |
--| 3  | 300    |
--+----+--------+
--例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。
--
--+---------------------+
--| SecondHighestSalary |
--+---------------------+
--| 200                 |
--+---------------------+


-- Write your MySQL query statement below
-- 如果没有这样的第二最高工资，这个解决方案将被判断为 “错误答案”，因为本表可能只有一项记录。
-- 而且第二高，就必须要去重
-- select
--     Salary as SecondHighestSalary
-- from
--     Employee
-- order by Salary desc 
-- limit 1,1


--结果作为临时表，就可以为null
select
    (
    select distinct
        Salary
    from
      Employee
    order by Salary desc 
    limit 1,1
) as SecondHighestSalary
;

--ifnull 函数也可以解决
