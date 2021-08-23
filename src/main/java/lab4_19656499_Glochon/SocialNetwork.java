package lab4_19656499_Glochon;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;

/**
 * Clase representativa de la red social.
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class SocialNetwork {
    private final String nombre;
    private final LocalDate fechaCreacion;
    private final HashMap<String,Usuario> usuarios;
    private final HashMap<Integer,Usuario> usuariosIndex;
    private final HashMap<Integer,Publicacion> publicaciones;
    private final HashMap<Integer,Comentario> comentarios;
    private final HashMap<Integer,Like> likes;
    private Usuario sesion;

    /**
     * Constructor de la clase SocialNwtwork.
     * @param nombre Nombre de la red social.
     */
    public SocialNetwork(String nombre){
        this.sesion = null;
        this.nombre = nombre;
        this.fechaCreacion = LocalDate.now();

        this.usuarios = new HashMap<>();
        this.usuariosIndex = new HashMap<>();
        this.publicaciones = new HashMap<>();
        this.comentarios = new HashMap<>();
        this.likes = new HashMap<>();
    }

    /**
     * Devuelve el nombre de la red social.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el nombre de usuario del usuario en sesion (si es que hay una sesion activa).
     * @return String
     */
    public String getSessionUsername() {
        if(sesion == null) return "";
        return sesion.getUsername();
    }

    /**
     *
     * @return
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     *
     * @param id
     * @return
     */
    public Usuario getUsuario(int id){
        return this.usuariosIndex.get(id);
    }

    /**
     *
     * @param username
     * @return
     */
    public Usuario getUsuario(String username){
        return this.usuarios.get(username);
    }

    /**
     *
     * @return
     */
    public Collection<Usuario> getUsuarios(){
        return this.usuarios.values();
    }

    /**
     *
     * @return
     */
    public Collection<Publicacion> getPublicaciones(){
        return this.publicaciones.values();
    }

    /**
     *
     * @return
     */
    public boolean hasSesion() {
        return(this.sesion != null);
    }
    
    /**
     *
     * @param pub
     */
    protected void addPublicacion(Publicacion pub){
        this.publicaciones.put(pub.getId(), pub);
    }
    
    /**
     *
     * @param comment
     */
    protected void addComentario(Comentario comment){
        this.comentarios.put(comment.getId(), comment);
    }

    /**
     *
     * @param id
     * @return
     */
    public Publicacion getPublicacion(int id){
        return this.publicaciones.get(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public Comentario getComentario(int id){
        return this.comentarios.get(id);
    }

    /**
     *
     * @return
     */
    public Usuario getSesion() {
        return sesion;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean register(String username, String password){
        // Comprobar si el nombre de usuario ya esta ocupado.
        Usuario user = this.usuarios.get(username);
        if(user != null) return false;

        // Agregar el usuario a la "BD".
        user = new Usuario(username, password);
        this.usuarios.put(username, user);
        this.usuariosIndex.put(user.getId(), user);
        return true;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        // Comprobar que exista el usuario.
        Usuario user = this.usuarios.get(username);
        if(user == null) return false;
        
        // Validar la contraseña del usuario.
        if(!user.validarPassword(password)) return false;

        // Iniciar la sesion
        this.sesion = user;
        return true;
    }

    /**
     * Cierra la sesion abierta en la instancia de SocialNetwork.
     * @return
     */
    public boolean logout() {
        if(this.hasSesion()){
            this.sesion = null;
            return true;
        }
        return false;
    }
    
    /**
     *
     * @param tipo
     * @param contenido
     * @return Publicacion
     */
    public Publicacion post(Publicacion.Tipo tipo, String contenido){
        if(this.hasSesion()){
            Publicacion pub = new Publicacion(this.sesion, contenido, tipo);
            this.addPublicacion(pub);
            this.sesion.addPublicacion(pub);
            return pub;
        }
        return null;
    }
    
    /**
     *
     * @param tipo
     * @param contenido
     * @param targets
     * @return Publicacion
     */
    public Publicacion post(Publicacion.Tipo tipo, String contenido, int[] targets){
        if(this.hasSesion()){
            // En caso de que el array de usuarios este vacio
            if(targets.length == 0) return this.post(tipo, contenido);

            Publicacion pub = new Publicacion(this.sesion, contenido, tipo);
            this.addPublicacion(pub);

            boolean found;
            Collection<Usuario> collec = this.usuarios.values();
            Iterator<Usuario> iter = collec.iterator();

            Usuario auxUser;
            while(iter.hasNext()){
                auxUser = iter.next();
                int userId = auxUser.getId();
                found = IntStream.of(targets).anyMatch(n -> n == userId);
                if(found){
                    auxUser.addPublicacion(pub);
                }
            }

            return pub;
        }
        return null;
    }
    
    /**
     *
     * @param tipo
     * @param contenido
     * @param targets
     * @return Publicacion
     */
    public Publicacion post(Publicacion.Tipo tipo, String contenido, Usuario[] targets){
        if(this.hasSesion()){
            // En caso de que el array de usuarios este vacio
            if(targets.length == 0) return this.post(tipo, contenido);

            Publicacion pub = new Publicacion(this.sesion, contenido, tipo);
            this.addPublicacion(pub);

            for(int i = 0; i < targets.length; ++i) targets[i].addPublicacion(pub);

            return pub;
        }
        return null;
    }
    
    /**
     *
     * @param username
     * @return
     */
    public boolean follow(String username){
        if(this.hasSesion()){
            Usuario user = this.usuarios.get(username);
            if(user != null) return this.follow(user);
        }
        return false;
    }
    
    /**
     *
     * @param targetUser
     * @return
     */
    public boolean follow(Usuario targetUser){
        if(this.hasSesion() && this.sesion != targetUser){
            targetUser.addFollower(this.sesion);
            this.sesion.addFollowing(targetUser);
        }
        return false;
    }
    
    /**
     *
     * @param pubId
     * @param targets
     * @return
     */
    public boolean share(int pubId, int[] targets){
        if(this.hasSesion() && targets.length > 0){
            Publicacion pub = this.sesion.findPublicacion(pubId, true);
            if(pub != null) return this.share(pub, targets);
        }
        return false;
    }
    
    /**
     *
     * @param pub
     * @param targets
     * @return
     */
    public boolean share(Publicacion pub, int[] targets){
        if(this.hasSesion() && targets.length > 0){
            PublicacionShared pubShared = new PublicacionShared(this.sesion, pub);
            this.addPublicacion(pubShared);

            boolean found;
            Collection<Usuario> collec = this.usuarios.values();
            Iterator<Usuario> iter = collec.iterator();

            Usuario auxUser;
            while(iter.hasNext()){
                auxUser = iter.next();
                int userId = auxUser.getId();
                found = IntStream.of(targets).anyMatch(n -> n == userId);
                if(found){
                    auxUser.addPublicacion(pubShared);
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param pubId
     * @param targets
     * @return
     */
    public boolean share(int pubId, Usuario[] targets){
        if(this.hasSesion() && targets.length > 0){
            Publicacion pub = this.sesion.findPublicacion(pubId, true);
            if(pub != null) return this.share(pub, targets);
        }
        return false;
    }
    
    /**
     *
     * @param pub
     * @param targets
     * @return
     */
    public boolean share(Publicacion pub, Usuario[] targets){
        if(this.hasSesion() && targets.length > 0){
            PublicacionShared pubShared = new PublicacionShared(this.sesion, pub);
            this.addPublicacion(pubShared);
            for(int i = 0; i < targets.length; ++i) targets[i].addPublicacion(pubShared);
        }
        return false;
    }

    /**
     *
     * @param pubId
     * @param contenido
     * @return Comentario o NULL
     */
    public Comentario comment(int pubId, String contenido){
        if(this.hasSesion()){
            Publicacion pub = this.getPublicacion(pubId);
            Comentario comment = new Comentario(this.sesion, pub, contenido);
            this.addComentario(comment);
            pub.addComentario(comment);
            return comment;
        }
        return null;
    }

    /**
     *
     * @param pub
     * @param contenido
     * @return Comentario o NULL
     */
    public Comentario comment(Publicacion pub, String contenido){
        if(this.hasSesion()){
            Comentario comment = new Comentario(this.sesion, pub, contenido);
            this.addComentario(comment);
            pub.addComentario(comment);
            return comment;
        }
        return null;
    }

    /**
     *
     * @param pubId
     * @param parentCommentId
     * @param contenido
     * @return Comentario o NULL
     */
    public Comentario comment(int pubId, int parentCommentId, String contenido){
        if(this.hasSesion()){
            Comentario parent = this.getComentario(parentCommentId);
            Comentario comment = new Comentario(this.sesion, parent, contenido);
            this.addComentario(comment);
            comment.getPublicacion().addComentario(comment);
            return comment;
        }
        return null;
    }

    /**
     *
     * @param parent
     * @param contenido
     * @return Comentario o NULL
     */
    public Comentario comment(Comentario parent, String contenido){
        if(this.hasSesion()){
            Comentario comment = new Comentario(this.sesion, parent, contenido);
            this.addComentario(comment);
            comment.getPublicacion().addComentario(comment);
            return comment;
        }
        return null;
    }

    /**
     *
     * @param pubId
     * @return
     */
    public boolean like(int pubId){
        if(this.hasSesion()){
            Publicacion pub = this.getPublicacion(pubId);
            Like like = pub.doLike(this.sesion);
            if(like != null){
                this.likes.put(like.getId(), like);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param pub
     * @return
     */
    public boolean like(Publicacion pub){
        if(this.hasSesion()){
            Like like = pub.doLike(this.sesion);
            if(like != null){
                this.likes.put(like.getId(), like);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param comment
     * @return
     */
    public boolean like(Comentario comment){
        if(this.hasSesion()){
            Like like = comment.doLike(this.sesion);
            if(like != null){
                this.likes.put(comment.getId(), like);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param publicaciones
     * @param minimo
     * @return
     */
    public ArrayList<Integer> isViral(int[] publicaciones, int minimo){
        Publicacion pub;
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < publicaciones.length; ++i){
            pub = this.getPublicacion(publicaciones[i]);
            if(pub.getShares() >= minimo){
                result.add(publicaciones[i]);
            }
        }
        
        return result;
    }

    /**
     *
     * @param publicaciones
     * @param minimo
     * @return
     */
    public ArrayList<Publicacion> isViral(Publicacion[] publicaciones, int minimo){
        ArrayList<Publicacion> result = new ArrayList<>();
        for(int i = 0; i < publicaciones.length; ++i){
            if(publicaciones[i].getShares() >= minimo){
                result.add(publicaciones[i]);
            }
        }
        return result;
    }

    /**
     *
     * @param query
     * @return
     */
    public ArrayList<Publicacion> searchPublicaciones(String query){
        ArrayList<Publicacion> result = new ArrayList<>();
        Collection<Publicacion> collec = this.publicaciones.values();
        Iterator<Publicacion> iter = collec.iterator();

        query = query.toLowerCase();

        Publicacion aux;
        String contenido;
        while(iter.hasNext()){
            aux = iter.next();
            contenido = aux.getContenido().toLowerCase();
            if(contenido.contains(query)){
                result.add(aux);
            }
        }

        return result;
    }

    /**
     *
     * @param query
     * @return
     */
    public ArrayList<Comentario> searchComentarios(String query){
        ArrayList<Comentario> result = new ArrayList<>();
        Collection<Comentario> collec = this.comentarios.values();
        Iterator<Comentario> iter = collec.iterator();

        query = query.toLowerCase();

        Comentario aux;
        String contenido;
        while(iter.hasNext()){
            aux = iter.next();
            contenido = aux.getContenido().toLowerCase();
            if(contenido.contains(query)){
                result.add(aux);
            }
        }

        return result;
    }

    /**
     *
     * @param query
     * @return
     */
    public ArrayList<Comentario> searchSubComentarios(Comentario comentario){
        ArrayList<Comentario> result = new ArrayList<>();
        Collection<Comentario> collec = this.comentarios.values();
        Iterator<Comentario> iter = collec.iterator();

        Comentario aux;
        while(iter.hasNext()){
            aux = iter.next();
            if(aux.getParentComment() == comentario){
                result.add(aux);
            }
        }

        return result;
    }

    /**
     *
     * @param query
     * @return
     */
    public ArrayList<Usuario> searchUsuarios(String query){
        ArrayList<Usuario> result = new ArrayList<>();
        Collection<Usuario> collec = this.usuarios.values();
        Iterator<Usuario> iter = collec.iterator();

        query = query.toLowerCase();

        Usuario aux;
        String contenido;
        while(iter.hasNext()){
            aux = iter.next();
            contenido = aux.getUsername().toLowerCase();
            if(contenido.contains(query)){
                result.add(aux);
            }
        }

        return result;
    }
}