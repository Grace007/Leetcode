--编写一个 SQL 查询，获取 Employee 表中第 n 高的薪水（Salary）。
--
--+----+--------+
--| Id | Salary |
--+----+--------+
--| 1  | 100    |
--| 2  | 200    |
--| 3  | 300    |
--+----+--------+
--例如上述 Employee 表，n = 2 时，应返回第二高的薪水 200。如果不存在第 n 高的薪水，那么查询应返回 null。
--
--+------------------------+
--| getNthHighestSalary(2) |
--+------------------------+
--| 200                    |
--+------------------------+

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT

BEGIN
set N = N - 1 ;
RETURN (
-- Write your MySQL query statement below.
-- 难点：1：salary重复，2：数据量可能小于N
    select distinct salary
    from employee
    order by salary desc
    limit N  , 1
    );


-- return (
--   select 
  --     salary
    --   from employee e
--   where N = (select count(distinct salary) from employee where e.salary >= salary)
  --     );

END