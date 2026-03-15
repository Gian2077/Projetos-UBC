import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StreamingMusica {

    // ArrayLists para armazenar os dados das músicas
    static ArrayList<String> titulos = new ArrayList<>();
    static ArrayList<String> artistas = new ArrayList<>();
    static ArrayList<Integer> duracoes = new ArrayList<>();
    static ArrayList<String> generos = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar música por título");
        System.out.println("4. Buscar músicas por artista");
        System.out.println("5. Buscar músicas por gênero");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarMusicas();
                break;
            case 3:
                buscarPorTitulo();
                break;
            case 4:
                buscarPorArtista();
                break;
            case 5:
                buscarPorGenero();
                break;
            case 6:
                exibirEstatisticas();
                break;
            default:
                break;
        }
    }
    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");
        System.out.println("Digite o titulo da música:");
        String titulo = scanner.nextLine();
        if (titulo.isEmpty()) {
            System.out.println("Titulo não pode estar em branco");
            return;
        }
        System.out.println("Digite o artista:");
        String artista = scanner.nextLine();
        if (artista.isEmpty()) {
            System.out.println("Artista não pode estar em branco");
            return;
        }
        System.out.println("Digite a duração da música:");
        int duracao = scanner.nextInt();
        scanner.nextLine();
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
        artistas.add(artista);
        duracoes.add(duracao);
        generos.add(genero);
        // 8. Exibir mensagem de sucesso
        System.out.println("Musica cadastrada com sucesso!");
    }
    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");
        if(artistas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada");
            return;
        }
        for(int i = 0; i < artistas.size(); i++) {
            System.out.println(i + 1 + ". Titulo: " + titulos.get(i) + " | Artista: " + artistas.get(i) + " | Duração: " + formatarDuracao(duracoes.get(i)) + " | Gênero: " + generos.get(i));
        }
    }
    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---\n");
        System.out.print("Digite o título: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        for (int i = 0; i < titulos.size(); i++) {
            encontrou = titulos.get(i).toLowerCase().contains(busca);
            if (encontrou) {
                System.out.println("Musicas encontradas\n");
                System.out.println("Titulo: " + titulos.get(i));
                System.out.println("Artista: " + artistas.get(i));
                System.out.println("Duração: " + formatarDuracao(duracoes.get(i)));
                System.out.println("Gênero: " + generos.get(i));
            }
        }
        if(encontrou) {
            System.out.println("Fim da busca!");
        } else {
            System.out.println("Fim da busca!");
            System.out.println("Musica não encontrada!");
        }
    }
    public static void buscarPorArtista() {
        System.out.println("\n--- BUSCAR POR ARTISTA ---\n");
        System.out.println("Digite o artista: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        for (int i = 0; i < artistas.size(); i++) {
            encontrou = artistas.get(i).toLowerCase().contains(busca);
            if (encontrou) {
                System.out.println("Musicas encontradas\n");
                System.out.println("Titulo: " + titulos.get(i));
                System.out.println("Artista: " + artistas.get(i));
                System.out.println("Duração: " + formatarDuracao(duracoes.get(i)));
                System.out.println("Gênero: " + generos.get(i));
            }
        }
        if(encontrou) {
            System.out.println("Fim da busca!");
        } else {
            System.out.println("Fim da busca!");
            System.out.println("Musica não encontrada!");
        }
    }
    public static void buscarPorGenero() {
        System.out.println("\n--- BUSCAR POR GÊNERO ---\n");
        System.out.println("Digite o gênero: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        for (int i = 0; i < generos.size(); i++) {
            encontrou = generos.get(i).toLowerCase().contains(busca);
            if (encontrou) {
                System.out.println("Musicas encontradas\n");
                System.out.println("Titulo: " + titulos.get(i));
                System.out.println("Artista: " + artistas.get(i));
                System.out.println("Duração: " + formatarDuracao(duracoes.get(i)));
                System.out.println("Gênero: " + generos.get(i));
            }
        }
        if(encontrou) {
            System.out.println("Fim da busca!");
        } else {
            System.out.println("Fim da busca!");
            System.out.println("Musica não encontrada!");
        }
    }
    public static void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===\n");
        System.out.println("Total de músicas: " + titulos.size());
        int duracaoTotal = 0;
        for (int i = 0; i < duracoes.size(); i++) {
            duracaoTotal = duracaoTotal + duracoes.get(i);
        }
        System.out.println("Duração total: " + formatarDuracao(duracaoTotal));
        System.out.println("Duração média: " + formatarDuracao(duracaoTotal / duracoes.size()));
        Map<String, Integer> generosMaisCadastrado = new HashMap<>();
        generosMaisCadastrado.put(generos.get(0), 1);
        for(int i = 1; i < generos.size(); i++) {
            if(generosMaisCadastrado.containsKey(generos.get(i))) {
                generosMaisCadastrado.put(generos.get(i), generosMaisCadastrado.get(generos.get(i)) + 1);
            } else {
                generosMaisCadastrado.put(generos.get(i), 1);
            }
        }
        String generoMaisCadastrado = null;
        int maisVezesCadastrado = 0;
        for(Map.Entry<String, Integer> entry : generosMaisCadastrado.entrySet()) {
            if(entry.getValue() > maisVezesCadastrado) {
                maisVezesCadastrado = entry.getValue();
                generoMaisCadastrado = entry.getKey();
            }
        }
        System.out.println("Gênero mais cadastrado: " + generoMaisCadastrado + " (" + maisVezesCadastrado + ") músicas");
    }
    public static String formatarDuracao(int segundos) {
        int min = segundos / 60;
        int seg = segundos % 60;
        return String.format("%d:%02d", min, seg);
    }
    public static void adicionarMusicasTeste() {
        titulos.add("Bohemian Rhapsody");
        artistas.add("Queen");
        duracoes.add(354);
        generos.add("Rock");
        titulos.add("Billie Jean");
        artistas.add("Michael Jackson");
        duracoes.add(293);
        generos.add("Pop");
        titulos.add("In the Morning");
        artistas.add("Paramore");
        duracoes.add(222);
        generos.add("Rock");
        titulos.add("The Only Exception");
        artistas.add("Paramore");
        duracoes.add(332);
        generos.add("Rock");
    }
}
