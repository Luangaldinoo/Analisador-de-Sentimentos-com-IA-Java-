import java.util.*;

/**
 * Classificador de Sentimentos baseado no algoritmo Naive Bayes.
 *
 * Esta classe implementa, do zero (sem bibliotecas externas de IA/ML),
 * um classificador probabilístico que aprende a distinguir textos
 * POSITIVOS de textos NEGATIVOS a partir de exemplos de treino.
 *
 * Conceitos de IA/Machine Learning demonstrados:
 *  - Representação de texto como "bag of words" (saco de palavras)
 *  - Treinamento supervisionado (aprende com exemplos rotulados)
 *  - Estimativa de probabilidade (Teorema de Bayes)
 *  - Suavização de Laplace (Laplace Smoothing) para lidar com palavras
 *    nunca vistas durante o treino
 */
public class NaiveBayesClassifier {

    // Contagem de vezes que cada palavra aparece em cada classe
    private final Map<String, Map<String, Integer>> wordCountPerClass = new HashMap<>();
    // Total de palavras (tokens) vistas em cada classe
    private final Map<String, Integer> totalWordsPerClass = new HashMap<>();
    // Quantos documentos de treino existem por classe
    private final Map<String, Integer> docCountPerClass = new HashMap<>();
    // Vocabulário total (todas as palavras distintas já vistas)
    private final Set<String> vocabulary = new HashSet<>();

    private int totalDocs = 0;

    /** Treina o modelo com uma frase e seu rótulo (ex: "positivo" / "negativo"). */
    public void train(String text, String label) {
        totalDocs++;
        docCountPerClass.merge(label, 1, Integer::sum);

        wordCountPerClass.putIfAbsent(label, new HashMap<>());
        totalWordsPerClass.putIfAbsent(label, 0);

        for (String word : tokenize(text)) {
            vocabulary.add(word);
            wordCountPerClass.get(label).merge(word, 1, Integer::sum);
            totalWordsPerClass.merge(label, 1, Integer::sum);
        }
    }

    /** Resultado da classificação: rótulo previsto + probabilidades por classe. */
    public static class Resultado {
        public final String rotulo;
        public final Map<String, Double> probabilidades;

        Resultado(String rotulo, Map<String, Double> probabilidades) {
            this.rotulo = rotulo;
            this.probabilidades = probabilidades;
        }
    }

    /** Classifica um novo texto retornando o rótulo mais provável. */
    public Resultado classify(String text) {
        List<String> tokens = tokenize(text);
        Map<String, Double> logScores = new HashMap<>();
        int vocabSize = vocabulary.size();

        for (String label : docCountPerClass.keySet()) {
            // P(classe) - probabilidade a priori
            double priorProb = (double) docCountPerClass.get(label) / totalDocs;
            double logScore = Math.log(priorProb);

            int totalWordsInClass = totalWordsPerClass.getOrDefault(label, 0);
            Map<String, Integer> wordCounts = wordCountPerClass.getOrDefault(label, Collections.emptyMap());

            for (String word : tokens) {
                int count = wordCounts.getOrDefault(word, 0);
                // Suavização de Laplace: soma 1 para evitar probabilidade zero
                double wordProb = (count + 1.0) / (totalWordsInClass + vocabSize);
                logScore += Math.log(wordProb);
            }
            logScores.put(label, logScore);
        }

        // Converte log-scores em probabilidades normalizadas (0 a 1) via softmax
        double maxLog = Collections.max(logScores.values());
        double sumExp = 0.0;
        Map<String, Double> probs = new HashMap<>();
        for (Map.Entry<String, Double> e : logScores.entrySet()) {
            double expVal = Math.exp(e.getValue() - maxLog);
            probs.put(e.getKey(), expVal);
            sumExp += expVal;
        }
        for (String key : probs.keySet()) {
            probs.put(key, probs.get(key) / sumExp);
        }

        String melhorRotulo = Collections.max(probs.entrySet(), Map.Entry.comparingByValue()).getKey();
        return new Resultado(melhorRotulo, probs);
    }

    /** Quebra o texto em palavras (tokens), normalizando para minúsculas e removendo pontuação. */
    private List<String> tokenize(String text) {
        String limpo = text.toLowerCase()
                .replaceAll("[^a-zA-Z0-9áàâãéèêíïóôõöúçñ\\s]", " ");
        List<String> tokens = new ArrayList<>();
        for (String palavra : limpo.split("\\s+")) {
            if (!palavra.isBlank() && palavra.length() > 1) {
                tokens.add(palavra);
            }
        }
        return tokens;
    }

    public int getTotalDocs() {
        return totalDocs;
    }

    public int getVocabularySize() {
        return vocabulary.size();
    }
}
