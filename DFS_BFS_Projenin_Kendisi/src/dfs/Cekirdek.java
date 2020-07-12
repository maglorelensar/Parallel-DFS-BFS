package dfs;

import java.util.Stack;

public class Cekirdek extends Thread {

    private int threadNumarasi;
    

    public void corDFS(Stack<Integer> localStack) {
        Stack<Integer> tmpYigin = new Stack<Integer>();
        
        while (!localStack.isEmpty()) {
            int node = localStack.pop();
            if (!dygrm.getZiyaret(node)) {
                dygrm.setZiyaret(node, true);
                dygrm.sayacArtim();
                boolean toLocal = true;
                for (int i = 0; i < dygrm.getdugumBoyut(); i++) {
                    if (node == i) {
                        continue;
                    }
                    if (dygrm.komsulukDurum(node, i) && !toLocal && !dygrm.getZiyaret(i)) {
                        tmpYigin.push(i);
                    }
                    if (dygrm.komsulukDurum(node, i) && toLocal && !dygrm.getZiyaret(i)) {
                        localStack.push(i);
                        toLocal = false;
                    }
                }
            }
        }
        dygrm.pushYigin(tmpYigin);
    }

    private Diyagram dygrm;
    
    @Override
    public long getId() {
        return threadNumarasi;
    }

    public Cekirdek(Diyagram d, int id) {
        this.threadNumarasi = id;
        setName("Core " + id);
        dygrm = d;
    }

    @Override
    public void run() {
        while (!dygrm.tamamlanmaDurumu()) {
            dygrm.dfs();
            yield();
            corDFS(dygrm.getLocalYiginlar().get(threadNumarasi));
        }
    }

    public int getThreadNumara() {
        return threadNumarasi;
    }

    public void setThreadNumara(int threadNumber) {
        this.threadNumarasi = threadNumber;
    }
}
