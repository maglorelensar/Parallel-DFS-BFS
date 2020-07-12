package bfs;

import java.util.PriorityQueue;
import java.util.Queue;


public class SeriBFS implements Runnable{
	private boolean[] ziyaretEdildiMi;
	private Diyagram dygrm;
	private Queue<Integer> kuyruk;
	private int baslangicDugumu;
	
        
        
        public SeriBFS(int size,boolean[] ziyaretEdildiMi,int beginPoint){
		dygrm = new Diyagram(size,ziyaretEdildiMi,1);
		this.ziyaretEdildiMi = ziyaretEdildiMi;
		kuyruk = new PriorityQueue<Integer>();
		baslangicDugumu = beginPoint;
        }
	
        
        
        
        
        @Override
	public void run() {
		for(int i = 0;i<dygrm.getDugumSayisi();i++){
			ziyaretEdildiMi[i] = false;
		}
		kuyruk.add(baslangicDugumu);
		while(!kuyruk.isEmpty()){
			int node = kuyruk.poll();
			if(ziyaretEdildiMi[node]==false){
				ziyaretEdildiMi[node] = true;
				for(int i = 0; i<dygrm.getDugumSayisi(); i++){
					if(node==i)continue;
					if(dygrm.komsulukDurumu(node, i) && ziyaretEdildiMi[i]==false){
						kuyruk.add(i);
					}
				}
			}
		} 
	}

}
