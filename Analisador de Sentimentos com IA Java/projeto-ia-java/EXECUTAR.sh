#!/bin/bash
echo "============================================"
echo " Analisador de Sentimentos com IA (Java)"
echo "============================================"
echo ""

cd "$(dirname "$0")"

if ! command -v java &> /dev/null; then
    echo "[ERRO] O Java nao foi encontrado no seu computador."
    echo "Instale o Java (JRE 17 ou superior) em:"
    echo "https://www.oracle.com/java/technologies/downloads/"
    read -p "Pressione Enter para sair..."
    exit 1
fi

echo "Iniciando aplicacao..."
java -jar AnalisadorDeSentimentos.jar

read -p "Pressione Enter para fechar..."
