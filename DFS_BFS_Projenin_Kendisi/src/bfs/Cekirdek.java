package bfs;

import java.util.PriorityQueue;
import java.util.Queue;

public class Cekirdek extends Thread  {
	
	private int threadNumarasi;
	private Diyagram dygrm;
        
	public Cekirdek(Diyagram d,int id){
		this.threadNumarasi = id;
		setName("İşlemci "+id);
		dygrm = d;
	}		
        
		
	public void cekBfs(Queue<Integer> localQueue){
		Queue<Integer> tmpKuyruk = new PriorityQueue<Integer>();
		while(!localQueue.isEmpty()){
			int node = localQueue.poll();
			if(!dygrm.getZiyaret(node)){
				dygrm.setZiyaret(node,true);
				dygrm.sayacArtim();
				boolean toLocal = true;
				for(int i = 0; i<dygrm.getDugumSayisi(); i++){
					if(node==i)continue;
					if(dygrm.komsulukDurumu(node, i) && !toLocal && !dygrm.getZiyaret(i)){
						tmpKuyruk.add(i);
						
					}
					if(dygrm.komsulukDurumu(node, i) && toLocal && !dygrm.getZiyaret(i)){
						localQueue.add(i);
						toLocal = false;
					}
				}
			}
		}
		dygrm.kuyrugaEkle(tmpKuyruk);
	}
        @Override
	public long getId() {
		return threadNumarasi;
	}
        
	@Override
	public void run() {
		while(!dygrm.tamalandiMi()){
			dygrm.BFS();
			yield();
			cekBfs(dygrm.getLocalKuyruklar().get(threadNumarasi));
		}
	}
        
        public int getThreadNumara() {
		return threadNumarasi;
	}
	public void setThreadNumara(int threadNumber) {
		this.threadNumarasi = threadNumber;
	}
	
}
