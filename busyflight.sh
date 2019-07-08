#!/bin/sh
#e.g. ./busyflight.sh start
#e.g. ./traccar.sh stop

SERVICE_NAME=busyflight
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f run/busyflight_pid ]; then
            mkdir -p run

            mvn clean install

            cd crazyair
            mvn clean package spring-boot:run -Dmaven.test.skip=true &
            echo $! > ../run/crazyair_pid

            cd ../toughjet
            mvn clean package spring-boot:run -Dmaven.test.skip=true &
            echo $! > ../run/toughjet_pid

            cd ../busyflight
            mvn clean package spring-boot:run -Dmaven.test.skip=true &
            echo $! > ../run/busyflight_pid

            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f run/busyflight_pid ]; then
            echo "$SERVICE_NAME stoping ..."
            PID=$(cat run/busyflight_pid);
            kill $PID;
            rm run/busyflight_pid

            PID=$(cat run/crazyair_pid);
            kill $PID;
            rm run/crazyair_pid

            PID=$(cat run/toughjet_pid);
            kill $PID;
            rm run/toughjet_pid

            echo "$SERVICE_NAME stopped ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac

