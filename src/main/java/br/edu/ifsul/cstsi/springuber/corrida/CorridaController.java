package br.edu.ifsul.cstsi.springuber.corrida;

import br.edu.ifsul.cstsi.springuber.motorista.Motorista;
import br.edu.ifsul.cstsi.springuber.motorista.MotoristaService;
import br.edu.ifsul.cstsi.springuber.usuario.Usuario;
import br.edu.ifsul.cstsi.springuber.usuario.UsuarioService;
import br.edu.ifsul.cstsi.springuber.veiculo.VeiculoService;

import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Controller
public class CorridaController {
    private static final Scanner input = new Scanner(System.in);
    private static br.edu.ifsul.cstsi.springuber.corrida.CorridaService CorridaService;
    private static br.edu.ifsul.cstsi.springuber.motorista.MotoristaService MotoristaService;
    private static br.edu.ifsul.cstsi.springuber.veiculo.VeiculoService VeiculoService;

    private static br.edu.ifsul.cstsi.springuber.usuario.UsuarioService UsuarioService ;

    public CorridaController(CorridaService CorridaService, VeiculoService VeiculoService, MotoristaService MotoristaService, UsuarioService UsuarioService) {
        CorridaController.CorridaService = CorridaService;
        CorridaController.VeiculoService = VeiculoService;
        CorridaController.MotoristaService = MotoristaService;
        CorridaController.UsuarioService = UsuarioService;
    }

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.print("\n\"-------  MENU Corrida -------\"");
            System.out.print(
                    """
    
                        1. Marcar nova corrida
                        2. Atualizar uma corrida
                        3. Excluir uma corrida 
                        4. Listar todas as corridas
                        5. Buscar corrida pelo codigo
                        6. Buscar corrida pela data 
                        7. Buscar corrida pelo motorista
                        8. Buscar corrida pelo usuario
                                               
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectcorridas();
                case 5 -> selectcorridasByIdCorrida();
                case 6 -> selectcorridasByDataInicio();
                case 7 -> selectcorridasByMotorista();
                case 8 -> selectcorridasByUsuario();


                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }
    //opção 1
    private static void inserir() {
        Corrida corrida = new Corrida();
        System.out.println("\n++++++ Cadastro de novo Corrida ++++++");
        System.out.print("Informe o tipo de pagamento: (Dinheiro, PIX ou Cartão)");
        corrida.setTipoPagamento(input.nextLine());

        System.out.print("Informe os detalhes do pagamento: (Local de origem, local do destino e/ou paradas) ");
        corrida.setDetalhesPagamento(input.nextLine());

        System.out.print("Digite a data de inicio da corrida (DD/MM/YYYY): ");
        LocalDate dataInicio = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        corrida.setDataInicio(dataInicio);

        System.out.print("Informe o preco da corrida: (R$00,00) ");
        corrida.setPreco(input.nextDouble());

        //mostrar motoristas disponíveis no banco
        System.out.println("Motoristas cadastrados no banco: " + MotoristaService.getMotoristas());
        System.out.print("Digite o motorista(id) da do corrida: ");
        Motorista motorista = MotoristaService.getMotoristaByIdMotorista(input.nextLong());
        corrida.setMotoristaByIdMotorista(motorista);

        //mostrar usuarios disponíveis no banco
        System.out.println("Usuarios cadastrados no banco: " + UsuarioService.getUsuarios());
        System.out.print("Digite o usuario(id) da corrida: ");
        Usuario usuario = UsuarioService.getUsuarioByIdUsuario(input.nextLong());
        corrida.setUsuarioByIdUsuario(usuario);

        System.out.println("corrida salva com sucesso:" + CorridaService.insert(corrida));

    }

    //opção 2
    private static void atualizar() {
        System.out.println("\n++++++ Alterar uma corrida ++++++");
        Corrida corrida;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código da corrida (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                corrida = CorridaService.getCorridaByIdCorrida(codigo);
                if (corrida == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Tipo de pagamento: " + corrida.getTipoPagamento());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo TIPO DE PAGAMENTO (Dinheiro, PIX ou Cartão) da corrida: ");
                        corrida.setTipoPagamento(input.nextLine());
                    }



                    System.out.println("Detalhes do pagamento: " + corrida.getDetalhesPagamento());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite os novos DETALHES DO PAGAMENTO (Local de origem, local do destino e/ou paradas) da corrida: ");
                        corrida.setDetalhesPagamento(input.nextLine());
                    }

                        System.out.println("Data de inicio: " + corrida.getDataInicio());
                        System.out.print("Alterar? (0-sim/1-não) ");
                        if (input.nextInt() == 0) {
                            input.nextLine();
                            System.out.println("Digite a nova DATA DE INICIO da corrida: ");
                            LocalDate dataInicio = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            corrida.setDataInicio(dataInicio);
                        }

                    System.out.println("Preco: " + corrida.getPreco());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo PRECO da corrida: ");
                        corrida.setPreco(input.nextDouble());
                    }

                    //mostrar motoristas disponíveis no banco
                    System.out.println("Motoristas cadastrados no banco: " + MotoristaService.getMotoristas());
                    System.out.println("Motorista da CORRIDA ATUAL: " + corrida.getMotoristaByIdMotorista());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo MOTORISTA da corrida: ");
                        Motorista motorista = MotoristaService.getMotoristaByIdMotorista(input.nextLong());
                        System.out.println(motorista);
                        corrida.setMotoristaByIdMotorista(motorista);

                    }


                    //mostrar usuarios disponíveis no banco
                    System.out.println("Usuarios cadastrados no banco: " + UsuarioService.getUsuarios());
                    System.out.println("Usuario da CORRIDA ATUAL: " + corrida.getUsuarioByIdUsuario());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo USUARIO da corrida: ");
                        Usuario usuario = UsuarioService.getUsuarioByIdUsuario(input.nextLong());
                        System.out.println(usuario);
                        corrida.setUsuarioByIdUsuario(usuario);

                    }


                        if (CorridaService.update(corrida) != null) {
                            System.out.println("Corrida atualizada com sucesso. " + CorridaService.getCorridaByIdCorrida(corrida.getId()));
                        } else {
                            System.out.println("Não foi possível atualizar esta corrida. Por favor, contate o administrador.");
                        }

                        opcao = 1;

                    }
                }
            }while (opcao != 0) ;

        }


    //opção 3
    private static void excluir() {
        System.out.println("\n++++++ Excluir uma corrida ++++++");
        Corrida corrida;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código da corrida (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else if(codigo > 0){
                corrida = CorridaService.getCorridaByIdCorrida(codigo);
                if (corrida == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println(corrida);
                    System.out.print("Excluir esta corrida? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();

                        CorridaService.delete(corrida.getId());
                        System.out.println("corrida excluída com sucesso:" + corrida);
                    }

                }
            } else {
                System.out.println("Digite um código válido!!!");
            }
        } while (opcao != 0);
    }

    //opção 4
    private static void selectcorridas() {
        System.out.println("\nLista de corridas cadastrados no banco de dados:\n" + CorridaService.getCorridas());
    }

    //opção 5
    private static void selectcorridasByIdCorrida() {
        System.out.print("\nDigite o código do corrida: ");
        Corrida corrida = CorridaService.getCorridaByIdCorrida(input.nextLong());
        input.nextLine();
        if (corrida != null) {
            System.out.println(corrida);
        } else {
            System.out.println("Código não localizado.");
        }
    }

    //opção 6
    private static void selectcorridasByDataInicio() {
        System.out.print("Digite a data da corrida: (dd/MM/yyyy)");
        LocalDate dataInicio = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Corrida> corridas = CorridaService.getCorridaByDataInicio(dataInicio);
        if (corridas.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + dataInicio);
        } else {
            System.out.println(corridas);
        }
    }

    //opção 7

    private static void selectcorridasByMotorista() {
        System.out.println("Motoristas cadastrados no banco: " + MotoristaService.getMotoristas());
        System.out.print("Iforme o motorista (pelo seu ID) da corrida: ");
        Long id_motorista = input.nextLong();
        Motorista motorista = MotoristaService.getMotoristaByIdMotorista(id_motorista);
        List<Corrida> corridas = CorridaService.getCorridaByMotoristaByIdMotorista(id_motorista);
        if (corridas.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + id_motorista);
        } else {
            System.out.println(corridas);
        }
    }

    //opção 8

    private static void selectcorridasByUsuario() {
        System.out.println("Usuarios cadastrados no banco: " + UsuarioService.getUsuarios());
        System.out.print("Iforme o usuario (pelo seu ID) da corrida: ");
        Long id_usuario = input.nextLong();
        Usuario usuario = UsuarioService.getUsuarioByIdUsuario(id_usuario);
        List<Corrida> corridas = CorridaService.getCorridaByUsuarioByIdUsuario(id_usuario);
        if (corridas.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + id_usuario);
        } else {
            System.out.println(corridas);
        }
    }


}
