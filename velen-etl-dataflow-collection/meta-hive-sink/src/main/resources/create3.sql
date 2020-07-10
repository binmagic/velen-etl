create table test_property
(
    distinct_id string ,
    time timestamp ,
    event string ,
    project string ,

    -- properties
    level int ,
    open boolean,
    money bigint,
    value bigint,
)
    row format delimited
    fields terminated by ','
    map keys terminated by ':';
