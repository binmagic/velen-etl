create table test1
(
    uuid string,
    id int,
    `time` date,
    prototypes map<string,string>
)
    row format delimited
    fields terminated by ','
    map keys terminated by ':';
