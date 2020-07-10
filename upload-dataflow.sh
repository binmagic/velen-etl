#!/bin/sh

scp -r ./velen-etl-dataflow-collection/demo-app/target/demo-app-0.0.2-SNAPSHOT.jar root@192.168.1.30:/usr/local/src/spring-cloud/

scp -r ./velen-etl-dataflow-collection/parse-processor/target/parse-processor-0.0.2-SNAPSHOT.jar root@192.168.1.30:/usr/local/src/spring-cloud/
curl 'http://192.168.1.30:9393/apps/source/parse-source' -i -X POST -d 'uri=file://root/scdf/parse-processor-0.0.2-SNAPSHOT.jar'





curl 'http://192.168.1.30:9393/apps/processor/parse-format-processor' -i -X POST -d 'uri=file://root/scdf/parse-format-source-0.0.2-SNAPSHOT.jar'

curl 'http://192.168.1.30:9393/apps/source/test-source' -i -X POST -d 'uri=file://root/scdf/test-source-0.0.2-SNAPSHOT.jar'