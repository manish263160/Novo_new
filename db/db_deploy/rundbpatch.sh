#!/bin/sh

SQLSCRIPT=$1
shift

echo ----------------
echo processing $SQLSCRIPT
echo ----------------

if [ ! -f installedpatches.txt ]; then
     echo "FILE installedpatches.txt does not exist!!!"
     exit 1
fi

ISVERSIONINSTALLED=`grep -x $SQLSCRIPT,true installedpatches.txt | awk -F "," '{print $2}' ;`
TRUE=true

case $ISVERSIONINSTALLED in
"true") echo [info] patch $SQLSCRIPT is already installed - exiting ; echo [info] patch $SQLSCRIPT is already installed - exiting>>$deployment_log_file;;
#####*) echo [info] patch $SQLSCRIPT not installed. Installing it now ; mysql -u $username --password=$password $schema > $SQLSCRIPT
*)echo [Info] Connecting to mysql; echo [info] patch $SQLSCRIPT not installed.; mysql -u $username --password=$password $schema < $SQLSCRIPT; mysql -u $username --password=$password $schema -e "insert into installed_patches(patch_name)values('${SQLSCRIPT}');"

esac

SQLRET=$?
if [ $SQLRET -ne 0 ]
then
    echo Sql Execution returned value = $SQLRET
    echo '[ERROR] Exception happened while executing sql ' ${SQLSCRIPT}
    echo '[ERROR] Exception happened while executing sql ' ${SQLSCRIPT} >> $deployment_log_file
	exit 255;
else
    echo Sql Execution returned value = $SQLRET
    echo '[INFO] No Exception happened while executing sql '${SQLSCRIPT}
    echo '[INFO] No Exception happened while executing sql '${SQLSCRIPT} >>$deployment_log_file
    echo '==============================================================================='>>$deployment_log_file
fi
