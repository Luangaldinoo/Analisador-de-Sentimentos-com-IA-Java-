# 🧠 Analisador de Sentimentos com IA (Java)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Machine Learning](https://img.shields.io/badge/Machine%20Learning-3F51B5?style=for-the-badge&logo=analytics&logoColor=white)
![Status](https://img.shields.io/badge/Status-Conclu%C3%ADdo-brightgreen?style=for-the-badge)

## 📝 Resumo do Projeto

Este projeto consiste em uma aplicação desktop desenvolvida em **Java** que implementa, totalmente do zero, um algoritmo de **Inteligência Artificial baseado no modelo Naive Bayes** para análise e classificação de sentimentos em textos. 

A aplicação analisa frases digitadas pelo usuário e determina se a conotação do texto é **Positiva** ou **Negativa**. O grande diferencial deste projeto é que ele opera de forma **100% local e offline** — o modelo de Machine Learning é treinado dinamicamente na inicialização do sistema, sem a necessidade de nenhuma API externa (como OpenAI ou Google Cloud), dependência de internet ou bibliotecas prontas de IA (como Python/Scikit-Learn). Tudo foi construído usando a API nativa do Java.

---

## 🛠️ Problemas Solucionados pelo Projeto

No cenário de desenvolvimento de software atual, o uso de IA geralmente está atrelado a pacotes pesados ou chamadas de APIs pagas baseadas em nuvem. Este projeto resolve e demonstra maturidade técnica nos seguintes pontos:

1. **Dependência de APIs de Terceiros:** Elimina custos com requisições e a necessidade de gerenciar chaves de API para tarefas simples de classificação de texto.
2. **Privacidade e Latência:** Como o processamento ocorre inteiramente na JVM (*Java Virtual Machine*) do cliente, os dados não saem da máquina, garantindo latência zero de rede e total privacidade da informação.
3. **Abstração "Black Box" de Machine Learning:** Resolve a falta de entendimento conceitual que muitos desenvolvedores enfrentam ao usar bibliotecas prontas. Ao codificar o Teorema de Bayes na mão, o projeto prova o domínio matemático sobre probabilidade condicional, vetorização e suavização de dados.

---

## 🚀 Como Executar

### 1. Baixe e extraia o ZIP
Clique com o botão direito no arquivo `projeto-ia-java.zip` ➔ **"Extrair tudo"** (Windows) ou dê dois cliques (Mac).

### 2. Rode o programa
Entre na pasta extraída (`projeto-ia-java`) e dê dois cliques no arquivo correspondente ao seu sistema operacional:
* **Windows:** `EXECUTAR.bat`
* **Mac/Linux:** `EXECUTAR.sh`

Uma janela preta (terminal) vai abrir rapidinho e, em seguida, a interface gráfica do programa (com o campo de texto e o botão "Analisar Sentimento") deve aparecer na tela.

> ⚠️ **Requisito:** Você precisa ter o Java instalado no computador (JRE 17 ou mais recente). Se der erro dizendo que *"java não é reconhecido"* ou algo parecido, baixe a versão oficial em: [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/).
> ## 👤 Desenvolvido por

* **Luan Galdino** - *Software Engineering Student & Full Stack Developer*

Se preferir rodar direto pelo terminal, use o comando:
```bash
java -jar AnalisadorDeSentimentos.jar



