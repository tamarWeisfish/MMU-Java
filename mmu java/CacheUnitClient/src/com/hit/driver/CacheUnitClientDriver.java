package com.hit.driver;

import com.hit.client.CacheUnitClientObserver;
import com.hit.view.CacheUnitView;
/*The client's running class*/
public class CacheUnitClientDriver  {
    public static void main(String[] args) {
        CacheUnitClientObserver cacheUnitClientObserver =
                new CacheUnitClientObserver();
        CacheUnitView view = new CacheUnitView();
        view.addPropertyChangeListener(cacheUnitClientObserver);
        view.start();
    }
}
