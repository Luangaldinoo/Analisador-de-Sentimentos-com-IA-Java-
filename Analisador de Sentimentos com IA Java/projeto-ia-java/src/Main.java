import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Locale;

/**
 * Analisador de Sentimentos com IA (Java + Naive Bayes)
 *
 * Projeto de portfólio: uma aplicação desktop em Java (Swing) que usa
 * um classificador de Inteligência Artificial (Naive Bayes) treinado
 * do zero para identificar se uma frase em português tem sentimento
 * POSITIVO ou NEGATIVO.
 *
 * Não depende de nenhuma API externa nem de internet: todo o
 * treinamento e a inferência do modelo de IA acontecem localmente.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::criarInterface);
    }

    private static void criarInterface() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        NaiveBayesClassifier classificador = new NaiveBayesClassifier();
        treinarModelo(classificador);

        JFrame frame = new JFrame("Analisador de Sentimentos com IA - Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(620, 480);
        frame.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(12, 12));
        painel.setBorder(new EmptyBorder(16, 16, 16, 16));

        JLabel titulo = new JLabel("Analisador de Sentimentos (Naive Bayes)");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel subtitulo = new JLabel(
                "<html>Modelo treinado localmente com " + classificador.getTotalDocs() +
                " frases de exemplo e vocabulário de " + classificador.getVocabularySize() +
                " palavras.<br>Digite uma frase em português e clique em Analisar.</html>");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JPanel topo = new JPanel();
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));
        topo.add(titulo);
        topo.add(Box.createVerticalStrut(6));
        topo.add(subtitulo);

        JTextArea entrada = new JTextArea(4, 40);
        entrada.setLineWrap(true);
        entrada.setWrapStyleWord(true);
        entrada.setFont(new Font("SansSerif", Font.PLAIN, 14));
        entrada.setText("O atendimento foi rápido e muito atencioso!");
        JScrollPane scrollEntrada = new JScrollPane(entrada);
        scrollEntrada.setBorder(BorderFactory.createTitledBorder("Digite sua frase"));

        JButton botaoAnalisar = new JButton("Analisar Sentimento");
        botaoAnalisar.setFont(new Font("SansSerif", Font.BOLD, 14));

        JTextArea resultado = new JTextArea(8, 40);
        resultado.setEditable(false);
        resultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollResultado = new JScrollPane(resultado);
        scrollResultado.setBorder(BorderFactory.createTitledBorder("Resultado da IA"));

        botaoAnalisar.addActionListener(e -> {
            String texto = entrada.getText().trim();
            if (texto.isEmpty()) {
                resultado.setText("Digite uma frase antes de analisar.");
                return;
            }
            NaiveBayesClassifier.Resultado r = classificador.classify(texto);
            StringBuilder sb = new StringBuilder();
            String emoji = r.rotulo.equals("positivo") ? "😀" : "☹️";
            sb.append("Sentimento previsto: ").append(r.rotulo.toUpperCase()).append(" ").append(emoji).append("\n\n");
            sb.append("Probabilidades calculadas pelo modelo:\n");
            r.probabilidades.forEach((label, prob) ->
                    sb.append(String.format(Locale.US, "  - %-10s %6.2f%%%n", label, prob * 100)));
            resultado.setText(sb.toString());
        });

        JPanel centro = new JPanel(new BorderLayout(8, 8));
        centro.add(scrollEntrada, BorderLayout.CENTER);
        JPanel botaoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botaoPanel.add(botaoAnalisar);
        centro.add(botaoPanel, BorderLayout.SOUTH);

        painel.add(topo, BorderLayout.NORTH);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(scrollResultado, BorderLayout.SOUTH);

        frame.setContentPane(painel);
        frame.setVisible(true);
    }

    /** Dados de treino: frases em português rotuladas como positivo/negativo. */
    private static void treinarModelo(NaiveBayesClassifier classificador) {
        String[][] exemplosPositivos = {
                {"Adorei o produto, superou minhas expectativas", "positivo"},
                {"Atendimento excelente, muito educado e rápido", "positivo"},
                {"Recomendo demais, qualidade incrível", "positivo"},
                {"Fiquei muito feliz com a entrega no prazo", "positivo"},
                {"O suporte técnico foi ótimo e resolveu meu problema", "positivo"},
                {"Excelente experiência de compra, voltarei a comprar", "positivo"},
                {"O curso é maravilhoso e o professor explica muito bem", "positivo"},
                {"Estou muito satisfeito com o resultado final", "positivo"},
                {"A comida estava deliciosa e o ambiente agradável", "positivo"},
                {"Produto de ótima qualidade, vale cada centavo", "positivo"},
                {"Equipe muito competente e prestativa", "positivo"},
                {"Simplesmente perfeito, sem defeitos", "positivo"},
                {"Que alegria receber esse presente incrível", "positivo"},
                {"O aplicativo é rápido, bonito e fácil de usar", "positivo"},
                {"Fui muito bem tratado durante toda a viagem", "positivo"},
                {"Serviço impecável, com certeza recomendo a todos", "positivo"},
                {"O filme foi maravilhoso, emocionante do início ao fim", "positivo"},
                {"Estou impressionado com a qualidade do material", "positivo"},
                {"Adorei o hotel, quarto limpo e confortável", "positivo"},
                {"Ótimo custo benefício, super indico", "positivo"}
        };

        String[][] exemplosNegativos = {
                {"Péssimo atendimento, demoraram horas para responder", "negativo"},
                {"O produto veio quebrado e com defeito", "negativo"},
                {"Fiquei muito insatisfeito com a entrega atrasada", "negativo"},
                {"Não recomendo, qualidade horrível", "negativo"},
                {"O suporte técnico não resolveu absolutamente nada", "negativo"},
                {"Experiência terrível, nunca mais compro aqui", "negativo"},
                {"O curso é confuso e o professor não explica direito", "negativo"},
                {"Estou muito decepcionado com o resultado final", "negativo"},
                {"A comida estava fria e sem sabor", "negativo"},
                {"Produto de má qualidade, dinheiro jogado fora", "negativo"},
                {"Equipe despreparada e mal educada", "negativo"},
                {"Simplesmente horrível, cheio de defeitos", "negativo"},
                {"Que decepção receber esse presente quebrado", "negativo"},
                {"O aplicativo trava toda hora e é difícil de usar", "negativo"},
                {"Fui muito mal tratado durante toda a viagem", "negativo"},
                {"Serviço péssimo, não recomendo a ninguém", "negativo"},
                {"O filme foi horrível, chato do início ao fim", "negativo"},
                {"Estou decepcionado com a qualidade do material", "negativo"},
                {"Odiei o hotel, quarto sujo e barulhento", "negativo"},
                {"Preço abusivo para um serviço tão ruim", "negativo"}
        };

        for (String[] exemplo : exemplosPositivos) classificador.train(exemplo[0], exemplo[1]);
        for (String[] exemplo : exemplosNegativos) classificador.train(exemplo[0], exemplo[1]);
    }
}
