import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class App {
    static Scanner digite = new Scanner(System.in);
    static ArrayList<Veiculo> veiculo = new ArrayList<>();
    static ArrayList<Viagem> viagem = new ArrayList<>();
    static SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
    static Date dataInicio = new Date();
    static Date dataFinal = new Date();
    static boolean confirma = false;

    public static void main(String[] args) throws Exception {
       
       boolean n = true;
        while (n){
           System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar veículo");
            System.out.println("2 - Consultar veículo");
            System.out.println("3 - Programar viagem");
            System.out.println("4 - Consultar viagem");
            System.out.println("5 - Iniciar viagem");
            System.out.println("6 - Finalizar viagem");
            System.out.println("7 - Colocar veículo em manutenção");
            System.out.println("0 - Finalizar");
            int opcao = digite.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarVeiculo();
                    break;
                case 2:
                    consultarVeiculo();
                    break;
                case 3:
                    programarViagem();
                    break;
                case 4:
                    consultarViagem();
                    break;
                case 5:
                    iniciarViagem();
                    break;
                case 6:
                    finalizarViagem();
                    break;
                case 7:
                    colocarVeiculoEmManutencao();
                    break;
                case 0:
                    System.out.println("PROGRAMA FINALIZADO");
                    n = false;
                    break;
                default:
                    System.out.println("Opção incorreta");    
            }   


    }
}
  

   

    private static Viagem pesquisarViagem(int id ){
        for (Viagem viagem : viagem) {
            if (viagem.getIdViagem() == id) {
                return viagem;
            }
        }
        return null;
    }

    private static Veiculo pesquisarVeiculo(int id ){
        for (Veiculo veiculo : veiculo) {
            if (veiculo.getIdVeiculo() == id) {
                return veiculo;
            }
        }
        return null;
    }
        private static void cadastrarVeiculo() {
            System.out.println("Digite o id do veículo:");
            int id = digite.nextInt();
            if (pesquisarVeiculo(id) != null) {
                System.out.println("Veículo já cadastrado.");
            } 
            else {
              
                System.out.println("Digite a placa do veículo:");
                String placa = digite.next();
                System.out.println("Digite a kilometragem do veículo:");
                int km = digite.nextInt();
                veiculo.add ( new Veiculo(id, placa, 'D', km));
                System.out.println("Veículo cadastrado com sucesso.");
            }
        }
        private static void consultarVeiculo() {
            if (veiculo.isEmpty()) {
                System.out.println("Não tem veículo cadastrado.");
                return;
            } 
                System.out.println("--------------------------------");
                System.out.println("Digite o id do veiculo");
                int id = digite.nextInt();
                Veiculo verificacao = pesquisarVeiculo(id);
                if(verificacao == null){
                    System.out.println("Veiculo não encontrado");
                    return;
                }
                System.out.println("--------------------------------");
                System.out.println("id: " + verificacao.getIdVeiculo());
                System.out.println("placa: " + verificacao.getPlaca());
                System.out.println("status: " + verificacao.getStatus());
                System.out.println("Kilometragem: " + verificacao.getKmOdometro());
                System.out.println("--------------------------------");


            }
            private static void programarViagem() throws ParseException {
              System.out.println("Digite o id da viagem:");
             int idv = digite.nextInt();
                if (pesquisarViagem(idv) != null) {
                System.out.println("Veículo já cadastrado.");
             } 
             else {
                
                System.out.println("Digite o destino");
                String destino = digite.next();
                System.out.println("Digite a data de inicio da viagem");
                String data1 = digite.next();                
                dataInicio = formatar.parse(data1);
                System.out.println("Digite a data do final da viagem");
                String data2 = digite.next();                
                dataFinal = formatar.parse(data2);

                viagem.add( new Viagem(idv, destino, dataInicio, dataFinal ,0));
                System.out.println("Viagem cadastrada com sucesso.");
            }                         
         }
         private static void consultarViagem() {
            if (viagem.isEmpty()) {
                System.out.println("Não tem viagem cadastrada.");
                return;
            } 
                System.out.println("--------------------------------");
                System.out.println("Digite o id da viagem");
                int idv = digite.nextInt();
                Viagem verificacao = pesquisarViagem(idv);
                if(verificacao == null){
                    System.out.println("Viagem não encontrada");
                    return;
                }
                System.out.println("--------------------------------");
                System.out.println("id: " + verificacao.getIdViagem());
                System.out.println("destino: " + verificacao.getDestino());
                System.out.println("data inicio: " + verificacao.getDataInicial());
                System.out.println("data final: " + verificacao.getDataFinal());
                System.out.println("km percorrido: " + verificacao.getKmPercorrido());
                System.out.println("--------------------------------");

        }

       private static boolean iniciarViagem() {
            if(veiculo.isEmpty() && viagem.isEmpty()){
                System.out.println("Não existe veiculos, e nem viagens cadastradas");
                return false;

            }
            else if(veiculo.isEmpty()){
                System.out.println("Não existe veiculos cadastrados");
                return false;
            }
            else if( viagem.isEmpty()){
                System.out.println("Não existe viagem cadastrada");
                return false;
            }
            System.out.println("Digite o id do veículo:");
            int id = digite.nextInt();
            System.out.println("Digite o id da viagem:");
            int idv = digite.nextInt();
            Veiculo verificacao = pesquisarVeiculo(id);
            Viagem  verificacao2 = pesquisarViagem(idv);

            verificacao2.iniciarViagem(verificacao);
            System.out.println("Viagem iniciada");
            confirma = true;
            return true;
        }                 
        private static void finalizarViagem() {
            if( viagem.isEmpty()){
                System.out.println("Não existe viagem cadastradas");
                return ;
            }
            else if(confirma == false){
                System.out.println("Não existe viagem em andamento");
                return;
            }
        System.out.println("Digite qual viagem você quer finalizar:");
        int idv = digite.nextInt();
        System.out.println("Quantos kilometros percorridos?");
        int km = digite.nextInt();
        Viagem  verificacao = pesquisarViagem(idv);
        verificacao.finalizarViagem(km);
        System.out.println("Viagem finalizada com sucesso");
        }

        private static void colocarVeiculoEmManutencao() {
            if(veiculo.isEmpty()){
                System.out.println("Não há veiculos castrados");
                return;
            }
            System.out.println("Digite qual veiculo você quer colocar em manunteção:");
            int id = digite.nextInt();
            Veiculo  verificacao = pesquisarVeiculo(id);
            verificacao.revisar();
            System.out.println("Veiculo foi colocado em manunteção com sucesso");
        }
       
    }
