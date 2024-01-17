package ro.ase.csie.ie.dma12;

public interface Callback<R>{
    void runResultOnUiThread(R result);
}
