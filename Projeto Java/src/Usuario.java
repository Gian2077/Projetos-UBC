import java.util.ArrayList;

public class Usuario {
    String nome;
    ArrayList<Playlist> playlists;
    void criarPlaylist(String nome) {
        Playlist playlist = new Playlist();
        playlist.nome = (nome);
        playlists.add(playlist);
    }
    Playlist getPlaylist(int indice) {
        return null;
    }
    void listarPlaylists() {

    }
}
