echo off
echo NUL>_.class&&del /s /f /q *.class
cls
javac com/krzem/pacman/Main.java&&java com/krzem/pacman/Main
start /min cmd /c "echo NUL>_.class&&del /s /f /q *.class"