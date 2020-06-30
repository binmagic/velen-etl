#!/bin/sh

PROFILE='dev'

if [ $# == 1 ] ; then
  if [ $1 == 'release' ]; then
      PROFILE='release'
  elif [ $1 == 'test' ]; then
      PROFILE='test'
  fi
fi

echo 'build '$PROFILE

mvn clean -P${PROFILE} install
mvn clean -P${PROFILE} package

cd velen-etl-stream/velen-etl-verification-server
mvn docker:build -DImageName=velen-etl-verification -DDockerHost=http://192.168.1.85:2375 -DExposePort=6001 -X

cd ../..

cd velen-etl-dispatcher/velen-etl-dispatcher-server
mvn docker:build -DImageName=velen-etl-dispatcher -DDockerHost=http://192.168.1.85:2375 -DExposePort=6001 -X

cd ../..

cd velen-etl-generator/velen-etl-generator-server
mvn docker:build -DImageName=velen-etl-generator -DDockerHost=http://192.168.1.85:2375 -DExposePort=6001 -X