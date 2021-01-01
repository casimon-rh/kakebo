#!/bin/sh
docker build --compress -t t-kakebo -f docker/thorntail.dockerfile . && \
docker run -it --rm --name t-kakebo -p 8080:8080 --add-host="psql:172.17.0.2" t-kakebo