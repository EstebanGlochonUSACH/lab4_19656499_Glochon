package lab4_19656499_Glochon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class Usuario extends AutoIncrementable {
    private final String username;
    private final String password;
    private final LocalDate fechaCreacion;
    private final LinkedList<Publicacion> publicaciones;
    private final LinkedList<Usuario> followers;
    private final LinkedList<Usuario> following;

    /**
     * Constructor para la clase Usuario.
     * @param user
     * @param pass
     */
    public Usuario(String user, String pass) {
        // Inicializar Id
        super();

        // Datos de la instancia
        this.username = user;
        this.password = pass;
        this.fechaCreacion = LocalDate.now();

        // Colecciones anidadas
        this.publicaciones = new LinkedList<Publicacion>();
        this.followers = new LinkedList<Usuario>();
        this.following = new LinkedList<Usuario>();
    }

    /**
     * Selector para obtener el nombre de usuario.
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Selector para obtener la fecha de registro del usuario.
     * @return LocalDate
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Selector para obtener una lista de los followers y followings.
     * @return Collection(Usuario)
     */
    public ArrayList<Usuario> getContactos() {
        ArrayList<Usuario> contactos = new ArrayList<>();
        
        Iterator<Usuario> iter = followers.iterator();
        Usuario aux;
        
        while(iter.hasNext()){
            aux = iter.next();
            if(!contactos.contains(aux)){
                contactos.add(aux);
            }
        }
        
        iter = following.iterator();
        while(iter.hasNext()){
            aux = iter.next();
            if(!contactos.contains(aux)){
                contactos.add(aux);
            }
        }
        
        return contactos;
    }

    /**
     * Selector para obtener el numero total de publicaciones realizadas por 
     * el usuario.
     * @return int
     */
    public int totalPublicaciones() {
        return publicaciones.size();
    }

    /**
     * Selector para obtener el numero total de followers que tiene el usuario.
     * @return int
     */
    public int totalFollowers() {
        return followers.size();
    }

    /**
     * Selector para obtener el numero total de usuarios al los que sigue el 
     * usuario.
     * @return int
     */
    public int totalFollowing() {
        return following.size();
    }

    /**
     * Metodo para comprobar si coincide una contraseña con la que uso el 
     * usuario para registrarse.
     * @param pass
     * @return boolean
     */
    public boolean validarPassword(String pass){
        return pass.equals(this.password);
    }

    /**
     * Metodo de pertenencia para verificar si una ID correctonde a alguna de 
     * las publicaciones creadas por el usuario.
     * @param pubId
     * @return boolean
     */
    public boolean hasPublicacion(int pubId){
        Iterator<Publicacion> iter = publicaciones.iterator();
        Publicacion auxPub;
        while(iter.hasNext()){
            auxPub = iter.next();
            if(auxPub.getId() == pubId) return true;
        }
        return false;
    }

    /**
     * Metodo de pertenencia para verificar que una publicacion este asociada 
     * al usuario.
     * @param pub
     * @return boolean
     */
    public boolean hasPublicacion(Publicacion pub){
        Iterator<Publicacion> iter = publicaciones.iterator();
        Publicacion auxPub;
        while(iter.hasNext()){
            auxPub = iter.next();
            if(auxPub == pub) return true;
        }
        return false;
    }
    
    /**
     * Agregar una nueva publicacion al registro de publicaciones del usuario.
     * @param pub
     */
    public void addPublicacion(Publicacion pub){
        if(!this.publicaciones.contains(pub) && pub.getAutor() == this){
            this.publicaciones.addFirst(pub);
        }
    }

    /**
     * Agregar un usuario como "follower" creando una relacion entre ambos.
     * @param user
     */
    public void addFollower(Usuario user) {
        if(user != this && !this.followers.contains(user)){
            this.followers.add(user);
        }
    }

    /**
     * Agregar un usuario como "following" creando una relacion entre ambos.
     * @param user
     */
    public void addFollowing(Usuario user) {
        if(user != this && !this.following.contains(user)){
            this.following.add(user);
        }
    }

    /**
     *
     * @return LinkedList(Publicacion)
     */
    public LinkedList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    /**
     * Obtener una publicacion desde el registro de publicaciones del usuario
     * a traves de su ID.
     * @param pubId
     * @return Publicacion
     */
    public Publicacion findPublicacion(int pubId) {
        Iterator<Publicacion> iter = publicaciones.iterator();
        Publicacion auxPub;
        while(iter.hasNext()){
            auxPub = iter.next();
            if(auxPub.getId() == pubId) return auxPub;
        }
        return null;
    }

    /**
     * Obtener una publicacion desde el registro de publicaciones del usuario
     * a traves de su ID. Cuando el parametro "broadSearch" es true, busca
     * tambien la publicacion en el registro de publicaciones de los usuarios
     * followers y following, en caso de que el usuario mismo no sea el autor.
     * @param pubId
     * @param broadSearch
     * @return Publicacion
     */
    public Publicacion findPublicacion(int pubId, boolean broadSearch) {
        // Si la publicacion es del mismo usuario:
        Publicacion auxPub = this.findPublicacion(pubId);
        if(auxPub != null) return auxPub;

        // En caso de que no se necesite buscar en los seguidores
        if(broadSearch == false) return null;

        // Verificar en los seguidores tambien:
        Usuario auxUser;
        Iterator<Usuario> iter = this.followers.iterator();
        while(iter.hasNext()){
            auxUser = iter.next();
            auxPub = auxUser.findPublicacion(pubId);
            if(auxPub != null) return auxPub;
        }
        iter = this.following.iterator();
        while(iter.hasNext()){
            auxUser = iter.next();
            auxPub = auxUser.findPublicacion(pubId);
            if(auxPub != null) return auxPub;
        }
        return null;
    }

    /**
     * Un metodo generico de Java para mostrar una representacion en forma de 
     * String de una instancia de Usuario.
     * @return String
     */
    @Override
    public String toString() {
        return "@" + this.getUsername();
    }

    /**
     * Comprueba si el usuario esta siguiendo a otro.
     * @param usuario
     * @return
     */
    public boolean hasFollowing(Usuario usuario) {
        return this.following.contains(usuario);
    }
}