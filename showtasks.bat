call runcrud

if "%ERRORLEVEL%" == "0" goto show
echo.
echo runcrud has errors - breaking work
goto fail

:show
start chrome http://localhost:8181/crud/v1/task/getTasks

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.