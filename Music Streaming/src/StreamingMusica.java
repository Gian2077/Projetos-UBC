import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sistema de Streaming de Música - CP1
 *
 */
public class StreamingMusica {

    // ArrayLists para armazenar os dados das músicas
    static ArrayList<String> titulos = new ArrayList<>();
    static ArrayList<String> artistas = new ArrayList<>();
    static ArrayList<Integer> duracoes = new ArrayList<>();
    static ArrayList<String> generos = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Adicionar músicas de teste
        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    /**
     * LIVE CODING: Professor implementa este método
     * Exibe o menu principal do sistema
     */
    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por título");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    /**
     * FORNECIDO: Lê opção com tratamento de erro
     */
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * LIVE CODING: Professor implementa este método
     * Processa a opção escolhida
     */
    public static void processarOpcao(int opcao) {
        // TODO: Professor implementa ao vivo
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarMusicas();
                break;
            case 3:
                buscarPorTitulo();
            default:
                System.out.println("Opção Inválida");
                break;
        }
    }

    /**
     * LIVE CODING: Professor implementa este método (PRINCIPAL)
     * Cadastra uma nova música
     */
    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        // TODO: Professor implementa ao vivo
        // 1. Solicitar título
        System.out.println("Digite o titulo da música:");
        String titulo = scanner.nextLine();
        // 2. Validar título (não vazio)
        if (titulo.isEmpty()) {
            System.out.println("Titulo não pode estar em branco");
            return;
        }
        // 3. Solicitar artista
        System.out.println("Digite o artista:");
        String artista = scanner.nextLine();
        // 4. Validar artista
        if (artista.isEmpty()) {
            System.out.println("Artista não pode estar em branco");
            return;
        }
        // 5. Solicitar duração
        System.out.println("Digite a duração da música:");
        int duracao = scanner.nextInt();
        scanner.nextLine();
        // 6. Validar duração
        if(duracao <= 0) {
            System.out.println("Duração da música é inválida");
            return;
        }
        System.out.println("Digite o gênero da musica:");
        String genero = scanner.nextLine();
        if (genero.isEmpty()) {
            System.out.println("Gênero da música é inválido");
            return;
        }
        // 7. Adicionar nos ArrayLists
        titulos.add(titulo);
        artistas.add(titulo);
        duracoes.add(duracao);
        generos.add(genero);
        // 8. Exibir mensagem de sucesso
        System.out.println("Musica cadastrada com sucesso!");
        System.out.println("⚠️ TODO: Implementar cadastro");
    }

    /**
     * LIVE CODING: Professor implementa este método
     * Lista todas as músicas
     */
    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");

        // TODO: Professor implementa ao vivo
        // 1. Verificar se está vazio
        if(artistas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada");
            return;
        }
        // 2. Percorrer ArrayLists
        for(int i = 0; i < artistas.size(); i++) {
            System.out.println("Titulo: " + titulos.get(i));
            System.out.println("Artista: " + artistas.get(i));
            System.out.println("Duração: " + formatarDuracao(duracoes.get(i)));
            System.out.println("Gênero: " + artistas.get(i));
        }
        // 3. Exibir cada música formatada

        System.out.println("⚠️ TODO: Implementar listagem");
    }

    /**
     * ALUNO IMPLEMENTA: Busca por título
     * (Professor mostra a estrutura, alunos completam depois)
     */
    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---");

        // TODO: Aluno implementa
        System.out.print("Digite o título: ");
        String busca = scanner.nextLine().toLowerCase();

        // TODO: Percorrer e buscar
        // Dica: usar .contains() e .toLowerCase()
        boolean encontrou = false;
        for (int i = 0; i < titulos.size(); i++) {
            encontrou = titulos.get(i).toLowerCase().contains(busca);
            if (encontrou) {
                System.out.println("Musica encontrada");
                System.out.println("Titulo: " + titulos.get(i));
                System.out.println("Artista: " + artistas.get(i));
                System.out.println("Duração: " + formatarDuracao(duracoes.get(i)));
                System.out.println("Gênero: " + artistas.get(i));
                break;
            }
            if (!encontrou) {
                System.out.println("Musica não encontrada!");
            }
        }
        System.out.println("Fim da busca!");

        System.out.println("⚠️ TODO: Implementar busca");
    }

    /**
     * FORNECIDO: Formata duração
     */
    public static String formatarDuracao(int segundos) {
        int min = segundos / 60;
        int seg = segundos % 60;
        return String.format("%d:%02d", min, seg);
    }

    /**
     * FORNECIDO: Músicas de teste
     */
    public static void adicionarMusicasTeste() {
        titulos.add("Bohemian Rhapsody");
        artistas.add("Queen");
        duracoes.add(354);
        generos.add("Rock");

        titulos.add("Billie Jean");
        artistas.add("Michael Jackson");
        duracoes.add(293);
        generos.add("Pop");
    }
}
