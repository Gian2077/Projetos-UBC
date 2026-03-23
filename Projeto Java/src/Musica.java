public class Musica {
    String titulo;
    String artista;
    int duracaoSegundos;
    String genero;
    void exibir() {
        System.out.println("Titulo: " + this.titulo);
        System.out.println("Artista: " + this.artista);
        System.out.println("Duração: " + this.getDuracaoFormatada());
        System.out.println("Gênero: " + this.genero + "\n");
    }
    String getDuracaoFormatada() {
        int min = this.duracaoSegundos / 60;
        int seg = this.duracaoSegundos % 60;
        return String.format("%d:%02d", min, seg);
    }
    boolean contemTitulo(String busca) {
        return busca.contains(this.titulo.toLowerCase());
    }
    boolean contemArtista(String busca) {
        return busca.contains(this.artista.toLowerCase());
    }
    boolean contemGenero(String busca) {
        return busca.contains(this.genero.toLowerCase());
    }
}
