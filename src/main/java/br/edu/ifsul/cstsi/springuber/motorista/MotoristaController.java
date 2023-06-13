package br.edu.ifsul.cstsi.springuber.motorista;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class MotoristaController {

    private static final Scanner input = new Scanner(System.in);
    private static MotoristaService MotoristaService;

    public MotoristaController(MotoristaService MotoristaService) {
        MotoristaController.MotoristaService = MotoristaService;
    }

    public static void main(String[] args) {

        int opcao;
        do {
            System.out.print("\n\"-------  MENU do Motorista -------\"");
            System.out.print(
                    """
    
                        1. Inserir novo motorista
                        2. Atualizar um motorista
                        3. Excluir um motorista 
                        4. Listar todos os motoristas
                        5. Buscar motoristas pelo código
                        6. Buscar motoristas pelo nome
                       
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectmotoristas();
                case 5 -> selectmotoristasByIdMotorista();
                case 6 -> selectmotoristasByNome();

                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    //opção 1
    private static void inserir() {
        Motorista motorista = new Motorista();
        System.out.println("\n++++++ Cadastro de novo Motorista ++++++");
        System.out.print("Digite o nome do motorista: ");
        motorista.setNome(input.nextLine());

        System.out.print("Digite o email do motorista: ");
        motorista.setEmail(input.nextLine());

        System.out.print("Digite o telefone do motorista: ");
        motorista.setTelefone(input.nextLine());
        //motorista.setId(1L);

        System.out.println("motorista salvo com sucesso:" + MotoristaService.insert(motorista));
    }

    //opção 2
    private static void atualizar() {
        System.out.println("\n++++++ Alterar um motorista ++++++");
        Motorista motorista;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do motorista (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                motorista = MotoristaService.getMotoristaByIdMotorista(codigo);
                if (motorista == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Nome: " + motorista.getNome());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo NOME do motorista: ");
                        motorista.setNome(input.nextLine());
                    }

                    System.out.println("Email: " + motorista.getEmail());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo EMAIL do motorista: ");
                        motorista.setEmail(input.nextLine());


                        System.out.println("Telefone: " + motorista.getTelefone());
                        System.out.print("Alterar? (0-sim/1-não) ");
                        if (input.nextInt() == 0) {
                            input.nextLine();
                            System.out.println("Digite o novo TELEFONE do motorista: ");
                            motorista.setTelefone(input.nextLine());
                        }

                        if (MotoristaService.update(motorista) != null) {
                            System.out.println("Motorista atualizado com sucesso. " + MotoristaService.getMotoristaByIdMotorista(motorista.getId()));
                        } else {
                            System.out.println("Não foi possível atualizar este motorista. Por favor, contate o administrador.");
                        }

                        opcao = 1;
                    }
                }
            }

        } while (opcao != 0) ;
    }

    //opção 3
    private static void excluir() {
        System.out.println("\n++++++ Excluir um motorista ++++++");
        Motorista motorista;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do motorista (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else if(codigo > 0){
                motorista = MotoristaService.getMotoristaByIdMotorista(codigo);
                if (motorista == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println(motorista);
                    System.out.print("Excluir este motorista? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        input.nextLine();
                        MotoristaService.delete(motorista.getId());
                        System.out.println("motorista excluído com sucesso:" + motorista);
                    }

                }
            } else {
                System.out.println("Digite um código válido!!!");
            }
        } while (opcao != 0);
    }

    //opção 4
    private static void selectmotoristas() {
        System.out.println("\nLista de motoristas cadastrados no banco de dados:\n" + MotoristaService.getMotoristas());
    }

    //opção 5
    private static void selectmotoristasByIdMotorista() {
        System.out.print("\nDigite o código do motorista: ");
        Motorista motorista = MotoristaService.getMotoristaByIdMotorista(input.nextLong());
        input.nextLine();
        if (motorista != null) {
            System.out.println(motorista);
        } else {
            System.out.println("Código não localizado.");
        }
    }

    //opção 6
    private static void selectmotoristasByNome() {
        System.out.print("Digite o nome do motorista: ");
        String nome = input.next();
        System.out.println("Chave de pesquisa: " + nome);
        List<Motorista> motoristas = MotoristaService.getMotoristasByNome(nome + "%");
        if (motoristas.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + nome);
        } else {
            System.out.println(motoristas);
        }
    }


}





