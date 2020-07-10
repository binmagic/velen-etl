create table test
(
    uuid string,
    id int,
    `time` date,
    prototypes struct<a:int,b:int>
)
    row format delimited
    fields terminated by ','
    map keys terminated by ':';
