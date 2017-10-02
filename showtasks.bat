call runcrud

@rem if "%ERRORLEVEL%" == "0" goto show
@rem echo.
@rem echo runcrud has errors - breaking work
@rem goto fail

:show
start chrome http://localhost:8181/crud/v1/task/getTasks

@rem :fail
@rem echo.
@rem echo There were errors

:end
echo.
echo Work is finished.