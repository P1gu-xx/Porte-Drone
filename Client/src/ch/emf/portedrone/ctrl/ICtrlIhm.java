package ch.emf.portedrone.ctrl;

/**
 * Interface de communication du Contrôleur pour l'IHM.
 * @author PeclatJ
 */
public interface ICtrlIhm {
    
    /**
     * Connect le client à l'application serveur.
     * @param login Le login de l'utilisateur.
     * @param motDePasse Le mot de passe de l'utilisateur.
     */
    public void connexion(String login, String motDePasse);
    
}
