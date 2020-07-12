package dfs;

import java.util.Stack;

public class SeriDFS implements Runnable {
    
    
   private Stack<Integer> yigin;
   private Diyagram dgr;
 
    @Override
    public void run() {
        for (int i = 0; i < dgr.getdugumBoyut(); i++) {
            ziyaretOnay[i] = false;
        }
        yigin.push(baslangisDugumu);
        while (!yigin.isEmpty()) {
            int node = yigin.pop();
            if (ziyaretOnay[node] == false) {
                ziyaretOnay[node] = true;
               
               
                for (int i = 0; i < dgr.getdugumBoyut(); i++) {
                    if (node == i) {
                        continue;
                    }
                    if (dgr.komsulukDurum(node, i) && ziyaretOnay[i] == false) {
                    }
                }
            }
        }
    }
     private int baslangisDugumu;
    private boolean[] ziyaretOnay;
    
    public SeriDFS(int boyut, boolean[] ziyaret, int baslangicNoktasi) {
        dgr = new Diyagram(boyut, ziyaret, 1);
        this.ziyaretOnay = ziyaret;
        yigin = new Stack<>();
        baslangisDugumu = baslangicNoktasi;
    }
}
