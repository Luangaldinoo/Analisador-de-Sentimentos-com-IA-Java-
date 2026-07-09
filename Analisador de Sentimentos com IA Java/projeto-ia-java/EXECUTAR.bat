@echo off
title Analisador de Sentimentos com IA - Java
echo ============================================
echo  Analisador de Sentimentos com IA (Java)
echo ============================================
echo.

where java >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERRO] O Java nao foi encontrado no seu computador.
    echo Instale o Java ^(JRE 17 ou superior^) em:
    echo https://www.oracle.com/java/technologies/downloads/
    echo.
    pause
    exit /b 1
)

echo Iniciando aplicacao...
java -jar AnalisadorDeSentimentos.jar

if %errorlevel% neq 0 (
    echo.
    echo Ocorreu um erro ao executar o programa.
    pause
)
