package bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random; 

public class Diyagram {
	
	private Queue<Integer> globalKuyruk;
        private boolean[] ziyaretEdildiMi;
	private boolean tamalandiMi;
	private List<Queue<Integer>> localKuyruklar;	   
	private int dugumSayisi;
	private int[][] koseler;//Yakınlık durumuna göre
	private int sayac;
        
        
               public synchronized void BFS(){
            
		while(!tamalandiMi && globalKuyruk.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
                                System.out.println(e.getMessage());
			}
		}
		int index = (int)(Thread.currentThread().getId());
		if(!globalKuyruk.isEmpty()){
			boolean popped = false;
			int node = globalKuyruk.poll();
			popped = true;
			while(ziyaretEdildiMi[node]){
				if(globalKuyruk.isEmpty()){
					tamalandiMi = true;
					popped = false;
					break;
				}else{
					node = globalKuyruk.poll();
					popped = true;
				}
			}
			if(popped){
				ziyaretEdildiMi[node] = true;
				sayac++;
				boolean flag = false;
				for(int i = 0;i<dugumSayisi;i++){
					if(node==i)continue;
					if(komsulukDurumu(node, i) && !ziyaretEdildiMi[i] && !flag){
						localKuyruklar.get(index).add(i);
						flag = true;
					}
					if(komsulukDurumu(node, i) && !ziyaretEdildiMi[i] && flag){
						globalKuyruk.add(i);
					}
				}
			}
		}
		if(globalKuyruk.isEmpty())
			tamalandiMi = true;
		if(tamalandiMi && sayac<dugumSayisi){
			tamalandiMi = false;
			for(int i=0;i<dugumSayisi;i++){
				if(!ziyaretEdildiMi[i])
					globalKuyruk.add(i);
			}
		}
		notifyAll();
	}
        
        
        
        
	public Diyagram(int dugumSayisi,boolean[] ziyaretEdildiMi,int numberOfProcessors){
		this.dugumSayisi = dugumSayisi;
		localKuyruklar = new ArrayList<Queue<Integer>>(numberOfProcessors);
		for(int i=0;i<numberOfProcessors;i++){
			localKuyruklar.add(new PriorityQueue<Integer>());
		}
		koseler = new int[dugumSayisi][dugumSayisi];
		this.ziyaretEdildiMi = ziyaretEdildiMi;
		tamalandiMi = false;
		globalKuyruk = new PriorityQueue<Integer>();
		globalKuyruk.add(dugumSayisi-1);
		sayac = 0;
		for(int i = 0; i<this.dugumSayisi; i++)
			for(int j = 0; j<this.dugumSayisi; j++){
				Random boolNumber = new Random();
                boolean kenar = boolNumber.nextBoolean();
                if(i==j)
                	koseler[i][j]=1;
                else	
                	koseler[i][j]=kenar ? 1 : 0;
			}
	}
  

	public int getDugumSayisi(){
		return dugumSayisi;
	}
	
	public synchronized boolean getZiyaret(int index){
		return ziyaretEdildiMi[index];
	}
	
	public synchronized void setZiyaret(int index, boolean value){
		ziyaretEdildiMi[index] = value;
	}
	
	   public List<Queue<Integer>> getLocalKuyruklar() {
		return localKuyruklar;
	}
	public void setLocalKuyruklar(List<Queue<Integer>> localKuyruklar) {
		this.localKuyruklar = localKuyruklar;
	}
        //Düğümün komşusuysa 1 se yani evet doğru (true) değilse hayır değil false 
	public boolean komsulukDurumu(int node, int neighbour){
		return koseler[node][neighbour]==1 ? true : false;
	}
	
	public synchronized void sayacArtim(){
		sayac++;
	}
        public synchronized void kuyrugaEkle(Queue<Integer> tmp){
		while(!tmp.isEmpty()){
			globalKuyruk.add(tmp.poll());
		}
	}
	//İşlem tamamlandı mı tamamlanmadı mı
	public boolean tamalandiMi() {
		return tamalandiMi;
	}
}
