
public class ThreadSemaforo implements Runnable{

	private CorSemaforo cor;
	private boolean parar;
	private boolean corMudou;

	public ThreadSemaforo() {
		this.cor = CorSemaforo.VERDE;

		this.parar = false;
		this.corMudou = false;

		new Thread(this).start();
	}

	@Override
	public void run() {

		while(!parar) {
			try {
				switch (this.cor) {
				case VERDE:
					Thread.sleep(120000);// 2 minutos
					break;
				case VERMELHO:
					Thread.sleep(60000);// 1 minuto
					break;
				case AMARELO:
					Thread.sleep(30000);// 30 segundos
					break;
				default:
					break;
				}
				this.mudarCor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void mudarCor() {
		switch (this.cor) {
		case VERDE:
			this.cor = CorSemaforo.VERMELHO;
			break;
		case VERMELHO:
			this.cor = CorSemaforo.AMARELO;
			break;
		case AMARELO:
			this.cor = CorSemaforo.VERDE;
			break;
		default:
			break;
		}
		this.corMudou = true;
		notify();
	}
	
	public synchronized void esperaCorMudar() {
		while(!this.corMudou) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.corMudou = false;
	}

	public CorSemaforo getCor() {
		return cor;
	}
}
