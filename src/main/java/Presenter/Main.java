/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import View.ManterInclusaoEdicaoView;
import config.AppInitializer;


public class Main {

    public static void main(String[] args) {
        // Criação dos objetos envolvidos
        // TelaPrincipalView telaPrincipalView = new TelaPrincipalView();
        AppInitializer.initialize();

        TelaPrincipalPresenter telaPrincipalPresenter = new TelaPrincipalPresenter(AppInitializer.getAutenticacaoService(), AppInitializer.getRepositorioUsuarios());
        // RepositorF9ioUsuariosImpl repositorioUsuarios = new RepositorioUsuariosImpl();
        // AutenticacaoService autenticacaoService = new AutenticacaoService(repositorioUsuarios);
        //TelaPrincipalPresenter telaPrincipalPresenter = new TelaPrincipalPresenter(telaPrincipalView, autenticacaoService, repositorioUsuarios);

        // new TelaPrincipalPresenter();
    }
}
