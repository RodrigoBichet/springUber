package br.edu.ifsul.cstsi.springuber.veiculo;

import br.edu.ifsul.cstsi.springuber.motorista.Motorista;
import br.edu.ifsul.cstsi.springuber.motorista.MotoristaService;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Controller
public class VeiculoController {

    private static final Scanner input = new Scanner(System.in);
    private static VeiculoService VeiculoService;
    private static MotoristaService MotoristaService;

    public VeiculoController(VeiculoService VeiculoService, MotoristaService MotoristaService) {
        VeiculoController.VeiculoService = VeiculoService;
        VeiculoController.MotoristaService = MotoristaService;
    }

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.print("\n\"-------  MENU do Veiculo -------\"");
            System.out.print(
                    """
    
                        1. Inserir novo veiculo
                        2. Atualizar um veiculo
                        3. Excluir um veiculo
                        4. Listar todos os veiculos
                        5. Buscar veiculo pelo código
                        6. Buscar veiculo pelo tipo
                        7. Buscar veiculo pela placa
                                               
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectveiculos();
                case 5 -> selectveiculosByIdVeiculo();
                case 6 -> selectveiculosByTipo();
                case 7 -> selectveiculosByPlaca();


                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }
    //opção 1
    private static void inserir() {
        Veiculo veiculo = new Veiculo();
        System.out.println("\n++++++ Cadastro de novo Veiculo ++++++");
        System.out.print("Digite o tipo do veiculo: ");
        veiculo.setTipo(input.nextLine());

        System.out.print("Digite a placa do veiculo: ");
        veiculo.setPlaca(input.nextLine());

        System.out.print("Digite a data de fabricacao do veiculo (DD/MM/YYYY): ");
        LocalDate AnoFabricacao = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        veiculo.setAnoFabricacao(AnoFabricacao);

        //mostrar motoristas disponíveis no banco
        System.out.println("Motoristas cadastrados no banco: " + MotoristaService.getMotoristas());
        System.out.print("Digite o motorista(id) dono do veiculo: ");
        Motorista motorista = MotoristaService.getMotoristaByIdMotorista(input.nextLong());
        veiculo.setMotoristaByIdMotorista(motorista);
        System.out.println("veiculo salvo com sucesso:" + VeiculoService.insert(veiculo));
        //update do veiculo vinculado ao motorista
        motorista.setIdVeiculo(veiculo.getId());
        MotoristaService.update(motorista);

    }

    //opção 2
    private static void atualizar() {
        System.out.println("\n++++++ Alterar um veiculo ++++++");
        Veiculo veiculo;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do veiculo (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                veiculo = VeiculoService.getVeiculoByIdVeiculo(codigo);
                if (veiculo == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Tipo: " + veiculo.getTipo());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo TIPO do veiculo: ");
                        veiculo.setTipo(input.nextLine());
                    }

                    System.out.println("Placa: " + veiculo.getPlaca());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite a nova PLACA do veiculo: ");
                        veiculo.setPlaca(input.nextLine());
                    }

                        System.out.println("Ano de Fabricacao: " + veiculo.getAnoFabricacao());
                        System.out.print("Alterar? (0-sim/1-não) ");
                        if (input.nextInt() == 0) {
                            input.nextLine();
                            System.out.println("Digite o novo ANO DE FABRICACAO do veiculo: ");
                            LocalDate AnoFabricacao = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            veiculo.setAnoFabricacao(AnoFabricacao);
                        }

                    System.out.println("Motorista do Veiculo: " + veiculo.getMotoristaByIdMotorista());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();

                        //mostrar motoristas disponíveis no banco
                        System.out.println("Motoristas cadastrados no banco: " + MotoristaService.getMotoristas());
                        System.out.print("Digite o novo motorista(id) dono do veiculo: ");
                        Motorista motorista = MotoristaService.getMotoristaByIdMotorista(input.nextLong());
                        //setar o veiculo para null
                        Motorista motorista2 = veiculo.getMotoristaByIdMotorista();
                        motorista2.setIdVeiculo(0L);

                        veiculo.setMotoristaByIdMotorista(motorista);
                        System.out.println("veiculo atualizado com sucesso:" + VeiculoService.insert(veiculo));
                        //update do veiculo vinculado ao motorista
                        motorista.setIdVeiculo(veiculo.getId());
                        MotoristaService.update(motorista);
                        MotoristaService.update(motorista2);
                    }

                        if (VeiculoService.update(veiculo) != null) {
                            System.out.println("Veiculo atualizado com sucesso. " + VeiculoService.getVeiculoByIdVeiculo(veiculo.getId()));
                        } else {
                            System.out.println("Não foi possível atualizar este veiculo. Por favor, contate o administrador.");
                        }

                        opcao = 1;
                    }
                }
            }while (opcao != 0) ;

        }


    //opção 3
    private static void excluir() {
        System.out.println("\n++++++ Excluir um veiculo ++++++");
        Veiculo veiculo;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do veiculo (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else if(codigo > 0){
                veiculo = VeiculoService.getVeiculoByIdVeiculo(codigo);
                if (veiculo == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println(veiculo);
                    System.out.print("Excluir este veiculo? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();

                        Motorista motorista2 = veiculo.getMotoristaByIdMotorista();
                        motorista2.setIdVeiculo(0L);
                        MotoristaService.update(motorista2);

                        VeiculoService.delete(veiculo.getId());
                        System.out.println("veiculo excluído com sucesso:" + veiculo);
                    }

                }
            } else {
                System.out.println("Digite um código válido!!!");
            }
        } while (opcao != 0);
    }

    //opção 4
    private static void selectveiculos() {
        System.out.println("\nLista de veiculos cadastrados no banco de dados:\n" + VeiculoService.getVeiculos());
    }

    //opção 5
    private static void selectveiculosByIdVeiculo() {
        System.out.print("\nDigite o código do veiculo: ");
        Veiculo veiculo = VeiculoService.getVeiculoByIdVeiculo(input.nextLong());
        input.nextLine();
        if (veiculo != null) {
            System.out.println(veiculo);
        } else {
            System.out.println("Código não localizado.");
        }
    }

    //opção 6
    private static void selectveiculosByTipo() {
        System.out.print("Digite o tipo do veiculo: ");
        String tipo = input.next();
        System.out.println("Chave de pesquisa: " + tipo);
        List<Veiculo> veiculos = VeiculoService.getVeiculosByTipo(tipo + "%");
        if (veiculos.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + tipo);
        } else {
            System.out.println(veiculos);
        }
    }

    //opção 7
    private static void selectveiculosByPlaca() {
        System.out.print("Digite a placa do veiculo: ");
        String placa = input.next();
        System.out.println("Chave de pesquisa: " + placa);
        List<Veiculo> veiculos = VeiculoService.getVeiculosByPlaca(placa + "%");
        if (veiculos.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + placa);
        } else {
            System.out.println(veiculos);
        }
    }



}

