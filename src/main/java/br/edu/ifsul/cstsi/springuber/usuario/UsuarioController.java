package br.edu.ifsul.cstsi.springuber.usuario;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class UsuarioController {

    private static final Scanner input = new Scanner(System.in);
    private static UsuarioService UsuarioService;

    public UsuarioController(UsuarioService UsuarioService) {
        UsuarioController.UsuarioService = UsuarioService;
    }

    public static void main(String[] args) {

        int opcao;
        do {
            System.out.print("\n\"-------  MENU do Usuário -------\"");
            System.out.print(
                    """
    
                        1. Inserir novo usuario
                        2. Atualizar um usuario
                        3. Excluir um usuario 
                        4. Listar todos os usuarios
                        5. Buscar usuarios pelo código
                        6. Buscar usuarios pelo nome
                       
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectusuarios();
                case 5 -> selectusuariosByIdUsuario();
                case 6 -> selectusuariosByNome();

                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    //opção 1
    private static void inserir() {
        Usuario usuario = new Usuario();
        System.out.println("\n++++++ Cadastro de novo Usuario ++++++");
        System.out.print("Digite o nome do usuario: ");
        usuario.setNome(input.nextLine());

        System.out.print("Digite o email do usuario: ");
        usuario.setEmail(input.nextLine());

        System.out.print("Digite o telefone do usuario: ");
        usuario.setTelefone(input.nextLine());

        System.out.println("usuario salvo com sucesso:" + UsuarioService.insert(usuario));
    }

    //opção 2
    private static void atualizar() {
        System.out.println("\n++++++ Alterar um usuario ++++++");
        Usuario usuario;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do usuario (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                usuario = UsuarioService.getUsuarioByIdUsuario(codigo);
                if (usuario == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Nome: " + usuario.getNome());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo NOME do usuário: ");
                        usuario.setNome(input.nextLine());
                    }

                    System.out.println("Email: " + usuario.getEmail());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo EMAIL do usuário: ");
                        usuario.setEmail(input.nextLine());


                        System.out.println("Telefone: " + usuario.getTelefone());
                        System.out.print("Alterar? (0-sim/1-não) ");
                        if (input.nextInt() == 0) {
                            input.nextLine();
                            System.out.println("Digite o novo TELEFONE do usuario: ");
                            usuario.setTelefone(input.nextLine());
                        }

                        if (UsuarioService.update(usuario) != null) {
                            System.out.println("Usuário atualizado com sucesso. " + UsuarioService.getUsuarioByIdUsuario(usuario.getId()));
                        } else {
                            System.out.println("Não foi possível atualizar este usuario. Por favor, contate o administrador.");
                        }

                        opcao = 1;
                    }
                }
            }

        } while (opcao != 0) ;
    }

    //opção 3
    private static void excluir() {
        System.out.println("\n++++++ Excluir um usuario ++++++");
        Usuario usuario;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do usuario (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else if(codigo > 0){
                usuario = UsuarioService.getUsuarioByIdUsuario(codigo);
                if (usuario == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println(usuario);
                    System.out.print("Excluir este usuario? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        UsuarioService.delete(usuario.getId());
                        System.out.println("usuario excluído com sucesso:" + usuario);
                    }

                }
            } else {
                System.out.println("Digite um código válido!!!");
            }
        } while (opcao != 0);
    }

    //opção 4
    private static void selectusuarios() {
        System.out.println("\nLista de usuarios cadastrados no banco de dados:\n" + UsuarioService.getUsuarios());
    }

    //opção 5
    private static void selectusuariosByIdUsuario() {
        System.out.print("\nDigite o código do usuario: ");
        Usuario usuario = UsuarioService.getUsuarioByIdUsuario(input.nextLong());
        input.nextLine();
        if (usuario != null) {
            System.out.println(usuario);
        } else {
            System.out.println("Código não localizado.");
        }
    }

    //opção 6
    private static void selectusuariosByNome() {
        System.out.print("Digite o nome do usuario: ");
        String nome = input.next();
        System.out.println("Chave de pesquisa: " + nome);
        List<Usuario> usuarios = UsuarioService.getUsuariosByNome(nome + "%");
        if (usuarios.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + nome);
        } else {
            System.out.println(usuarios);
        }
    }


}





