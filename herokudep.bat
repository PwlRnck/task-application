echo off
set var1=trello.app.key
set var2=trello.app.token
set var3=trello.app.username
set var4=spring.mail.username
set var5=spring.mail.password
set var6=admin.name
set var7=admin.mail
set var8=spring.datasource.url
set var9=spring.datasource.username
set var10=spring.datasource.password


For /F "tokens=1* delims==" %%A IN (src\main\resources\secret.properties) DO (
    IF "%%A"=="%var1%" set val1=%%B
    IF "%%A"=="%var2%" set val2=%%B
    IF "%%A"=="%var3%" set val3=%%B
    IF "%%A"=="%var4%" set val4=%%B
    IF "%%A"=="%var5%" set val5=%%B
    IF "%%A"=="%var6%" set val6=%%B
    IF "%%A"=="%var7%" set val7=%%B
 )

 For /F "tokens=1* delims==" %%A IN (src\main\resources\secret-heroku.properties) DO (
     IF "%%A"=="%var8%" set val8=%%B
     IF "%%A"=="%var9%" set val9=%%B
     IF "%%A"=="%var10%" set val10=%%B
)

call set var1=%%var1:.=_%%
call set var2=%%var2:.=_%%
call set var3=%%var3:.=_%%
call set var4=%%var4:.=_%%
call set var5=%%var5:.=_%%
call set var6=%%var6:.=_%%
call set var7=%%var7:.=_%%
call set var8=%%var8:.=_%%
call set var9=%%var9:.=_%%
call set var10=%%var10:.=_%%

call :upcase var1
call :upcase var2
call :upcase var3
call :upcase var4
call :upcase var5
call :upcase var6
call :upcase var7
call :upcase var8
call :upcase var9
call :upcase var10

call heroku config:set %var1%=%val1%
call heroku config:set %var2%=%val2%
call heroku config:set %var3%=%val3%
call heroku config:set %var4%=%val4%
call heroku config:set %var5%=%val5%
call heroku config:set %var6%=%val6%
call heroku config:set %var7%=%val7%
call heroku config:set %var8%=%val8%
call heroku config:set %var9%=%val9%
call heroku config:set %var10%=%val10%

call git add .
call git commit -m "new deployment"
call git push heroku master

heroku logs

:LoCase
:: Subroutine to convert a variable VALUE to all lower case.
:: The argument for this subroutine is the variable NAME.
FOR %%i IN ("A=a" "B=b" "C=c" "D=d" "E=e" "F=f" "G=g" "H=h" "I=i" "J=j" "K=k" "L=l" "M=m" "N=n" "O=o" "P=p" "Q=q" "R=r" "S=s" "T=t" "U=u" "V=v" "W=w" "X=x" "Y=y" "Z=z") DO CALL SET "%1=%%%1:%%~i%%"


:UpCase
:: Subroutine to convert a variable VALUE to all UPPER CASE.
:: The argument for this subroutine is the variable NAME.
FOR %%i IN ("a=A" "b=B" "c=C" "d=D" "e=E" "f=F" "g=G" "h=H" "i=I" "j=J" "k=K" "l=L" "m=M" "n=N" "o=O" "p=P" "q=Q" "r=R" "s=S" "t=T" "u=U" "v=V" "w=W" "x=X" "y=Y" "z=Z") DO CALL SET "%1=%%%1:%%~i%%"

