package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Diyagram {

    private boolean[] ziyaretDurum;
    private boolean tamamlandiMi;
    private int sayac;
   

    public Diyagram(int boyut, boolean[] ziyaret, int islemciNumara) {
        this.dugumBoyut = boyut;
        localYigin = new ArrayList<Stack<Integer>>(islemciNumara);
        for (int i = 0; i < islemciNumara; i++) {
            localYigin.add(new Stack<Integer>());
        }
        koseler = new int[boyut][boyut];
        this.ziyaretDurum = ziyaret;
        tamamlandiMi = false;
        globalYigin = new Stack<Integer>();
        globalYigin.push(boyut - 1);
        sayac = 0;
        for (int i = 0; i < this.dugumBoyut; i++) {
            for (int j = 0; j < this.dugumBoyut; j++) {
                Random nb = new Random();
                boolean kenar = nb.nextBoolean();
                if (i == j) {
                    koseler[i][j] = 1;
                } else {
                    koseler[i][j] = kenar ? 1 : 0;
                }
            }
        }
    }
     private int dugumBoyut;//Düğüm Sayısı
    private int[][] koseler;//Matris oluşturmak için 
    private Stack<Integer> globalYigin;
    private List<Stack<Integer>> localYigin;

    public synchronized void dfs() {
        while (!tamamlandiMi && globalYigin.empty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int index = (int) (Thread.currentThread().getId());
        if (!globalYigin.isEmpty()) {
            boolean popped = false;
            int node = globalYigin.pop();
            popped = true;
            while (ziyaretDurum[node]) {
                if (globalYigin.empty()) {
                    tamamlandiMi = true;
                    popped = false;
                    break;
                } else {
                    node = globalYigin.pop();
                    popped = true;
                }
            }
            if (popped) {
                ziyaretDurum[node] = true;
                sayac++;
                boolean flag = false;
                for (int i = 0; i < dugumBoyut; i++) {
                    if (node == i) {
                        continue;
                    }
                    if (komsulukDurum(node, i) && !ziyaretDurum[i] && !flag) {
                        localYigin.get(index).push(i);
                        flag = true;
                    }
                    if (komsulukDurum(node, i) && !ziyaretDurum[i] && flag) {
                        globalYigin.push(i);
                    }
                }
            }
        }
        if (globalYigin.empty()) {
            tamamlandiMi = true;
        }
        if (tamamlandiMi && sayac < dugumBoyut) {
            tamamlandiMi = false;
            for (int i = 0; i < dugumBoyut; i++) {
                if (!ziyaretDurum[i]) {
                    globalYigin.push(i);
                }
            }
        }
        notifyAll();
    }

    public int getdugumBoyut() {
        return dugumBoyut;
    }

    public synchronized boolean getZiyaret(int index) {
        return ziyaretDurum[index];
    }

    public synchronized void setZiyaret(int index, boolean value) {
        ziyaretDurum[index] = value;
    }

    public synchronized void pushYigin(Stack<Integer> tmp) {
        while (!tmp.isEmpty()) {
            globalYigin.push(tmp.pop());
        }
    }

    public boolean komsulukDurum(int node, int neighbour) {
        return koseler[node][neighbour] == 1 ? true : false;
    }

    public synchronized void sayacArtim() {
        sayac++;
    }

    public boolean tamamlanmaDurumu() {
        return tamamlandiMi;
    }

    public List<Stack<Integer>> getLocalYiginlar() {
        return localYigin;
    }

    public void setLocalYiginlar(List<Stack<Integer>> localStacks) {
        this.localYigin = localStacks;
    }

}
