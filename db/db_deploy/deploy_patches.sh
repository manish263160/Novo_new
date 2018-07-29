#!/bin/sh
#variables to change

export username=root
export password=root
export hostname=localhost
export schema=novo_wash;
export PATCH_DIR=/home/manishkm/NovoWashProject/NovoWash/db

export EMAIL="mydelivery263160@gmail.com"

rm -rf $PATCH_DIR/deployment_log
mkdir $PATCH_DIR/deployment_log
export deployment_log_file=$PATCH_DIR/deployment_log/deploy.log

cd
cd $PATCH_DIR
echo to patches directory $PATCH_DIR

export OUTPUT_FILE=installedpatches.txt;
rm $OUTPUT_FILE;

echo "Check if installed patches exist" >> $deployment_log_file

mysql -u $username --password=$password $schema < $PATCH_DIR/ddl_create_table_installed_patches.sql;


if [ -f  $PATCH_DIR/patchlist.txt ]; then
echo picking up list from $PATCH_DIR/patchlist.txt file

if [ -f installedpatches.txt ];
 then echo "installedpatches.txt exists!!"
else
      echo "=== Getting the list of installed patches from DB===="
      mysql -u $username --password=$password $schema -e "select CONCAT(patch_name,',',status) from installed_patches;" >> $OUTPUT_FILE
fi

dos2unix $PATCH_DIR/patchlist.txt 1>/dev/null;
echo "=====picking up list from $PATCH_DIR/patchlist.txt ===="
echo "========="
echo ""
echo ""
ALL_PATCHES=`cat $PATCH_DIR/patchlist.txt | grep -v ^# | grep -v ^^M`
patches=""
for f in $ALL_PATCHES
do
	echo $f
	patches="$patches $f";
done
echo "Deployment Log for $PATCH_DIR/patchlist.txt" >> $deployment_log_file
echo "==========" >> $deployment_log_file
echo "Following patches from $PATCH_DIR/patchlist.txt will be executed" >> $deployment_log_file
echo "==========" >> $deployment_log_file
for i in $patches;do echo $i >> $deployment_log_file;done


flag=0;
echo "" >> $deployment_log_file
echo "==Starting Patch Execution===" >> $deployment_log_file	
for i in $patches;
  do 
    if [ $flag -eq 0 ]
      then $PATCH_DIR/db_deploy/rundbpatch.sh $i
       if [ $? -gt 0 ]
         then echo "$i -- Patch failed during execution" >> $deployment_log_file
	      echo "" >> $deployment_log_file
	      echo "===Following subsequent patches in the patchlist won't be executed!!===" >> $deployment_log_file
       fi
    else
	echo "$i" >> $deployment_log_file
    fi
  done
fi

