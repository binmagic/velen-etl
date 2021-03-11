CREATE DATABASE aaa;

CREATE EXTERNAL TABLE tbl.tb_event
(
    distinct_id STRING COMMENT 'user of identity' ,
    create_time TIMESTAMP COMMENT 'create time' ,
    event STRING COMMENT 'name of event' ,
    project STRING COMMENT 'name of project'
)
COMMENT 'event table'
--PARTITIONED BY(p_event STRING, p_time TIMESTAMP)
ROW FORMAT DELIMITED
    FIELDS TERMINATED BY '\001'
STORED AS SEQUENCEFILE;