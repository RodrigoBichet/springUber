package br.edu.ifsul.cstsi.springuber;

import br.edu.ifsul.cstsi.springuber.corrida.CorridaController;
import br.edu.ifsul.cstsi.springuber.motorista.MotoristaController;
import br.edu.ifsul.cstsi.springuber.veiculo.VeiculoController;
import br.edu.ifsul.cstsi.springuber.usuario.UsuarioController;

import java.util.Scanner;

public class HomeController {
    private static final Scanner input = new Scanner(System.in);

    public static void main() {

        int opcao;
        do {
            System.out.print("\n-------  Home -------");
            System.out.print(
                    """
                        
                        1. Usuario
                        2. Motorista
                        3. Veiculo
                        4. Corrida
                        
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> UsuarioController.main(null);
                case 2 -> MotoristaController.main(null);
                case 3 -> VeiculoController.main(null);
                case 4 -> CorridaController.main(null);
                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while(opcao != 0) ;
        System.out.println("\n\nMuito obrigado, volte sempre!");
        input.close();
    }

}
