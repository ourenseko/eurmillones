@echo off 
color f0
title Eurmillons
:play
if exist eurmillons.jar (
java -jar eurmillons.jar
) else (
echo. Se necesita el archivo eurmillons.jar
)
pause
cls
goto play
