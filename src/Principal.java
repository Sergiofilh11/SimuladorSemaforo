import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		
		ThreadSemaforo semaforo = new ThreadSemaforo();
		
		String isContinue;
		Scanner scanner;
		
		for(int i=0; i<400; i++) {
			System.out.println(semaforo.getCor());
			semaforo.esperaCorMudar();
			
			if(i==2) {
				System.out.println("Deseja continuar? (s/n)");
				
				scanner = new Scanner(System.in);
				isContinue = scanner.nextLine();
				
				if(isContinue.equals("s")) {
					i=0;
					semaforo.esperaCorMudar();
				}
				else if(isContinue.equals("n")) {
					System.out.println("Programa finalizado!!");
					break;
				}
			}
		}
	}
}
