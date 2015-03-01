@echo off 

mysql -u admin -pletmein towers < towers.sql
mysql -u admin -pletmein towerstest < towerstest.sql