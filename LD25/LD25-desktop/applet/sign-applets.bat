@for /f %%i in ("%0") do @set curpath=%%~dpi 
@cd /d %curpath%

@for /r . %%X in (*.jar) do "C:\Program Files\Java\jdk1.7.0\bin\jarsigner.exe" -keystore .keystore -storepass qes34hml %%X gdxkey